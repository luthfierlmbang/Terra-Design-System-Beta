import { Fragment } from 'react';
import { TerraIcon } from '../Icon';
import type { TerraStepperProps, TerraStepperState } from './Stepper.types';
import './Stepper.css';

function getDerivedStepState(index: number, activeStep: number): TerraStepperState {
  if (index < activeStep) return 'complete';
  if (index === activeStep) return 'progress';
  return 'inactive';
}

function getStepState(stepState: TerraStepperState | undefined, index: number, activeStep: number): TerraStepperState {
  return stepState ?? getDerivedStepState(index, activeStep);
}

function getLineState(currentState: TerraStepperState, nextState: TerraStepperState): 'inactive' | 'progress' | 'complete' {
  if (currentState === 'complete') return 'complete';
  if (currentState === 'progress' || nextState === 'progress') return 'progress';
  return 'inactive';
}

export function TerraStepper({ steps, activeStep = 0, className }: TerraStepperProps) {
  return (
    <div className={['terraStepper', className].filter(Boolean).join(' ')}>
      {steps.map((step, i) => {
        const stepState = getStepState(step.state, i, activeStep);
        const nextStepState = i < steps.length - 1 ? getStepState(steps[i + 1].state, i + 1, activeStep) : null;
        const lineState = nextStepState ? getLineState(stepState, nextStepState) : null;

        return (
          <Fragment key={i}>
            <div className="terraStepper__step">
              <div className="terraStepper__bullet-wrapper">
                <div className={`terraStepper__bullet terraStepper__bullet--${stepState}`}>
                  {stepState === 'complete' ? (
                    <TerraIcon category="action" name="check" size="sm" />
                  ) : stepState === 'error' ? (
                    <TerraIcon category="action" name="close" size="sm" />
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
