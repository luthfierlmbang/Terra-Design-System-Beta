import type { TerraChipProps } from './Chip.types';
import './Chip.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraChip({ children, className, counter, leftIcon, rightIcon, selected = false, ...props }: TerraChipProps) {
  return (
    <button
      className={classNames('terraChip', selected && 'terraChip--selected', className)}
      type="button"
      {...props}
    >
      {leftIcon ? <span className="terraChip__icon">{leftIcon}</span> : null}
      <span className="terraChip__label">{children}</span>
      {typeof counter === 'number' ? <span className="terraChip__counter">{counter}</span> : null}
      {rightIcon ? <span className="terraChip__icon">{rightIcon}</span> : null}
    </button>
  );
}
