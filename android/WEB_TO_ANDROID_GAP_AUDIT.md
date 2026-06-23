# Web to Android Component Gap Audit

Audit ini fokus pada pertanyaan:

> Komponen apa saja yang sudah ada di Web, lalu status implementasinya di Android bagaimana?

Status dipakai dengan aturan sederhana:
- **Done**: ada komponen Android Kotlin + attrs XML + demo/catalog dasar.
- **Partial**: ada implementasi Android, tapi coverage/parity masih belum penuh atau platform di registry belum dianggap full parity.
- **Missing**: komponen web sudah ada, tapi belum ditemukan padanan Android yang layak.

---

## Source of truth

### Web
Sumber utama komponen web:
- `packages/web-ui/src/components/*`
- `apps/web-catalog/src/content/registry.tsx`

### Android
Sumber utama komponen Android:
- `android/terra-ui/src/main/java/com/terra/ds/*/*.kt`
- `android/terra-ui/src/main/res/values/attrs_*.xml`
- `android/catalog/src/main/res/layout/*.xml`

---

## Summary

### Done
- Button
- Checkbox
- Chip
- Empty State
- Icon
- Loading
- Page Control
- Progress Bar
- Quantity Editor
- Radio Button
- Search Bar
- Select
- Tabs
- Text Field
- Ticker
- Toggle

### Partial
- Card
- Header
- Navbar
- Stepper
- Timer

### Missing
- Tidak ada missing komponen utama pada baseline audit saat ini

---

## Detailed matrix

| Web Component | Web Source | Android Mapping | Kotlin | XML Attrs | Catalog Demo | Status | Notes / Gap |
|---|---|---|---:|---:|---:|---|---|
| Button | `packages/web-ui/src/components/Button` | `TerraButtonView` | Yes | Yes | Yes | Done | Variants and sizes exist on Android. |
| Checkbox | `packages/web-ui/src/components/Checkbox` | `TerraCheckboxView` | Yes | Yes | Yes | Done | Basic checked/unchecked/disabled support appears present. |
| Chip | `packages/web-ui/src/components/Chip` | `TerraChipView` | Yes | Yes | Yes | Done | Left/right icon and counter attrs exist. |
| Empty State | `packages/web-ui/src/components/EmptyState` | `TerraEmptyStateView` | Yes | Yes | Yes | Done | Variant attr list exists and demo is present. |
| Header | `packages/web-ui/src/components/Header` | `TerraHeaderView` | Yes | Yes | Yes | Partial | Android exists, but earlier spec audit showed subparts/variant parity not yet complete. Registry also still marks platform as Android and status as partial. |
| Icon | `packages/web-ui/src/components/Icon` | `TerraIconView` | Yes | Yes | Yes | Done | View + attrs + demo exist. Separate asset completeness still needs a deeper icon-by-icon parity audit. |
| Loading | `packages/web-ui/src/components/Loading` | `TerraLoadingView` | Yes | Yes | Yes | Done | Base Android implementation and demo exist. |
| Navbar | `packages/web-ui/src/components/Navbar` | `TerraNavbarView` | Yes | Yes | Yes | Partial | Android exists, but registry still treats this as Android-only partial parity. Needs behavior/API parity confirmation with web. |
| Page Control | `packages/web-ui/src/components/PageControl` | `TerraPageControlView` | Yes | Yes | Yes | Done | Android implementation and demo found. |
| Progress Bar | `packages/web-ui/src/components/ProgressBar` | `TerraProgressBarView` | Yes | Yes | Yes | Done | Android variant attrs exist (`base`, `bar1`, `bar2`). |
| Quantity Editor | `packages/web-ui/src/components/QuantityEditor` | `TerraQuantityEditorView` | Yes | Yes | Yes | Done | Android implementation and attrs exist. |
| Radio Button | `packages/web-ui/src/components/Radio` | `TerraRadioView` | Yes | Yes | Yes | Done | Android implementation and demo found. |
| Search Bar | `packages/web-ui/src/components/SearchField` | `TerraSearchBarView` | Yes | Yes | Yes | Done | Naming differs (`SearchField` on web vs `SearchBar` on Android), but clear functional mapping exists. |
| Select | `packages/web-ui/src/components/SelectField` | `TerraSelectView` | Yes | Yes | Yes | Done | Naming differs (`SelectField` web vs `Select` Android), but functional mapping exists. |
| Stepper | `packages/web-ui/src/components/Stepper` | `TerraStepperView` | Yes | Yes | Yes | Partial | Android implementation exists, but registry still marks this Android-only partial. Needs parity validation against web API/behavior. |
| Tabs | `packages/web-ui/src/components/Tabs` | `TerraTabView` | Yes | Yes | Yes | Done | Naming differs (`Tabs` web vs `TabView` Android), but mapping is clear and demo exists. |
| Text Field | `packages/web-ui/src/components/TextField` | `TerraTextFieldView` | Yes | Yes | Yes | Done | Android exists with variant attrs and demo. Full prop parity can be audited later. |
| Ticker | `packages/web-ui/src/components/Ticker` | `TerraTickerView` | Yes | Yes | Yes | Done | Tone and detailed attrs exist on Android. |
| Timer | `packages/web-ui/src/components/Timer` | `TerraTimerView` | Yes | Yes | No | Partial | Kotlin + attrs exist, but no dedicated catalog usage found after current workspace build stabilization. Registry/android snippet also previously marked this as unavailable. |
| Toggle | `packages/web-ui/src/components/Toggle` | `TerraToggleView` | Yes | Yes | Yes | Done | Android implementation and demo found. |
| Card | Registry only (`slug: card`) | `TerraCardView` | Yes | Yes | Yes | Partial | Kotlin view, attrs, internal XML layout, dan catalog demo sudah ada. Workspace build validation untuk `terra-ui` dan `catalog` juga sudah lolos. Gap tersisa ada di parity visual, API richness, dan coverage polish. |

