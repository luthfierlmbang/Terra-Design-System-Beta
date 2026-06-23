# terra-tokens

`terra-tokens` adalah module foundation resource untuk TERRA Android.

Module ini menjadi sumber token dasar yang dipakai oleh component di `terra-ui` dan, bila perlu, oleh `catalog`.

---

## Tujuan module

`terra-tokens` dipakai untuk menyimpan resource dasar yang bersifat shared, seperti:
- color tokens
- spacing tokens
- radius tokens
- component dimens dasar
- foundation resource lain yang reusable lintas component

Tujuan utamanya adalah menjaga semua component Android memakai foundation yang konsisten.

---

## Isi utama module

Struktur yang diharapkan di module ini:

```text
android/terra-tokens/
  src/main/res/values/
```

Contoh isi:
- `terra_colors_primitives.xml`
- `terra_dimens_spacing.xml`
- `terra_dimens_radius.xml`
- `terra_component_dimens.xml`

---

## Yang harus masuk ke module ini

- foundational color resources
- spacing scale
- radius scale
- ukuran dasar reusable
- token resource lain yang dipakai banyak component

## Yang tidak boleh masuk ke module ini

- custom view logic
- attrs component seperti `attrs_card.xml`
- internal layout component
- sample/demo screen
- business-specific resource

Semua reusable component logic tetap tinggal di:
- `android/terra-ui`

Semua sample/demo usage tetap tinggal di:
- `android/catalog`

---

## Peran dalam arsitektur

Posisi `terra-tokens` di workspace adalah sebagai foundation layer.

Relasi sederhananya:
- `terra-ui` memakai token dari `terra-tokens`
- `catalog` bisa ikut memakai token bila perlu untuk background/demo layout

Jadi module ini tidak menangani component logic, tapi menyediakan fondasinya.

---

## Cara baca module ini

Kalau developer mau memahami token Android TERRA, lihat folder:
- `src/main/res/values/`

Biasanya yang dicari:
- warna neutral / semantic
- spacing scale
- radius scale
- ukuran dasar component

---

## Prinsip maintainability

Agar module ini tetap rapi:
- token harus reusable
- hindari resource yang terlalu spesifik ke satu component bila tidak perlu
- pakai naming prefix `terra_`
- bedakan resource foundation dengan resource implementation-specific

Kalau resource hanya relevan untuk satu component tertentu, pertimbangkan apakah lebih cocok tinggal di `terra-ui`.

---

## Related docs

- `android/README.md`
- `android/ANDROID_WORKSPACE_STRUCTURE.md`
- `android/terra-ui/README.md`

---

## Summary

Kalau disingkat:
- `terra-tokens` = foundation resources Android TERRA
- module ini bukan tempat component logic
- module ini adalah layer dasar yang dipakai oleh `terra-ui`
