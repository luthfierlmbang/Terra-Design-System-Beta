import type { ReactNode } from 'react';

export type TerraNavbarItem = {
  id: string;
  label: string;
  icon: ReactNode;
};

export type TerraNavbarProps = {
  items: TerraNavbarItem[];
  activeId?: string;
  onItemClick?: (id: string) => void;
  className?: string;
};
