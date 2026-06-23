import type { CSSProperties } from 'react';
import { terraIconRegistry } from './iconRegistry';
import type { TerraIconCategory, TerraIconName, TerraIconProps, TerraIconSize } from './Icon.types';
import './Icon.css';

const iconSvgMap = Object.fromEntries(
  terraIconRegistry.map((icon) => [`${icon.category}:${icon.name}`, icon.svg]),
) as Record<string, string>;

const placeholderIconKey = 'system:placeholder';

const legacyCategoryMap: Partial<Record<TerraIconName, TerraIconCategory>> = {
  placeholder: 'system',
  plus: 'action',
  check: 'action',
  close: 'action',
  delete: 'action',
  edit: 'action',
  refresh: 'action',
  search: 'action',
  sort: 'action',
  'qr-scan': 'action',
  minus: 'action',
  'arrow-left': 'navigation',
  'arrow-right': 'navigation',
  'chevron-down': 'navigation',
  'chevron-left': 'navigation',
  'chevron-right': 'navigation',
  'chevron-up': 'navigation',
  'kebab-horizontal': 'navigation',
  'kebab-vertical': 'navigation',
  'kebab-horizontal-2': 'navigation',
  'hamburger-menu': 'navigation',
  done: 'status',
  'in-progress': 'status',
  offline: 'status',
  active: 'state',
  inactive: 'state',
  pending: 'state',
  success: 'state',
  warning: 'state',
  error: 'state',
  exclamation: 'info',
  information: 'info',
  questionmark: 'info',
  counter: 'info',
  'cash-in': 'domain',
  'cash-out': 'domain',
  nasabah: 'domain',
  bank: 'domain',
  report: 'domain',
  'report-transaction': 'domain',
  akusisi: 'domain',
  home: 'system',
  user: 'system',
  settings: 'system',
  notification: 'system',
  time: 'system',
  calendar: 'system',
  square: 'shapes',
  circle: 'shapes',
  diamond: 'shapes',
};

function getIconSize(size: TerraIconSize | number): string | undefined {
  if (typeof size === 'number') {
    return undefined;
  }

  return `terra-icon--${size}`;
}

function resolveIconKey(category: TerraIconCategory | undefined, name: TerraIconName): string {
  const resolvedCategory = category ?? legacyCategoryMap[name] ?? 'system';
  return `${resolvedCategory}:${name}`;
}

export function TerraIcon({
  category,
  name,
  size = 'md',
  decorative = true,
  title,
  className,
  style,
  ...props
}: TerraIconProps) {
  const iconClassName = ['terra-icon', getIconSize(size), className].filter(Boolean).join(' ');
  const iconStyle: CSSProperties = {
    ...(typeof size === 'number' ? { width: size, height: size } : null),
    ...style,
  };
  const iconKey = resolveIconKey(category, name);
  const iconSvg = iconSvgMap[iconKey] ?? iconSvgMap[placeholderIconKey];

  return (
    <span
      className={iconClassName}
      style={iconStyle}
      aria-hidden={decorative ? 'true' : undefined}
      role={decorative ? undefined : 'img'}
      {...props}
    >
      <span
        className="terra-icon__svg"
        aria-hidden="true"
        dangerouslySetInnerHTML={{
          __html: iconSvg,
        }}
      />
    </span>
  );
}
