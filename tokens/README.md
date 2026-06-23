# TERRA Tokens

This folder contains the token source of truth and generated token outputs.

## Structure
- `source/primitive/` — raw primitives
- `source/semantic/` — semantic aliases
- `source/component/` — component-level aliases
- `dist/css/` — generated CSS variables
- `dist/json/` — generated JSON artifacts
- `dist/android/` — generated Android resources
- `mappings/` — platform mapping files

## Current pipeline baseline
For now, the repository uses a lightweight static token pipeline:
- source JSON files under `tokens/source/`
- generated CSS variables under `tokens/dist/css/variables.css`
- aggregated web-friendly JSON under `tokens/dist/json/tokens.json`

This can later be replaced with an automated generation pipeline.

## Web spacing baseline
- Web spacing primitives must stay on a 4px scale.
- Current approved primitive spacing values: `4, 8, 12, 16, 20, 24, 32, 40, 48, 64`.
- `tokens/source/primitive/spacing.json` is the source of truth for web spacing values.
- Generated web outputs must match the source scale exactly; undocumented values such as `6` or `14` should not appear in CSS variables.
- Use spacing tokens for layout rhythm and component spacing; avoid one-off raw px values for standard UI spacing.

## Web typography baseline
- Web catalog typography uses `Jenius Sans` as the primary base family.
- Primitive typography values live in `tokens/source/primitive/typography.json`.
- Semantic text roles for the web catalog live in `tokens/source/semantic/typography.json`.
- Typography should follow the same layering rule as color: primitive values first, semantic roles second, component-specific usage last when needed.
- Prefer CSS variables and semantic text roles over ad-hoc font declarations in catalog and shared UI surfaces.
