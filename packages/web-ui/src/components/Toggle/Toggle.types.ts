import type { InputHTMLAttributes } from 'react';

export type TerraToggleProps = Omit<InputHTMLAttributes<HTMLInputElement>, 'type'> & {
  label?: string;
  helperText?: string;
};
