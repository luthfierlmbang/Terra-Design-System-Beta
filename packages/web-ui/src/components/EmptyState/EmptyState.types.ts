import type { ReactNode } from 'react';

/** Predefined illustrated empty / error / success states from the TERRA design system. */
export type TerraEmptyStateVariant =
  | 'searchNotFound'
  | 'happyBirthday'
  | 'connectionError'
  | 'underMaintenance'
  | 'defaultSearchState'
  | 'pageNotFound'
  | 'serverBusy'
  | 'dataNotFound'
  | 'timeOut'
  | 'successSubmit';

export type TerraEmptyStateProps = {
  /**
   * Predefined state. Sets the illustration, title, description, and CTA label
   * from the design system. Any of those can be overridden via the props below.
   */
  variant?: TerraEmptyStateVariant;
  /** Title text. Overrides the variant default; required when no variant is set. */
  title?: string;
  /** Body text. Overrides the variant default; required when no variant is set. */
  description?: string;
  /** CTA button label. Overrides the variant default; pass `null` to hide the CTA. */
  ctaLabel?: string | null;
  /** CTA click handler. */
  onCtaClick?: () => void;
  /** Custom illustration. Overrides the variant's bundled illustration. */
  illustration?: ReactNode;
  /** Extra CSS class on the root element. */
  className?: string;
};
