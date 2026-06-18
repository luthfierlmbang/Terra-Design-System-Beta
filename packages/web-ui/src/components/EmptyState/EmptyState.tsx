import type { TerraEmptyStateProps } from './EmptyState.types';
import { TerraButton } from '../Button';
import './EmptyState.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraEmptyState({
  title,
  description,
  ctaLabel,
  onCtaClick,
  illustration,
  className,
}: TerraEmptyStateProps) {
  return (
    <div className={classNames('terraEmptyState', className)}>
      {illustration && (
        <div className="terraEmptyState__illustration">{illustration}</div>
      )}
      <div className="terraEmptyState__message">
        <p className="terraEmptyState__title">{title}</p>
        <p className="terraEmptyState__description">{description}</p>
      </div>
      {ctaLabel && (
        <TerraButton
          variant="primary"
          className="terraEmptyState__cta"
          onClick={onCtaClick}
        >
          {ctaLabel}
        </TerraButton>
      )}
    </div>
  );
}
