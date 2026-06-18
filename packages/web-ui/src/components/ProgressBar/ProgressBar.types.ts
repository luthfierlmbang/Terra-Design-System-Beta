export type TerraProgressBarVariant = 'base' | 'bar1' | 'bar2';

export type TerraProgressBarProps = {
  variant?: TerraProgressBarVariant;
  value?: number;
  className?: string;
};
