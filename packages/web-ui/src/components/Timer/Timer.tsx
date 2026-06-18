import type { TerraTimerProps } from './Timer.types';
import './Timer.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraTimer({ active = false, className, label = 'Label', value }: TerraTimerProps) {
  return (
    <div className={classNames('terraTimer', active && 'terraTimer--active', className)}>
      <span className="terraTimer__label">{label}</span>
      <span className="terraTimer__valueRow">
        <span className="terraTimer__dot" aria-hidden="true" />
        <strong className="terraTimer__value">{value}</strong>
      </span>
    </div>
  );
}