---

## Components currently missing on Android

Saat ini **tidak ada komponen utama yang masih benar-benar missing** pada baseline audit ini.

Komponen `Card` sudah naik dari **Missing** menjadi **Partial** karena Android reusable component awalnya sudah tersedia, namun parity dan validasi implementasi masih belum lengkap.

---

## Components present on Android but still need parity validation

### 1. Header
Main gap areas:
- leading/action/subpart parity with web/spec
- variant formalization
- richer API parity if web evolves further

### 2. Navbar
Main gap areas:
- validate active item API parity
- validate number of items, icon-label behavior, and click model parity

### 3. Stepper
Main gap areas:
- validate step model parity (`steps[]`, captions, active step handling)
- confirm Android API is not just visual but functionally aligned to web

### 4. Timer
Main gap areas:
- add catalog demo coverage
- verify active/inactive styling parity
- verify whether Android snippet/docs are still outdated

---

## Recommended immediate priority

### Priority 1 — Partial parity yang paling dekat untuk dituntaskan
1. Card
2. Header
3. Navbar
4. Stepper
5. Timer

### Priority 3 — Later deep audits
6. Icon asset-by-asset parity
7. Text Field prop parity
8. Select/Search detailed behavior parity

---

## Conclusion

Kalau fokusnya hanya ke pertanyaan:

> komponen web yang belum jadi komponen Android itu apa saja?

Maka hasil audit saat ini adalah:

### Belum ada / Missing
- Tidak ada pada baseline audit saat ini

### Sudah ada tapi masih Partial
- **Card**
- **Header**
- **Navbar**
- **Stepper**
- **Timer**

### Sudah ada dan basic coverage-nya tersedia
- Button
- Checkbox
- Chip
- Empty State
- Icon
- Loading
- Page Control
- Progress Bar
- Quantity Editor
- Radio Button
- Search Bar
- Select
- Tabs
- Text Field
- Ticker
- Toggle
