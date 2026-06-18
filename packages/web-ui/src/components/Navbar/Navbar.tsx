import type { TerraNavbarProps } from './Navbar.types';
import './Navbar.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraNavbar({ items, activeId, onItemClick, className }: TerraNavbarProps) {
  return (
    <nav className={classNames('terraNavbar', className)}>
      {items.map((item) => {
        const isActive = item.id === activeId;
        return (
          <button
            key={item.id}
            type="button"
            className={classNames('terraNavbar__item', isActive && 'terraNavbar__item--active')}
            onClick={() => onItemClick?.(item.id)}
          >
            <span className="terraNavbar__icon">{item.icon}</span>
            <span className="terraNavbar__label">{item.label}</span>
          </button>
        );
      })}
    </nav>
  );
}
