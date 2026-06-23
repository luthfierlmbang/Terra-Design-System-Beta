# Web Catalog Revamp Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Standardize the TERRA web catalog into a branded, category-based documentation surface with consistent overview cards, component detail pages, and catalog-only preview framing.

**Architecture:** The work stays entirely inside `apps/web-catalog`. The catalog shell will own brand switching, navigation treatment, overview composition, and detail-page templates, while `content/registry.tsx` remains the primary content source for component metadata and demos. Preview consistency comes from catalog-only wrappers and layout rules rather than changes to the underlying component library.

**Tech Stack:** React, TypeScript, CSS Modules/vanilla CSS, existing web catalog routing/content registry, existing `@terra/web-ui` component library, existing `Jenius Sans` typography baseline.

---

### Task 1: Standardize catalog shell and brand toggle

**Files:**
- Modify: `apps/web-catalog/src/App.tsx`
- Modify: `apps/web-catalog/src/components/AppShell.tsx`
- Modify: `apps/web-catalog/src/components/AppShell.css`
- Modify: `apps/web-catalog/src/components/WorkspaceSwitcher.tsx`
- Modify: `apps/web-catalog/src/components/WorkspaceSwitcher.css`
- Modify: `apps/web-catalog/src/components/OasisShell.tsx`
- Modify: `apps/web-catalog/src/components/OasisShell.css`
- Modify: `apps/web-catalog/src/styles.css`
- Modify: `apps/web-catalog/src/lib/workspaces.ts`
- Test: `apps/web-catalog/src/components/AppShell.tsx` and catalog route screens via local preview

- [ ] **Step 1: Add workspace-driven shell state**

```tsx
export type WorkspaceKey = 'terra' | 'sitepat';

export const workspaces: Record<WorkspaceKey, { label: string; accentClassName: string; description: string }> = {
  terra: {
    label: 'Terra',
    accentClassName: 'workspaceTheme--terra',
    description: 'Aplikasi Android Tablet Terra',
  },
  sitepat: {
    label: 'Sitepat',
    accentClassName: 'workspaceTheme--sitepat',
    description: 'Aplikasi Mobile Android',
  },
};
```

- [ ] **Step 2: Wire the global toggle into the shell**

```tsx
const [workspace, setWorkspace] = useState<WorkspaceKey>('terra');

return (
  <div className={`appShell ${workspaces[workspace].accentClassName}`}>
    <WorkspaceSwitcher value={workspace} onChange={setWorkspace} />
    <AppShell workspace={workspace} />
  </div>
);
```

- [ ] **Step 3: Restyle sidebar as filled brand navigation**

```css
.workspaceTheme--terra {
  --sidebar-bg: var(--color-bg-fill-secondary-default);
}

.workspaceTheme--sitepat {
  --sidebar-bg: var(--color-bg-fill-primary-default);
}

.appShell__sidebar {
  padding-left: 16px;
  padding-right: 16px;
}
```

- [ ] **Step 4: Run catalog and confirm brand switching updates shell chrome**

Run: `npm run dev -- --host` in `apps/web-catalog`
Expected: the catalog opens with a global Terra/Sitepat toggle and the sidebar theme changes with the selected workspace.

- [ ] **Step 5: Commit**

```bash
git add apps/web-catalog/src/App.tsx apps/web-catalog/src/components/AppShell.tsx apps/web-catalog/src/components/AppShell.css apps/web-catalog/src/components/WorkspaceSwitcher.tsx apps/web-catalog/src/components/WorkspaceSwitcher.css apps/web-catalog/src/components/OasisShell.tsx apps/web-catalog/src/components/OasisShell.css apps/web-catalog/src/styles.css apps/web-catalog/src/lib/workspaces.ts
git commit -m "feat(web-catalog): add brand toggle shell"
```

### Task 2: Rebuild overview into categorized component landing

**Files:**
- Modify: `apps/web-catalog/src/content/registry.tsx`
- Modify: `apps/web-catalog/src/components/DocPage.tsx`
- Modify: `apps/web-catalog/src/components/DocPage.css`
- Modify: `apps/web-catalog/src/lib/types.ts`
- Test: catalog overview page in browser

- [ ] **Step 1: Extend registry metadata for overview cards**

```ts
export type DocEntry = {
  slug: string;
  name: string;
  group: string;
  description: string;
  shortDescription?: string;
  category?: string;
  representativeDemo?: ReactNode;
  usage?: {
    do: string[];
    dont: string[];
    tokenRule?: string;
  };
};
```

- [ ] **Step 2: Add category grouping for overview rendering**

