export type TerraSelectOption = {
  value: string;
  label: string;
};

export type TerraSelectFieldProps = {
  label?: string;
  helperText?: string;
  placeholder?: string;
  value?: string;
  options?: TerraSelectOption[];
  disabled?: boolean;
  onChange?: (value: string) => void;
  className?: string;
};
