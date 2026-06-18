# TERRA TAB Token Applier

Local Figma development plugin untuk membuat TERRA TAB primitive, semantic, dan component color variables tanpa Codex Figma connector.

## Cara Pakai

1. Buka Figma Desktop.
2. Buka file **TERRA TAB Design System v.1.0 (Copy)**.
3. Menu: `Plugins` -> `Development` -> `Import plugin from manifest...`.
4. Pilih file:
   `/Users/luthfierlambang/Documents/Design System TERRA/figma-token-applier/manifest.json`
5. Jalankan plugin: `Plugins` -> `Development` -> `TERRA TAB Token Applier`.

Plugin akan:

- membuat collection `TERRA Primitive`, `TERRA Semantic Color`, dan `TERRA Component Color`;
- membuat/update primitive variables;
- membuat/update semantic variables sebagai alias ke primitives;
- membuat/update component variables sebagai alias ke semantic variables;
- mengisi WEB code syntax dengan format `--color-...`;
- mencoba bind variable ke komponen prioritas: Button, FAB, Overlay, dan Page Identifier.

## Catatan

- Plugin bersifat non-destructive: tidak menghapus style lama.
- Jika variable sudah ada, plugin akan update value/syntax/scope.
- Jika node komponen tidak ditemukan, plugin tetap membuat variables dan menampilkan warning.
