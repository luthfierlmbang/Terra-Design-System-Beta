import type { ReactNode } from 'react';

export type Platform = 'Web' | 'Android' | 'Web + Android';

export type PropRow = {
  name: string;
  type: string;
  default?: string;
  description: string;
};

export type AnatomyPart = {
  part: string;
  description: string;
};

export type DemoState = {
  name: string;
  description: string;
  demo?: ReactNode;
};

export type ComponentGroup =
  | 'Foundations'
  | 'Actions'
  | 'Selection'
  | 'Forms'
  | 'Feedback'
  | 'Navigation'
  | 'Overlay';

export type AndroidSnippetBlock = {
  language: 'xml' | 'kotlin';
  code?: string;
  note?: string;
};

export type AndroidSnippet = {
  xml: AndroidSnippetBlock;
  kotlin: AndroidSnippetBlock;
};

export type DocEntry = {
  /** URL slug, e.g. "button" → #/component/button */
  slug: string;
  name: string;
  group: ComponentGroup;
  platform: Platform;
  /** Short one-line summary shown in nav + page header. */
  description: string;
  /** Live specimen, rendered inside a tablet frame. */
  demo: ReactNode;
  /** How the demo should sit on the tablet surface. */
  demoAlign?: 'center' | 'start' | 'stretch';
  /** Render the demo without the tablet frame (for reference/foundation pages). */
  bareDemo?: boolean;
  anatomy?: AnatomyPart[];
  props?: PropRow[];
  states?: DemoState[];
  androidSnippet: AndroidSnippet;
  usage?: {
    do: string[];
    dont: string[];
  };
};

export const GROUP_ORDER: ComponentGroup[] = [
  'Foundations',
  'Actions',
  'Selection',
  'Forms',
  'Feedback',
  'Navigation',
  'Overlay',
];
