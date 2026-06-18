import type { ReactNode } from 'react';

export type TerraEmptyStateProps = {
  title: string;
  description: string;
  ctaLabel?: string;
  onCtaClick?: () => void;
  illustration?: ReactNode;
  className?: string;
};
