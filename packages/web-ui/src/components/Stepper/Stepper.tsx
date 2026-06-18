import { Fragment } from 'react';
import type { TerraStepperProps } from './Stepper.types';
import './Stepper.css';

function CheckIcon() {
  return (
    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" aria-hidden="true">
      <path d="M5 13L9.5 17.5L19 7" stroke="white" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" />
    </svg>
  );
}

type StepState = 'inactive' | 'progress' | 'complete';

function getStepState(index: number, activeStep: number): StepState {
  if (index < activeStep) return 'complete';
  if (index === activeStep) return 'progress';
  return 'inactive';
}

export function TerraStepper({ steps, activeStep = 0, className }: TerraStepperProps) {
  return (
    <div className={['terraStepper', className].filter(Boolean).join(' ')}>
      {steps.map((step, i) => {
        const stepState = getStepState(i, activeStep);
        const lineState: StepState | null = i < steps.length - 1 ? getStepState(i + 1, activeStep) : null;
        return (
          <Fragment key={i}>
            <div className="terraStepper__step">
              <div className="terraStepper__bullet-wrapper">
                <div className={`terraStepper__bullet terraStepper__bullet--${stepState}`}>
                  {stepState === 'complete' ? (
                    <CheckIcon />
                  ) : (
                    <span className="terraStepper__bullet-number">{i + 1}</span>
                  )}
                </div>
              </div>
              <span className="terraStepper__caption">{step.label}</span>
            </div>
            {lineState !== null && (
              <div className="terraStepper__connector">
                <div className={`terraStepper__line terraStepper__line--${lineState}`} />
              </div>
            )}
          </Fragment>
        );
      })}
    </div>
  );
}
