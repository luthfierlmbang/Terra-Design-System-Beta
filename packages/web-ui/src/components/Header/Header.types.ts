export interface TerraHeaderProps {
  /** Main title text */
  title: string;
  
  /** Optional icon name or component for the left side */
  icon?: React.ReactNode;
  
  /** Optional page identifier/breadcrumb info */
  pageIdentifier?: {
    moduleName?: string;
    apkName?: string;
  };
  
  /** Show status bar (time, wifi, etc) - typically for mobile */
  showStatusBar?: boolean;
  
  /** Background color override - defaults to primary brand color */
  backgroundColor?: string;
  
  /** Additional CSS class */
  className?: string;
}

export interface TerraPageIdentifierProps {
  moduleName?: string;
  apkName?: string;
  className?: string;
}
