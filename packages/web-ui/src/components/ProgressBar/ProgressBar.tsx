import type { TerraProgressBarProps } from './ProgressBar.types';
import './ProgressBar.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraProgressBar({ className, value = 100, variant = 'base' }: TerraProgressBarProps) {
  const safeValue = Math.max(0, Math.min(100, value));

  return (
    <div className={classNames('terraProgressBar', `terraProgressBar--${variant}`, className)}>
      <span className="terraProgressBar__fill" style={{ width: `${safeValue}%` }} />
    </div>
  );
}
