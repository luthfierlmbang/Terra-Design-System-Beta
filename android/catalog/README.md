# catalog

`catalog` adalah module sample/demo application untuk TERRA Android.

Module ini dipakai untuk menampilkan preview component, sample usage, dan validasi visual dasar.

---

## Tujuan module

`catalog` dipakai untuk:
- preview component Android TERRA
- menampilkan variant/state component
- menjadi contoh penggunaan component untuk developer
- membantu QA visual dasar

Module ini bukan tempat reusable component utama dibuat.

---

## Isi utama module

Struktur yang diharapkan di module ini:

```text
android/catalog/
  src/main/res/layout/
```

Contoh isi:
- `terra_catalog_buttons.xml`
- `terra_catalog_card.xml`
- `terra_catalog_header_icon.xml`
- `terra_catalog_feedback_selection.xml`
- `terra_catalog_forms_overlay.xml`

---

## Yang harus masuk ke module ini

- layout demo component
- sample XML usage
- kumpulan variant/state untuk preview
- layout validasi visual dasar

## Yang tidak boleh masuk ke module ini

- reusable custom view utama
- attrs component reusable
- token foundation ownership
- component implementation yang seharusnya reusable

Reusable component utama tetap tinggal di:
- `android/terra-ui`

Foundation token tetap tinggal di:
- `android/terra-tokens`

---

## Peran dalam arsitektur

Posisi `catalog` di workspace adalah preview layer.

Relasi sederhananya:
- `catalog` memakai component dari `terra-ui`
- `catalog` bisa memakai token dari `terra-tokens`
- `catalog` membantu validasi visual, tapi bukan source reusable component

---

## Cara baca module ini

Kalau developer mau cari contoh penggunaan component:
- buka `src/main/res/layout/`

Biasanya tiap file catalog mewakili kelompok component atau satu area demo tertentu.

Contoh:
- `terra_catalog_buttons.xml` → demo button
- `terra_catalog_card.xml` → demo card variants

---

## Rule of usage

- semua reusable logic tetap dibuat di `terra-ui`
- `catalog` hanya memanggil dan menampilkan component
- kalau ada kebutuhan variant demo baru, tambahkan di layout catalog
- jangan pindahkan source reusable ke module ini hanya karena lebih cepat untuk demo

---

## Manfaat untuk developer

Buat developer Android, module ini berguna untuk:
- melihat hasil component lebih cepat
- memahami cara pakai XML attrs
- mengecek state/variant tanpa harus bikin screen baru di app utama

---

## Related docs

- `android/README.md`
- `android/ANDROID_WORKSPACE_STRUCTURE.md`
- `android/terra-ui/README.md`
- `android/WEB_TO_ANDROID_GAP_AUDIT.md`

---

## Summary

Kalau disingkat:
- `catalog` = preview/demo app untuk component TERRA Android
- module ini dipakai untuk sample usage dan visual checking
- module ini bukan tempat reusable component utama dibuat
