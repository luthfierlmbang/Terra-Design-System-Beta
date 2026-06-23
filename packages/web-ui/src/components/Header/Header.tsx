import { TerraIcon } from '../Icon';
import type { TerraHeaderLeading, TerraHeaderProps, TerraPageIdentifierProps } from './Header.types';
import './Header.css';

function TerraPageIdentifier({
  moduleName = 'Put Main Module name',
  apkName = 'Put APK Name',
  text,
  className,
}: TerraPageIdentifierProps) {
  const resolvedText = text?.trim() || [moduleName, apkName].filter(Boolean).join(' | ');

  if (!resolvedText) return null;

  return (
    <p className={['terra-page-identifier', className].filter(Boolean).join(' ')}>
      {resolvedText}
    </p>
  );
}

function TerraStatusBar() {
  return (
    <div className="terra-header__status-bar">
      <span className="terra-header__status-time">12:30</span>
      <div className="terra-header__status-indicators" aria-hidden="true">
        <span className="terra-header__status-glyph terra-header__status-glyph--wifi" />
        <span className="terra-header__status-glyph terra-header__status-glyph--cellular" />
        <span className="terra-header__status-glyph terra-header__status-glyph--battery" />
      </div>
    </div>
  );
}

function renderLeading(leading: TerraHeaderLeading, icon?: React.ReactNode) {
  switch (leading) {
    case 'none':
      return null;
    case 'back':
      return <TerraIcon category="navigation" name="chevron-left" size="md" />;
    case 'menu':
      return <TerraIcon category="navigation" name="hamburger-menu" size="md" />;
    case 'icon':
    default:
      return icon ?? <TerraIcon category="system" name="scooter" size="md" />;
  }
}

export function TerraHeader({
  title = 'Title',
  subtitle,
  variant = 'main',
  leading = 'icon',
  icon,
  actionOne,
  actionTwo,
  showActionOne = true,
  showActionTwo = true,
  showIdentifier = true,
  pageIdentifier,
  showStatusBar = true,
  backgroundColor,
  className,
}: TerraHeaderProps) {
  const headerStyle = backgroundColor ? { backgroundColor } : undefined;
  const isMain = variant === 'main';
  const hasActions = showActionOne || showActionTwo;

  return (
    <div className={['terra-header', `terra-header--${variant}`, className].filter(Boolean).join(' ')}>
      {showStatusBar ? <TerraStatusBar /> : null}
      <div className="terra-header__main" style={headerStyle}>
        <div className={['terra-header__leading', leading === 'none' ? 'terra-header__leading--hidden' : ''].filter(Boolean).join(' ')} aria-hidden={leading === 'none' ? 'true' : undefined}>
          {renderLeading(leading, icon)}
        </div>

        <div className="terra-header__content">
          {isMain ? (
            <img
              className="terra-header__logo-image"
              src="/figma-card-assets/terra-header-logo.svg"
              alt="Terra"
            />
          ) : (
            <>
              <div className="terra-header__title-row">
                <h1 className="terra-header__title">{title}</h1>
              </div>
              {subtitle ? <p className="terra-header__subtitle">{subtitle}</p> : null}
              {showIdentifier ? (
                <TerraPageIdentifier
                  moduleName={pageIdentifier?.moduleName}
                  apkName={pageIdentifier?.apkName}
                  text={pageIdentifier?.text}
                />
              ) : null}
            </>
          )}
        </div>

        <div className={['terra-header__actions', !hasActions ? 'terra-header__actions--hidden' : ''].filter(Boolean).join(' ')}>
          {showActionOne ? <div className="terra-header__action">{actionOne ?? <TerraIcon category="system" name="home" size="md" />}</div> : null}
          {showActionTwo ? <div className="terra-header__action">{actionTwo ?? <TerraIcon category="system" name="notification" size="md" />}</div> : null}
        </div>
      </div>
    </div>
  );
}
