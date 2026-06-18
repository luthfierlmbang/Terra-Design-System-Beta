import type { DocEntry } from '../lib/types';
import { TabletFrame } from './TabletFrame';
import './DocPage.css';

type DocPageProps = {
  entry: DocEntry;
};

export function DocPage({ entry }: DocPageProps) {
  return (
    <article className="docPage">
      <header className="docPage__header">
        <div className="docPage__eyebrow">
          <span className="docPage__group">{entry.group}</span>
          <span className="docPage__platform">{entry.platform}</span>
        </div>
        <h1 className="docPage__title">{entry.name}</h1>
        <p className="docPage__lede">{entry.description}</p>
      </header>

      <section className="docPage__section">
        <h2>Preview</h2>
        {entry.bareDemo ? (
          <div className="docPage__bare-demo">{entry.demo}</div>
        ) : (
          <>
            <p className="docPage__hint">Shown at true on-device scale inside an Android tablet surface.</p>
            <TabletFrame align={entry.demoAlign ?? 'center'}>{entry.demo}</TabletFrame>
          </>
        )}
      </section>

      {entry.anatomy && entry.anatomy.length > 0 ? (
        <section className="docPage__section">
          <h2>Anatomy</h2>
          <ol className="docPage__anatomy">
            {entry.anatomy.map((part, i) => (
              <li key={part.part}>
                <span className="docPage__anatomy-num">{i + 1}</span>
                <div>
                  <strong>{part.part}</strong>
                  <span>{part.description}</span>
                </div>
              </li>
            ))}
          </ol>
        </section>
      ) : null}

      {entry.states && entry.states.length > 0 ? (
        <section className="docPage__section">
          <h2>States &amp; variants</h2>
          <div className="docPage__states">
            {entry.states.map((state) => (
              <div key={state.name} className="docPage__state">
                {state.demo ? <div className="docPage__state-demo">{state.demo}</div> : null}
                <div className="docPage__state-meta">
                  <strong>{state.name}</strong>
                  <span>{state.description}</span>
                </div>
              </div>
            ))}
          </div>
        </section>
      ) : null}

      {entry.props && entry.props.length > 0 ? (
        <section className="docPage__section">
          <h2>Props</h2>
          <div className="docPage__table-wrap">
            <table className="docPage__table">
              <thead>
                <tr>
                  <th>Prop</th>
                  <th>Type</th>
                  <th>Default</th>
                  <th>Description</th>
                </tr>
              </thead>
              <tbody>
                {entry.props.map((prop) => (
                  <tr key={prop.name}>
                    <td>
                      <code>{prop.name}</code>
                    </td>
                    <td>
                      <code className="docPage__type">{prop.type}</code>
                    </td>
                    <td>{prop.default ? <code>{prop.default}</code> : <span className="docPage__muted">—</span>}</td>
                    <td>{prop.description}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </section>
      ) : null}

      <section className="docPage__section">
        <h2>Android snippets</h2>
        <div className="docPage__snippet-grid">
          <div>
            <h3>XML</h3>
            <div className="docPage__snippet">
              {entry.androidSnippet.xml.code ? <pre>{entry.androidSnippet.xml.code}</pre> : null}
              {entry.androidSnippet.xml.note ? <p className="docPage__snippet-note">{entry.androidSnippet.xml.note}</p> : null}
            </div>
          </div>
          <div>
            <h3>Kotlin</h3>
            <div className="docPage__snippet">
              {entry.androidSnippet.kotlin.code ? <pre>{entry.androidSnippet.kotlin.code}</pre> : null}
              {entry.androidSnippet.kotlin.note ? <p className="docPage__snippet-note">{entry.androidSnippet.kotlin.note}</p> : null}
            </div>
          </div>
        </div>
      </section>

      {entry.usage ? (
        <section className="docPage__section">
          <h2>Usage guidance</h2>
          <div className="docPage__usage">
            <div className="docPage__usage-col docPage__usage-col--do">
              <h3>Do</h3>
              <ul>
                {entry.usage.do.map((item) => (
                  <li key={item}>{item}</li>
                ))}
              </ul>
            </div>
            <div className="docPage__usage-col docPage__usage-col--dont">
              <h3>Don&apos;t</h3>
              <ul>
                {entry.usage.dont.map((item) => (
                  <li key={item}>{item}</li>
                ))}
              </ul>
            </div>
          </div>
        </section>
      ) : null}
    </article>
  );
}
