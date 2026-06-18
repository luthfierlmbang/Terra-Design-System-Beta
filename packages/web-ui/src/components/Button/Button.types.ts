import type { ButtonHTMLAttributes, PropsWithChildren } from 'react';

export type TerraButtonType =
  | 'primary'
  | 'secondary'
  | 'outlined-primary'
  | 'outlined-secondary'
  | 'danger'
  | 'text';

export type TerraButtonSize = 'normal' | 'small';

export type TerraButtonProps = PropsWithChildren<
  Omit<ButtonHTMLAttributes<HTMLButtonElement>, 'type'> & {
    variant?: TerraButtonType;
    size?: TerraButtonSize;
    fullWidth?: boolean;
  }
>;
