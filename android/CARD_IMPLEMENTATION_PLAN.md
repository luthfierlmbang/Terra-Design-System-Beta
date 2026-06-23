# TerraCardView Implementation Plan

Dokumen ini menerjemahkan `android/CARD_COMPONENT_SPEC.md` menjadi langkah implementasi yang actionable.

---

## 1. Objective

Bangun reusable Android component:
- `TerraCardView`

Dengan cakupan awal:
- 1 menu variant
- 5 information variants
- Kotlin class
- XML attrs
- internal layout XML
- catalog demo

---

## 2. Implementation scope

### In scope
- `TerraCardView.kt`
- `attrs_card.xml`
- `terra_view_card.xml`
- `styles_card.xml`
- `terra_catalog_card.xml`
- update audit/tracker docs

### Out of scope for phase awal
- avatar/icon customer yang kompleks
- list dinamis untuk information rows
- dual CTA button
- action callback API yang kompleks
- full visual parity asset-by-asset dengan web bila ada kebutuhan tambahan yang belum dispesifikkan

---

## 3. File plan

### Kotlin
- `android/terra-ui/src/main/java/com/terra/ds/card/TerraCardView.kt`

### XML resources
- `android/terra-ui/src/main/res/values/attrs_card.xml`
- `android/terra-ui/src/main/res/values/styles_card.xml`
- `android/terra-ui/src/main/res/layout/terra_view_card.xml`

### Catalog demo
- `android/catalog/src/main/res/layout/terra_catalog_card.xml`

### Docs update
- `android/WEB_TO_ANDROID_GAP_AUDIT.md`
- `android/WEB_TO_ANDROID_TRACKER.md`

---

## 4. Work breakdown

## Step 1 — Scaffold package and files
**Goal:** file structure siap.

### Tasks
- buat package `com.terra.ds.card`
- buat `TerraCardView.kt`
- buat `attrs_card.xml`
- buat `styles_card.xml`
- buat `terra_view_card.xml`
- buat `terra_catalog_card.xml`

### Output
Struktur file Card sudah ada walaupun belum full logic.

---

## Step 2 — Define public API and enums
**Goal:** API dasar komponen stabil.

### Tasks
- buat `TerraCardVariant`
- buat property public untuk:
  - menu title/subtitle
  - customer name/meta/secondary text
  - primary info label/value
  - secondary info label/value
  - button text/visible
- tambahkan `@JvmOverloads`
- parse attrs dari XML

### Output
`TerraCardView` bisa di-inflate dari XML dan menerima konfigurasi dasar.

---

## Step 3 — Build internal layout
**Goal:** anatomy card tersedia.

### Layout sections
- root card container
- menu section
- customer info section
- information section
- button section

### Tasks
- inflate `terra_view_card.xml` dari `TerraCardView`
- bind internal views
- pakai `TerraButtonView` untuk button area

### Output
Struktur visual dasar siap dipakai.

---

## Step 4 — Implement variant visibility rules
**Goal:** semua variant punya layout behavior yang jelas.

### Tasks
Implement logic untuk:
- `MENU`
- `INFORMATION_FULL`
- `INFORMATION_INFO_BUTTON`
- `INFORMATION_CUSTOMER_ONLY`
- `INFORMATION_CUSTOMER_BUTTON`
- `INFORMATION_INFO_ONLY`

### Rules
- section visibility ditentukan oleh variant
- button section hanya muncul jika variant mendukung + `terraCardButtonVisible = true`
- row kosong di-hide jika value tidak ada

### Output
Semua variant render struktur section yang benar.

---

## Step 5 — Cek tampilan
**Goal:** card tampak layak dan konsisten.

### Tasks
- buat style card container
- gunakan token radius/spacing/color yang sudah ada bila tersedia
- definisikan spacing antar section
- definisikan hierarchy text minimum
- pastikan resource token valid dan tidak ada reference yang missing

### Output
Card punya visual baseline yang usable.

---

## Step 6 — Create catalog demo
**Goal:** semua variant bisa dilihat dan dicek.

### Required demos
1. Menu Card
2. Information Full
3. Information Info + Button
4. Information Customer Only
5. Information Customer + Button
6. Information Info Only

### Output
`terra_catalog_card.xml` menampilkan 6 state utama.

---

## Step 7 — Cek cara pakai
**Goal:** pastikan implementasi dasar aman dan enak dipakai.

### Validation checklist
- XML inflate works
- attrs terbaca
- variant visibility benar
- empty field handling benar
- internal button tampil sesuai rules
- catalog demo memakai package path yang benar
- button bisa diatur text/visible/enabled/type
- click listener button punya API jelas

### Output
Component siap dipakai lebih aman oleh developer Android.

---

## Step 8 — Cek kelengkapan variant
**Goal:** pastikan scope yang disepakati benar-benar ke-cover.

### Checklist
- `MENU`
- `INFORMATION_FULL`
- `INFORMATION_INFO_BUTTON`
- `INFORMATION_CUSTOMER_ONLY`
- `INFORMATION_CUSTOMER_BUTTON`
- `INFORMATION_INFO_ONLY`
- demo catalog 6 variant tersedia
- empty rows hidden sesuai rules

### Output
Scope variant Card bisa ditutup dengan jelas.

---

## Step 9 — Docs and tracker update
**Goal:** status project sinkron.

### Tasks
- update `WEB_TO_ANDROID_GAP_AUDIT.md`
  - Card: `Missing` → `Partial` atau `Done`
- update `WEB_TO_ANDROID_TRACKER.md`
  - ganti next action sesuai hasil implementasi
- simpan checklist praktis di dokumen terpisah bila perlu

### Output
Dokumen status tetap akurat.

---

## 5. Definition of done

### Minimum for `Partial`
- `TerraCardView` class ada
- attrs XML ada
- internal layout ada
- minimal 1–2 variant sudah jalan
- catalog demo awal ada

### Minimum for `Done`
- semua 6 variant tersedia
- XML attrs final tersedia
- catalog demo lengkap tersedia
- visibility rules sesuai spec
- tidak ada gap struktural utama untuk skenario yang sudah disepakati

---

## 6. Risks and decisions

### Risk 1
Customer info mungkin nanti butuh avatar/icon.

**Mitigation:**
Phase awal pakai text-only, sisakan slot untuk pengembangan berikutnya.

### Risk 2
Information rows bisa berkembang lebih dari 2.

**Mitigation:**
Phase awal batasi 2 row, lalu upgrade ke list dinamis bila benar-benar dibutuhkan.

### Risk 3
Visual design detail belum sepenuhnya terdefinisi.

**Mitigation:**
Bangun struktur dan visibility logic dulu; styling detail bisa diiterasi.

---

## 7. Recommended execution order

Urutan implementasi paling efisien:
1. scaffold file
2. attrs + enum + API
3. internal layout
4. variant visibility logic
5. styling baseline
6. catalog demo
7. docs update

---

## 8. Suggested milestone

### Milestone A
Card dari status `Missing` menjadi `Partial`

### Milestone B
Semua 6 variant Card tersedia

### Milestone C
Card siap dianggap `Done` di audit parity dasar

---

## 9. Immediate next action

Next action paling konkret:
- mulai scaffold `TerraCardView` dan resource file Card

Itu akan membuka jalan untuk implementasi variant satu per satu tanpa mengubah kontrak komponen lagi.
