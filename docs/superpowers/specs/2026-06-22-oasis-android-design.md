# Oasis Design System — Android Native Port

**Status:** Design — approved 2026-06-22
**Owner:** Android team
**Source of truth:** `apps/web-catalog/src/components/oasis/` (Oasis web, React + CSS)
**Target repo path:** `android/` (Gradle root, alongside existing TERRA tablet modules)

---

## 1. Background

TERRA is a multi-form-factor design system. It already ships **TERRA Tablet** — a native Android library (Kotlin + XML) for tablet apps, organized as three Gradle modules under `android/`:

- `terra-tokens` — colors, dimens, typography
- `terra-ui` — 23 custom view components
- `catalog` — preview app

Oasis is the **mobile (phone) variant** of the design system. Today it exists only as **web components** (React + TypeScript + CSS) under `apps/web-catalog/src/components/oasis/`. There is no Android source for it.

This spec defines the port: ship an **Oasis Android library** that mirrors the Oasis web catalog's 24 documented components as native Android custom views, in a **fully federated Gradle module** that does not touch the existing TERRA tablet code.

## 2. Goals

1. Provide native Android equivalents for the 24 Oasis web components in `apps/web-catalog/src/content/oasis-registry.tsx`.
2. Match Oasis web visuals **1:1** (colors, sizing, typography weight).
3. Keep TERRA tablet and Oasis mobile **fully independent** — no shared modules, no shared resources, no shared Kotlin packages.
4. Follow the **same per-component recipe** TERRA tablet already uses, so contributors familiar with TERRA have zero learning curve.
5. Ship a **catalog preview app** that mirrors the web catalog's role.

## 3. Non-Goals (explicit out-of-scope for v1)

| Out of scope | Reason |
|---|---|
| `OasisLoader`, `OasisOtp`, `OasisPageState` | Not in registry — full-screen specimens, not atomic components |
| Dark theme | Oasis web has no dark variant yet |
| Custom Oasis font family | Web spec does not declare a font |
| Motion / animation tokens | Web spec is static |
| Unit tests / instrumentation tests | TERRA tablet follows manual validation; same philosophy |
| Maven publishing | v1 ships as local Gradle modules; publish later if needed |
| Accessibility audit | Default Android lint only for v1; full a11y pass in a later round |

## 4. Architecture

### 4.1 Module layout

Three new Gradle modules live next to the existing TERRA tablet modules, under the same Gradle root at `android/`:

```
android/
├── settings.gradle.kts                  (modified: includes 3 new modules)
├── build.gradle.kts                     (root, mostly unchanged)
├── gradle.properties                    (modified)
│
├── oasis-designsystem-tokens/           FOUNDATION — colors, dimens, typography
├── oasis-designsystem/                  COMPONENT LIBRARY — 24 custom views
└── oasis-catalog/                       PREVIEW APP — entry list + per-component preview
```

**Existing TERRA tablet modules are not modified.** Zero code or resource in `terra-tokens`, `terra-ui`, or `catalog` changes.

### 4.2 Dependency graph

```
oasis-designsystem-tokens  →  oasis-designsystem  →  oasis-catalog
   (library)                   (library)              (application)
```

### 4.3 Namespace per module

| Module | Gradle `namespace` | Generated R class |
|---|---|---|
| `oasis-designsystem-tokens` | `com.oasis.designsystem.tokens` | `com.oasis.designsystem.tokens.R` |
| `oasis-designsystem` | `com.oasis.designsystem` | `com.oasis.designsystem.R` |
| `oasis-catalog` | `com.oasis.designsystem.catalog` | `com.oasis.designsystem.catalog.R` |

All components live under Kotlin package `com.oasis.designsystem.<component>` — distinct from TERRA tablet's `com.terra.ds.<component>`.

### 4.4 Why federated (not sub-packages inside TERRA)

- TERRA tablet and Oasis mobile have different form factors, layouts, and audiences
- Federated modules can be published to Maven independently if needed later
- Catalog app stays single-purpose (no mixing tablet + mobile in one preview)
- Federation enforces the architectural rule "one design system = one module" — the same rule TERRA tablet already follows

---

## 5. Component pattern (per-component recipe)

Every component follows the **same recipe** as TERRA tablet. No per-component improvisation.

### 5.1 File layout per component

