import { TerraIcon } from '../Icon';
import type { TerraSelectFieldProps } from './SelectField.types';
import './SelectField.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraSelectField({
  className,
  disabled = false,
  helperText,
  label,
  onChange,
  options = [],
  placeholder = 'Select option',
  value = '',
}: TerraSelectFieldProps) {
  return (
    <label className={classNames('terraField', 'terraSelectField', disabled && 'terraField--disabled', className)}>
      {label ? <span className="terraField__label">{label}</span> : null}
      <span className="terraField__control terraField__control--select">
        <select
          className="terraField__input terraField__input--select"
          disabled={disabled}
          value={value}
          onChange={(event) => onChange?.(event.target.value)}
        >
          <option value="">{placeholder}</option>
          {options.map((option) => (
            <option key={option.value} value={option.value}>
              {option.label}
            </option>
          ))}
        </select>
        <span className="terraField__icon terraField__icon--select" aria-hidden="true">
          <TerraIcon category="navigation" name="chevron-down" size="sm" />
        </span>
      </span>
      {helperText ? <span className="terraField__helper">{helperText}</span> : null}
    </label>
  );
}
