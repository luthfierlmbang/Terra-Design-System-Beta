# TERRA TAB Token Documentation

Target Figma file: **TERRA TAB Design System v.1.0 (Copy)**

Figma file key: `6D11IquaFJQq5tYERVH6JQ`

## Naming Structure

Semantic color tokens use this structure:

```text
color-{ui-element}-{priority}-{state}
```

In Figma Variables, use slash-separated hierarchy:

```text
color/bg/surface/secondary/hover
```

For code syntax and documentation, use flat hyphenated CSS variable names:

```text
--color-bg-surface-secondary-hover
```

## Token Layers

TERRA color tokens should be organized into three collections:

| Collection | Purpose | Example |
| --- | --- | --- |
| `TERRA Primitive` | Raw color values and generated ramps | `orange/500` |
| `TERRA Semantic Color` | Product meaning and UI usage | `color/text/primary/default` |
| `TERRA Component Color` | Component-specific aliases | `color/button/primary/bg` |

Semantic and component tokens should alias primitives. Do not duplicate raw hex values in semantic/component layers.

## Primitive Tokens

These primitives should be created before semantic tokens.

Brand, status, neutral, surface, overlay, and shadow primitives use consistent naming: `primitive/{family}/{family-name-scale}`. Brand and status ramps are expanded from existing main colors so semantic tokens have complete primitive foundations. Existing source colors stay unchanged at their anchor steps.

| Figma Variable | Value | Source / Notes |
| --- | ---: | --- |
| `orange/50` | `#FEF5ED` | Generated from `Primary/Main` |
| `orange/100` | `#FDE8D6` | Generated from `Primary/Main` |
| `orange/200` | `#FBCFA8` | Generated from `Primary/Main` |
| `orange/300` | `#F8B67B` | Generated from `Primary/Main` |
| `orange/400` | `#F69C4D` | Generated from `Primary/Main` |
| `orange/500` | `#F4831F` | Existing `Primary/Main` anchor |
| `orange/600` | `#D2711B` | Generated from `Primary/Main` |
| `orange/700` | `#B15F16` | Generated from `Primary/Main` |
| `orange/800` | `#8F4D12` | Generated from `Primary/Main` |
| `orange/900` | `#6E3B0E` | Generated from `Primary/Main` |
| `teal/50` | `#EBF4F4` | Generated from `Secondary/Main` |
| `teal/100` | `#D9EAEB` | Generated from `Secondary/Main` |
| `teal/200` | `#B5D6D8` | Generated from `Secondary/Main` |
| `teal/300` | `#91C3C5` | Generated from `Secondary/Main` |
| `teal/400` | `#6DAFB2` | Generated from `Secondary/Main` |
| `teal/500` | `#4A9B9F` | Generated from `Secondary/Main` |
| `teal/600` | `#26888C` | Generated from `Secondary/Main` |
| `teal/700` | `#027479` | Existing `Secondary/Main` anchor |
| `teal/800` | `#015458` | Generated from `Secondary/Main` |
| `teal/900` | `#013436` | Generated from `Secondary/Main` |
| `yellow/50` | `#FEF9ED` | Generated from `Warning/Main` |
| `yellow/100` | `#FEF0D5` | Generated from `Warning/Main` |
| `yellow/200` | `#FCE0A6` | Generated from `Warning/Main` |
| `yellow/300` | `#FACF77` | Generated from `Warning/Main` |
| `yellow/400` | `#F9BF48` | Generated from `Warning/Main` |
| `yellow/500` | `#F7AE19` | Existing `Warning/Main` anchor |
| `yellow/600` | `#D59616` | Generated from `Warning/Main` |
| `yellow/700` | `#B37E12` | Generated from `Warning/Main` |
| `yellow/800` | `#91660F` | Generated from `Warning/Main` |
| `yellow/900` | `#6F4E0B` | Generated from `Warning/Main` |
| `red/50` | `#FEEFEF` | Generated from `Danger/Main` |
| `red/100` | `#FCDADA` | Generated from `Danger/Main` |
| `red/200` | `#F9B1B1` | Generated from `Danger/Main` |
| `red/300` | `#F68888` | Generated from `Danger/Main` |
| `red/400` | `#F25F5F` | Generated from `Danger/Main` |
| `red/500` | `#EF3636` | Existing `Danger/Main` anchor |
| `red/600` | `#CE2F2F` | Generated from `Danger/Main` |
| `red/700` | `#AD2727` | Generated from `Danger/Main` |
| `red/800` | `#8C2020` | Generated from `Danger/Main` |
| `red/900` | `#6C1818` | Generated from `Danger/Main` |
| `neutral/10` | `#FFFFFF` | Existing `Neutral/10` |
| `neutral/20` | `#EBF0F4` | Existing `Neutral/20` |
| `neutral/30` | `#EDEDED` | Existing `Neutral/30`, `Background Color/Behind Card` |
| `neutral/40` | `#DADADA` | Existing `Neutral/40` |
| `neutral/50` | `#CCCCCC` | Existing `Neutral/50` |
| `neutral/60` | `#999999` | Existing `Neutral/60` |
| `neutral/70` | `#7A7D7F` | Existing `Neutral/70` |
| `neutral/80` | `#555555` | Existing `Neutral/80` |
| `neutral/90` | `#333333` | Existing `Neutral/90` |
| `neutral/100` | `#000000` | Existing `Neutral/100` |
| `blue/50` | `#F5FAFE` | Existing style guide background |
| `blue/100` | `#EBF5FD` | Generated from style guide background |
| `blue/200` | `#D8EBFA` | Generated from style guide background |
| `blue/300` | `#BFE0F6` | Generated from style guide background |
| `pink/50` | `#FFF8F8` | Copy file thumbnail background |
| `pink/100` | `#FDEDED` | Generated from thumbnail background |
| `pink/200` | `#FADADA` | Generated from thumbnail background |
| `black/alpha-50` | `rgba(0,0,0,0.5)` | Existing `Background Color/Overlay Color` |
| `black/alpha-12` | `rgba(0,0,0,0.12)` | Existing `Shadow/Card` opacity |