```tsx
const groupedEntries = entries.reduce<Record<string, DocEntry[]>>((groups, entry) => {
  const groupName = entry.group ?? 'Uncategorized';
  groups[groupName] ??= [];
  groups[groupName].push(entry);
  return groups;
}, {});
```

- [ ] **Step 3: Render overview cards with centered representative previews**

```tsx
<div className="overviewCard__preview">
  <div className="overviewCard__previewFrame">
    {entry.demo}
  </div>
</div>
```

- [ ] **Step 4: Update overview copy for Terra and Sitepat contexts**

```tsx
const workspaceDescription =
  workspace === 'terra'
    ? 'Aplikasi Android Tablet Terra'
    : 'Aplikasi Mobile Android';
```

- [ ] **Step 5: Verify cards never overflow their container**

Run: `npm run dev -- --host` in `apps/web-catalog`
Expected: overview cards keep showcase content centered, clipped safely, and readable in both workspace themes.

- [ ] **Step 6: Commit**

```bash
git add apps/web-catalog/src/content/registry.tsx apps/web-catalog/src/components/DocPage.tsx apps/web-catalog/src/components/DocPage.css apps/web-catalog/src/lib/types.ts
git commit -m "feat(web-catalog): standardize overview sections"
```

### Task 3: Standardize component detail page template

**Files:**
- Modify: `apps/web-catalog/src/components/DocPage.tsx`
- Modify: `apps/web-catalog/src/components/DocPage.css`
- Modify: `apps/web-catalog/src/content/registry.tsx`
- Modify: `apps/web-catalog/src/lib/types.ts`
- Test: one representative component page per category

- [ ] **Step 1: Lock the page section order**

```tsx
<section className="docPage__titleBlock" />
<section className="docPage__descriptionBlock" />
<section className="docPage__playgroundBlock" />
<section className="docPage__variantsBlock" />
<section className="docPage__usageBlock" />
<section className="docPage__anatomyBlock" />
<section className="docPage__rulesBlock" />
<section className="docPage__snippetsBlock" />
```

- [ ] **Step 2: Add guidance, anatomy, do/don’t, and Java/Kotlin snippet slots to the content model**

```ts
export type DocEntry = {
  usage?: { do: string[]; dont: string[]; whenToUse?: string[] };
  anatomy?: Array<{ part: string; description: string }>;
  snippets?: {
    java?: { code: string; label?: string };
    kotlin?: { code: string; label?: string };
  };
};
```

- [ ] **Step 3: Render snippet blocks in a stable two-column or stacked layout**

```tsx
<div className="docPage__snippetGrid">
  <CodeBlock language="java" code={entry.snippets?.java?.code ?? ''} />
  <CodeBlock language="kotlin" code={entry.snippets?.kotlin?.code ?? ''} />
</div>
```

- [ ] **Step 4: Fill representative entries so the template can be validated**

```ts
snippets: {
  java: { code: 'view.setLabel("Label");' },
  kotlin: { code: 'view.label = "Label"' },
}
```

- [ ] **Step 5: Verify all supported pages still render with missing optional sections**

Run: `npm run dev -- --host` in `apps/web-catalog`
Expected: pages with incomplete metadata still render in the same section order without layout breakage.

- [ ] **Step 6: Commit**

```bash
git add apps/web-catalog/src/components/DocPage.tsx apps/web-catalog/src/components/DocPage.css apps/web-catalog/src/content/registry.tsx apps/web-catalog/src/lib/types.ts
git commit -m "feat(web-catalog): standardize component detail template"
```

### Task 4: Add catalog-only preview frame and variant wrapping rules

**Files:**
- Modify: `apps/web-catalog/src/components/TabletFrame.tsx`
- Modify: `apps/web-catalog/src/components/TabletFrame.css`
- Modify: `apps/web-catalog/src/components/DocPage.tsx`
- Modify: `apps/web-catalog/src/components/DocPage.css`
- Modify: `apps/web-catalog/src/content/registry.tsx`
- Test: button, header, and dense variant pages

- [ ] **Step 1: Wrap showcases with a reusable preview frame**

```tsx
export function ShowcaseFrame({ children }: { children: React.ReactNode }) {
  return <div className="showcaseFrame">{children}</div>;
}
```

- [ ] **Step 2: Center and bound preview content**

```css
.showcaseFrame {
  display: grid;
  place-items: center;
  padding: 16px;
  overflow: hidden;
}
```

- [ ] **Step 3: Add auto-fit rules for oversized showcases**

```css
.showcaseFrame__content {
  transform-origin: center;
  max-width: 100%;
  max-height: 100%;
}
```