```
oasis-designsystem/src/main/
├── java/com/oasis/designsystem/<component>/
│   ├── Oasis<Name>View.kt              # custom view, extends stock Android widget
│   ├── Oasis<Name><Enum>.kt            # variant/size/state enums (when >1 variant)
│   └── package-info.java
└── res/
    ├── values/
    │   ├── attrs_<component>.xml       # declare-styleable Oasis<Name>View
    │   └── styles_<component>.xml      # TextAppearance.Oasis.* and Style.Oasis.*
    ├── drawable/
    │   └── oasis_bg_<component>.xml    # selectors (when background varies by state)
    └── layout/
        └── oasis_view_<component>.xml  # ONLY for composite anatomy
```

### 5.2 Custom view pattern

```kotlin
class OasisButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.buttonStyle
) : AppCompatButton(context, attrs, defStyleAttr) {

    private var variant: OasisButtonVariant = OasisButtonVariant.PRIMARY
    private var size: OasisButtonSize = OasisButtonSize.MEDIUM

    init {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.OasisButtonView).apply {
                variant = OasisButtonVariant.from(getInt(R.styleable.OasisButtonView_oasisButtonVariant, 0))
                size = OasisButtonSize.from(getInt(R.styleable.OasisButtonView_oasisButtonSize, 1))
                recycle()
            }
        }
        applyStyle()
    }

    fun setVariant(value: OasisButtonVariant) { variant = value; applyStyle() }
    fun setSize(value: OasisButtonSize) { size = value; applyStyle() }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        applyStyle()
    }

    private fun applyStyle() { /* token refs + text appearance */ }
}
```

### 5.3 Naming conventions

| Surface | Convention | Example |
|---|---|---|
| Kotlin package | `com.oasis.designsystem.<component>` | `com.oasis.designsystem.button` |
| View class | `Oasis<Name>View` | `OasisButtonView` |
| Variant enum | `Oasis<Name><Type>` | `OasisButtonVariant`, `OasisButtonSize` |
| Listener interface | `On<Event>Listener` nested or top-level | `OnValueChangeListener` |
| XML attribute | `oasis<ComponentName><Prop>` | `oasisButtonVariant`, `oasisTextFieldState` |
| Drawable resource | `oasis_bg_<component>` | `oasis_bg_button_primary.xml` |
| Style | `Style.Oasis.<Component>.<Variant>` | `Style.Oasis.Button.Primary` |
| Text appearance | `TextAppearance.Oasis.<Role>` | `TextAppearance.Oasis.Button.Large` |

### 5.4 State handling

States are exposed as **attrs** (not separate properties), aligning with Android's `state_*` idiom:

```kotlin
enum class OasisTextFieldState { DEFAULT, ACTIVE, FILLED, ERROR, DISABLED }
```

```xml
<com.oasis.designsystem.textfield.OasisTextFieldView
    app:oasisTextFieldState="error"
    android:hint="Email" />
```

### 5.5 Asset handling

**Icons** (64 variants) — convert SVG → Vector Drawable XML, stored at `oasis-designsystem/src/main/res/drawable/oasis_ic_<variant>.xml`. Naming mirrors the `OasisIconVariant` enum.

**Illustrations** (14 variants) — copy raster PNG from `apps/web-catalog/public/figma-card-assets/` (the public folder is the build source; `dist/` is its build output). Stored at `oasis-designsystem/src/main/res/drawable/oasis_illustration_<category>_<name>.png`.

### 5.6 Listener convention

- `OnClick`: standard `View.OnClickListener`
- Text change: `addOnTextChangedListener` wrapper around `TextWatcher`
- Component-specific events (qty change, tab change, dropdown select): nested or top-level functional interface in the component's package

---

## 6. Token system (`oasis-designsystem-tokens`)

### 6.1 File structure

```
oasis-designsystem-tokens/src/main/res/
├── values/
│   ├── oasis_colors_primitives.xml     # raw palette, hex literals
│   ├── oasis_colors_semantic.xml       # semantic aliases
│   ├── oasis_dimens_spacing.xml        # 4dp scale
│   ├── oasis_dimens_radius.xml         # corner radius scale
│   ├── oasis_component_dimens.xml      # per-component fixed dims
│   ├── oasis_typography.xml            # TextAppearance.Oasis.*
│   └── oasis_themes.xml                # Theme.Oasis, Theme.Oasis.Base
└── font/                                # (empty in v1 — no custom font)
```

### 6.2 Color tokens

**Primitives** — 1:1 with Oasis web hex literals:

