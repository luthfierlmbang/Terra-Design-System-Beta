# TerraCardView — Final Spec

## 1. Tujuan
`TerraCardView` adalah reusable Android card component untuk kebutuhan:

- **Menu Card**
- **Information Card**

Dengan pendekatan:
- **1 komponen**
- **1 enum variant**
- section ditampilkan/disembunyikan berdasarkan variant

Ini paling efisien untuk scope sekarang.

---

## 2. Nama komponen

### Component name
- `TerraCardView`

### Package
- `com.terra.ds.card`

### File utama
- `android/terra-ui/src/main/java/com/terra/ds/card/TerraCardView.kt`

---

## 3. Variant model

```kotlin
enum class TerraCardVariant {
    MENU,
    INFORMATION_FULL,
    INFORMATION_INFO_BUTTON,
    INFORMATION_CUSTOMER_ONLY,
    INFORMATION_CUSTOMER_BUTTON,
    INFORMATION_INFO_ONLY
}
```

---

## 4. Mapping variant

## A. Menu Card
Untuk card menu / shortcut / entry action

```kotlin
MENU
```

---

## B. Information Card

### 1. Informasi Nasabah + Informasi + Button
```kotlin
INFORMATION_FULL
```

### 2. Informasi + Button
```kotlin
INFORMATION_INFO_BUTTON
```

### 3. Informasi Nasabah saja
```kotlin
INFORMATION_CUSTOMER_ONLY
```

### 4. Informasi Nasabah + Button
```kotlin
INFORMATION_CUSTOMER_BUTTON
```

### 5. Informasi saja
```kotlin
INFORMATION_INFO_ONLY
```

---

## 5. Anatomy komponen

`TerraCardView` dibagi menjadi beberapa section:

1. **Card container**
2. **Menu section**
3. **Customer info section**
4. **Information section**
5. **Button section**

Variant akan menentukan section mana yang visible.

---

## 6. Section detail

## A. Menu section
Dipakai untuk:
- `MENU`

### Data minimum
- title
- subtitle / description

### Attr awal
- `terraCardMenuTitle`
- `terraCardMenuSubtitle`

---

## B. Customer info section
Dipakai untuk:
- `INFORMATION_FULL`
- `INFORMATION_CUSTOMER_ONLY`
- `INFORMATION_CUSTOMER_BUTTON`

### Data minimum
- customer name
- customer meta
- secondary text opsional

### Attr awal
- `terraCardCustomerName`
- `terraCardCustomerMeta`
- `terraCardCustomerSecondaryText`

---

## C. Information section
Dipakai untuk:
- `INFORMATION_FULL`
- `INFORMATION_INFO_BUTTON`
- `INFORMATION_INFO_ONLY`

Untuk fase awal, information section cukup pakai **2 row label-value**.

### Attr awal
- `terraCardInfoPrimaryLabel`
- `terraCardInfoPrimaryValue`
- `terraCardInfoSecondaryLabel`
- `terraCardInfoSecondaryValue`

---

## D. Button section
Dipakai untuk:
- `INFORMATION_FULL`
- `INFORMATION_INFO_BUTTON`
- `INFORMATION_CUSTOMER_BUTTON`

### Data minimum
- button text
- button visible

### Attr awal
- `terraCardButtonText`
- `terraCardButtonVisible`

### Rekomendasi
Button internal menggunakan:
- `TerraButtonView`

---

## 7. Visibility rules

## `MENU`
### Visible
- menu section

### Hidden
- customer info section
- information section
- button section

---

## `INFORMATION_FULL`
### Visible
- customer info
- information
- button

---

## `INFORMATION_INFO_BUTTON`
### Visible
- information
- button

### Hidden
- customer info

---

## `INFORMATION_CUSTOMER_ONLY`
### Visible
- customer info

### Hidden
- information
- button

---

## `INFORMATION_CUSTOMER_BUTTON`
### Visible
- customer info
- button

### Hidden
- information

---

## `INFORMATION_INFO_ONLY`
### Visible
- information

### Hidden
- customer info
- button

---

## 8. Public Kotlin API draft

```kotlin
var terraCardVariant: TerraCardVariant

var terraCardMenuTitle: String
var terraCardMenuSubtitle: String

var terraCardCustomerName: String
var terraCardCustomerMeta: String
var terraCardCustomerSecondaryText: String

var terraCardInfoPrimaryLabel: String
var terraCardInfoPrimaryValue: String
var terraCardInfoSecondaryLabel: String
var terraCardInfoSecondaryValue: String

var terraCardButtonText: String
var terraCardButtonVisible: Boolean
var terraCardButtonEnabled: Boolean
var terraCardButtonType: TerraButtonType

fun setOnCardButtonClickListener(listener: View.OnClickListener?)
```

---

## 9. XML attrs draft

