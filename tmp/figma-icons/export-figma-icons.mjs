import { writeFile } from 'node:fs/promises';
import path from 'node:path';

// Fresh extraction from Figma node 564:60 (TERRA Icon documentation).
// Asset URLs come from the Figma MCP `get_design_context` response and are
// valid for ~7 days. Re-run get_design_context if they expire.
const ASSET_BASE = 'https://www.figma.com/api/mcp/asset/';
const outputDir = '/Users/luthfierlambang/Documents/Design System TERRA/packages/web-ui/src/icons/figma-source';

// [fileName, assetId]
const icons = [
  // --- Action ---
  ['action__plus.svg', '5f84c524-3890-4471-825b-33a295246801'],
  ['action__check.svg', 'b6cee1ae-0fd7-46be-80ef-e347b3db5917'],
  ['action__close.svg', '6ed66f7a-28db-4353-a765-08db580aa49f'],
  ['action__delete.svg', '8ea50b5f-e695-476e-a8df-cbf069beccf0'],
  ['action__hamburger-menu.svg', '0c4a1175-35c6-4570-af56-563f8b1b731a'],
  ['action__edit.svg', 'ea888f2a-9e3b-4f4b-8b06-14069f8da1bd'],
  ['action__center.svg', '9f0359d6-1f11-4024-8ec6-b68e11f452ff'],
  ['action__align-left.svg', '4af32c8e-3baa-4299-ac8f-bf1f0b7c8703'],
  ['action__minus.svg', '5cb37b61-8793-4279-8bb8-c5b14f77bc27'],
  ['action__refresh.svg', '4fdc1e42-eadd-4df3-b658-e10efa5226d6'],
  ['action__qr-scan.svg', 'b46e385a-3028-471c-b196-b4f1b3f2a6a4'],
  ['action__search.svg', 'eb286ac4-701e-4adb-9fcf-813d09929462'],
  ['action__sort.svg', '6cb837d7-32f1-4cd9-8e53-ba786ec00366'],
  ['action__download.svg', 'cbb6e384-2186-4ed4-b0dc-b220e92e14bd'],
  ['action__upload.svg', '44ad3e90-f4c2-4780-8f82-b45aad8a19ed'],
  ['action__print.svg', '554fdf1a-f1d6-490b-a298-a7c5fb260aa4'],
  ['action__logout.svg', 'de31ff19-064e-4f4b-9bda-6774f3b49267'],
  ['action__manual-sync.svg', '2e0a5d7c-8300-4ee4-b361-3437ddb7e671'],
  ['action__digital-sign.svg', '90300bca-57fc-4891-8726-be9be0f501a7'],
  ['action__search-user.svg', '33c63769-dcf4-48b0-8dad-6bfad980cea9'],
  ['action__filter-2.svg', 'ba899e9c-6253-4296-8add-2705e421ede5'],

  // --- Navigation ---
  ['navigation__arrow-left.svg', '5b050fcd-b31f-4f96-8ae5-e3abcb6c0000'],
  ['navigation__arrow-right.svg', 'e18c7074-78b4-4f47-9db1-151d9e31ebb9'],
  ['navigation__chevron-down.svg', 'b162d7f0-38de-430e-86f9-12c7b4b2183c'],
  ['navigation__chevron-left.svg', '5a69c76a-68ab-46f9-aabb-fd9b7895d3d4'],
  ['navigation__chevron-right.svg', '452eef34-72b0-4d29-8cc2-5c7f183123fd'],
  ['navigation__chevron-up.svg', '3c5a9507-5ce1-4e6e-820b-66e4a3b34ae2'],
  ['navigation__kebab-vertical.svg', 'be9aba1f-b5fd-4007-8ac6-22a4856c2528'],
  ['navigation__kebab-horizontal.svg', '1c17c6f2-36ba-4928-a742-80f51be71ae8'],
  ['navigation__expand.svg', '8d19c780-eeb0-4b0f-bf2f-12ad082b8b1c'],
  ['navigation__expand-2.svg', 'ec0475f1-6f96-4a7c-8ed0-f7ba7854a229'],

  // --- Status ---
  ['status__done.svg', '76b4bba0-7274-45f4-bc01-6ee3c003a7dc'],
  ['status__in-progress.svg', '9fc5828e-53b6-4f92-aad8-b3c60daf5107'],
  ['status__offline.svg', '3b56763d-6551-49ef-8b10-32db45e34db9'],
  // Eye (visibility) — node "State" Default/Disabled
  ['status__default.svg', '288ed853-ec9d-4e9e-88bc-388bc42e45c5'],
  ['status__disabled.svg', '5248bde6-9711-439e-a31e-55e0c3645fdf'],

  // --- Info ---
  ['info__exclamation.svg', '70b014b5-385b-42a6-8282-2fe4765d697d'],
  ['info__information.svg', 'f290b08a-8d0e-47ce-af29-cef95989c6cc'],
  ['info__questionmark.svg', 'ffbd8113-e0e1-4850-a45e-eac4a1640397'],
  ['info__quest.svg', '215a7c64-3649-4c92-9b52-601040487544'],

  // --- Domain ---
  ['domain__cash-in.svg', '5a50abf5-b355-4b95-bb52-6ee6cb25df46'],
  ['domain__cash-out.svg', 'bf418a46-7096-413b-ada9-07ff8fcd1526'],
  ['domain__nasabah.svg', '53ccaa31-f9f3-45ca-a4cc-111d2213582f'],
  ['domain__cash.svg', '31fdd803-259f-4a6e-ac01-1629175f24eb'],
  ['domain__account-mutation.svg', 'e73a229f-1385-4b0e-a6ca-fd9852742185'],
  ['domain__achievement.svg', '0e3613a6-1404-46e5-b16e-d008e2c87ed8'],
  ['domain__akusisi.svg', '9f332916-62c9-4ca8-81b5-70fa0fafdac0'],
  ['domain__bank.svg', 'e67cdb9d-ee07-4a47-a870-a412e1e59b72'],
  ['domain__eform.svg', 'e871944c-5670-4ea2-8aaf-dec67a069711'],
  ['domain__interview-survey.svg', 'f745c75b-41fe-4505-ab20-0d4631eb1b45'],
  ['domain__klaim-asuransi.svg', '45ac9bbf-520a-48ad-826a-98d904578037'],
  ['domain__learning.svg', 'af22005b-ee7c-45a4-8e03-15ef45b07eb4'],
  ['domain__report-transaction.svg', '1d2b0c4d-de68-4d49-b2e7-7d7b000d5316'],
  ['domain__report.svg', 'f0fd9cb2-aa1a-4487-b455-72a0ae5b7e52'],
  ['domain__restruktur.svg', 'a81da736-ad95-4a03-8f68-4fca1ae7756e'],

  // --- System (Default grid) ---
  ['system__alarm.svg', 'f665a996-48ea-4a20-8aec-3790271a4833'],
  ['system__calendar.svg', 'ed92bc23-91aa-449c-b155-9fbdd38b510c'],
  ['system__camera.svg', '271532ca-12aa-4b94-9ffb-3ce4a2e889a6'],
  ['system__chat.svg', 'f454e383-ce3f-4696-97a1-e2253ae40162'],
  ['system__dashboard.svg', 'e43dd88a-c093-4663-9b9a-13b8ee8d8ac0'],
  ['system__email.svg', '47fd0f40-6f33-4a42-9427-78bca0e13673'],
  ['system__email-2.svg', '0942c24d-c3dc-412a-8d02-2e13f7d72c1f'],
  ['system__star.svg', '5cd2a84f-b43e-401c-b237-a9a909540615'],
  ['system__face-recognition.svg', '287a208c-ee54-4915-b619-1852e2075837'],
  ['system__grow.svg', '18ec21f5-fc1f-406d-88e8-7164c334cb9d'],
  ['system__history.svg', '42b62a8b-7ef4-4b54-817c-ec5347b4e75e'],
  ['system__home.svg', 'cfd485e0-b712-44b3-b122-4ee8ab3984a3'],
  ['system__image.svg', 'b33125ae-d667-4c32-91aa-f511dfefbfd6'],
  ['system__lock.svg', 'da591b14-d5ff-4bee-860a-8c4fed612b59'],
  ['system__key.svg', '99becef0-c2d0-4097-ad94-ec53a8dbfae6'],
  ['system__map.svg', '7313e7b5-ab1d-4e30-8a8d-29af240caf94'],
  ['system__monitoring.svg', 'da1c3535-cd8b-4392-8d7c-d8153b635351'],
  ['system__notification.svg', '54d73f85-205c-45dd-a317-ea7b2577b6b4'],
  ['system__performance.svg', '89611485-9ed8-47d9-9078-1718fa48b389'],
  ['system__scooter.svg', 'f584400b-fa90-43ac-ba79-6940b06cf02c'],
  ['system__settings.svg', 'b11c9c39-5b53-4f71-b6b4-9c29c289f153'],
  ['system__switch-2.svg', '844d6a0c-d21d-4506-9ad0-c657b3635a2f'],
  ['system__switch.svg', '1eddb762-c477-4e7e-8399-f80a21882670'],
  ['system__target.svg', '9332268c-b45f-4e35-8111-bcd20cec5326'],
  ['system__time.svg', '10e11732-5e7e-4c7b-89fc-8015500c2faa'],
  ['system__user.svg', '29b3f11a-83ca-4191-819f-76aca29f5744'],
  ['system__vault.svg', '8a49de46-5c88-41d7-880f-8ab1b89246cd'],
  ['system__light.svg', 'd3a6ad47-3401-45e0-9bae-8b4379999cb8'],
  ['system__calculator.svg', '0a4568d6-8c45-4538-9b85-f2c9764ad8ba'],

  // --- Priority (NEW, colored vectors — colors preserved) ---
  ['priority__highest.svg', '30682c7f-3135-4d27-8693-5e8c0726410e'],
  ['priority__low.svg', 'f962199a-9be1-4fe5-8ff2-b68c932f85bb'],
  ['priority__medium.svg', 'b81376ec-00ea-419f-8db2-2edfc8dc1234'],
  ['priority__high.svg', '2d0f3942-287a-49aa-8735-76a001d8d8c7'],
  ['priority__more-highest.svg', '0cc93d4c-c36b-4713-b15c-be607dcaf6ec'],

  // --- Menu (NEW, raster PNG illustrations) ---
  ['menu__akusisi-nasabah.png', 'baed689d-4276-4990-9db7-fbea84357ba0'],
  ['menu__keagenan.png', 'eae17675-00d8-4417-89cb-c1e5d20f6628'],
  ['menu__pemeliharaan-nasabah.png', '99372c3c-e6f8-4042-845a-d3cfd8af5b0b'],
  ['menu__transaksi.png', 'd0ea8258-5ebd-4f63-9092-e9ed9b3c9ba6'],
];

for (const [fileName, assetId] of icons) {
  const url = ASSET_BASE + assetId;
  const response = await fetch(url);
  if (!response.ok) {
    throw new Error(`Failed to fetch ${fileName} (${url}): ${response.status}`);
  }
  const isBinary = fileName.endsWith('.png');
  const data = isBinary
    ? Buffer.from(await response.arrayBuffer())
    : await response.text();
  await writeFile(path.join(outputDir, fileName), data);
  console.log(`Exported ${fileName}`);
}

console.log(`Done: ${icons.length} assets.`);