| Token | Hex | Role |
|---|---|---|
| `oasis_orange_500` | `#F58220` | Primary action |
| `oasis_orange_600` | `#D86A0E` | Pressed state |
| `oasis_yellow_500` | `#F7BE26` | Accent |
| `oasis_teal_500` | `#186D6D` | Brand secondary |
| `oasis_neutral_900` | `#1A1A1A` | Text primary |
| `oasis_neutral_700` | `#4C4C4C` | Text secondary |
| `oasis_neutral_500` | `#747474` | Border strong |
| `oasis_neutral_300` | `#C5B8B8` | Text disabled |
| `oasis_neutral_200` | `#E5D9D9` | Border default |
| `oasis_neutral_100` | `#F2EEEE` | Surface disabled |
| `oasis_neutral_50` | `#FAF8F8` | Surface subtle |
| `oasis_white` | `#FFFFFF` | Inverse text, surface default |
| `oasis_black` | `#000000` | (reserved) |
| `oasis_success_500` | `#2E7D32` | Success feedback |
| `oasis_warning_500` | `#ED6C02` | Warning feedback |
| `oasis_danger_500` | `#D32F2F` | Danger feedback |
| `oasis_info_500` | `#0288D1` | Info feedback |

**Semantic** — components consume these, not primitives:

| Semantic token | Maps to |
|---|---|
| `oasis_primary` | `oasis_orange_500` |
| `oasis_primary_pressed` | `oasis_orange_600` |
| `oasis_accent` | `oasis_yellow_500` |
| `oasis_brand_secondary` | `oasis_teal_500` |
| `oasis_text_primary` / `secondary` / `disabled` / `inverse` / `on_primary` | `oasis_neutral_900/700/300/white/white` |
| `oasis_surface_default` / `subtle` / `disabled` | `oasis_white / neutral_50 / neutral_100` |
| `oasis_border_default` / `strong` / `focus` / `error` | `oasis_neutral_200 / neutral_500 / primary / danger_500` |
| `oasis_feedback_success` / `warning` / `danger` / `info` | `oasis_<status>_500` |

### 6.3 Spacing tokens (4dp scale)

`oasis_spacing_0` (0dp), `_4` (4dp), `_8` (8dp), `_12` (12dp), `_16` (16dp), `_20` (20dp), `_24` (24dp), `_32` (32dp), `_48` (48dp).

### 6.4 Radius tokens

`oasis_radius_none` (0dp), `_xs` (4dp), `_sm` (8dp), `_md` (12dp), `_pill` (999dp).

### 6.5 Component dimensions

Per-component fixed dims live in `oasis_component_dimens.xml`:

- Button: `oasis_button_height_large/medium/small` (52/44/36dp), `oasis_button_padding_horizontal` (20dp)
- Input: `oasis_input_height` (52dp), `oasis_input_padding_horizontal` (16dp), `oasis_input_border_width` (1dp), `oasis_input_border_width_focus` (2dp)
- Icon: `oasis_icon_size_16/20/24/32` (16/20/24/32dp)
- Avatar: `oasis_avatar_size_64/96/128` (64/96/128dp)
- Toggle: `oasis_toggle_width` (52dp), `oasis_toggle_height` (32dp)
- BottomNav: `oasis_bottom_nav_height` (64dp), `oasis_bottom_nav_fab_size` (56dp)
- Modal: `oasis_modal_max_width` (360dp), `oasis_modal_padding` (20dp)
- Header: `oasis_header_height` (56dp), `oasis_header_status_bar_height` (24dp)
- IndicatorGroup: `oasis_indicator_size` (12dp), `oasis_indicator_gap` (8dp)

### 6.6 Typography

TextAppearance styles in `oasis_typography.xml`:

- `TextAppearance.Oasis.DisplayLarge` — 32sp, medium weight
- `TextAppearance.Oasis.Title` — 18sp, medium weight
- `TextAppearance.Oasis.Body` — 14sp, regular
- `TextAppearance.Oasis.BodyBold` — 14sp, medium weight
- `TextAppearance.Oasis.Label` — 12sp, regular
- `TextAppearance.Oasis.NumericValue` — 24sp, bold (used by `OasisNumericInput`)
- `TextAppearance.Oasis.Button.Large/Medium/Small` — 16/14/12sp, medium weight

### 6.7 Theme

`Theme.Oasis.Base` extends `Theme.Material3.Light.NoActionBar`, wires `colorPrimary` → `oasis_primary`, status bar to `oasis_primary`, light status bar = false. `Theme.Oasis` inherits Base.

---

## 7. Component scope and ordering

### 7.1 In scope (24 components)

The 24 components registered in `apps/web-catalog/src/content/oasis-registry.tsx`.

### 7.2 Out of scope for v1 (3 components exist in web but not in registry)

| Component | Why deferred |
|---|---|
| `OasisLoader` | Spinner; not in registry. Web file exists but undocumented in catalog. |
| `OasisOtp` | Full OTP screen specimen (status bar + image + 6-digit input + CTA). Not atomic — it's a screen composition. Would require screen-level pattern, not custom view. |
| `OasisPageState` | Full empty-state page specimen. Same reasoning as `OasisOtp`. |

