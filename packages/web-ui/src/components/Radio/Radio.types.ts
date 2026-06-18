import type { InputHTMLAttributes } from 'react';

export type TerraRadioProps = Omit<InputHTMLAttributes<HTMLInputElement>, 'type'> & {
  label?: string;
  helperText?: string;
};
