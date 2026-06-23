export type TerraStepperState = 'inactive' | 'progress' | 'complete' | 'error';

export type TerraStepperStep = {
  label: string;
  state?: TerraStepperState;
};

export type TerraStepperProps = {
  steps: TerraStepperStep[];
  /** Zero-based index of the active (in-progress) step. Ignored when explicit per-step state is provided. */
  activeStep?: number;
  className?: string;
};
