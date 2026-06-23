import { useEffect, useMemo, useRef, useState } from 'react';
import type { DocEntry, ComponentGroup } from '../lib/types';
import { GROUP_ORDER } from '../lib/types';
import { componentHref, navigate } from '../lib/router';
import { DocPage } from './DocPage';
import { NavIcon } from './NavIcon';
import { WorkspaceSwitcher } from './WorkspaceSwitcher';
import { getWorkspace } from '../lib/workspaces';
import './AppShell.css';

type AppShellProps = {
  entries: DocEntry[];
  route: string;
  workspaceId: 'terra' | 'oasis';
};

type GroupedEntry = {
  group: ComponentGroup;
  items: DocEntry[];
};

function groupEntries(entries: DocEntry[]) {
  const map = new Map<ComponentGroup, DocEntry[]>();
  for (const entry of entries) {
    const list = map.get(entry.group) ?? [];
    list.push(entry);
    map.set(entry.group, list);
  }
  return GROUP_ORDER.filter((group) => map.has(group)).map((group) => ({
    group,
    items: (map.get(group) ?? []).sort((a, b) => a.name.localeCompare(b.name)),
  }));
}

function groupDescription(group: ComponentGroup) {
  switch (group) {
    case 'Foundations':
      return 'Tokens, iconography, and core visual building blocks.';
    case 'Actions':
      return 'Primary taps, commands, and high-emphasis triggers.';
    case 'Selection':
      return 'Choice patterns for single, multiple, and segmented input.';
    case 'Forms':
      return 'Inputs, fields, and data-capture surfaces for tablet flows.';
    case 'Feedback':
      return 'Status, loading, progress, and guidance moments.';
    case 'Navigation':
      return 'Movement, hierarchy, and orientation across experiences.';
    case 'Overlay':
      return 'Temporary surfaces layered above task content.';
  }
}

function categoryLabel(entry: DocEntry) {
  if (entry.category) return entry.category;

  switch (entry.group) {
    case 'Foundations':
      return 'Core system';
    case 'Actions':
      return 'Primary action';
    case 'Selection':
      return 'Choice input';
    case 'Forms':
      return 'Data entry';
    case 'Feedback':
      return 'Status pattern';
    case 'Navigation':
      return 'Wayfinding';
    case 'Overlay':
      return 'Layered surface';
  }
}

