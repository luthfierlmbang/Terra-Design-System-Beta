import { TerraIcon } from '../Icon';
import type { TerraSearchFieldProps } from './SearchField.types';
import './SearchField.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraSearchField({
  className,
  disabled = false,
  helperText,
  id,
  label,
  showClearButton = false,
  value,
  ...props
}: TerraSearchFieldProps) {
  const inputId = id ?? `terra-search-field-${Math.random().toString(36).slice(2, 10)}`;
  const hasValue = typeof value === 'string' ? value.length > 0 : false;

  return (
    <label className={classNames('terraField', 'terraSearchField', disabled && 'terraField--disabled', className)} htmlFor={inputId}>
      {label ? <span className="terraField__label">{label}</span> : null}
      <span className="terraField__control">
        <span className="terraField__icon">
          <TerraIcon category="action" name="search" size="sm" />
        </span>
        <input className="terraField__input" id={inputId} type="search" disabled={disabled} value={value} {...props} />
        {showClearButton && hasValue ? (
          <button className="terraSearchField__clear" type="button" tabIndex={-1} aria-label="Clear search">
            <TerraIcon category="action" name="close" size="sm" />
          </button>
        ) : null}
      </span>
      {helperText ? <span className="terraField__helper">{helperText}</span> : null}
    </label>
  );
}
