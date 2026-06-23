# terra-ui

`terra-ui` adalah module reusable Android XML/View library untuk TERRA design system.

Module ini adalah tempat utama untuk semua **component Android reusable**.

---

## Tujuan module

`terra-ui` dipakai untuk menyimpan:
- custom view reusable
- attrs XML per component
- internal layout reusable
- styles component
- drawable pendukung component

Tujuan akhirnya adalah supaya developer Android bisa memakai komponen TERRA secara konsisten tanpa harus membuat ulang component di setiap app.

---

## Isi utama module

Struktur yang diharapkan di module ini:

```text
android/terra-ui/
  src/main/java/com/terra/ds/
  src/main/res/layout/
  src/main/res/drawable/
  src/main/res/values/
```

### `src/main/java/com/terra/ds/`
Berisi Kotlin source per component.

Contoh:
- `button/TerraButtonView.kt`
- `card/TerraCardView.kt`
- `header/TerraHeaderView.kt`

### `src/main/res/layout/`
Berisi internal reusable layout XML component.

Contoh:
- `terra_view_card.xml`
- `terra_view_header.xml`

### `src/main/res/drawable/`
Berisi drawable pendukung component.

Contoh:
- `terra_bg_card.xml`
- `terra_bg_button_primary.xml`

### `src/main/res/values/`
Berisi attrs dan style per component.

Contoh:
- `attrs_button.xml`
- `attrs_card.xml`
- `styles_button.xml`
- `styles_card.xml`

---

## Rule of placement

### Yang harus masuk ke `terra-ui`
- reusable custom view
- enum/state/variant yang menjadi bagian dari component API
- attrs XML component
- internal layout component
- component-specific drawable/style

### Yang tidak boleh masuk ke `terra-ui`
- screen business feature
- sample app screen production
- catalog-only demo logic
- token foundation ownership

Token foundation tetap tinggal di:
- `android/terra-tokens`

Demo/sample usage tetap tinggal di:
- `android/catalog`

---

## Struktur component yang ideal

Setiap component sebaiknya mengikuti pola ini:

### Kotlin
```text
src/main/java/com/terra/ds/<component>/Terra<Component>View.kt
```

### Attrs
```text
src/main/res/values/attrs_<component>.xml
```

### Internal layout
```text
src/main/res/layout/terra_view_<component>.xml
```

### Styles
```text
src/main/res/values/styles_<component>.xml
```

### Drawable bila perlu
```text
src/main/res/drawable/terra_bg_<component>.xml
```

---

## Komponen yang saat ini sudah ada

Scope saat ini mencakup component seperti:
- `TerraButtonView`
- `TerraCardView`
- `TerraHeaderView`
- `TerraIconView`
- `TerraTimerView`
- `TerraLoadingView`
- `TerraPageControlView`
- `TerraProgressBarView`
- `TerraStepperView`
- `TerraTickerView`
- `TerraEmptyStateView`
- `TerraChipView`
- `TerraNavbarView`
- `TerraCheckboxView`
- `TerraRadioView`
- `TerraToggleView`
- `TerraTabView`
- `TerraImageUploadView`
- `TerraBottomSheetView`
- `TerraDatePickerView`
- `TerraSearchBarView`
- `TerraSelectView`
- `TerraTextFieldView`
- `TerraQuantityEditorView`

Catatan: status parity tiap component tetap mengacu ke audit Android di root `android/`.

---

## Cara baca module ini

Kalau developer mau memahami module ini:

### Cari class component
Lihat:
- `src/main/java/com/terra/ds/*`

### Cari attrs component
Lihat:
- `src/main/res/values/attrs_*.xml`

### Cari internal layout component
Lihat:
- `src/main/res/layout/terra_view_*.xml`

### Cari style atau background component
Lihat:
- `src/main/res/values/styles_*.xml`
- `src/main/res/drawable/*`

---

## Dependency direction

Secara arsitektur:
- `terra-ui` bergantung pada `terra-tokens`
- `catalog` bergantung pada `terra-ui`

Jadi `terra-ui` berada di tengah sebagai reusable component layer.

---

## Prinsip maintainability

Agar module ini tetap rapi:
- 1 component = 1 package utama
- attrs dipisah per component
- reusable logic jangan dicampur dengan catalog demo
- pakai token dari `terra-tokens`, jangan hardcode sembarangan
- jaga prefix resource tetap `terra_`

---

## Related docs

- `android/README.md`
- `android/ANDROID_WORKSPACE_STRUCTURE.md`
- `android/WEB_TO_ANDROID_GAP_AUDIT.md`
- `android/WEB_TO_ANDROID_TRACKER.md`

---

## Summary

Kalau disingkat:
- `terra-ui` = tempat reusable component Android TERRA
- semua logic component utama harus tinggal di sini
- sample/demo usage tidak tinggal di sini, tapi di `catalog`