export function AppShell({ entries, route, workspaceId }: AppShellProps) {
  const [query, setQuery] = useState('');
  const mainContentRef = useRef<HTMLDivElement>(null);
  const grouped = useMemo(() => groupEntries(entries), [entries]);
  const workspace = getWorkspace(workspaceId);
  const routePrefix = workspaceId === 'oasis' ? '/oasis' : '';
  const componentPrefix = `${routePrefix}/component/`;

  const activeSlug = route.startsWith(componentPrefix) ? route.replace(componentPrefix, '') : null;
  const activeEntry = activeSlug ? entries.find((entry) => entry.slug === activeSlug) ?? null : null;

  const filtered = useMemo(() => {
    if (!query.trim()) return grouped;
    const q = query.toLowerCase();
    return grouped
      .map((section) => ({
        group: section.group,
        items: section.items.filter((item) => {
          const matchesKeyword = item.keywords?.some((keyword) => keyword.toLowerCase().includes(q)) ?? false;
          return (
            item.name.toLowerCase().includes(q) ||
            item.description.toLowerCase().includes(q) ||
            item.platform.toLowerCase().includes(q) ||
            matchesKeyword
          );
        }),
      }))
      .filter((section) => section.items.length > 0);
  }, [grouped, query]);

  const hasSearchResults = filtered.length > 0;

  useEffect(() => {
    mainContentRef.current?.focus();
  }, [route]);

  return (
    <>
      <a className="shell__skip-link" href="#main-content">
        Skip to main content
      </a>
      <main className={`shell shell--${workspace.theme}`}>
        <aside className="shell__sidebar">
          <WorkspaceSwitcher currentId={workspace.id} />

          <nav className="shell__nav" aria-label="Component library navigation">
            <button
              className={`shell__nav-link shell__nav-link--overview${route === workspace.route ? ' is-active' : ''}`}
              type="button"
              onClick={() => navigate(workspace.route)}
            >
              <span className="shell__nav-link-icon" aria-hidden="true">
                <NavIcon kind="overview" className="shell__nav-link-iconSvg" />
              </span>
              <span>Overview</span>
            </button>

            {filtered.map((section) => {
              const visibleItems = section.items.filter((item) => item.slug !== 'tokens');
              if (!visibleItems.length) return null;

              return (
                <div key={section.group} className="shell__nav-group">
                  <div className="shell__nav-headingRow">
                    <p className="shell__nav-heading">{section.group}</p>
                    <span className="shell__nav-count">{visibleItems.length}</span>
                  </div>

                  {visibleItems.map((item) => (
                    <button
                      key={item.slug}
                      className={`shell__nav-link${activeSlug === item.slug ? ' is-active' : ''}`}
                      type="button"
                      onClick={() => navigate(`${routePrefix}/component/${item.slug}`)}
                    >
                      <span className="shell__nav-link-icon" aria-hidden="true">
                        <NavIcon kind={section.group} className="shell__nav-link-iconSvg" />
                      </span>
                      <span className="shell__nav-link-label">{item.name}</span>
                    </button>
                  ))}
                </div>
              );
            })}

            {!hasSearchResults ? (
              <div className="shell__nav-empty" role="status" aria-live="polite">
                <strong>No matching components</strong>
                <p>Try searching for button, token, form, or navigation.</p>
                <button type="button" onClick={() => setQuery('')}>
                  Reset search
                </button>
              </div>
            ) : null}
          </nav>
        </aside>

        <section className="shell__main" id="main-content" ref={mainContentRef} tabIndex={-1}>
          <div className="shell__topbar">
            <div className="shell__topbar-spacer" aria-hidden="true" />
            <label className="shell__topbar-search" aria-label="Search components">
              <span className="shell__topbar-searchIcon" aria-hidden="true">
                ⌕
              </span>
              <input
                className="shell__topbar-searchInput"
                placeholder="Search components or tokens"
                value={query}
                onChange={(event) => setQuery(event.target.value)}
              />
              {query ? (
                <button type="button" className="shell__topbar-searchClear" onClick={() => setQuery('')} aria-label="Clear search">
                  Clear
                </button>
              ) : null}
            </label>
            <div className="shell__topbar-actions">
              <a className="shell__topbar-action" href="https://www.figma.com/" target="_blank" rel="noopener noreferrer" aria-label="Open Figma">
                <svg viewBox="0 0 24 24" fill="none" aria-hidden="true">
                  <path d="M12 2.5H9a3.5 3.5 0 1 0 0 7h3v-7Z" fill="currentColor" />
                  <path d="M12 9.5H9a3.5 3.5 0 1 0 0 7h3v-7Z" fill="currentColor" opacity="0.85" />
                  <path d="M12 16.5H9a3.5 3.5 0 1 0 3 5.3v-5.3Z" fill="currentColor" opacity="0.7" />
                  <path d="M12 2.5h3a3.5 3.5 0 1 1 0 7h-3v-7Z" fill="currentColor" opacity="0.78" />
                  <path d="M15 9.5a3.5 3.5 0 1 1 0 7 3.5 3.5 0 0 1 0-7Z" fill="currentColor" opacity="0.92" />
                </svg>
              </a>
              <a className="shell__topbar-action" href="https://github.com/" target="_blank" rel="noopener noreferrer" aria-label="Open GitHub">
                <svg viewBox="0 0 24 24" fill="none" aria-hidden="true">
                  <path
                    d="M12 3C7.03 3 3 7.03 3 12c0 3.98 2.58 7.36 6.16 8.55.45.08.62-.19.62-.43 0-.21-.01-.92-.01-1.67-2.26.42-2.85-.55-3.03-1.05-.1-.26-.52-1.05-.88-1.26-.3-.16-.72-.56-.01-.57.67-.01 1.15.62 1.31.88.77 1.29 2 0.93 2.49.71.08-.56.3-.93.54-1.14-2-.23-4.09-1-4.09-4.44 0-.98.35-1.79.92-2.42-.09-.23-.4-1.15.09-2.4 0 0 .75-.24 2.48.92a8.52 8.52 0 0 1 4.52 0c1.73-1.17 2.48-.92 2.48-.92.49 1.25.18 2.17.09 2.4.57.63.92 1.44.92 2.42 0 3.45-2.1 4.21-4.1 4.44.31.27.58.79.58 1.6 0 1.16-.01 2.09-.01 2.38 0 .24.17.52.62.43A9.01 9.01 0 0 0 21 12c0-4.97-4.03-9-9-9Z"
                    fill="currentColor"
                  />
                </svg>
              </a>
            </div>
          </div>
          <div className="shell__content-frame">
            {activeEntry ? (
              <DocPage key={activeEntry.slug} entry={activeEntry} />
            ) : (
              <Overview entries={entries} grouped={grouped} workspace={workspace} />
            )}
          </div>
        </section>
      </main>
    </>
  );
}

function Overview({ entries, grouped, workspace }: { entries: DocEntry[]; grouped: GroupedEntry[]; workspace: ReturnType<typeof getWorkspace> }) {
  return (
    <div className="overview">
      <header className="overview__hero">
        <div className="overview__hero-copy">
          <h1>{workspace.name} Design System</h1>
          <p>{workspace.description}</p>
        </div>
        <div className="overview__hero-meta" aria-label="Catalog summary">
          <span>{entries.length} components</span>
        </div>
      </header>

      {grouped.map((section) => (
        <section key={section.group} className="overview__group">
          <div className="overview__group-header">
            <h2>{section.group}</h2>
            <span className="overview__group-count">{section.items.length}</span>
          </div>
          <div className="overview__grid">
            {section.items.map((item) => {
              const itemCategory = categoryLabel(item);
              const itemHref = workspace.id === 'oasis' ? `#/oasis/component/${item.slug}` : componentHref(item.slug);
              const previewKind =
                item.slug === 'foundations' || item.slug === 'tokens' || item.slug === 'icon'
                  ? 'reference'
                  : item.slug === 'text-field' || item.slug === 'search-field' || item.slug === 'select-field'
                    ? 'form'
                    : item.slug === 'tabs' || item.slug === 'progress-bar' || item.slug === 'ticker' || item.slug === 'header' || item.slug === 'navbar'
                      ? 'surface'
                      : 'default';

              return (
                <a key={item.slug} className="overview__card" href={itemHref}>
                  <div className={`overview__card-preview overview__card-preview--${previewKind}`}>
                    <div className={`overview__card-previewFrame overview__card-previewFrame--${item.demoAlign ?? 'center'}`}>
                      <div className="overview__card-previewViewport">{item.demo}</div>
                    </div>
                  </div>
                  <div className="overview__card-body">
                    <div className="overview__card-meta-row">
                      <span className="overview__card-category">{itemCategory}</span>
                    </div>
                    <div className="overview__card-copy">
                      <strong>{item.name}</strong>
                      <span className="overview__card-desc">{item.description}</span>
                    </div>
                  </div>
                </a>
              );
            })}
          </div>
        </section>
      ))}
    </div>
  );
}
