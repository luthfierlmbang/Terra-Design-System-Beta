export type Workspace = {
  id: string;
  name: string;
  label: string;
  route: string;
  logo?: string;
  wordmark?: string;
  accent: string;
  theme: 'terra' | 'oasis';
  description: string;
};

export const WORKSPACES: Workspace[] = [
  {
    id: 'terra',
    name: 'Terra',
    label: 'Android Tablet',
    route: '/',
    logo: '/terra.svg',
    accent: '#027479',
    theme: 'terra',
    description: 'Tablet-first documentation surface for the Terra component library.',
  },
  {
    id: 'oasis',
    name: 'Sitepat',
    label: 'Android Mobile',
    route: '/oasis',
    logo: '/sitepat_horizontal_1.svg',
    accent: '#f58220',
    theme: 'oasis',
    description: 'Mobile-first documentation surface for the Sitepat component library.',
  },
];

export function getWorkspace(id: string): Workspace {
  return WORKSPACES.find((workspace) => workspace.id === id) ?? WORKSPACES[0];
}
