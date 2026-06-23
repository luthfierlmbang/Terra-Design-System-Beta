# Android Typography Foundation

Dokumen ini menjelaskan fondasi typography Android untuk TERRA, status implementasi saat ini, dan backlog migrasi supaya semua komponen memakai sistem yang konsisten.

---

## Current status

Typography Android sekarang sudah punya fondasi token yang aktif, font brand yang terhubung, dan sweep komponen utama yang sudah tervalidasi build.

### Sudah ada
- Semantic typography token file di `android/terra-tokens/src/main/res/values/terra_typography.xml`
- Text appearance layer di `android/terra-ui/src/main/res/values/styles_typography.xml`
- Font brand `Jenius Sans` aktif lewat token alias `terra_font_family_brand`
- Helper reusable untuk apply text appearance dari Kotlin di `android/terra-ui/src/main/java/com/terra/ds/internal/ViewStyling.kt`
- Audit Kotlin/XML untuk hardcoded typography utama di `terra-ui` sudah dilakukan
- Komponen berikut sudah memakai typography system:
  - `Button`
  - `Card`
  - `Header`
  - `TextField`
  - `Select`
  - `Ticker`
  - `Timer`
  - `SearchBar`
  - `EmptyState`
  - `Navbar`
  - `ImageUpload`
  - `BottomSheet`
  - `DatePicker`
  - `Chip`
  - `Checkbox`
  - `Radio`
  - `Tabs`
  - `QuantityEditor`

### Masih perlu dilanjutkan
- Audit visual/manual QA di Android Studio untuk memastikan line-height, clipping, dan hierarchy text sudah sesuai design intent
- Sweep parity komponen yang status produknya masih `Partial` walau fondasi typography-nya sudah aman

---

## Jenius Sans status

Sumber token/dokumentasi menyatakan bahwa font brand utama adalah `Jenius Sans`. Asset font sekarang sudah tersedia di Android workspace dan sudah dihubungkan ke token layer.

Reference:
- `TERRA-TAB-token-documentation.md`

Catatan penting dari dokumentasi:
- family name platform harus tetap `Jenius Sans`
- implementasi platform harus siap memakai branded asset secara terpusat lewat token layer

### Implikasi ke Android
Saat ini Android memakai alias semantic font resource:
- `@font/terra_font_family_brand`

Alias ini menunjuk ke family resource:
- `android/terra-tokens/src/main/res/font/jenius_sans.xml`

Dengan setup ini, semua komponen yang memakai `TextAppearance.Terra.*` akan ikut memakai Jenius Sans secara otomatis.

---

## Typography tokens currently defined

File:
- `android/terra-ui/src/main/res/values/styles_typography.xml`

### Header
- `TextAppearance.Terra.Header.XL`
- `TextAppearance.Terra.Header.L`
- `TextAppearance.Terra.Header.M`
- `TextAppearance.Terra.Header.S`

### Body
- `TextAppearance.Terra.Body.Medium`
- `TextAppearance.Terra.Body.Medium.Bold`
- `TextAppearance.Terra.Body.Small`
- `TextAppearance.Terra.Body.Small.Bold`

### Caption
- `TextAppearance.Terra.Caption`

### CTA
- `TextAppearance.Terra.CTA.Medium`
- `TextAppearance.Terra.CTA.Small`

---

## Components already using typography system

### 1. Button
Files:
- `android/terra-ui/src/main/java/com/terra/ds/button/TerraButtonView.kt`

Mapping:
- normal button → `TextAppearance.Terra.CTA.Medium`
- small button → `TextAppearance.Terra.CTA.Small`

### 2. Card
Files:
- `android/terra-ui/src/main/res/values/styles_card.xml`

Mapping:
- title → `TextAppearance.Terra.Body.Medium.Bold`
- subtitle → `TextAppearance.Terra.Caption`
- customer name → `TextAppearance.Terra.Body.Small.Bold`
- customer meta → `TextAppearance.Terra.Caption`
- info label → `TextAppearance.Terra.Caption`
- info value → `TextAppearance.Terra.Body.Small`

