import type { InputHTMLAttributes } from 'react';

export type TerraCheckboxProps = Omit<InputHTMLAttributes<HTMLInputElement>, 'type'> & {
  label?: string;
  helperText?: string;
};
