# Web to Android Tracker

Tracker ini dipakai untuk eksekusi harian dan sprint planning.

Gunakan bersama:
- `android/WEB_TO_ANDROID_GAP_AUDIT.md` → status & analisis gap
- `android/WEB_TO_ANDROID_ROADMAP.md` → arah kerja jangka panjang
- `android/WEB_TO_ANDROID_TRACKER.md` → progress kerja yang actionable

---

## Status legend
- **Missing** → web ada, Android belum ada
- **Partial** → Android sudah ada, parity belum lengkap
- **Done** → Android sudah layak dipakai dengan parity dasar
- **Blocked** → ada dependency/keputusan yang menahan progres

## Priority legend
- **P0** → harus dikerjakan dulu
- **P1** → penting setelah P0
- **P2** → parity audit lanjutan
- **P3** → polish/stabilization

---

## Active tracker

| Component | Web Mapping | Android Mapping | Status | Priority | Next Action | Target Outcome |
|---|---|---|---|---|---|---|
| Card | `Card` | `TerraCardView` | Partial | P1 | Finalisasi parity visual/API, rapikan sample usage, sinkronkan typography token, dan putuskan gap yang masih tersisa menuju Done | Status naik ke Done |
| Header | `Header` | `TerraHeaderView` | Partial | P1 | Audit subpart parity: icon, identifier, status bar, action | Status naik ke Done |
| Navbar | `Navbar` | `TerraNavbarView` | Partial | P1 | Audit item model, active state, click behavior | Status naik ke Done |
| Stepper | `Stepper` | `TerraStepperView` | Partial | P1 | Audit step model dan state complete/current/inactive | Status naik ke Done |
| Timer | `Timer` | `TerraTimerView` | Partial | P1 | Tambahkan demo catalog terdedikasi + validasi active/inactive parity | Status naik ke Done |
| Text Field | `TextField` | `TerraTextFieldView` | Done | P2 | Audit prop parity: prefix/suffix/icon/helper/disabled | Tetap Done atau turun ke Partial bila gap besar ditemukan |
| Select | `SelectField` | `TerraSelectView` | Done | P2 | Audit parity placeholder/value/options/search state | Tetap Done atau turun ke Partial bila gap besar ditemukan |
| Search Bar | `SearchField` | `TerraSearchBarView` | Done | P2 | Audit parity clear button, state, helper/label model | Tetap Done atau turun ke Partial bila gap besar ditemukan |
| Empty State | `EmptyState` | `TerraEmptyStateView` | Done | P2 | Audit variant and CTA parity | Tetap Done atau turun ke Partial bila gap besar ditemukan |
| Tabs | `Tabs` | `TerraTabView` | Done | P2 | Audit parity items, selected state, scrollable behavior | Tetap Done atau turun ke Partial bila gap besar ditemukan |
| Icon | `Icon` | `TerraIconView` | Done | P2 | Audit icon asset parity per registry category/name | Tetap Done atau pecah issue asset coverage |
| Button | `Button` | `TerraButtonView` | Done | P3 | Typography foundation sudah dipakai; polish only if parity audit later finds mismatch | Stable |
| Checkbox | `Checkbox` | `TerraCheckboxView` | Done | P3 | Polish only | Stable |
| Radio Button | `Radio` | `TerraRadioView` | Done | P3 | Polish only | Stable |
| Toggle | `Toggle` | `TerraToggleView` | Done | P3 | Polish only | Stable |
| Chip | `Chip` | `TerraChipView` | Done | P3 | Polish only | Stable |
| Loading | `Loading` | `TerraLoadingView` | Done | P3 | Polish only | Stable |
| Page Control | `PageControl` | `TerraPageControlView` | Done | P3 | Polish only | Stable |
| Progress Bar | `ProgressBar` | `TerraProgressBarView` | Done | P3 | Polish only | Stable |
| Quantity Editor | `QuantityEditor` | `TerraQuantityEditorView` | Done | P3 | Polish only | Stable |
| Ticker | `Ticker` | `TerraTickerView` | Done | P3 | Polish only | Stable |

---

## Suggested execution order

### Wave 1 — Close the biggest gaps
1. Card
2. Header
3. Navbar
4. Stepper
5. Timer

### Wave 2 — Revalidate components marked Done
6. Text Field
7. Select
8. Search Bar
9. Empty State
10. Tabs
11. Icon

### Wave 3 — Stabilization
12. Button
13. Checkbox
14. Radio Button
15. Toggle
16. Chip
17. Loading
18. Page Control
19. Progress Bar
20. Quantity Editor
21. Ticker

---

## Ready-for-sprint checklist

Sebuah komponen siap masuk sprint kalau:
- status saat ini jelas
- gap utamanya jelas
- target outcome sprint jelas
- tidak bergantung pada keputusan naming/scope yang belum dibuat

---

## Per-component implementation checklist

Gunakan checklist ini saat mulai mengerjakan komponen:

- [ ] Mapping Web ↔ Android sudah jelas
- [ ] Scope komponen sudah diputuskan
- [ ] Kotlin component ada / diupdate
- [ ] XML attrs ada / diupdate
- [ ] Internal layout ada bila perlu
- [ ] Catalog demo ada / diupdate
- [ ] Variant utama ter-cover
- [ ] State utama ter-cover
- [ ] Audit status diupdate

---

## Notes

### Typography foundation
- Android typography foundation sudah terdokumentasi di `android/TYPOGRAPHY_ANDROID.md`
- `Jenius Sans` sudah tersedia dan aktif lewat token/font resource Android
- semantic text appearances `TextAppearance.Terra.*` sudah dipakai di komponen utama dan lolos build + unit test
- fokus typography berikutnya adalah visual QA, bukan lagi setup font dasar


### Naming mismatches to keep documented
- `SearchField` (Web) ↔ `SearchBar` (Android)
- `SelectField` (Web) ↔ `Select` (Android)
- `Tabs` (Web) ↔ `TabView` (Android)

### Rule of use
- Update tracker saat prioritas berubah
- Update audit saat status berubah
- Update roadmap hanya jika arah besar berubah
