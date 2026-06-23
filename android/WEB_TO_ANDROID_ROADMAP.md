# Web to Android Roadmap

Roadmap ini fokus pada satu tujuan utama:

> memastikan semua komponen yang sudah ada di Web punya coverage Android yang jelas, rapi, dan terprioritaskan.

Dokumen ini dipisah dari audit agar fungsinya jelas:
- `android/WEB_TO_ANDROID_GAP_AUDIT.md` = **status saat ini**
- `android/WEB_TO_ANDROID_ROADMAP.md` = **arah kerja jangka panjang**

---

## 1. Objective

Target akhir roadmap ini:
- tidak ada komponen Web yang `Missing` di Android
- semua komponen `Partial` naik menjadi `Done`
- semua komponen `Done` punya parity dasar yang konsisten antara Web dan Android

---

## 2. Working model

Setiap komponen Web harus selalu masuk salah satu status berikut:
- **Missing** → web ada, Android belum ada
- **Partial** → Android sudah ada, tapi parity belum lengkap
- **Done** → Android sudah punya implementasi dasar yang layak dipakai

Status source of truth ada di:
- `android/WEB_TO_ANDROID_GAP_AUDIT.md`

---

## 3. Current baseline

### Missing
- Tidak ada missing komponen utama pada baseline saat ini

### Partial
- Card
- Header
- Navbar
- Stepper
- Timer

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

---

## 4. Priority order

### P0 — Missing
Saat ini tidak ada komponen utama yang masih benar-benar missing.

### P1 — Partial, high priority
Komponen yang sudah ada di Android tapi belum parity penuh:
1. Card
2. Header
3. Navbar
4. Stepper
5. Timer

### P2 — Deep parity audit
Komponen yang sudah ada tapi perlu validasi parity lebih dalam:
6. Text Field
7. Select
8. Search Bar
9. Empty State
10. Tabs
11. Icon

### P3 — Polish
Komponen yang basic coverage-nya sudah bagus dan tinggal stabilisasi:
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

## 5. Roadmap phases

## Phase 1 — Lock the baseline
**Goal:** audit jadi pegangan resmi.

### Actions
- maintain `android/WEB_TO_ANDROID_GAP_AUDIT.md`
- pastikan semua komponen web selalu tercatat
- pastikan setiap komponen punya status + alasan

### Done when
- tidak ada komponen web yang belum masuk audit
- semua mapping Web → Android jelas

---

## Phase 2 — Close Missing components
**Goal:** hilangkan semua status `Missing`.

### Current note
Phase ini pada baseline saat ini secara efektif sudah tertutup.
`Card` sudah naik dari `Missing` menjadi `Partial` lewat `TerraCardView`, attrs XML, internal layout XML, dan catalog demo.

### Actions bila ada komponen baru yang ternyata belum termapping
- tentukan scope komponen Android
- buat Kotlin component
- buat attrs XML
- buat catalog demo
- update audit status

### Done when
- tidak ada lagi komponen utama berstatus `Missing`

---

## Phase 3 — Upgrade Partial to Done
**Goal:** komponen partial jadi cukup stabil untuk dipakai.

### Focus
- Card
- Header
- Navbar
- Stepper
- Timer

### Actions
#### Header
- samakan subpart penting dengan web/spec
- rapikan API title/icon/identifier/status bar/action
- tambahkan coverage demo utama

#### Navbar
- samakan item model dan selected state
- validasi click/active behavior parity
- tambah demo state penting

#### Stepper
- samakan step model dan active state behavior
- validasi current/complete/inactive state
- tambah demo parity

#### Timer
- tambahkan catalog demo
- validasi active/inactive state parity
- rapikan snippet/docs jika masih outdated

### Done when
- masing-masing komponen punya:
  - Kotlin implementation
  - attrs XML
  - demo/catalog
  - parity dasar yang jelas

---

## Phase 4 — Deep parity audit for Done components
**Goal:** memastikan status `Done` memang valid, bukan hanya “sudah ada class”.

### Focus
- Text Field
- Select
- Search Bar
- Empty State
- Tabs
- Icon

### Audit checklist
- variant parity
- state parity
- prop/API parity
- XML attr parity
- demo parity

### Done when
- gap parity untuk komponen Done terdokumentasi jelas
- komponen yang ternyata belum setara diturunkan ke `Partial`

---

## Phase 5 — Stabilization
**Goal:** semua komponen Android yang sudah parity punya fondasi penggunaan yang stabil.

### Actions
- tambah sample XML/Kotlin yang konsisten
- rapikan catalog agar mudah dipakai validasi
- rapikan dokumentasi per komponen bila perlu
- audit naming mismatch Web ↔ Android agar terdokumentasi

### Examples of naming differences to keep documented
- `SearchField` (Web) ↔ `SearchBar` (Android)
- `SelectField` (Web) ↔ `Select` (Android)
- `Tabs` (Web) ↔ `TabView` (Android)

### Done when
- penggunaan Android lebih mudah dipahami
- parity difference yang disengaja terdokumentasi

---

## 6. Execution order

Urutan paling efisien:
1. jaga audit tetap akurat
2. selesaikan `Missing`
3. selesaikan `Partial`
4. audit ulang komponen `Done`
5. stabilisasi docs/demo

---

## 7. Suggested sprint sequence

### Sprint 1
- finalize baseline audit
- close Android workspace scaffold
- lock real build validation

### Sprint 2
- upgrade Card from Partial toward Done
- upgrade Header
- upgrade Navbar

### Sprint 3
- upgrade Stepper
- upgrade Timer
- update audit statuses

### Sprint 4
- deep audit Text Field, Select, Search Bar
- deep audit Tabs and Empty State

### Sprint 5
- icon parity audit
- stabilization and documentation cleanup

---

## 8. Success criteria

Roadmap ini dianggap berhasil jika:

### Milestone 1
- tidak ada lagi komponen `Missing`
- Android workspace minimal buildable untuk `terra-ui` dan `catalog`

### Milestone 2
- semua komponen priority P1 berubah menjadi `Done`

### Milestone 3
- semua komponen Web punya mapping Android yang terdokumentasi

### Milestone 4
- audit bisa dipakai langsung untuk sprint planning tanpa audit ulang besar-besaran

---

## 9. Short version

Kalau diringkas, roadmap ini berarti:

1. **catat semua komponen Web**
2. **mapping ke Android**
3. **tutup yang Missing**
4. **naikkan yang Partial jadi Done**
5. **cek ulang komponen Done agar parity-nya benar**

Itu urutan paling efisien dan paling rapi untuk ngejar parity Web → Android.
