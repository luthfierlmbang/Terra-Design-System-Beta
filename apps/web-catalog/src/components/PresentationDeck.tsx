import { useEffect, useMemo } from 'react';
import type { PresentationDeckEntry, PresentationSlide } from '../content/presentations/terraAiWorkflow';
import { navigate } from '../lib/router';
import './PresentationDeck.css';

type PresentationDeckProps = {
  deck: PresentationDeckEntry;
  route: string;
};

function deckHref(slug: string, slideId?: string) {
  return slideId ? `#/deck/${slug}/${slideId}` : `#/deck/${slug}`;
}

function resolveSlide(deck: PresentationDeckEntry, route: string) {
  const parts = route.replace(/^\/deck\//, '').split('/').filter(Boolean);
  const requestedId = parts[1];
  const activeIndex = requestedId ? deck.slides.findIndex((slide) => slide.id === requestedId) : 0;
  return activeIndex >= 0 ? activeIndex : 0;
}

export function PresentationDeck({ deck, route }: PresentationDeckProps) {
  const activeIndex = useMemo(() => resolveSlide(deck, route), [deck, route]);
  const activeSlide = deck.slides[activeIndex];
  const progress = ((activeIndex + 1) / deck.slides.length) * 100;

  useEffect(() => {
    const onKeyDown = (event: KeyboardEvent) => {
      if (event.key === 'ArrowRight' || event.key === 'PageDown' || event.key === ' ') {
        event.preventDefault();
        if (activeIndex < deck.slides.length - 1) {
          navigate(`/deck/${deck.slug}/${deck.slides[activeIndex + 1].id}`);
        }
      }

      if (event.key === 'ArrowLeft' || event.key === 'PageUp') {
        event.preventDefault();
        if (activeIndex > 0) {
          navigate(`/deck/${deck.slug}/${deck.slides[activeIndex - 1].id}`);
        }
      }

      if (event.key === 'Home') {
        event.preventDefault();
        navigate(`/deck/${deck.slug}/${deck.slides[0].id}`);
      }

      if (event.key === 'End') {
        event.preventDefault();
        navigate(`/deck/${deck.slug}/${deck.slides[deck.slides.length - 1].id}`);
      }
    };

    window.addEventListener('keydown', onKeyDown);
    return () => window.removeEventListener('keydown', onKeyDown);
  }, [activeIndex, deck]);

  return (
    <main className="presentationDeck">
      <header className="presentationDeck__topbar">
        <div className="presentationDeck__brand">
          <span className="presentationDeck__eyebrow">TERRA Presentation</span>
          <div>
            <h1>{deck.title}</h1>
            <p>{deck.description}</p>
          </div>
        </div>

        <div className="presentationDeck__meta">
          <div className="presentationDeck__counter">
            <strong>
              {String(activeIndex + 1).padStart(2, '0')} / {String(deck.slides.length).padStart(2, '0')}
            </strong>
            <span>{activeSlide.title}</span>
          </div>
          <div className="presentationDeck__progress" aria-hidden="true">
            <span style={{ width: `${progress}%` }} />
          </div>
        </div>
      </header>

      <div className="presentationDeck__body">
        <aside className="presentationDeck__rail" aria-label="Slide navigation">
          {deck.slides.map((slide, index) => {
            const isActive = index === activeIndex;
            return (
              <a
                key={slide.id}
                className={`presentationDeck__rail-link${isActive ? ' is-active' : ''}`}
                href={deckHref(deck.slug, slide.id)}
              >
                <span className="presentationDeck__rail-index">{String(index + 1).padStart(2, '0')}</span>
                <span className="presentationDeck__rail-copy">
                  <strong>{slide.title}</strong>
                  <small>{slide.eyebrow ?? 'Slide'}</small>
                </span>
              </a>
            );
          })}
        </aside>

        <section className="presentationDeck__stage">
          <PresentationSlideView slide={activeSlide} index={activeIndex} total={deck.slides.length} />
        </section>
      </div>

      <footer className="presentationDeck__footer">
        <div className="presentationDeck__controls">
          <button
            type="button"
            onClick={() => navigate(`/deck/${deck.slug}/${deck.slides[Math.max(activeIndex - 1, 0)].id}`)}
            disabled={activeIndex === 0}
          >
            ← Previous
          </button>
          <button
            type="button"
            onClick={() => navigate(`/deck/${deck.slug}/${deck.slides[Math.min(activeIndex + 1, deck.slides.length - 1)].id}`)}
            disabled={activeIndex === deck.slides.length - 1}
          >
            Next →
          </button>
        </div>
        <p>Gunakan ← →, Page Up, Page Down, Home, atau End untuk navigasi.</p>
      </footer>
    </main>
  );
}

function PresentationSlideView({ slide, index, total }: { slide: PresentationSlide; index: number; total: number }) {
  return (
    <article className={`presentationSlide presentationSlide--${slide.kind} presentationSlide--${slide.accent ?? 'default'}`}>
      <div className="presentationSlide__frame">
        <header className="presentationSlide__header">
          <div className="presentationSlide__eyebrowRow">
            {slide.eyebrow ? <span className="presentationSlide__eyebrow">{slide.eyebrow}</span> : null}
            <span className="presentationSlide__ordinal">
              {String(index + 1).padStart(2, '0')} / {String(total).padStart(2, '0')}
            </span>
          </div>
          <h2 className="presentationSlide__title">{slide.title}</h2>
          {slide.subtitle ? <p className="presentationSlide__subtitle">{slide.subtitle}</p> : null}
          {slide.summary ? <p className="presentationSlide__summary">{slide.summary}</p> : null}
        </header>

        <div className="presentationSlide__content">
          {renderSlideBody(slide)}
          {slide.notes ? (
            <aside className="presentationSlide__notes">
              <span>Speaker note</span>
              <p>{slide.notes}</p>
            </aside>
          ) : null}
        </div>
      </div>
    </article>
  );
}

function renderSlideBody(slide: PresentationSlide) {
  switch (slide.kind) {
    case 'title':
    case 'bullet-list':
      return (
        <div className="presentationSlide__grid presentationSlide__grid--mainAside">
          <div className="presentationSlide__panel presentationSlide__panel--hero">
            <ul className="presentationSlide__bulletList presentationSlide__bulletList--large">
              {slide.bullets?.map((bullet) => <li key={bullet}>{bullet}</li>)}
            </ul>
          </div>
          <div className="presentationSlide__panel presentationSlide__panel--accent">
            <h3>Key takeaway</h3>
            <p>{slide.summary ?? slide.bullets?.[0]}</p>
          </div>
        </div>
      );
    case 'architecture':
      return (
        <div className="presentationSlide__stack">
          <div className="presentationSlide__panel presentationSlide__panel--hero">
            <ul className="presentationSlide__bulletList">
              {slide.bullets?.map((bullet) => <li key={bullet}>{bullet}</li>)}
            </ul>
          </div>
          <div className="presentationSlide__panel presentationSlide__panel--diagram">{slide.visual}</div>
        </div>
      );
    case 'process':
      return (
        <ol className="presentationSlide__stepList">
          {slide.steps?.map((step, index) => (
            <li key={step}>
              <span>{String(index + 1).padStart(2, '0')}</span>
              <p>{step}</p>
            </li>
          ))}
        </ol>
      );
    case 'comparison':
    case 'role-split':
      return (
        <div className="presentationSlide__grid presentationSlide__grid--halves">
          <section className="presentationSlide__panel presentationSlide__panel--muted">
            <h3>{slide.leftTitle}</h3>
            <ul className="presentationSlide__bulletList">
              {slide.leftBullets?.map((bullet) => <li key={bullet}>{bullet}</li>)}
            </ul>
          </section>
          <section className="presentationSlide__panel presentationSlide__panel--highlight">
            <h3>{slide.rightTitle}</h3>
            <ul className="presentationSlide__bulletList">
              {slide.rightBullets?.map((bullet) => <li key={bullet}>{bullet}</li>)}
            </ul>
          </section>
        </div>
      );
    case 'mapping':
      return (
        <div className="presentationSlide__grid presentationSlide__grid--mainAside">
          <div className="presentationSlide__panel presentationSlide__panel--hero">
            <ul className="presentationSlide__bulletList">
              {slide.bullets?.map((bullet) => <li key={bullet}>{bullet}</li>)}
            </ul>
          </div>
          <div className="presentationSlide__mapping">
            {slide.mapping?.map((item) => (
              <div key={item.label} className="presentationSlide__mappingRow">
                <span>{item.label}</span>
                <strong>{item.value}</strong>
              </div>
            ))}
          </div>
        </div>
      );
    case 'stack':
      return (
        <div className="presentationSlide__stack">
          <div className="presentationSlide__stackNodes">
            {slide.nodes?.map((node, index) => (
              <div key={node} className="presentationSlide__stackNode">
                <strong>{node}</strong>
                {index < (slide.nodes?.length ?? 0) - 1 ? <span>→</span> : null}
              </div>
            ))}
          </div>
          <div className="presentationSlide__panel presentationSlide__panel--hero">
            <ul className="presentationSlide__bulletList">
              {slide.bullets?.map((bullet) => <li key={bullet}>{bullet}</li>)}
            </ul>
          </div>
        </div>
      );
    case 'benefits':
      return (
        <div className="presentationSlide__benefitGrid">
          {slide.bullets?.map((bullet) => (
            <div key={bullet} className="presentationSlide__benefitCard">
              <strong>{bullet}</strong>
            </div>
          ))}
        </div>
      );
    case 'closing':
      return (
        <div className="presentationSlide__closing">
          <div className="presentationSlide__panel presentationSlide__panel--hero">
            <ul className="presentationSlide__bulletList">
              {slide.bullets?.map((bullet) => <li key={bullet}>{bullet}</li>)}
            </ul>
          </div>
          {slide.closingLine ? <blockquote>{slide.closingLine}</blockquote> : null}
        </div>
      );
    default:
      return null;
  }
}