If an existing component uses an uncovered color, create a new primitive first and document it here before aliasing it semantically.

## Semantic Color Tokens

| Figma Variable | CSS Variable | Alias Target | Usage |
| --- | --- | --- | --- |
| `color/bg/default` | `--color-bg-default` | `neutral/10` | Default screen/background |
| `color/bg/canvas/default` | `--color-bg-canvas-default` | `blue/50` | Style guide canvas/background |
| `color/bg/thumbnail/default` | `--color-bg-thumbnail-default` | `pink/50` | Thumbnail/cover background |
| `color/bg/surface/primary/default` | `--color-bg-surface-primary-default` | `neutral/10` | Primary surface/card |
| `color/bg/surface/secondary/default` | `--color-bg-surface-secondary-default` | `neutral/30` | Secondary surface |
| `color/bg/surface/secondary/hover` | `--color-bg-surface-secondary-hover` | `neutral/20` | Secondary surface hover |
| `color/bg/fill/primary/default` | `--color-bg-fill-primary-default` | `orange/500` | Primary filled UI |
| `color/bg/fill/primary/active` | `--color-bg-fill-primary-active` | `orange/500` | Primary filled active |
| `color/bg/fill/secondary/default` | `--color-bg-fill-secondary-default` | `teal/700` | Secondary filled UI |
| `color/bg/fill/danger/default` | `--color-bg-fill-danger-default` | `red/500` | Destructive filled UI |
| `color/bg/fill/warning/default` | `--color-bg-fill-warning-default` | `yellow/500` | Warning filled UI |
| `color/bg/disabled/default` | `--color-bg-disabled-default` | `neutral/50` | Disabled fill |
| `color/bg/overlay/default` | `--color-bg-overlay-default` | `black/alpha-50` | Modal/scrim overlay |
| `color/text/primary/default` | `--color-text-primary-default` | `neutral/90` | Main text |
| `color/text/primary/disabled` | `--color-text-primary-disabled` | `neutral/50` | Disabled text |
| `color/text/secondary/default` | `--color-text-secondary-default` | `neutral/80` | Secondary text |
| `color/text/inverse/default` | `--color-text-inverse-default` | `neutral/10` | Text on dark/filled background |
| `color/text/action/primary/default` | `--color-text-action-primary-default` | `teal/700` | Text button/link primary action |
| `color/text/action/secondary/default` | `--color-text-action-secondary-default` | `orange/500` | Secondary text action |
| `color/text/action/danger/default` | `--color-text-action-danger-default` | `red/500` | Danger text action |
| `color/border/default` | `--color-border-default` | `neutral/30` | Default divider/border |
| `color/border/secondary/default` | `--color-border-secondary-default` | `neutral/50` | Secondary border |
| `color/border/tertiary/default` | `--color-border-tertiary-default` | `neutral/40` | Tertiary border |
| `color/border/action/primary/default` | `--color-border-action-primary-default` | `teal/700` | Primary outlined border |
| `color/border/action/secondary/default` | `--color-border-action-secondary-default` | `orange/500` | Secondary outlined border |
| `color/border/disabled/default` | `--color-border-disabled-default` | `neutral/50` | Disabled border |
| `color/icon/primary/default` | `--color-icon-primary-default` | `neutral/90` | Primary icon |
| `color/icon/secondary/default` | `--color-icon-secondary-default` | `neutral/80` | Secondary icon |
| `color/icon/secondary/hover` | `--color-icon-secondary-hover` | `teal/700` | Secondary icon hover/action |
| `color/icon/action/primary/default` | `--color-icon-action-primary-default` | `teal/700` | Primary action icon |
| `color/icon/action/secondary/default` | `--color-icon-action-secondary-default` | `orange/500` | Secondary action icon |
| `color/icon/action/danger/default` | `--color-icon-action-danger-default` | `red/500` | Danger action icon |
| `color/icon/inverse/default` | `--color-icon-inverse-default` | `neutral/10` | Icon on dark/filled background |
| `color/icon/disabled/default` | `--color-icon-disabled-default` | `neutral/50` | Disabled icon |

