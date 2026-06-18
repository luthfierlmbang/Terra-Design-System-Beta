import type { TerraTextFieldProps } from './TextField.types';
import './TextField.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraTextField({
  className,
  disabled = false,
  helperText,
  id,
  label,
  leftIcon,
  prefix,
  rightIcon,
  suffix,
  ...props
}: TerraTextFieldProps) {
  const inputId = id ?? `terra-text-field-${Math.random().toString(36).slice(2, 10)}`;

  return (
    <label className={classNames('terraField', disabled && 'terraField--disabled', className)} htmlFor={inputId}>
      {label ? <span className="terraField__label">{label}</span> : null}
      <span className="terraField__control">
        {leftIcon ? <span className="terraField__icon">{leftIcon}</span> : null}
        {prefix ? <span className="terraField__affix terraField__affix--prefix">{prefix}</span> : null}
        <input className="terraField__input" id={inputId} disabled={disabled} {...props} />
        {suffix ? <span className="terraField__affix terraField__affix--suffix">{suffix}</span> : null}
        {rightIcon ? <span className="terraField__icon">{rightIcon}</span> : null}
      </span>
      {helperText ? <span className="terraField__helper">{helperText}</span> : null}
    </label>
  );
}
