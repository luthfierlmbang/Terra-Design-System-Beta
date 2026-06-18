import type { TerraToggleProps } from './Toggle.types';
import './Toggle.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraToggle({ checked = false, className, disabled = false, helperText, id, label, ...props }: TerraToggleProps) {
  const inputId = id ?? `terra-toggle-${Math.random().toString(36).slice(2, 10)}`;

  return (
    <label className={classNames('terraToggleField', disabled && 'terraToggleField--disabled', className)} htmlFor={inputId}>
      <span className="terraToggleField__row">
        <span className="terraToggleField__copy">
          {label ? <span className="terraToggleField__label">{label}</span> : null}
          {helperText ? <span className="terraToggleField__helper">{helperText}</span> : null}
        </span>
        <span className="terraToggleField__switch">
          <input className="terraToggleField__input" id={inputId} type="checkbox" checked={checked} disabled={disabled} {...props} />
          <span className="terraToggleField__track" aria-hidden="true">
            <span className="terraToggleField__thumb" />
          </span>
        </span>
      </span>
    </label>
  );
}
