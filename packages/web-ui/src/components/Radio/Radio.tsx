import type { TerraRadioProps } from './Radio.types';
import './Radio.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraRadio({ className, disabled = false, helperText, id, label, ...props }: TerraRadioProps) {
  const inputId = id ?? `terra-radio-${Math.random().toString(36).slice(2, 10)}`;

  return (
    <label className={classNames('terraChoiceField', disabled && 'terraChoiceField--disabled', className)} htmlFor={inputId}>
      <span className="terraChoiceField__main">
        <input className="terraChoiceField__input" id={inputId} type="radio" disabled={disabled} {...props} />
        <span className="terraChoiceField__control terraChoiceField__control--radio" aria-hidden="true">
          <span className="terraChoiceField__indicator" />
        </span>
        {label ? <span className="terraChoiceField__label">{label}</span> : null}
      </span>
      {helperText ? <span className="terraChoiceField__helper">{helperText}</span> : null}
    </label>
  );
}
