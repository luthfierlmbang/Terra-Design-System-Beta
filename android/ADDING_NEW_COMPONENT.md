# Adding New Component

Dokumen ini menjelaskan cara menambah component baru ke workspace Android TERRA.

Tujuannya supaya developer punya panduan yang konsisten saat membuat component baru, tanpa bingung:
- file harus ditaruh di mana
- module mana yang harus diubah
- dokumentasi mana yang harus ikut diupdate
- apa saja checklist minimum sebelum component dianggap masuk dengan rapi

Dokumen ini berlaku untuk component Android berbasis **XML/View**.

---

## 1. Kapan dokumen ini dipakai

Pakai dokumen ini ketika:
- ada component baru dari web/design system yang belum ada di Android
- ada component lama yang ingin dipisah menjadi reusable Android component
- ada variant component baru yang butuh file/resource tambahan

Contoh:
- menambah `TerraCardView`
- menambah `TerraBadgeView`
- menambah component baru hasil parity audit Web → Android

---

## 2. Prinsip dasar

Saat menambah component baru, pakai prinsip ini:

- reusable logic masuk ke `terra-ui`
- token foundation masuk ke `terra-tokens`
- sample/demo masuk ke `catalog`
- dokumentasi status masuk ke root `android/`

Jangan campur reusable component implementation dengan demo layer.

---

## 3. Quick decision tree

Sebelum mulai, tentukan dulu component ini termasuk apa.

### A. Apakah ini reusable component?
Kalau ya:
- buat di `terra-ui`

### B. Apakah butuh token foundation baru?
Kalau ya:
- tambahkan di `terra-tokens`

### C. Apakah butuh sample/demo visual?
Kalau ya:
- tambahkan di `catalog`

### D. Apakah ini mengubah status parity Web → Android?
Kalau ya:
- update audit/tracker/docs di root `android/`

---

## 4. Struktur file yang biasanya dibutuhkan

Untuk component baru, biasanya file minimalnya adalah:

### Reusable layer
```text
android/terra-ui/src/main/java/com/terra/ds/<component>/Terra<Component>View.kt
android/terra-ui/src/main/res/values/attrs_<component>.xml
android/terra-ui/src/main/res/layout/terra_view_<component>.xml
android/terra-ui/src/main/res/values/styles_<component>.xml
```

### Optional reusable resources
```text
android/terra-ui/src/main/res/drawable/terra_bg_<component>.xml
android/terra-ui/src/main/res/drawable/terra_ic_<component>.xml
```

### Demo layer
```text
android/catalog/src/main/res/layout/terra_catalog_<component>.xml
```

### Docs layer
```text
android/WEB_TO_ANDROID_GAP_AUDIT.md
android/WEB_TO_ANDROID_TRACKER.md
android/WEB_TO_ANDROID_ROADMAP.md
```

---

## 5. Step-by-step workflow

## Step 1 — Tentukan mapping component
Tentukan dulu component web/design ini akan dipetakan ke Android jadi apa.

Contoh pertanyaan:
- nama Android component-nya apa?
- 1 component atau beberapa component?
- butuh enum variant atau component terpisah?
- reusable atau hanya demo?

### Output step ini
- nama component jelas
- scope component jelas
- mapping Web → Android jelas

---

## Step 2 — Tentukan module yang terlibat
Biasanya:

- `terra-ui` → wajib untuk component reusable
- `catalog` → wajib kalau ingin ada preview/demo
- `terra-tokens` → hanya jika butuh token baru

### Rule
Jangan langsung menambah token baru kalau sebenarnya token yang ada masih cukup.

---

## Step 3 — Buat package Kotlin di `terra-ui`
Buat package per component.

### Contoh
```text
android/terra-ui/src/main/java/com/terra/ds/card/
android/terra-ui/src/main/java/com/terra/ds/badge/
```

### Rule
- 1 component utama = 1 package utama
- nama class utama konsisten: `Terra<Component>View`

---

## Step 4 — Buat Kotlin component utama
Buat file utama component.

### Contoh
```text
android/terra-ui/src/main/java/com/terra/ds/card/TerraCardView.kt
```

### Isi minimum yang biasanya perlu
- custom view class
- property public yang relevan
- enum/state/variant bila diperlukan
- parsing attrs XML
- binding ke internal views
- visibility/state handling

### Rule
- API component harus enak dipakai dari XML dan Kotlin
- jangan masukkan business logic screen ke component

---

## Step 5 — Buat attrs XML
Kalau component configurable dari XML, buat attrs khusus component.

### Contoh
```text
android/terra-ui/src/main/res/values/attrs_card.xml
```

### Isi minimum
- `declare-styleable`
- attrs untuk variant/state/text/flags yang dibutuhkan

### Rule
- attrs dipisah per component
- nama attr pakai prefix yang konsisten

---

## Step 6 — Buat internal layout reusable
Kalau component punya anatomy yang cukup kompleks, buat layout internal XML.

### Contoh
```text
android/terra-ui/src/main/res/layout/terra_view_card.xml
```

### Rule
- ini bukan layout screen
- ini adalah internal reusable layout untuk component
- pakai naming `terra_view_<component>.xml`

---

## Step 7 — Buat style/drawable pendukung
Kalau component butuh style atau background khusus:

### Contoh
```text
android/terra-ui/src/main/res/values/styles_card.xml
android/terra-ui/src/main/res/drawable/terra_bg_card.xml
```

