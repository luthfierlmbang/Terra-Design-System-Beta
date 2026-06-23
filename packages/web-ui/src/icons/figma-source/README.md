# Figma icon source

Taruh hasil export SVG dari Figma icon set TERRA di folder ini.

Aturan naming sementara:
- format file: `<category>__<name>.svg`
- contoh: `action__plus.svg`
- gunakan slug yang sama dengan `TerraIconCategory` dan `TerraIconName`

Folder ini menjadi source of truth asset mentah sebelum dinormalisasi ke registry web.

Sesudah menambah/mengubah file SVG di sini, jalankan generator:

```bash
node scripts/generate-icon-registry.mjs
```