### 3. Header
Files:
- `android/terra-ui/src/main/res/values/styles_header.xml`
- `android/terra-ui/src/main/res/layout/terra_view_header.xml`

Mapping:
- title → `TextAppearance.Terra.Header.M`
- page identifier → `TextAppearance.Terra.Caption`
- status time → `TextAppearance.Terra.Body.Small`

---

## Components still using manual typography

Hasil audit terakhir di `android/terra-ui` tidak menemukan sisa penggunaan manual yang obvious untuk pola berikut:
- `textSize = ...`
- `setTypeface(...)`
- `Typeface.BOLD` dan turunannya
- `android:textSize`
- `android:fontFamily`
- `android:textStyle`

### Current follow-up
- fokus berikutnya bukan lagi sweep hardcoded typography, tetapi visual QA dan parity audit komponen
- `android/terra-ui/src/main/java/com/terra/ds/stepper/TerraStepperView.kt` masih perlu parity audit, tetapi bukan karena masalah typography
- komponen lain bisa diaudit berdasarkan behavior/state, bukan fondasi font/text token

---

## Recommended migration rules

Supaya migrasi konsisten, pakai aturan ini:

### Rule 1 — no new hardcoded text sizes
Hindari menambah:
- `textSize = 12f`
- `textSize = 14f`
- `android:textSize="...sp"`

Kecuali untuk temporary bridge yang memang belum punya token.

### Rule 2 — prefer text appearances over inline styling
Gunakan:
- `style="@style/TextAppearance.Terra.*"` di XML
- `TextViewCompat.setTextAppearance(...)` di Kotlin

### Rule 3 — keep semantic font alias stable
Semua typography branded harus bergantung pada:
- `@font/terra_font_family_brand`

Jadi ketika family resource berubah, komponen tidak perlu diubah satu-satu.

### Rule 4 — map component roles, not arbitrary sizes
Contoh:
- label/helper/meta → `TextAppearance.Terra.Caption`
- input/value/body kecil → `TextAppearance.Terra.Body.Small`
- body default → `TextAppearance.Terra.Body.Medium`
- CTA → `TextAppearance.Terra.CTA.*`
- heading/title → `TextAppearance.Terra.Header.*`

---

## Suggested next migration wave

### Wave 1
- `TextField` ✅
- `Select` ✅
- `Ticker` ✅
- `Timer` ✅

### Wave 2
- `SearchBar` ✅
- `EmptyState` ✅
- `Navbar` ✅
- `Stepper` ✅ audit only (no text styling to migrate)

### Wave 3
- `BottomSheet` ✅
- `DatePicker` ✅
- `Chip` ✅
- `Checkbox` ✅
- `Radio` ✅
- `Tabs` ✅
- `QuantityEditor` ✅

### Next wave
- visual QA in catalog
- parity audit component-by-component

---

## Jenius Sans activation status

Jenius Sans sekarang sudah diaktifkan di Android token layer melalui:

1. font files di `android/terra-tokens/src/main/res/font/`
2. family resource `jenius_sans.xml`
3. alias token `terra_font_family_brand`
4. typography styles `TextAppearance.Terra.*`

Follow-up yang masih disarankan:
- rebuild `terra-ui` dan `catalog`
- audit clipping/line-height pada komponen yang paling padat text
- sweep komponen yang belum migrate ke typography system

---

## Conclusion

Fondasi typography Android sekarang sudah aktif dan tervalidasi build. Yang belum selesai bukan lagi setup token/font dasarnya, tetapi:
- visual QA lintas preview/catalog
- parity audit komponen yang secara produk masih `Partial`

Dengan fondasi ini, rollout `Jenius Sans` dan text appearance sudah cukup sistematis dan tidak perlu manual per file untuk area yang sudah mengikuti `TextAppearance.Terra.*`.
