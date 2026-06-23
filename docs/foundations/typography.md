# Typography

Typography foundation for the TERRA design system, extracted from Figma node `3:10` in **TERRA TAB Design System v.1.0 (Copy)**.

## Typeface

### Primary brand font
- **Font family:** `Jenius Sans`
- **Fallback stack (web):** `'Jenius Sans', Inter, -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif`
- **Usage:** all product-facing headings, body text, caption text, and CTA labels in the current TERRA TAB foundation.

## Font asset status

`Jenius Sans` is referenced in the design system and already used in the web catalog styles, but no font source files were found in this repository.

Not found in repo:
- `.ttf`
- `.otf`
- `.woff`
- `.woff2`

### Recommendation
Provide `Jenius Sans` as a licensed brand asset outside the token repo, then load it in platform-specific layers:

- **Web:** via `@font-face` in the app or shared UI package
- **Android:** via `res/font/` or downloadable font strategy
- **Figma:** keep the family name exactly `Jenius Sans` for parity

Example web registration:

```css
@font-face {
  font-family: 'Jenius Sans';
  src: url('/fonts/jenius-sans-regular.woff2') format('woff2');
  font-weight: 400;
  font-style: normal;
  font-display: swap;
}

@font-face {
  font-family: 'Jenius Sans';
  src: url('/fonts/jenius-sans-bold.woff2') format('woff2');
  font-weight: 700;
  font-style: normal;
  font-display: swap;
}
```

## Text style set

All text styles below are extracted directly from Figma.

| Token | Family | Weight | Size | Line Height | Letter Spacing | Color | Notes |
| --- | --- | ---: | ---: | ---: | --- | --- | --- |
| `text/header/xl` | Jenius Sans | 700 | 28px | 32px | 0.003em | `#333333` | Main large heading |
| `text/header/l` | Jenius Sans | 700 | 24px | 30px | 0.0025em | `#333333` | Section heading |
| `text/header/m` | Jenius Sans | 700 | 20px | 26px | 0.0025em | `#333333` | Medium heading |
| `text/header/s` | Jenius Sans | 700 | 18px | 22px | 0.002em | `#333333` | Small heading |
| `text/body/md/regular` | Jenius Sans | 400 | 16px | 22px | `normal` | `#333333` | Default body copy |
| `text/body/md/bold` | Jenius Sans | 700 | 16px | 22px | `normal` | `#333333` | Emphasized body copy |
| `text/body/sm/regular` | Jenius Sans | 400 | 14px | 20px | `normal` | `#333333` | Dense supporting copy |
| `text/body/sm/bold` | Jenius Sans | 700 | 14px | 22px | `normal` | `#333333` | Emphasized small copy |
| `text/caption/dark` | Jenius Sans | 400 | 12px | 20px | `normal` | `#CCCCCC` | Secondary or low-emphasis caption |
| `text/cta/md` | Jenius Sans | 700 | 16px | 24px | 0.0032em | `#FFFFFF` | Medium CTA label on filled button |
| `text/cta/sm` | Jenius Sans | 700 | 14px | 24px | 0.0028em | `#FFFFFF` | Small CTA label on filled button |

## Figma style mapping

| Figma Style | Suggested token |
| --- | --- |
| `Header/XL` | `text/header/xl` |
| `Header/L` | `text/header/l` |
| `Header/M` | `text/header/m` |
| `Header/S` | `text/header/s` |
| `Medium Body Text/Regular` | `text/body/md/regular` |
| `Medium Body Text/Bold` | `text/body/md/bold` |
| `Small Body Text/Regular` | `text/body/sm/regular` |
| `Small Body Text/Bold` | `text/body/sm/bold` |
| `Caption Text/Dark` | `text/caption/dark` |
| `CTA Button/Medium` | `text/cta/md` |
| `CTA Button/Small` | `text/cta/sm` |

## CSS custom property reference

