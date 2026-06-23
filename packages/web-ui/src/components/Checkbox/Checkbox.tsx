import type { TerraCheckboxProps } from './Checkbox.types';
import './Checkbox.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraCheckbox({ className, disabled = false, helperText, id, label, ...props }: TerraCheckboxProps) {
  const inputId = id ?? `terra-checkbox-${Math.random().toString(36).slice(2, 10)}`;

  return (
    <label className={classNames('terraChoiceField', disabled && 'terraChoiceField--disabled', className)} htmlFor={inputId}>
      <span className="terraChoiceField__main">
        <input className="terraChoiceField__input" id={inputId} type="checkbox" disabled={disabled} {...props} />
        <span className="terraChoiceField__control terraChoiceField__control--checkbox" aria-hidden="true">
          <span className="terraChoiceField__indicator">✓</span>
        </span>
        {label ? <span className="terraChoiceField__label">{label}</span> : null}
      </span>
      {helperText ? <span className="terraChoiceField__helper">{helperText}</span> : null}
    </label>
  );
}