If these are later needed, they go into a separate spec as **screen specimens** (not custom views).

### 7.3 Implementation phases

#### Phase 0 — Foundation
- Build skeleton of all 3 modules (empty `package-info.java`, manifest, `build.gradle.kts`)
- Wire `settings.gradle.kts`, root `build.gradle.kts`, `gradle.properties`
- `oasis-catalog` shows empty list, builds, launches
- **Gate**: catalog app launches; `R` classes resolve in all 3 modules

#### Phase 1 — Foundation components (5)
`OasisIcon`, `OasisIllustration`, `OasisButton`, `OasisAvatar`, `OasisTags`
- **Gate**: catalog entry list shows 5 components; each renders without crash; Button primary/secondary/tertiary × large/medium/small switch

#### Phase 2 — Forms & Selection (11)
`OasisTextField`, `OasisNumericInput`, `OasisTextArea`, `OasisSearchBar`, `OasisToggle`, `OasisRadioButton`, `OasisCheckbox`, `OasisChips`, `OasisDropdown`, `OasisQtyInput`, `OasisTable`
- **Gate**: 16 catalog entries total; state cycle `default → active → filled → error → disabled` works on TextField; Dropdown switches menu/checkbox/radio modes

#### Phase 3 — Feedback & Navigation (7)
`OasisToast`, `OasisAlert`, `OasisProgressBar`, `OasisTabbing`, `OasisIndicatorGroup`, `OasisBottomNav`, `OasisHeader`
- **Gate**: 23 catalog entries total; Alert renders with Icon + Button; BottomNav `fab/four/five` modes switch; Header composite renders

#### Phase 4 — Overlay & finishing (2 + catalog)
`OasisModal`, `OasisFileUpload`, plus final catalog wiring
- **Gate**: 24 catalog entries total; Modal open/close cycle; FileUpload trigger button; catalog scrolls all 24 entries; lint passes zero errors

### 7.4 Phase → dependency matrix

| Component | Phase | Depends on |
|---|---|---|
| OasisIcon | 1 | — |
| OasisIllustration | 1 | — |
| OasisButton | 1 | — |
| OasisAvatar | 1 | — |
| OasisTags | 1 | — |
| OasisTextField | 2 | — |
| OasisNumericInput | 2 | — |
| OasisTextArea | 2 | — |
| OasisSearchBar | 2 | OasisIcon |
| OasisToggle | 2 | — |
| OasisRadioButton | 2 | — |
| OasisCheckbox | 2 | — |
| OasisChips | 2 | — |
| OasisDropdown | 2 | — |
| OasisQtyInput | 2 | — |
| OasisTable | 2 | — |
| OasisToast | 3 | — |
| OasisAlert | 3 | OasisIcon, OasisButton |
| OasisProgressBar | 3 | — |
| OasisTabbing | 3 | — |
| OasisIndicatorGroup | 3 | — |
| OasisBottomNav | 3 | OasisIcon |
| OasisHeader | 3 | OasisIcon |
| OasisModal | 4 | OasisButton, OasisIllustration |
| OasisFileUpload | 4 | OasisIcon |

---

## 8. Catalog app (`oasis-catalog`)

### 8.1 Purpose

Preview app for designers and Android developers to validate each component's look and behavior in a real Android environment, without integrating into an external app.

### 8.2 Structure

```
oasis-catalog/src/main/
├── AndroidManifest.xml                          (application, launcher activity = MainActivity)
├── res/
│   ├── layout/
│   │   ├── activity_oasis_catalog_list.xml      (RecyclerView entry list)
│   │   ├── item_oasis_catalog_entry.xml         (row: icon + name + status badge)
│   │   └── oasis_catalog_<component>.xml        (24 layouts, one per component)
│   ├── values/strings.xml                       (component names, group labels)
│   └── mipmap-*/                                (launcher icon)
└── java/com/oasis/designsystem/catalog/
    ├── MainActivity.kt                          (renders entry list from registry)
    ├── PreviewActivity.kt                       (inflates layout from extra)
    ├── home/
    ├── button/                                  (one package per component if adapter glue needed)
    ├── icon/
    ├── ...
    └── registry/
        ├── OasisCatalogEntry.kt                 (data: slug, title, group, layoutResId, status)
        └── OasisCatalogRegistry.kt              (manual list of 24 entries)
```

### 8.3 Behavior

