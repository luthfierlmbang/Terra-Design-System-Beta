import type { CSSProperties, ReactNode } from 'react';
import type { TerraIconCategory, TerraIconName, TerraIconProps, TerraIconSize } from './Icon.types';
import './Icon.css';

type IconRegistry = Record<string, ReactNode>;

const iconPaths: IconRegistry = {
  'system:placeholder': <rect x="5" y="5" width="14" height="14" rx="2" fill="currentColor" />,

  'action:plus': <path d="M12 5v14M5 12h14" stroke="currentColor" strokeWidth="2" strokeLinecap="round" />,
  'action:check': <path d="m5 12 4 4L19 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'action:close': <path d="M6 6l12 12M18 6L6 18" stroke="currentColor" strokeWidth="2" strokeLinecap="round" />,
  'action:delete': <path d="M7 8h10M9 8V6h6v2m-7 0v10m4-10v10m4-10v10M8 20h8" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'action:edit': <path d="M6 18l3.5-.8L18 8.7 15.3 6 6.8 14.5 6 18Zm0 0h3" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'action:refresh': <path d="M18 8V5m0 0h-3m3 0-3 3a7 7 0 1 0 1.8 6.8M6 16v3m0 0h3m-3 0 3-3" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'action:search': <path d="M11 18a7 7 0 1 1 4.95-2.05L19 19" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'action:sort': <path d="M8 7h8M10 12h6M12 17h4" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'action:qr-scan': <path d="M7 7h3v3H7zm7 0h3v3h-3zM7 14h3v3H7zm9 3v-1h1v-2h-2v3zm-2-2h1m-4-8h2m1 10h-2m-1 0v-2" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'action:minus': <path d="M5 12h14" stroke="currentColor" strokeWidth="2" strokeLinecap="round" />,

  'navigation:arrow-left': <path d="M19 12H5m0 0 5-5m-5 5 5 5" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'navigation:arrow-right': <path d="M5 12h14m0 0-5-5m5 5-5 5" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'navigation:chevron-down': <path d="m6 9 6 6 6-6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'navigation:chevron-left': <path d="m15 6-6 6 6 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'navigation:chevron-right': <path d="m9 6 6 6-6 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'navigation:chevron-up': <path d="m6 15 6-6 6 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'navigation:kebab-horizontal': <path d="M8 12h.01M12 12h.01M16 12h.01" stroke="currentColor" strokeWidth="2.4" strokeLinecap="round" />,
  'navigation:kebab-horizontal-2': <path d="M12 8h.01M12 12h.01M12 16h.01" stroke="currentColor" strokeWidth="2.4" strokeLinecap="round" />,
  'navigation:hamburger-menu': <path d="M5 8h14M5 12h14M5 16h14" stroke="currentColor" strokeWidth="2" strokeLinecap="round" />,

  'status:done': <path d="m5 12 4 4L19 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'status:in-progress': <path d="M12 7v5l3 2m6-2a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'status:offline': <path d="M5 9a10 10 0 0 1 14 0M8 12a6 6 0 0 1 8 0M11 15a2 2 0 0 1 2 0M4 4l16 16" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,

  'info:exclamation': <path d="M12 8v5m0 4h.01M12 21a9 9 0 1 1 0-18 9 9 0 0 1 0 18Z" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'info:information': <path d="M12 8h.01M11 11h1v5h1M12 21a9 9 0 1 1 0-18 9 9 0 0 1 0 18Z" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'info:questionmark': <path d="M9.1 9a3 3 0 1 1 5.2 2c-.9.9-1.8 1.5-1.8 3M12 17h.01M12 21a9 9 0 1 1 0-18 9 9 0 0 1 0 18Z" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'info:counter': <><circle cx="12" cy="12" r="8" fill="currentColor" opacity="0.12" /><text x="12" y="15" textAnchor="middle" fontSize="10" fontWeight="700" fill="currentColor">2</text></>,

  'domain:cash-in': <path d="M12 4v16M8 8h6a2 2 0 1 1 0 4h-4a2 2 0 1 0 0 4h6" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'domain:cash-out': <path d="M12 4v16M16 8h-6a2 2 0 1 0 0 4h4a2 2 0 1 1 0 4H8" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'domain:nasabah': <path d="M12 12a4 4 0 1 0-4-4 4 4 0 0 0 4 4Zm-7 8a7 7 0 0 1 14 0" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'domain:bank': <path d="M4 10 12 5l8 5M6 10v7m4-7v7m4-7v7m4-7v7M4 19h16" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'domain:report': <path d="M8 5h6l3 3v11H8zM14 5v4h4M10 12h5M10 15h5" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'domain:report-transaction': <path d="M8 5h6l3 3v11H8zM14 5v4h4M10 12h2m-2 3h5" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'domain:akusisi': <path d="M7 17V9m5 8V5m5 12v-6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" fill="none" />,

  'system:home': <path d="M4 11.5 12 5l8 6.5V19a1 1 0 0 1-1 1h-4v-5H9v5H5a1 1 0 0 1-1-1v-7.5Z" stroke="currentColor" strokeWidth="1.8" strokeLinejoin="round" fill="none" />,
  'system:user': <path d="M12 12a4 4 0 1 0-4-4 4 4 0 0 0 4 4Zm-7 8a7 7 0 0 1 14 0" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'system:settings': <path d="M12 9.5A2.5 2.5 0 1 0 12 14.5 2.5 2.5 0 0 0 12 9.5Zm7 2.5-1.7.7a5.9 5.9 0 0 1-.4 1l1 1.5-1.4 1.4-1.5-1a5.9 5.9 0 0 1-1 .4L12 19l-2-.5a5.9 5.9 0 0 1-1-.4l-1.5 1-1.4-1.4 1-1.5a5.9 5.9 0 0 1-.4-1L5 12l.7-2a5.9 5.9 0 0 1 .4-1l-1-1.5L6.5 6l1.5 1a5.9 5.9 0 0 1 1-.4L12 5l2 .7a5.9 5.9 0 0 1 1 .4l1.5-1 1.4 1.4-1 1.5a5.9 5.9 0 0 1 .4 1Z" stroke="currentColor" strokeWidth="1.4" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'system:notification': <path d="M12 20a2.5 2.5 0 0 0 2.3-1.5H9.7A2.5 2.5 0 0 0 12 20Zm6-3H6l1.5-2.2V11a4.5 4.5 0 1 1 9 0v3.8L18 17Z" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'system:time': <path d="M12 7v5l3 2m6-2a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
  'system:calendar': <path d="M7 4v3m10-3v3M5 8h14M6 6h12a1 1 0 0 1 1 1v11a1 1 0 0 1-1 1H6a1 1 0 0 1-1-1V7a1 1 0 0 1 1-1Z" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" fill="none" />,
};

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
  'kebab-horizontal-2': 'navigation',
  'hamburger-menu': 'navigation',
  done: 'status',
  'in-progress': 'status',
  offline: 'status',
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
  const iconNode = iconPaths[iconKey] ?? iconPaths['system:placeholder'];

  return (
    <span
      className={iconClassName}
      style={iconStyle}
      aria-hidden={decorative ? 'true' : undefined}
      role={decorative ? undefined : 'img'}
      {...props}
    >
      <svg
        viewBox="0 0 24 24"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
        aria-hidden="true"
        focusable="false"
      >
        {title ? <title>{title}</title> : null}
        {iconNode}
      </svg>
    </span>
  );
}
