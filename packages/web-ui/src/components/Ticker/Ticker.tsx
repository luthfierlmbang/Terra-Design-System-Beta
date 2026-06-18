import { TerraIcon } from '../Icon';
import type { TerraTickerProps, TerraTickerTone } from './Ticker.types';
import './Ticker.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

const toneIconMap: Record<TerraTickerTone, { category: 'info'; name: 'information' | 'exclamation' }> = {
  information: { category: 'info', name: 'information' },
  warning: { category: 'info', name: 'exclamation' },
  error: { category: 'info', name: 'exclamation' },
};

export function TerraTicker({ className, detailed = false, message, title, tone = 'information' }: TerraTickerProps) {
  const icon = toneIconMap[tone];

  return (
    <div className={classNames('terraTicker', `terraTicker--${tone}`, detailed && 'terraTicker--detailed', className)}>
      <span className="terraTicker__icon" aria-hidden="true">
        <TerraIcon category={icon.category} name={icon.name} size="sm" />
      </span>
      <div className="terraTicker__content">
        {detailed && title ? <strong className="terraTicker__title">{title}</strong> : null}
        <span className="terraTicker__message">{message}</span>
      </div>
    </div>
  );
}
