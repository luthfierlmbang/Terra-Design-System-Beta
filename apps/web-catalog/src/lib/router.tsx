import { useEffect, useState } from 'react';

/**
 * Minimal zero-dependency hash router for the catalog SPA.
 * Routes look like `#/component/button` or `#/` for the overview.
 */

export function getRoute(): string {
  const hash = window.location.hash.replace(/^#/, '');
  return hash || '/';
}

export function navigate(path: string) {
  const next = path.startsWith('#') ? path : `#${path}`;
  if (window.location.hash !== next) {
    window.location.hash = next;
  }
  window.scrollTo({ top: 0 });
}

export function useRoute(): string {
  const [route, setRoute] = useState<string>(getRoute());

  useEffect(() => {
    const onChange = () => setRoute(getRoute());
    window.addEventListener('hashchange', onChange);
    return () => window.removeEventListener('hashchange', onChange);
  }, []);

  return route;
}

/** Build a navigable href for a component slug. */
export function componentHref(slug: string): string {
  return `#/component/${slug}`;
}
