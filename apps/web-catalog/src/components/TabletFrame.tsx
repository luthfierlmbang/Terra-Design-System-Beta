import type { ReactNode } from 'react';
import './TabletFrame.css';

type TabletFrameProps = {
  children: ReactNode;
  /** Portrait (default) ~768px surface, or landscape ~1024px. */
  orientation?: 'portrait' | 'landscape';
  /** How children are placed on the surface. */
  align?: 'center' | 'start' | 'stretch';
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
  caption,
}: TabletFrameProps) {
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
        <div className={`tabletFrame__surface tabletFrame__surface--${align}`}>{children}</div>
      </div>
      {caption ? <figcaption className="tabletFrame__caption">{caption}</figcaption> : null}
    </figure>
  );
}
