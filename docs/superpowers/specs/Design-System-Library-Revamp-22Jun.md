# Web Catalog Revamp Design

Date: 2026-06-22
Status: Approved for planning
Scope: `apps/web-catalog` only

## Summary

This revamp standardizes the visual presentation and information structure of the web catalog without modifying the underlying component libraries or token source of truth. The catalog becomes a cleaner, more systematic documentation layer with strong brand expression for both Terra and Sitepat.

## Goals

- Standardize the web catalog so it feels consistent, structured, and easier to browse.
- Improve visual quality without turning the catalog into a marketing site.
- Preserve the component library as the source of truth.
- Support both Terra and Sitepat through a single global brand context.
- Make overview and component-detail pages predictable, scannable, and maintainable.
- Keep component showcase previews visually tidy without changing component source dimensions.

## Non-Goals

- No changes to `packages/web-ui` component behavior or source sizing.
- No changes to Android component libraries or their documentation payloads outside catalog consumption.
- No token architecture rewrite as part of this revamp.
- No requirement to redesign every component itself; the focus is catalog presentation and documentation structure.

## Product Direction

The catalog should follow a hybrid direction: documentation-first in structure and readability, but still visibly branded. It should feel polished and modern while remaining practical for design-system consumers.

## Brand Model

A global brand toggle controls the presentation context of the catalog.

### Terra
- Product context: Android Tablet Terra.
- Sidebar style: filled.
- Sidebar color family: derived from Terra tile primary colors.
- Sidebar horizontal padding: `16px`.

### Sitepat
- Product context: Android Mobile Sitepat.
- Sidebar style: filled.
- Sidebar color family: derived from Sitepat orange primary colors.
- Sidebar horizontal padding: `16px`.

### Toggle behavior
- The active brand applies globally across the catalog.
- The selected brand affects sidebar treatment, overview messaging, platform context copy, and other catalog-only brand accents.
- The toggle does not modify underlying component implementation.

## Information Architecture

The catalog structure is standardized into these top-level experiences:

1. Overview
2. Foundations (when applicable)
3. Components grouped by category
4. Component detail pages

### Component grouping
Components are grouped by category rather than shown as a flat list. The category model should support present and future shared component growth.

Representative category examples:
- Actions
- Forms
- Navigation
- Feedback
- Data display

The exact category labels should align with the current component registry and be adjusted only when necessary for clarity.

## Overview Page

The overview page becomes the main landing page for each active brand context.

### Required content
- A short explanation of what the design system is for.
- Installation guidance.
- Basic usage guidance.
- Brand-specific platform context:
  - Terra = Android Tablet application.
  - Sitepat = Android Mobile application.
- A categorized list of available components.

### Overview component cards
Each component card includes:
- A representative showcase preview.
- The component name.
- A short supporting description.

### Overview preview rules
- Preview content must remain centered inside the card.
- Preview content must never overflow its card boundary.
- The card should adapt to different representative components without creating offside or clipped visuals.
- Preview scaling is allowed only at the catalog presentation layer.

## Component Detail Page Template

Every component page should follow a standard content sequence.

### Required sections
1. Component title
2. Component description
3. Showcase / playground
4. Variants
5. Usage guidance
6. Anatomy
7. Do / Don’t rules
8. Android code snippets for Java and Kotlin

This structure creates a predictable reading flow for users and keeps documentation parity across components.

## Showcase and Variant Presentation System

A dedicated catalog-only preview system is used to keep showcases consistent without altering the source components.

### Preview frame model
The catalog uses a standard preview frame for rendering component showcases.

The frame is responsible for:
- centering content
- providing safe internal padding
- setting max bounds
- allowing optional scale adjustment when the rendered component would otherwise exceed its presentation area

### Scaling rules
- Original component size definitions remain untouched.
- Scaling affects showcase presentation only.
- Previews may be reduced to fit the card or frame.
- Previews must keep their aspect and visual proportions.
- Previews must never be stretched disproportionately.

### Variant layout rules
- Variant galleries should look symmetrical whenever possible.
- When a symmetrical horizontal layout would become visually cramped, the layout should wrap downward into a clean grid.
- The gallery should not force items into a single row if doing so reduces clarity or visual precision.
- Shared frame sizing should be used where possible to keep alignment neat across variant examples.

## Layout System

The catalog should use a single consistent layout system for spacing, section rhythm, card structure, content width, and typography.

### Layout expectations
- Sections share consistent vertical spacing.
- Major content areas use stable width rules.
- Cards use standardized spacing and visual treatment.
- Showcase sections use repeatable internal alignment rules.
- The page should feel intentionally organized rather than individually styled per component.

### Typography direction
- The catalog must continue using `Jenius Sans` as the primary typeface.
- Typography refreshes in the catalog may adjust hierarchy, sizing, rhythm, and emphasis, but must not replace `Jenius Sans` with another family.
- Heading, body, label, and supporting text treatments should all remain aligned with the existing `Jenius Sans` token baseline already used in the design system.

## Content Model Requirements

The catalog needs a more standardized component metadata model so every page can be rendered from a consistent content contract.

Each component should be able to define:
- category
- short description
- platform context copy where needed
- representative showcase entry
- variant list
- usage guidance
- anatomy content
- do content
- don’t content
- Java snippet
- Kotlin snippet

If some components are currently missing pieces of this content, the template should support progressive completion while preserving section order.

## Implementation Boundaries

All changes for this revamp should stay inside the web catalog layer.

### Allowed areas
- `apps/web-catalog` layout and routing updates
- catalog-specific styling
- catalog page templates
- catalog registry/content model changes
- catalog preview wrappers and presentation helpers

### Disallowed areas
- changing component internals for presentation convenience
- changing source component sizing to make showcases fit
- altering token source files solely to satisfy catalog layout issues unless a real token gap is discovered independently

## Error Handling and Edge Cases

The catalog design should anticipate uneven content and component shape differences.

Examples:
- A component with many variants should wrap into a balanced grid instead of forcing a single row.
- A narrow component and a wide component should both remain centered within the same preview system.
- A component missing anatomy or do/don’t content should still render in a stable way using placeholder-safe section behavior during migration.
- Long component names or descriptions should not break card height or visual alignment unexpectedly.

## Testing Strategy

Validation for this revamp should focus on presentation consistency and template behavior.

### Verify
- Brand toggle updates the expected catalog-only visual contexts.
- Sidebar uses the correct filled treatment and `16px` horizontal padding.
- Overview cards remain tidy across different representative showcases.
- Preview scaling keeps content inside the frame without distortion.
- Variant galleries wrap cleanly when symmetry cannot be preserved in one row.
- Component pages render the full standard section order.
- Java and Kotlin snippets appear in the expected place.

## Rollout Notes

The revamp can be implemented incrementally:
1. establish global catalog shell and brand toggle behavior
2. standardize overview layout and component cards
3. standardize component detail page template
4. add preview frame and variant gallery rules
5. backfill component metadata section by section

This order reduces risk and allows the catalog to improve progressively without requiring a full content rewrite in one pass.

## Open Decisions Resolved

- Revamp target: web catalog only.
- Visual direction: hybrid.
- Brand switching model: one global toggle.
- Component grouping on overview: by category.
- Showcase fitting model: standard frame with auto-fit fallback.

## Final Outcome

When complete, the web catalog should feel like a standardized design-system documentation surface: branded, structured, readable, and visually disciplined. It should present the same component inventory more clearly and more attractively while preserving the integrity of the underlying design-system libraries.
