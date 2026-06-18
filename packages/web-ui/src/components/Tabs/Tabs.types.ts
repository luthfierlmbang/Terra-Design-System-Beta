export type TerraTabItem = {
  id: string;
  label: string;
};

export type TerraTabsProps = {
  items: TerraTabItem[];
  selectedId?: string;
  onChange?: (id: string) => void;
  scrollable?: boolean;
  className?: string;
};
