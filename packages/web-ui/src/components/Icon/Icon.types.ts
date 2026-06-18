export type TerraIconSize = 'sm' | 'md' | 'lg';

export type TerraIconCategory =
  | 'action'
  | 'navigation'
  | 'status'
  | 'info'
  | 'domain'
  | 'system';

export type TerraIconName =
  | 'placeholder'
  | 'plus'
  | 'check'
  | 'close'
  | 'delete'
  | 'edit'
  | 'refresh'
  | 'search'
  | 'sort'
  | 'qr-scan'
  | 'minus'
  | 'arrow-left'
  | 'arrow-right'
  | 'chevron-down'
  | 'chevron-left'
  | 'chevron-right'
  | 'chevron-up'
  | 'kebab-horizontal'
  | 'kebab-horizontal-2'
  | 'hamburger-menu'
  | 'done'
  | 'in-progress'
  | 'offline'
  | 'exclamation'
  | 'information'
  | 'questionmark'
  | 'counter'
  | 'cash-in'
  | 'cash-out'
  | 'nasabah'
  | 'bank'
  | 'report'
  | 'report-transaction'
  | 'akusisi'
  | 'home'
  | 'user'
  | 'settings'
  | 'notification'
  | 'time'
  | 'calendar';

export interface TerraIconProps extends React.HTMLAttributes<HTMLSpanElement> {
  category?: TerraIconCategory;
  name: TerraIconName;
  size?: TerraIconSize | number;
  decorative?: boolean;
  title?: string;
}
