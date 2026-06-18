export type TerraQuantityEditorProps = {
  value: string;
  onDecrement?: () => void;
  onIncrement?: () => void;
  decrementDisabled?: boolean;
  incrementDisabled?: boolean;
  className?: string;
};
