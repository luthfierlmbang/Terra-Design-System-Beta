import type { TerraTabsProps } from './Tabs.types';
import './Tabs.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraTabs({ className, items, onChange, scrollable = false, selectedId }: TerraTabsProps) {
  return (
    <div className={classNames('terraTabs', scrollable && 'terraTabs--scrollable', className)} role="tablist">
      {items.map((item) => {
        const selected = item.id === selectedId;

        return (
          <button
            key={item.id}
            className={classNames('terraTabs__item', selected && 'terraTabs__item--selected')}
            role="tab"
            type="button"
            aria-selected={selected}
            onClick={() => onChange?.(item.id)}
          >
            <span className="terraTabs__label">{item.label}</span>
            <span className="terraTabs__indicator" aria-hidden="true" />
          </button>
        );
      })}
    </div>
  );
}
