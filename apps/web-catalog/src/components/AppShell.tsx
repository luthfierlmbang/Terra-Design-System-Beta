import { useMemo, useState } from 'react';
import type { DocEntry, ComponentGroup } from '../lib/types';
import { GROUP_ORDER } from '../lib/types';
import { componentHref, navigate } from '../lib/router';
import { DocPage } from './DocPage';
import './AppShell.css';

type AppShellProps = {
  entries: DocEntry[];
  route: string;
};

function groupEntries(entries: DocEntry[]) {
  const map = new Map<ComponentGroup, DocEntry[]>();
  for (const entry of entries) {
    const list = map.get(entry.group) ?? [];
    list.push(entry);
    map.set(entry.group, list);
  }
  return GROUP_ORDER.filter((g) => map.has(g)).map((group) => ({
    group,
    items: (map.get(group) ?? []).sort((a, b) => a.name.localeCompare(b.name)),
  }));
}

export function AppShell({ entries, route }: AppShellProps) {
  const [query, setQuery] = useState('');
  const grouped = useMemo(() => groupEntries(entries), [entries]);

  const activeSlug = route.startsWith('/component/') ? route.replace('/component/', '') : null;
  const activeEntry = activeSlug ? entries.find((e) => e.slug === activeSlug) ?? null : null;

  const filtered = useMemo(() => {
    if (!query.trim()) return grouped;
    const q = query.toLowerCase();
    return grouped
      .map((section) => ({
        group: section.group,
        items: section.items.filter((i) => i.name.toLowerCase().includes(q)),
      }))
      .filter((section) => section.items.length > 0);
  }, [grouped, query]);

  return (
    <main className="shell">
      <aside className="shell__sidebar">
        <button className="shell__logo" type="button" onClick={() => navigate('/')}>
          <span className="shell__logo-mark">T</span>
          <span className="shell__logo-copy">
            <strong>TERRA</strong>
            <span>Design System</span>
          </span>
        </button>

        <label className="shell__search">
          <span>⌕</span>
          <input
            placeholder="Search components"
            value={query}
            onChange={(e) => setQuery(e.target.value)}
          />
        </label>

        <nav className="shell__nav">
          <button
            className={`shell__nav-link shell__nav-link--top${route === '/' ? ' is-active' : ''}`}
            type="button"
            onClick={() => navigate('/')}
          >
            Overview
          </button>

          {filtered.map((section) => (
            <div key={section.group} className="shell__nav-group">
              <p className="shell__nav-heading">{section.group}</p>
              {section.items.map((item) => (
                <button
                  key={item.slug}
                  className={`shell__nav-link${activeSlug === item.slug ? ' is-active' : ''}`}
                  type="button"
                  onClick={() => navigate(`/component/${item.slug}`)}
                >
                  {item.name}
                </button>
              ))}
            </div>
          ))}
        </nav>
      </aside>

      <section className="shell__main">
        {activeEntry ? (
          <DocPage entry={activeEntry} />
        ) : (
          <Overview entries={entries} grouped={grouped} />
        )}
      </section>
    </main>
  );
}

function Overview({
  entries,
  grouped,
}: {
  entries: DocEntry[];
  grouped: { group: ComponentGroup; items: DocEntry[] }[];
}) {
  return (
    <div className="overview">
      <header className="overview__header">
        <h1>All Components</h1>
        <p>
          Browse the TERRA inventory grouped by purpose. Each component has its own page with
          anatomy, states, props, and usage guidance — shown at true Android tablet scale.
        </p>
        <span className="overview__count">{entries.length} components</span>
      </header>

      {grouped.map((section) => (
        <section key={section.group} className="overview__group">
          <h2>{section.group}</h2>
          <div className="overview__grid">
            {section.items.map((item) => (
              <a key={item.slug} className="overview__card" href={componentHref(item.slug)}>
                <span className="overview__card-platform">{item.platform}</span>
                <strong>{item.name}</strong>
                <span className="overview__card-desc">{item.description}</span>
              </a>
            ))}
          </div>
        </section>
      ))}
    </div>
  );
}