```css
--text-header-xl-font-family: 'Jenius Sans';
--text-header-xl-font-weight: 700;
--text-header-xl-font-size: 28px;
--text-header-xl-line-height: 32px;
--text-header-xl-letter-spacing: 0.003em;
--text-header-xl-color: #333333;

--text-header-l-font-family: 'Jenius Sans';
--text-header-l-font-weight: 700;
--text-header-l-font-size: 24px;
--text-header-l-line-height: 30px;
--text-header-l-letter-spacing: 0.0025em;
--text-header-l-color: #333333;

--text-header-m-font-family: 'Jenius Sans';
--text-header-m-font-weight: 700;
--text-header-m-font-size: 20px;
--text-header-m-line-height: 26px;
--text-header-m-letter-spacing: 0.0025em;
--text-header-m-color: #333333;

--text-header-s-font-family: 'Jenius Sans';
--text-header-s-font-weight: 700;
--text-header-s-font-size: 18px;
--text-header-s-line-height: 22px;
--text-header-s-letter-spacing: 0.002em;
--text-header-s-color: #333333;

--text-body-md-regular-font-family: 'Jenius Sans';
--text-body-md-regular-font-weight: 400;
--text-body-md-regular-font-size: 16px;
--text-body-md-regular-line-height: 22px;
--text-body-md-regular-color: #333333;

--text-body-md-bold-font-family: 'Jenius Sans';
--text-body-md-bold-font-weight: 700;
--text-body-md-bold-font-size: 16px;
--text-body-md-bold-line-height: 22px;
--text-body-md-bold-color: #333333;

--text-body-sm-regular-font-family: 'Jenius Sans';
--text-body-sm-regular-font-weight: 400;
--text-body-sm-regular-font-size: 14px;
--text-body-sm-regular-line-height: 20px;
--text-body-sm-regular-color: #333333;

--text-body-sm-bold-font-family: 'Jenius Sans';
--text-body-sm-bold-font-weight: 700;
--text-body-sm-bold-font-size: 14px;
--text-body-sm-bold-line-height: 22px;
--text-body-sm-bold-color: #333333;

--text-caption-dark-font-family: 'Jenius Sans';
--text-caption-dark-font-weight: 400;
--text-caption-dark-font-size: 12px;
--text-caption-dark-line-height: 20px;
--text-caption-dark-color: #CCCCCC;

--text-cta-md-font-family: 'Jenius Sans';
--text-cta-md-font-weight: 700;
--text-cta-md-font-size: 16px;
--text-cta-md-line-height: 24px;
--text-cta-md-letter-spacing: 0.0032em;
--text-cta-md-color: #FFFFFF;

--text-cta-sm-font-family: 'Jenius Sans';
--text-cta-sm-font-weight: 700;
--text-cta-sm-font-size: 14px;
--text-cta-sm-line-height: 24px;
--text-cta-sm-letter-spacing: 0.0028em;
--text-cta-sm-color: #FFFFFF;
```

## Usage guidance

### Do
- Use `Jenius Sans` as the primary family for all product text that follows the current TERRA TAB visual language.
- Prefer semantic text tokens over ad-hoc font declarations in components.
- Keep CTA labels bold and high-contrast on filled surfaces.
- Use heading styles only for hierarchy, not just for visual emphasis.

### Don't
- Do not mix `Inter` and `Jenius Sans` inside the same product UI pattern unless there is a documented exception.
- Do not apply `Caption Text/Dark` to content that must remain highly readable.
- Do not create one-off font sizes outside the approved scale without updating the foundation docs first.

## Accessibility notes

- `Caption Text/Dark` uses `#CCCCCC` and should be treated as low-emphasis text only.
- White CTA labels assume placement on strong filled backgrounds, not light surfaces.
- Bold body styles should be reserved for emphasis, labels, or short content runs to preserve hierarchy.

## Source

- Figma file key: `6D11IquaFJQq5tYERVH6JQ`
- Node: `3:10`
- Page: `Typography`
