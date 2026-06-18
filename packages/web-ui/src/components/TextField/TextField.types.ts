import type { InputHTMLAttributes, ReactNode } from 'react';

export type TerraTextFieldProps = Omit<InputHTMLAttributes<HTMLInputElement>, 'size'> & {
  label?: string;
  helperText?: string;
  prefix?: ReactNode;
  suffix?: ReactNode;
  leftIcon?: ReactNode;
  rightIcon?: ReactNode;
};
