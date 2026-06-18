import type { TerraButtonProps } from './Button.types';
import './Button.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraButton({
  children,
  className,
  disabled = false,
  fullWidth = false,
  size = 'normal',
  variant = 'primary',
  ...props
}: TerraButtonProps) {
  return (
    <button
      className={classNames(
        'terraButton',
        `terraButton--${variant}`,
        `terraButton--${size}`,
        fullWidth && 'terraButton--fullWidth',
        className,
      )}
      disabled={disabled}
      {...props}
    >
      {children}
    </button>
  );
}