- MainActivity shows all 24 entries in a scrollable list, grouped by `Actions / Forms / Selection / Feedback / Navigation / Overlay / Surfaces / Foundations`
- Tap → `PreviewActivity` with `layoutResId` extra; PreviewActivity just inflates the layout
- Each entry shows a status badge: `MISSING / PARTIAL / DONE / BLOCKED`
- Status is hardcoded in the registry (matches current implementation state) — not auto-detected

---

## 9. Testing & validation

TERRA tablet does not ship unit tests. Oasis Android follows the same philosophy for v1: **build + manual visual validation**.

### 9.1 Validation pyramid

| Level | Tool | Scope |
|---|---|---|
| Build | Gradle | Every module — gating |
| Lint | Android Studio default | Zero errors (warnings allowed) |
| Smoke inflate | Manual / Layout Inspector | Each component inflates without crash |
| Visual check | Manual screenshot | Each variant matches Oasis web |
| Interaction | Manual test script | Click handlers, state transitions, listener fires |

### 9.2 Per-phase gates

See section 7.3 — each phase has explicit gating criteria.

---

## 10. Build & tooling

### 10.1 Gradle config

- `compileSdk`: 34 (match TERRA tablet)
- `minSdk`: 24 (Android 7+; Oasis web is mobile-first)
- `targetSdk`: 34
- Kotlin: 1.9.x (match TERRA)
- AGP: 8.x (match TERRA)

### 10.2 Module declarations

```kotlin
// android/settings.gradle.kts
include(":oasis-designsystem-tokens")
include(":oasis-designsystem")
include(":oasis-catalog")
```

### 10.3 Module types

| Module | Type |
|---|---|
| `oasis-designsystem-tokens` | `com.android.library` |
| `oasis-designsystem` | `com.android.library` |
| `oasis-catalog` | `com.android.application` |

### 10.4 Cross-module dependencies

- `oasis-designsystem` → `oasis-designsystem-tokens` via `implementation(project(":oasis-designsystem-tokens"))`
- `oasis-catalog` → `oasis-designsystem` via `implementation(project(":oasis-designsystem"))`

### 10.5 buildFeatures

- `viewBinding` enabled in `oasis-catalog` only (component library does not consume layouts)
- `buildConfig` enabled across all modules if needed for debug flags

### 10.6 Documentation

Adapt `android/ADDING_NEW_COMPONENT.md` into `oasis-designsystem/ADDING_NEW_OASIS_COMPONENT.md` so future contributors can replicate the recipe.

---

## 11. Known future-work items

Recorded for follow-up, not part of v1 scope:

| Item | Trigger |
|---|---|
| Token rename Gradle task | Large-scale rename of any `oasis_*` resource |
| Token alias layer | Backward-compat shim if rename is breaking |
| Dark theme tokens | When Oasis web adds dark variant |
| Custom Oasis font family | When brand/font is finalized |
| `OasisLoader`, `OasisOtp`, `OasisPageState` components | Separate screen-specimen spec |
| Maven publishing setup | When Oasis Android is consumed by external apps |
| Per-component accessibility audit | Before external consumers adopt |
| Auto-generated component docs site | If community contribution grows |

---

## 12. Risk register

| Risk | Mitigation |
|---|---|
| Naming collisions if a future app imports both TERRA tablet and Oasis Android | All Oasis resources prefixed `oasis_`; namespaces distinct (`com.terra.ds.*` vs `com.oasis.designsystem.*`) |
| Icon SVG → Vector Drawable conversion time-consuming | Batch convert via Android Studio plugin or scripted import in Phase 1; defer non-mvp icons if needed |
| Visual drift between web and Android | Screenshot-compare in each phase gate; explicit 1:1 hex tokens in Section 6.2 |
| Token rename later breaks consumers | v1 establishes resource-based references only (no Kotlin string literals); rename = IDE refactor + Gradle task |

---

## 13. Open questions

None. All scope, naming, structure, and ordering decisions confirmed during brainstorming on 2026-06-22.

---

## 14. References

- `apps/web-catalog/src/components/oasis/` — Oasis web source (TSX + CSS, 30+ files)
- `apps/web-catalog/src/content/oasis-registry.tsx` — registry of 24 official components
- `apps/web-catalog/public/figma-card-assets/oasis-icons/` — SVG icons source of truth
- `apps/web-catalog/public/figma-card-assets/oasis-illustration/` — raster illustration source of truth (folder name singular, as it appears in repo)
- `android/terra-tokens/` — TERRA tablet token module (pattern reference)
- `android/terra-ui/` — TERRA tablet component library (pattern reference)
- `android/catalog/` — TERRA tablet catalog app (pattern reference)
- `android/ADDING_NEW_COMPONENT.md` — per-component recipe reference