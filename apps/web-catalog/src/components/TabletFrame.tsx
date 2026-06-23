import { useLayoutEffect, useRef, useState, type ReactNode } from 'react';
import './TabletFrame.css';

type TabletFrameProps = {
  children: ReactNode;
  /** Portrait (default) ~768px surface, or landscape ~1024px. */
  orientation?: 'portrait' | 'landscape';
  /** How children are placed on the surface. */
  align?: 'center' | 'start' | 'stretch';
  /** Fitted previews shrink to the available docs viewport without changing component source size. */
  fit?: 'contain' | 'none';
  /** Optional caption shown under the device. */
  caption?: string;
};

/**
 * Renders children inside an Android tablet device surface at 1:1 dp scale,
 * so component specimens reflect their real on-device size instead of
 * stretching to fill the documentation column.
 */
export function TabletFrame({
  children,
  orientation = 'portrait',
  align = 'center',
  fit = 'contain',
  caption,
}: TabletFrameProps) {
  const surfaceRef = useRef<HTMLDivElement | null>(null);
  const contentRef = useRef<HTMLDivElement | null>(null);
  const [contentSize, setContentSize] = useState({ width: 0, height: 0 });
  const [scale, setScale] = useState(1);

  useLayoutEffect(() => {
    if (align === 'stretch' || fit === 'none') {
      setScale(1);
      return;
    }

    const measure = () => {
      const surface = surfaceRef.current;
      const content = contentRef.current;

      if (!surface || !content) {
        return;
      }

      const width = content.scrollWidth;
      const height = content.scrollHeight;

      if (!width || !height) {
        return;
      }

      setContentSize((current) => (current.width === width && current.height === height ? current : { width, height }));
      setScale(Math.min(1, surface.clientWidth / width));
    };

    measure();

    const observer = new ResizeObserver(measure);
    if (surfaceRef.current) {
      observer.observe(surfaceRef.current);
    }
    if (contentRef.current) {
      observer.observe(contentRef.current);
    }

    window.addEventListener('resize', measure);
    return () => {
      observer.disconnect();
      window.removeEventListener('resize', measure);
    };
  }, [align, children, fit]);

  const shouldFit = align !== 'stretch' && fit === 'contain' && contentSize.width > 0;

  return (
    <figure className={`tabletFrame tabletFrame--${orientation}`}>
      <div className="tabletFrame__device">
        <div className="tabletFrame__statusbar">
          <span className="tabletFrame__time">9:41</span>
          <span className="tabletFrame__statusbar-icons">
            <span className="tabletFrame__dot" />
            <span className="tabletFrame__dot" />
            <span className="tabletFrame__dot" />
          </span>
        </div>
        <div ref={surfaceRef} className={`tabletFrame__surface tabletFrame__surface--${align}`}>
          {align === 'stretch' ? (
            children
          ) : (
            <div
              className="tabletFrame__viewport"
              style={shouldFit ? { width: contentSize.width * scale, height: contentSize.height * scale } : undefined}
            >
              <div
                ref={contentRef}
                className="tabletFrame__content"
                style={shouldFit ? { transform: `scale(${scale})` } : undefined}
              >
                {children}
              </div>
            </div>
          )}
        </div>
      </div>
      {caption ? <figcaption className="tabletFrame__caption">{caption}</figcaption> : null}
    </figure>
  );
}
