import type { TerraLoadingProps } from './Loading.types';
import './Loading.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraLoading({ className, inline = false, message = 'Dalam Proses...' }: TerraLoadingProps) {
  return (
    <div className={classNames('terraLoading', inline && 'terraLoading--inline', className)}>
      <span className="terraLoading__spinner" aria-hidden="true" />
      <span className="terraLoading__message">{message}</span>
    </div>
  );
}
