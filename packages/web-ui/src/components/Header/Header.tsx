import { TerraIcon } from '../Icon';
import type { TerraHeaderProps, TerraPageIdentifierProps } from './Header.types';
import './Header.css';

function TerraPageIdentifier({
  moduleName = 'Put Main Module name',
  apkName = 'Put APK Name',
  className,
}: TerraPageIdentifierProps) {
  return (
    <div className={['terra-page-identifier', className].filter(Boolean).join(' ')}>
      <p className="terra-page-identifier__text">{moduleName}</p>
      <span className="terra-page-identifier__separator">|</span>
      <p className="terra-page-identifier__text">{apkName}</p>
    </div>
  );
}

function TerraStatusBar() {
  return (
    <div className="terra-header__status-bar">
      <div className="terra-header__status-indicators" aria-hidden="true">
        <span className="terra-header__status-indicator">◢</span>
        <span className="terra-header__status-indicator">◫</span>
        <span className="terra-header__status-indicator">▮</span>
      </div>
      <span className="terra-header__status-time">12:30</span>
    </div>
  );
}

export function TerraHeader({
  title,
  icon,
  pageIdentifier,
  showStatusBar = true,
  backgroundColor,
  className,
}: TerraHeaderProps) {
  const headerStyle = backgroundColor
    ? { backgroundColor }
    : undefined;

  return (
    <div className={['terra-header', className].filter(Boolean).join(' ')}>
      {showStatusBar ? <TerraStatusBar /> : null}
      <div className="terra-header__main" style={headerStyle}>
        <div className="terra-header__content">
          <div className="terra-header__title-row">
            <div className="terra-header__icon" aria-hidden="true">
              {icon ?? <TerraIcon category="system" name="placeholder" size="md" />}
            </div>
            <h1 className="terra-header__title">{title}</h1>
          </div>
          <div className="terra-header__page-identifier-wrapper">
            <TerraPageIdentifier
              moduleName={pageIdentifier?.moduleName}
              apkName={pageIdentifier?.apkName}
            />
          </div>
        </div>
      </div>
    </div>
  );
}
