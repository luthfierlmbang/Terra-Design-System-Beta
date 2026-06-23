import { useEffect, useRef, useState } from 'react';
import { WORKSPACES, getWorkspace, type Workspace } from '../lib/workspaces';
import { navigate } from '../lib/router';
import './WorkspaceSwitcher.css';

function WorkspaceMark({ workspace }: { workspace: Workspace }) {
  if (workspace.logo) {
    return <img src={workspace.logo} alt={workspace.name} className="wsSwitcher__logo" />;
  }

  return (
    <span className="wsSwitcher__wordmark" style={{ color: workspace.accent }}>
      {workspace.wordmark ?? workspace.name}
    </span>
  );
}

export function WorkspaceSwitcher({ currentId }: { currentId: string }) {
  const [open, setOpen] = useState(false);
  const rootRef = useRef<HTMLDivElement>(null);
  const current = getWorkspace(currentId);

  useEffect(() => {
    if (!open) return;

    const onDocClick = (event: MouseEvent) => {
      if (rootRef.current && !rootRef.current.contains(event.target as Node)) {
        setOpen(false);
      }
    };

    const onKey = (event: KeyboardEvent) => {
      if (event.key === 'Escape') setOpen(false);
    };

    document.addEventListener('mousedown', onDocClick);
    document.addEventListener('keydown', onKey);
    return () => {
      document.removeEventListener('mousedown', onDocClick);
      document.removeEventListener('keydown', onKey);
    };
  }, [open]);

  const handleSelect = (workspace: Workspace) => {
    setOpen(false);
    navigate(workspace.route);
  };

  return (
    <div className="wsSwitcher" ref={rootRef}>
      <button
        type="button"
        className={`wsSwitcher__trigger wsSwitcher__trigger--${current.theme}`}
        aria-haspopup="listbox"
        aria-expanded={open}
        onClick={() => setOpen((value) => !value)}
      >
        <span className="wsSwitcher__brandRow">
          <WorkspaceMark workspace={current} />
        </span>
        <span className={`wsSwitcher__chevron${open ? ' is-open' : ''}`} aria-hidden="true">
          <svg width="16" height="16" viewBox="0 0 16 16" fill="none">
            <path d="M4 6l4 4 4-4" stroke="currentColor" strokeWidth="1.6" strokeLinecap="round" strokeLinejoin="round" />
          </svg>
        </span>
      </button>

      {open && (
        <ul className="wsSwitcher__menu" role="listbox" aria-label="Switch design system">
          {WORKSPACES.map((workspace) => (
            <li key={workspace.id}>
              <button
                type="button"
                role="option"
                aria-selected={workspace.id === current.id}
                className={`wsSwitcher__option${workspace.id === current.id ? ' is-active' : ''}`}
                onClick={() => handleSelect(workspace)}
              >
                <span className="wsSwitcher__dot" style={{ background: workspace.accent }} />
                <span className="wsSwitcher__option-copy">
                  <span className="wsSwitcher__option-name">{workspace.name}</span>
                  <span className="wsSwitcher__option-label">{workspace.label}</span>
                </span>
                {workspace.id === current.id && (
                  <svg className="wsSwitcher__check" width="16" height="16" viewBox="0 0 16 16" fill="none" aria-hidden="true">
                    <path d="M3 8.5l3.5 3.5L13 5" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" />
                  </svg>
                )}
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
