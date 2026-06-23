export type TerraHeaderVariant = 'main' | 'secondary';
export type TerraHeaderLeading = 'none' | 'back' | 'menu' | 'icon';

export interface TerraHeaderPageIdentifier {
  moduleName?: string;
  apkName?: string;
  text?: string;
}

export interface TerraHeaderProps {
  title?: string;
  subtitle?: string;
  variant?: TerraHeaderVariant;
  leading?: TerraHeaderLeading;
  icon?: React.ReactNode;
  actionOne?: React.ReactNode;
  actionTwo?: React.ReactNode;
  showActionOne?: boolean;
  showActionTwo?: boolean;
  showIdentifier?: boolean;
  pageIdentifier?: TerraHeaderPageIdentifier;
  showStatusBar?: boolean;
  backgroundColor?: string;
  className?: string;
}

export interface TerraPageIdentifierProps {
  moduleName?: string;
  apkName?: string;
  text?: string;
  className?: string;
}
