# Local Build Setup

Dokumen ini menjelaskan cara menjalankan workspace Android TERRA secara lokal setelah Gradle workspace minimal discaffold.

---

## 1. Prasyarat

Sebelum build, pastikan local machine punya:

- JDK 17
- Android Studio terbaru, atau
- Android SDK dengan `compileSdk 34`
- Gradle wrapper files di folder `android/`

Recommended:
- Android Studio Hedgehog atau lebih baru
- JDK 17

---

## 2. Kondisi workspace saat ini

Workspace `android/` saat ini sudah punya:
- `settings.gradle.kts`
- `build.gradle.kts`
- `gradle.properties`
- module `terra-tokens`
- module `terra-ui`
- module `catalog`

Namun build lokal tetap membutuhkan:
- Java runtime / JDK
- Gradle wrapper (`gradlew`, `gradlew.bat`, `gradle/wrapper/*`) atau Gradle terpasang di machine developer

---

## 3. Cara generate Gradle wrapper

Kalau local machine sudah punya Gradle terinstall, jalankan dari folder `android/`:

```sh
gradle wrapper
```

Setelah itu seharusnya akan muncul file seperti:

```text
android/gradlew
android/gradlew.bat
android/gradle/wrapper/gradle-wrapper.jar
android/gradle/wrapper/gradle-wrapper.properties
```

Kalau team lebih suka versi wrapper tertentu, gunakan command yang sesuai di local environment.

---

## 4. Cara buka di Android Studio

1. Open folder `android/`
2. Tunggu Gradle sync
3. Pastikan SDK dan JDK terdeteksi
4. Jalankan module `catalog`

`catalog` saat ini adalah app demo minimal yang me-render layout catalog awal.

---

## 5. Command build yang direkomendasikan

Dijalankan dari folder `android/` setelah wrapper tersedia.

### Sync/build dasar
```sh
./gradlew tasks
./gradlew projects
```

### Validasi library tokens
```sh
./gradlew :terra-tokens:assemble
```

### Validasi library UI
```sh
./gradlew :terra-ui:assemble
```

### Validasi app catalog
```sh
./gradlew :catalog:assembleDebug
```

### Validasi resource + Kotlin compile
Bila perlu, lanjutkan ke task compile yang lebih spesifik sesuai hasil Gradle task listing.

---

## 6. Urutan validasi yang disarankan

Urutan paling aman:

1. `./gradlew projects`
2. `./gradlew :terra-tokens:assemble`
3. `./gradlew :terra-ui:assemble`
4. `./gradlew :catalog:assembleDebug`

Kalau ada error, perbaiki mulai dari module paling bawah:
- `terra-tokens`
- `terra-ui`
- `catalog`

---

## 7. Apa yang perlu dicek saat build gagal

Kalau build gagal, cek hal-hal ini dulu:

- JDK version sesuai atau tidak
- Android SDK / compileSdk tersedia atau tidak
- attrs sinkron dengan Kotlin parsing atau tidak
- resource reference valid atau tidak
- dependency module sudah benar atau tidak
- manifest/theme/activity minimal sudah valid atau belum

---

## 8. Current known blocker from non-local environment

Pada environment kerja saat dokumen ini dibuat:
- `gradle` command belum tersedia
- Java runtime belum tersedia

Artinya:
- Gradle wrapper belum bisa digenerate otomatis dari environment tersebut
- build validation nyata harus dijalankan di local machine developer yang punya JDK/Gradle atau Android Studio

---

## 9. Summary

Agar workspace Android TERRA bisa dibuild secara lokal, developer perlu:

1. install JDK 17
2. buka folder `android/`
3. generate Gradle wrapper bila belum ada
4. jalankan build module bertahap

Setelah itu workspace ini siap dipakai untuk validation, preview, dan integrasi library lebih lanjut.
