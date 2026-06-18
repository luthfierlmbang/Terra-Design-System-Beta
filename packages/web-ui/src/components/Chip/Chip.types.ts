import type { ButtonHTMLAttributes, ReactNode } from 'react';

export type TerraChipProps = Omit<ButtonHTMLAttributes<HTMLButtonElement>, 'type'> & {
  selected?: boolean;
  leftIcon?: ReactNode;
  rightIcon?: ReactNode;
  counter?: number;
};
