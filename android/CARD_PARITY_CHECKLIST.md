# TerraCardView Practical Checklist

Checklist ini dipakai untuk 3 hal praktis:
1. cek tampilan
2. cek cara pakai
3. cek kelengkapan variant

---

## 1. Cek tampilan

- [x] Card punya container visual yang jelas
- [x] Background pakai token Android yang valid
- [x] Border dasar ada
- [x] Radius dasar ada
- [x] Hierarchy text title/subtitle/customer/info dibedakan
- [x] Jarak antar section dasar sudah diatur
- [ ] Visual parity final dengan web sudah dicek side-by-side
- [ ] Empty state visual untuk content minim sudah terasa rapi

## 2. Cek cara pakai

- [x] Bisa dipakai dari XML
- [x] Bisa dipakai dari Kotlin property
- [x] Variant bisa diganti via API
- [x] Button text bisa dikonfigurasi
- [x] Button visible bisa dikonfigurasi
- [x] Button enabled bisa dikonfigurasi
- [x] Button type bisa dikonfigurasi
- [x] Button click listener punya API jelas
- [ ] Perlu diputuskan apakah nanti butuh data model/helper API tambahan

## 3. Cek kelengkapan variant

- [x] `MENU`
- [x] `INFORMATION_FULL`
- [x] `INFORMATION_INFO_BUTTON`
- [x] `INFORMATION_CUSTOMER_ONLY`
- [x] `INFORMATION_CUSTOMER_BUTTON`
- [x] `INFORMATION_INFO_ONLY`
- [x] Demo catalog 6 variant ada
- [x] Empty row info di-hide bila kosong
- [x] Section visibility mengikuti variant
- [ ] Build validation nyata sudah dijalankan
- [ ] Status bisa dinaikkan ke `Done` setelah visual + build validation selesai
