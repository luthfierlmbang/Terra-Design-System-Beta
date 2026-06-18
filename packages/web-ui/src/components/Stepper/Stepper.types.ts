export type TerraStepperStep = {
  label: string;
};

export type TerraStepperProps = {
  steps: TerraStepperStep[];
  /** Zero-based index of the active (in-progress) step. Default 0. */
  activeStep?: number;
  className?: string;
};