### Rule
- style component-specific tinggal di `terra-ui`
- kalau resource benar-benar foundation/shared, baru pertimbangkan `terra-tokens`

---

## Step 8 — Tambahkan token baru hanya jika benar-benar perlu
Kalau ada kebutuhan warna/spacing/radius yang belum tersedia dan memang layak jadi foundation token, baru tambahkan ke `terra-tokens`.

### Rule penting
Jangan setiap kebutuhan visual baru langsung dibuat token baru.

Tanya dulu:
- ini reusable lintas component?
- ini foundation-level atau hanya kebutuhan satu component?

Kalau hanya satu component-specific background, biasanya cukup tinggal di `terra-ui`.

---

## Step 9 — Buat catalog demo
Buat sample/demo XML agar component bisa dicek visualnya.

### Contoh
```text
android/catalog/src/main/res/layout/terra_catalog_card.xml
```

### Isi minimum
- variant utama
- state utama
- contoh attrs penting

### Rule
- catalog dipakai untuk preview, bukan reusable implementation
- tampilkan state yang paling penting terlebih dahulu

---

## Step 10 — Update dokumentasi parity/status
Kalau component ini terkait parity Web → Android, update dokumen root Android.

### Minimum update
- `android/WEB_TO_ANDROID_GAP_AUDIT.md`
- `android/WEB_TO_ANDROID_TRACKER.md`

### Optional update
- `android/WEB_TO_ANDROID_ROADMAP.md`
- spec/plan/checklist khusus component bila scope besar

---

## Step 11 — Validation
Kalau workspace build sudah tersedia, lakukan validation.

### Minimal validation
- attrs terbaca
- XML reusable tidak broken
- resource reference valid
- Kotlin compile
- catalog demo bisa dipakai

### Jika build system belum siap
Minimal lakukan static validation:
- cek path file
- cek naming consistency
- cek resource reference
- cek attrs sinkron dengan Kotlin parsing

---

## 6. Checklist minimum sebelum merge

Gunakan checklist ini sebelum component dianggap masuk dengan rapi:

### Scope
- [ ] Mapping Web → Android jelas
- [ ] Scope reusable vs demo jelas
- [ ] Naming component final jelas

### Reusable implementation
- [ ] Kotlin class ada
- [ ] package component benar
- [ ] attrs XML ada bila perlu
- [ ] internal layout ada bila perlu
- [ ] style/drawable pendukung ada bila perlu

### API
- [ ] bisa dipakai dari XML
- [ ] bisa dipakai dari Kotlin bila relevan
- [ ] variant/state utama ter-cover
- [ ] empty/visibility behavior jelas

### Demo
- [ ] catalog demo ada
- [ ] variant utama tampil
- [ ] state utama tampil

### Docs
- [ ] audit diupdate
- [ ] tracker diupdate
- [ ] roadmap diupdate bila arah besar berubah
- [ ] README/component doc diupdate bila perlu

### Validation
- [ ] static validation dilakukan
- [ ] build validation dilakukan jika workspace siap

---

## 7. Contoh penerapan: Card

Contoh hasil implementasi `Card`:

### Reusable files
```text
android/terra-ui/src/main/java/com/terra/ds/card/TerraCardView.kt
android/terra-ui/src/main/res/values/attrs_card.xml
android/terra-ui/src/main/res/layout/terra_view_card.xml
android/terra-ui/src/main/res/values/styles_card.xml
android/terra-ui/src/main/res/drawable/terra_bg_card.xml
```

### Demo file
```text
android/catalog/src/main/res/layout/terra_catalog_card.xml
```

### Docs
```text
android/CARD_COMPONENT_SPEC.md
android/CARD_IMPLEMENTATION_PLAN.md
android/CARD_PARITY_CHECKLIST.md
```

Ini contoh yang cukup representatif untuk component yang punya:
- variant
- internal anatomy
- attrs XML
- katalog demo

---

## 8. Anti-pattern yang perlu dihindari

Hindari hal-hal ini:

- menaruh reusable component logic di `catalog`
- menaruh token foundation di `terra-ui` tanpa alasan jelas
- mencampur beberapa component berbeda dalam satu file besar tanpa kebutuhan kuat
- membuat attrs terlalu banyak tanpa kebutuhan nyata
- membuat demo saja tanpa reusable component yang jelas
- update component tanpa update audit/tracker

---

## 9. Recommended workflow paling efisien

Kalau mau kerja paling rapi dan efisien, urutannya:

1. define scope component
2. buat Kotlin class
3. buat attrs
4. buat internal layout
5. buat style/drawable pendukung
6. buat catalog demo
7. update docs
8. validation

Ini urutan yang paling aman agar struktur tidak berantakan.

---

## 10. Related docs

- `android/README.md`
- `android/ANDROID_WORKSPACE_STRUCTURE.md`
- `android/terra-ui/README.md`
- `android/terra-tokens/README.md`
- `android/catalog/README.md`
- `android/WEB_TO_ANDROID_GAP_AUDIT.md`
- `android/WEB_TO_ANDROID_TRACKER.md`

---

## 11. Summary

Kalau disingkat, cara nambah component baru di Android TERRA adalah:

- tentukan mapping dan scope
- bangun reusable component di `terra-ui`
- tambah token di `terra-tokens` hanya bila perlu
- buat demo di `catalog`
- update docs parity/status
- validasi hasilnya

Dengan pola ini, workspace akan tetap rapi, konsisten, dan mudah dipahami developer lain.
