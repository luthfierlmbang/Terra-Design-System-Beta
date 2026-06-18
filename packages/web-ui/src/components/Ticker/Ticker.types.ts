export type TerraTickerTone = 'information' | 'warning' | 'error';

export type TerraTickerProps = {
  tone?: TerraTickerTone;
  detailed?: boolean;
  title?: string;
  message: string;
  className?: string;
};