```xml
<declare-styleable name="TerraCardView">
    <attr name="terraCardVariant" format="enum">
        <enum name="menu" value="0" />
        <enum name="informationFull" value="1" />
        <enum name="informationInfoButton" value="2" />
        <enum name="informationCustomerOnly" value="3" />
        <enum name="informationCustomerButton" value="4" />
        <enum name="informationInfoOnly" value="5" />
    </attr>

    <attr name="terraCardMenuTitle" format="string" />
    <attr name="terraCardMenuSubtitle" format="string" />

    <attr name="terraCardCustomerName" format="string" />
    <attr name="terraCardCustomerMeta" format="string" />
    <attr name="terraCardCustomerSecondaryText" format="string" />

    <attr name="terraCardInfoPrimaryLabel" format="string" />
    <attr name="terraCardInfoPrimaryValue" format="string" />
    <attr name="terraCardInfoSecondaryLabel" format="string" />
    <attr name="terraCardInfoSecondaryValue" format="string" />

    <attr name="terraCardButtonText" format="string" />
    <attr name="terraCardButtonVisible" format="boolean" />
    <attr name="terraCardButtonEnabled" format="boolean" />
    <attr name="terraCardButtonType" format="enum">
        <enum name="primary" value="0" />
        <enum name="secondary" value="1" />
        <enum name="outlinedPrimary" value="2" />
        <enum name="outlinedSecondary" value="3" />
        <enum name="danger" value="4" />
        <enum name="text" value="5" />
    </attr>
</declare-styleable>
```

---

## 10. Internal layout recommendation

### File
- `android/terra-ui/src/main/res/layout/terra_view_card.xml`

### Internal structure
Minimal internal view tree:
- root card container
- menu container
- customer info container
- information container
- button container

Kenapa pakai internal XML:
- lebih enak maintain
- visibility section lebih gampang
- anatomy card cukup kompleks untuk ditulis full programmatic

---

## 11. Resource structure

### Kotlin
- `android/terra-ui/src/main/java/com/terra/ds/card/TerraCardView.kt`

### Attrs
- `android/terra-ui/src/main/res/values/attrs_card.xml`

### Layout
- `android/terra-ui/src/main/res/layout/terra_view_card.xml`

### Styles
- `android/terra-ui/src/main/res/values/styles_card.xml`

### Catalog demo
- `android/catalog/src/main/res/layout/terra_catalog_card.xml`

---

## 12. Catalog demo wajib

Di `terra_catalog_card.xml`, minimal tampilkan:

### Menu
- 1 Menu Card

### Information
- 1 Information Full
- 1 Information Info + Button
- 1 Information Customer Only
- 1 Information Customer + Button
- 1 Information Info Only

### Total
- **6 demo card**

---

## 13. Behavior rules

## A. Variant precedence
Variant menentukan layout utama.

Contoh:
- kalau `MENU`, semua field information/customer/button di-ignore secara visual
- kalau `INFORMATION_*`, field menu di-ignore secara visual

---

## B. Button visibility
Kalau variant memang tidak butuh button:
- button section selalu hidden

Kalau variant butuh button:
- button section visible jika `terraCardButtonVisible = true`
- jika `terraCardButtonVisible = false`, hide button section

---

## C. Empty value handling
Rekomendasi:
- row atau text yang kosong **di-hide**
- jangan tampilkan placeholder palsu kecuali memang diminta design

Misalnya:
- `secondary info` kosong → row kedua hidden
- `customer secondary text` kosong → text hidden

---

## 14. Phase implementasi

## Phase 1
Scaffold dasar:
- package `card`
- `TerraCardView.kt`
- `attrs_card.xml`
- `terra_view_card.xml`
- `styles_card.xml`

## Phase 2
Implement variant:
- `MENU`
- `INFORMATION_FULL`
- `INFORMATION_INFO_BUTTON`
- `INFORMATION_CUSTOMER_ONLY`
- `INFORMATION_CUSTOMER_BUTTON`
- `INFORMATION_INFO_ONLY`

## Phase 3
Tambahkan catalog:
- `terra_catalog_card.xml`

## Phase 4
Update status dokumen:
- `WEB_TO_ANDROID_GAP_AUDIT.md`
- `WEB_TO_ANDROID_TRACKER.md`

---

## 15. Checklist praktis implementasi

### 1. Cek tampilan
- card container terlihat jelas
- radius/border/background pakai token Android valid
- hierarchy text kebaca
- spacing antar section konsisten

### 2. Cek cara pakai
- bisa dari XML
- bisa dari Kotlin property
- button punya text/visible/enabled/type
- button click listener jelas

### 3. Cek kelengkapan variant
- semua 6 variant tersedia
- demo catalog lengkap
- empty row di-hide
- visibility section sesuai variant

---

## 16. Open decisions yang masih bisa difinalkan nanti
Masih ada beberapa hal yang bisa diputuskan belakangan tanpa merusak struktur utama:

1. Menu card perlu icon atau tidak
2. Customer info perlu avatar/icon atau text-only
3. Information rows cukup 2 dulu atau perlu list dinamis nanti
4. Button hanya 1 CTA atau nanti bisa berkembang

---

## 17. Keputusan final yang dipakai sekarang
Struktur final yang saya rekomendasikan:

- **1 component:** `TerraCardView`
- **1 enum:** `TerraCardVariant`
- **5 information variants + 1 menu variant**
- **internal layout pakai XML**
- **button pakai `TerraButtonView`**
- **phase awal pakai max 2 info rows**

---

## 18. Kesimpulan
Versi ini lebih rapih dibanding model sebelumnya karena:

- tidak perlu `type + scenario`
- cukup `variant`
- gampang dipakai dari XML
- gampang dimengerti tim
- scalable untuk implementasi awal