## Component Color Tokens

Component tokens alias semantic tokens. These make component binding stable even if semantic values change later.

| Component Token | CSS Variable | Alias Target | Component |
| --- | --- | --- | --- |
| `color/button/primary/bg/default` | `--color-button-primary-bg-default` | `color/bg/fill/secondary/default` | Button primary background |
| `color/button/primary/text/default` | `--color-button-primary-text-default` | `color/text/inverse/default` | Button primary label |
| `color/button/secondary/bg/default` | `--color-button-secondary-bg-default` | `color/bg/fill/primary/default` | Button secondary background |
| `color/button/secondary/text/default` | `--color-button-secondary-text-default` | `color/text/inverse/default` | Button secondary label |
| `color/button/outlined-primary/border/default` | `--color-button-outlined-primary-border-default` | `color/border/action/primary/default` | Outlined primary border |
| `color/button/outlined-primary/text/default` | `--color-button-outlined-primary-text-default` | `color/text/action/primary/default` | Outlined primary label |
| `color/button/outlined-secondary/border/default` | `--color-button-outlined-secondary-border-default` | `color/border/action/secondary/default` | Outlined secondary border |
| `color/button/outlined-secondary/text/default` | `--color-button-outlined-secondary-text-default` | `color/text/action/secondary/default` | Outlined secondary label |
| `color/button/danger/bg/default` | `--color-button-danger-bg-default` | `color/bg/fill/danger/default` | Danger button background |
| `color/button/danger/text/default` | `--color-button-danger-text-default` | `color/text/inverse/default` | Danger button label |
| `color/button/disabled/bg/default` | `--color-button-disabled-bg-default` | `color/bg/disabled/default` | Disabled button background |
| `color/button/disabled/text/default` | `--color-button-disabled-text-default` | `color/text/inverse/default` | Current disabled filled label |
| `color/button/text-button/text/default` | `--color-button-text-button-text-default` | `color/text/action/primary/default` | Text button label |
| `color/button/text-button/text/disabled` | `--color-button-text-button-text-disabled` | `color/text/primary/disabled` | Disabled text button label |
| `color/fab/sticky/bg/default` | `--color-fab-sticky-bg-default` | `color/bg/surface/primary/default` | Sticky FAB container |
| `color/overlay/scrim/default` | `--color-overlay-scrim-default` | `color/bg/overlay/default` | Overlay component |
| `color/page-identifier/text/default` | `--color-page-identifier-text-default` | `color/text/inverse/default` | Page identifier label |

## Figma Binding Targets

Priority components in the Copy file:

| Component | Node ID | Binding Work |
| --- | --- | --- |
| `Button/Button` | `7:1081` | Bind all background, text, stroke colors to component tokens |
| `FAB Button/Sticky` | `120:1170` | Bind container background and nested button instances |
| `Overlay` | `97:3080`, `982:14` | Bind scrim fill to overlay token |
| `Page Identifier` | `505:35` | Bind text fill to page identifier token |
| All local components | `COMPONENT`, `COMPONENT_SET`, descendants | Global sweep binds matching known palette colors across the DS |
| Icon sets | `568:*`, `565:*` | Included in global sweep when icon/vector colors match known palette colors |

## Accessibility Notes

Initial contrast checks from the existing palette:

| Color | Against White | Note |
| --- | ---: | --- |
| `#027479` | `5.56:1` | Passes normal text contrast |
| `#F4831F` | `2.59:1` | Fails normal text contrast with white |
| `#EF3636` | `4.00:1` | Below AA for normal text |
| `#CCCCCC` | `1.61:1` | Disabled only; do not use for required readable text |

Do not change existing visual values during the first migration pass unless a contrast correction is explicitly approved.

## Migration Rules

- Create primitives before semantic tokens.
- Semantic/component tokens must alias other variables instead of storing duplicate raw values.
- Keep existing styles during the first migration pass.
- Bind priority components first: Button, FAB, Overlay, Page Identifier.
- Sweep all local components afterward and bind fills/strokes that exactly match known primitive colors.
- Rename component sets only after confirming no downstream instance usage will break.
- Document any newly discovered primitive color before using it in semantic/component tokens.

## QA Checklist

- [ ] All primitive variables exist.
- [ ] All semantic variables alias primitives.
- [ ] All component variables alias semantic variables.
- [ ] Figma code syntax uses `--color-...` names.
- [ ] Button variants are bound to component tokens.
- [ ] Overlay uses `color/overlay/scrim/default`.
- [ ] Page Identifier no longer uses raw `#FFFFFF`.
- [ ] All local components with matching known palette colors are bound to variables.
- [ ] Documentation table matches Figma variable names.
