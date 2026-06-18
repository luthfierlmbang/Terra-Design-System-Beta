import type { TerraQuantityEditorProps } from './QuantityEditor.types';
import './QuantityEditor.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraQuantityEditor({
  className,
  decrementDisabled = false,
  incrementDisabled = false,
  onDecrement,
  onIncrement,
  value,
}: TerraQuantityEditorProps) {
  return (
    <div className={classNames('terraQuantityEditor', className)}>
      <button className="terraQuantityEditor__button" type="button" onClick={onDecrement} disabled={decrementDisabled}>
        −
      </button>
      <span className="terraQuantityEditor__value">{value}</span>
      <button className="terraQuantityEditor__button" type="button" onClick={onIncrement} disabled={incrementDisabled}>
        +
      </button>
    </div>
  );
}