- [ ] **Step 4: Make variant grids wrap when symmetry breaks**

```css
.docPage__variantGrid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
  gap: 16px;
}
```

- [ ] **Step 5: Validate representative component families with wide and narrow variants**

Run: `npm run dev -- --host` in `apps/web-catalog`
Expected: button, header, and dense form components remain centered inside frames and wrap cleanly when one-row layout would be cramped.

- [ ] **Step 6: Commit**

```bash
git add apps/web-catalog/src/components/TabletFrame.tsx apps/web-catalog/src/components/TabletFrame.css apps/web-catalog/src/components/DocPage.tsx apps/web-catalog/src/components/DocPage.css apps/web-catalog/src/content/registry.tsx
git commit -m "feat(web-catalog): add showcase frame system"
```

### Task 5: Backfill component metadata and overview content

**Files:**
- Modify: `apps/web-catalog/src/content/registry.tsx`
- Modify: `apps/web-catalog/src/components/oasis/*.tsx` as needed for showcase-friendly demo wrappers only
- Test: overview cards and detail pages for all major component categories

- [ ] **Step 1: Add missing short descriptions and categories for all entries**

```ts
shortDescription: 'Button utama untuk aksi user',
category: 'Actions',
```

- [ ] **Step 2: Add representative showcase entries for cards that currently render too large or too wide**

```tsx
representativeDemo: <div className="catalogPreview catalogPreview--compact">...</div>,
```

- [ ] **Step 3: Add usage guidance and do/don’t content to the most visible component pages first**

```ts
usage: {
  whenToUse: ['Gunakan untuk aksi utama.', 'Gunakan saat user perlu konfirmasi eksplisit.'],
  do: ['Gunakan label singkat dan jelas.'],
  dont: ['Jangan jadikan tombol sebagai navigasi utama.'],
}
```

- [ ] **Step 4: Add Java and Kotlin snippets for the components most likely to be checked first**

```ts
snippets: {
  java: { code: 'button.setEnabled(true);' },
  kotlin: { code: 'button.isEnabled = true' },
}
```

- [ ] **Step 5: Recheck the catalog overview for balanced card height and readable category grouping**

Run: `npm run dev -- --host` in `apps/web-catalog`
Expected: the overview is visibly grouped, the cards are balanced, and the workspace toggle still works across all sections.

- [ ] **Step 6: Commit**

```bash
git add apps/web-catalog/src/content/registry.tsx apps/web-catalog/src/components/oasis
 git commit -m "feat(web-catalog): backfill catalog metadata"
```

### Task 6: Final polish and verification

**Files:**
- Modify: `apps/web-catalog/src/styles.css`
- Modify: `apps/web-catalog/src/components/DocPage.css`
- Modify: `apps/web-catalog/src/components/AppShell.css`
- Test: full web catalog navigation flow

- [ ] **Step 1: Tighten spacing, typography, and card rhythm across catalog pages while keeping `Jenius Sans`**

```css
.docPage {
  gap: 32px;
  font-family: 'Jenius Sans', sans-serif;
}

.docPage__section {
  margin-top: 24px;
}
```

- [ ] **Step 2: Make the brand theme variables feel visibly distinct but still consistent**

```css
.workspaceTheme--terra {
  --catalog-accent: var(--color-bg-fill-secondary-default);
}

.workspaceTheme--sitepat {
  --catalog-accent: var(--color-bg-fill-primary-default);
}
```

- [ ] **Step 3: Run a full manual walkthrough from overview to representative component pages**

Run: `npm run dev -- --host` in `apps/web-catalog`
Expected: overview, component pages, and brand toggle all behave consistently without layout overflow.

- [ ] **Step 4: Commit**

```bash
git add apps/web-catalog/src/styles.css apps/web-catalog/src/components/DocPage.css apps/web-catalog/src/components/AppShell.css
git commit -m "feat(web-catalog): polish layout rhythm"
```

## Self-Review Checklist

- [x] Every spec requirement maps to at least one task.
- [x] No placeholder steps remain.
- [x] File paths are exact and scoped to `apps/web-catalog`.
- [x] The plan keeps the component library untouched and limits changes to the catalog layer.
- [x] The preview-frame approach is consistent with the approved design.
- [x] The plan can be executed task-by-task with checkpoints.

## Execution Handoff

Plan complete and saved to `docs/superpowers/plans/2026-06-22-web-catalog-revamp.md`. Two execution options:

1. Subagent-Driven (recommended) - I dispatch a fresh subagent per task, review between tasks, fast iteration.
2. Inline Execution - Execute tasks in this session using executing-plans, batch execution with checkpoints.

Which approach?
