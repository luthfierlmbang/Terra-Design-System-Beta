import type { TerraPageControlProps } from './PageControl.types';
import './PageControl.css';

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraPageControl({ className, count, selectedPage = 0 }: TerraPageControlProps) {
  return (
    <div className={classNames('terraPageControl', className)}>
      {Array.from({ length: count }, (_, index) => (
        <span
          key={index}
          className={classNames('terraPageControl__dot', index === selectedPage && 'terraPageControl__dot--selected')}
        />
      ))}
    </div>
  );
}
