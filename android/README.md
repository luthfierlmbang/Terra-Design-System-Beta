# TERRA Android

Workspace Android untuk TERRA design system berbasis XML/View.

Folder `android/` ini dipakai sebagai:
- **source of truth** untuk Android design system
- **reusable library source** untuk developer Android
- **preview/demo area** untuk validasi visual component
- **workspace documentation area** untuk parity audit, roadmap, dan struktur code

Dokumen ini adalah entry point untuk developer yang baru masuk ke workspace Android.

---

## Quick overview

TERRA Android dibagi jadi 3 layer utama:

- `terra-tokens` → foundation resources
- `terra-ui` → reusable component library
- `catalog` → sample/demo app

Relasinya:
- `terra-ui` memakai token dari `terra-tokens`
- `catalog` memakai component dari `terra-ui`

---

## Modules

### `terra-tokens`
Module resource dasar untuk Android.

Isi utamanya:
- colors
- spacing
- radius
- component dimens
- token resource lain

### `terra-ui`
Module reusable Android XML/View library.

Isi utamanya:
- custom views
- attrs XML
- internal reusable layouts
- styles/drawables component

### `catalog`
Module sample/demo application.

Dipakai untuk:
- preview component
- sample usage
- QA visual dasar

---

## Recommended reading order

Kalau kamu baru masuk ke workspace Android, baca urutan ini:

1. `android/README.md`
2. `android/ANDROID_WORKSPACE_STRUCTURE.md`
3. `android/WEB_TO_ANDROID_GAP_AUDIT.md`
4. `android/WEB_TO_ANDROID_TRACKER.md`
5. `android/WEB_TO_ANDROID_ROADMAP.md`

---

## Important docs

### Local build setup
- `android/LOCAL_BUILD_SETUP.md`

Gunanya untuk memahami:
- prasyarat local build
- cara generate Gradle wrapper
- command build yang direkomendasikan
- known blocker environment bila JDK/Gradle belum tersedia


### Workspace structure
- `android/ANDROID_WORKSPACE_STRUCTURE.md`

Gunanya untuk memahami:
- folder mana isi apa
- file component harus ditaruh di mana
- relasi antar module
- struktur ideal untuk source library Android

### Parity audit
- `android/WEB_TO_ANDROID_GAP_AUDIT.md`

Gunanya untuk melihat:
- komponen web mana yang sudah ada di Android
- mana yang masih `Partial`
- mana yang masih perlu dikerjakan

### Execution tracker
- `android/WEB_TO_ANDROID_TRACKER.md`

Gunanya untuk melihat:
- prioritas kerja
- next action per component
- target outcome tiap komponen

### Long-term roadmap
- `android/WEB_TO_ANDROID_ROADMAP.md`

Gunanya untuk melihat:
- arah kerja jangka panjang
- urutan prioritas global
- milestone parity Web → Android

---

## Current architecture direction

Arah arsitektur yang dipakai saat ini:
- source-first
- modular
- reusable
- buildable secara ideal
- mudah dibaca oleh developer

Artinya, walaupun folder ini dipakai sebagai source file library, strukturnya tetap harus disusun seperti workspace Android yang proper agar:
- mudah divalidasi
- mudah di-maintain
- mudah dipakai developer lain
- siap dikembangkan jadi local module atau published library

---

## Where to look

### Kalau mau cari token dasar
Lihat:
- `android/terra-tokens/src/main/res/values/*`

### Kalau mau cari reusable component
Lihat:
- `android/terra-ui/src/main/java/com/terra/ds/*`
- `android/terra-ui/src/main/res/layout/*`
- `android/terra-ui/src/main/res/values/attrs_*.xml`
- `android/terra-ui/src/main/res/values/styles_*.xml`

### Kalau mau lihat contoh penggunaan component
Lihat:
- `android/catalog/src/main/res/layout/*`

---

## Current note

Saat ini folder Android sudah berisi:
- source structure yang dipisah rapi per module dan per component
- scaffold Gradle workspace minimal untuk `terra-tokens`, `terra-ui`, dan `catalog`
- Gradle wrapper di dalam folder `android/`
- local SDK setup untuk build lokal
- validasi build nyata yang sudah lolos untuk `:terra-ui` dan `:catalog`

Langkah berikut yang ideal:
1. jaga struktur folder tetap konsisten
2. lanjutkan parity audit component
3. rapikan demo/catalog per komponen yang masih partial
4. sinkronkan status dokumen saat implementasi berubah

---

## Summary

Kalau diringkas:
- `terra-tokens` = foundation
- `terra-ui` = reusable library
- `catalog` = preview/demo

Dan dokumen paling penting untuk mulai memahami workspace ini adalah:
- `android/ANDROID_WORKSPACE_STRUCTURE.md`
