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
