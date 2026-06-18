import './foundations/theme.css';

const swatches = [
  {
    label: 'Canvas Background',
    value: 'var(--color-bg-canvas-default)',
  },
  {
    label: 'Primary Fill',
    value: 'var(--color-bg-fill-primary-default)',
  },
  {
    label: 'Secondary Fill',
    value: 'var(--color-bg-fill-secondary-default)',
  },
  {
    label: 'Danger Fill',
    value: 'var(--color-bg-fill-danger-default)',
  },
] as const;

export function TerraTokenShowcase() {
  return (
    <section
      style={{
        padding: 'var(--spacing-32)',
        background: 'var(--color-bg-canvas-default)',
        color: 'var(--color-text-primary-default)',
      }}
    >
      <div
        style={{
          maxWidth: 960,
          margin: '0 auto',
          display: 'grid',
          gap: 'var(--spacing-24)',
        }}
      >
        <header>
          <p style={{ margin: 0, color: 'var(--color-text-action-primary-default)' }}>
            TERRA Design System
          </p>
          <h1 style={{ margin: 'var(--spacing-8) 0 0' }}>React + TypeScript Token Preview</h1>
          <p style={{ margin: 'var(--spacing-12) 0 0', color: 'var(--color-text-secondary-default)' }}>
            Initial web setup with token-driven foundations for colors, spacing, radius, and shadow.
          </p>
        </header>

        <div
          style={{
            display: 'grid',
            gap: 'var(--spacing-16)',
            gridTemplateColumns: 'repeat(auto-fit, minmax(180px, 1fr))',
          }}
        >
          {swatches.map((swatch) => (
            <article
              key={swatch.label}
              style={{
                background: 'var(--color-bg-surface-primary-default)',
                border: '1px solid var(--color-border-default)',
                borderRadius: 'var(--radius-large)',
                boxShadow: 'var(--shadow-card)',
                overflow: 'hidden',
              }}
            >
              <div style={{ height: 120, background: swatch.value }} />
              <div style={{ padding: 'var(--spacing-16)' }}>
                <strong>{swatch.label}</strong>
                <p style={{ margin: 'var(--spacing-8) 0 0', color: 'var(--color-text-secondary-default)' }}>
                  {swatch.value}
                </p>
              </div>
            </article>
          ))}
        </div>
      </div>
    </section>
  );
}
