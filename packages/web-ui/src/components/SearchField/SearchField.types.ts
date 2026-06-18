import type { InputHTMLAttributes } from 'react';

export type TerraSearchFieldProps = Omit<InputHTMLAttributes<HTMLInputElement>, 'size' | 'type'> & {
  label?: string;
  helperText?: string;
  showClearButton?: boolean;
};
