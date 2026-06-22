import {
  TerraButton,
  TerraCheckbox,
  TerraChip,
  TerraEmptyState,
  TerraHeader,
  TerraIcon,
  TerraLoading,
  TerraNavbar,
  TerraPageControl,
  TerraProgressBar,
  TerraQuantityEditor,
  TerraRadio,
  TerraSearchField,
  TerraSelectField,
  TerraStepper,
  TerraTabs,
  TerraTextField,
  TerraTicker,
  TerraTimer,
  TerraToggle,
  TerraTokenShowcase,
  type TerraButtonType,
  type TerraIconCategory,
  type TerraIconName,
  type TerraNavbarItem,
  type TerraTabItem,
} from '@terra/web-ui';
import type { DocEntry } from '../lib/types';

/* ------------------------------------------------------------------ */
/* Shared demo data                                                    */
/* ------------------------------------------------------------------ */

const buttonVariants: TerraButtonType[] = [
  'primary',
  'secondary',
  'outlined-primary',
  'outlined-secondary',
  'danger',
  'text',
];

type IconRow = {
  names: TerraIconName[];
};

type IconGroup = {
  category: TerraIconCategory;
  label: string;
  description: string;
  rows: IconRow[];
};

const iconGroups: IconGroup[] = [
  {
    category: 'shapes',
    label: 'Placeholder',
    description: 'Bentuk awal yang terlihat di node.',
    rows: [{ names: ['square', 'circle'] }],
  },
  {
    category: 'system',
    label: 'Default',
    description: 'Grid ikon default yang besar pada node.',
    rows: [
      { names: ['home', 'settings', 'calendar', 'camera', 'chat', 'dashboard', 'email', 'email-2', 'face-recognition', 'grow'] },
      { names: ['history', 'image', 'key', 'light', 'light-ray-top', 'light-ray-right', 'light-ray-bottom-left', 'light-ray-top-left', 'lock', 'map'] },
      { names: ['monitoring', 'performance', 'scooter', 'star', 'switch', 'switch-2', 'target', 'vault', 'user', 'notification'] },
    ],
  },
  {
    category: 'action',
    label: 'Action',
    description: 'Ikon aksi yang terlihat di node.',
    rows: [
      { names: ['plus', 'check', 'close', 'delete', 'edit', 'refresh', 'search', 'sort', 'minus', 'filter-2'] },
      { names: ['download', 'upload', 'print', 'logout', 'manual-sync', 'digital-sign', 'search-user', 'center', 'align-left', 'qr-scan'] },
    ],
  },
  {
    category: 'navigation',
    label: 'Navigation',
    description: 'Ikon navigasi di node.',
    rows: [{ names: ['arrow-left', 'arrow-right', 'chevron-left', 'chevron-right', 'chevron-up', 'chevron-down', 'kebab-horizontal', 'kebab-vertical', 'hamburger-menu', 'expand', 'expand-2'] }],
  },
  {
    category: 'status',
    label: 'Status',
    description: 'Ikon status utama di node.',
    rows: [{ names: ['done', 'in-progress', 'offline'] }],
  },
  {
    category: 'status',
    label: 'Eye',
    description: 'Ikon visibilitas di node (default & disabled).',
    rows: [{ names: ['default', 'disabled'] }],
  },
  {
    category: 'info',
    label: 'Information',
    description: 'Ikon informasi di node.',
    rows: [{ names: ['exclamation', 'information', 'questionmark', 'counter', 'quest'] }],
  },
  {
    category: 'domain',
    label: 'Cash',
    description: 'Ikon keuangan di node.',
    rows: [{ names: ['cash-in', 'cash-out', 'cash'] }],
  },
  {
    category: 'menu',
    label: 'Menu',
    description: 'Ilustrasi menu berwarna (raster) di node.',
    rows: [{ names: ['akusisi-nasabah', 'keagenan', 'pemeliharaan-nasabah', 'transaksi'] }],
  },
  {
    category: 'priority',
    label: 'Priority',
    description: 'Ikon prioritas berwarna di node.',
    rows: [{ names: ['highest', 'high', 'medium', 'low', 'more-highest'] }],
  },
];

const navbarItems: TerraNavbarItem[] = [
  { id: 'home', label: 'Home', icon: <TerraIcon category="system" name="home" size="sm" /> },
  { id: 'activity', label: 'Aktivitas', icon: <TerraIcon category="domain" name="report" size="sm" /> },
  { id: 'notification', label: 'Notifikasi', icon: <TerraIcon category="system" name="notification" size="sm" /> },
];

const previewTabs: TerraTabItem[] = [
  { id: 'overview', label: 'Overview' },
  { id: 'detail', label: 'Detail' },
  { id: 'history', label: 'History' },
];

const cardInfoRows = Array.from({ length: 8 }, (_, index) => ({
  label: 'Write Information Here...',
  value: 'Write Information Here...',
  visible: index === 0 || index === 1 || index === 2 || index === 3 || index === 4 || index === 5 || index === 6 || index === 7,
}));

function TerraCardShowcase() {
  return (
    <div className="cardShowcase">
      <div className="cardShowcase__topbar">
        <div className="cardShowcase__brandLockup">
          <img src="/tmp/figma-card-assets/terra-header-logo.svg" alt="TERRA" className="cardShowcase__brandLogoImage" />
          <span className="cardShowcase__brandText">Terra Design System</span>
        </div>
        <div className="cardShowcase__sparkles" aria-hidden="true">
          {Array.from({ length: 21 }).map((_, index) => (
            <span key={index} />
          ))}
        </div>
      </div>

      <div className="cardShowcase__hero">
        <h3>Card</h3>
        <p>Icon berfungsi sebagai jembatan antara pengguna dan fungsionalitas aplikasi.</p>
      </div>

      <section className="cardShowcase__group">
        <div className="cardShowcase__groupHeader">
          <h4>Menu Card</h4>
          <p>Menandai area yang belum terisi, memberikan petunjuk visual yang jelas untuk konten yang akan datang.</p>
        </div>
        <div className="cardShowcase__surface">
          <div className="terraCard terraCard--menu">
            <div className="terraCard__menuLead">
              <span className="terraCard__menuLeadIcon">
                <TerraIcon category="menu" name="transaksi" size={24} />
              </span>
              <span className="terraCard__menuLabel">Menu Name</span>
            </div>
            <TerraIcon category="navigation" name="chevron-right" size="sm" className="terraCard__menuArrow" />
          </div>
        </div>
      </section>

      <section className="cardShowcase__group">
        <div className="cardShowcase__groupHeader">
          <h4>Information Card</h4>
          <p>Menandai area yang belum terisi, memberikan petunjuk visual yang jelas untuk konten yang akan datang.</p>
        </div>
        <div className="cardShowcase__surface cardShowcase__surface--stacked">
          <article className="terraCard terraCard--information">
            <header className="terraCard__header">
              <div className="terraCard__profile">
                <img src="/tmp/figma-card-assets/siti-burhan-avatar-56586a.svg" alt="Siti Burhan" className="terraCard__avatarImage" />
                <div className="terraCard__profileCopy">
                  <strong>Siti Burhan</strong>
                  <span>NIK 1111 2222 3333 4444</span>
                </div>
              </div>
              <div className="terraCard__headerMeta">
                <span className="terraCard__badge">Belum Terdaftar</span>
                <TerraIcon category="navigation" name="kebab-horizontal" size="sm" />
              </div>
            </header>
            <strong className="terraCard__sectionTitle">Label</strong>
            <div className="terraCard__infoList">
              {cardInfoRows.map((row, index) => (
                <div key={index} className="terraCard__infoRow">
                  <span>{row.label}</span>
                  <strong>{row.value}</strong>
                </div>
              ))}
            </div>
            <TerraButton variant="primary" fullWidth>
              Button
            </TerraButton>
          </article>

          <article className="terraCard terraCard--information">
            <header className="terraCard__header">
              <div className="terraCard__profile">
                <img src="/tmp/figma-card-assets/siti-burhan-avatar-56586a.svg" alt="Siti Burhan" className="terraCard__avatarImage" />
                <div className="terraCard__profileCopy">
                  <strong>Siti Burhan</strong>
                  <span>NIK 1111 2222 3333 4444</span>
                </div>
              </div>
              <div className="terraCard__headerMeta">
                <span className="terraCard__badge">Belum Terdaftar</span>
                <TerraIcon category="navigation" name="kebab-horizontal" size="sm" />
              </div>
            </header>
          </article>

          <article className="terraCard terraCard--information terraCard--informationCompact">
            <strong className="terraCard__sectionTitle">Label</strong>
            <div className="terraCard__infoList">
              {cardInfoRows.map((row, index) => (
                <div key={index} className="terraCard__infoRow">
                  <span>{row.label}</span>
                  <strong>{row.value}</strong>
                </div>
              ))}
            </div>
          </article>
        </div>
      </section>

      <section className="cardShowcase__group">
        <div className="cardShowcase__groupHeader">
          <h4>Activity Card</h4>
          <p>Menandai area yang belum terisi, memberikan petunjuk visual yang jelas untuk konten yang akan datang.</p>
        </div>
        <div className="cardShowcase__surface">
          <article className="terraCard terraCard--activity">
            <header className="terraCard__activityHeader">
              <span className="terraCard__activityTag">
                <TerraIcon category="system" name="notification" size={12} />
                Label Name
              </span>
              <TerraIcon category="navigation" name="chevron-right" size="sm" />
            </header>
            <div className="terraCard__activityBody">
              <span className="terraCard__activityName">Input Activity</span>
              <div className="terraCard__activityTime">
                <TerraIcon category="system" name="calendar" size={24} />
                <span>Input Time</span>
              </div>
              <button type="button" className="terraCard__activityButton">
                Atur Jam
              </button>
            </div>
          </article>
        </div>
      </section>

      <div className="cardShowcase__footer">
        <img src="/tmp/figma-card-assets/terra-header-logo.svg" alt="TERRA" className="cardShowcase__footerLogoImage" />
        <div className="cardShowcase__footerRule" />
        <span>Card</span>
      </div>
    </div>
  );
}

function asString(value: string | boolean | undefined, fallback: string) {
  return typeof value === 'string' ? value : fallback;
}

function asBoolean(value: string | boolean | undefined, fallback = false) {
  return typeof value === 'boolean' ? value : fallback;
}

const errorIllustration = (
  <svg viewBox="0 0 240 240" fill="none" xmlns="http://www.w3.org/2000/svg" width="160" height="160">
    <circle cx="120" cy="120" r="96" fill="#F0F4F4" />
    <rect x="80" y="72" width="80" height="104" rx="8" fill="#fff" stroke="#DADADA" strokeWidth="2" />
    <rect x="92" y="88" width="56" height="6" rx="3" fill="#DADADA" />
    <rect x="92" y="102" width="40" height="6" rx="3" fill="#DADADA" />
    <circle cx="120" cy="148" r="16" fill="#E53935" />
    <path d="M114 148h12M120 142v12" stroke="#fff" strokeWidth="2.5" strokeLinecap="round" />
  </svg>
);

/* A consistent width for form-style components on the tablet surface. */
const formStyle: React.CSSProperties = { width: 360, maxWidth: '100%' };

const androidSnippets = {
  unavailable: {
    xml: {
      language: 'xml' as const,
      note: 'Android XML snippet belum tersedia di source Android saat ini. Tambahkan contoh dari terra-ui/catalog saat implementasinya sudah ada.',
    },
    kotlin: {
      language: 'kotlin' as const,
      note: 'Snippet Kotlin belum tersedia di source Android saat ini. Tambahkan contoh usage dari activity/fragment saat implementasinya sudah ada.',
    },
  },
  foundations: {
    xml: {
      language: 'xml' as const,
      code: `<resources>
    <color name="terra_bg_button_primary">#027479</color>
    <color name="terra_color_secondary_500">#F58220</color>
    <dimen name="terra_radius_md">8dp</dimen>
    <style name="TextAppearance.Terra.Body.Medium" parent="TextAppearance.MaterialComponents.Body1" />
</resources>`,
      note: 'Di spec saat ini fondasi Android didokumentasikan sebagai resource token, bukan custom view XML tunggal.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val primaryButtonColor = ContextCompat.getColor(context, R.color.terra_bg_button_primary)
val accentColor = ContextCompat.getColor(context, R.color.terra_color_secondary_500)
val mediumRadius = resources.getDimension(R.dimen.terra_radius_md)

val ctaText = TextView(context).apply {
    setTextAppearance(R.style.TextAppearance_Terra_Body_Medium)
    text = "CTA / Medium"
}`,
      note: 'Gunakan resource token Android untuk menjaga parity dengan semantic token di docs web.',
    },
  },
  icon: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.icon.TerraIconView
    android:layout_width="24dp"
    android:layout_height="24dp"
    app:terraIconCategory="action"
    app:terraIconName="search" />`,
      note: 'Diambil dari TERRA-Design-System-Spec.md dan sample catalog header/icon.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val icon = findViewById<TerraIconView>(R.id.terraIcon)
icon.terraIconCategory = TerraIconCategory.ACTION
icon.terraIconName = TerraIconName.SEARCH`,
    },
  },
  button: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.button.TerraButtonView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Primary"
    app:terraButtonType="primary"
    app:terraButtonSize="normal" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_buttons.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val button = findViewById<TerraButtonView>(R.id.primaryButton)
button.text = "Primary"
button.terraButtonType = TerraButtonType.PRIMARY
button.terraButtonSize = TerraButtonSize.NORMAL`,
      note: 'Disesuaikan ke API property TerraButtonView yang ada di source Android saat ini.',
    },
  },
  checkbox: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.selection.TerraCheckboxView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:terraSelectionChecked="true"
    app:terraSelectionLabel="Checkbox" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_feedback_selection.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val checkbox = findViewById<TerraCheckboxView>(R.id.terraCheckbox)
checkbox.terraSelectionLabel = "Checkbox"
checkbox.terraSelectionChecked = true`,
    },
  },
  radio: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.selection.TerraRadioView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:terraSelectionLabel="Radio" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_feedback_selection.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val radio = findViewById<TerraRadioView>(R.id.terraRadio)
radio.terraSelectionLabel = "Radio"
radio.terraSelectionChecked = true`,
    },
  },
  toggle: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.selection.TerraToggleView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:terraSelectionChecked="true"
    app:terraSelectionLabel="Toggle" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_feedback_selection.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val toggle = findViewById<TerraToggleView>(R.id.terraToggle)
toggle.terraSelectionLabel = "Toggle"
toggle.terraSelectionChecked = true`,
    },
  },
  chip: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.selection.TerraChipView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:terraChipSelected="true"
    app:terraChipShowCounter="true"
    app:terraChipText="This is Chips" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_feedback_selection.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val chip = findViewById<TerraChipView>(R.id.terraChip)
chip.terraChipText = "This is Chips"
chip.terraChipSelected = true
chip.terraChipShowCounter = true`,
    },
  },
  tabs: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.selection.TerraTabView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraTabItems="Overview,Detail,History"
    app:terraTabSelectedIndex="1" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_feedback_selection.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val tabs = findViewById<TerraTabView>(R.id.terraTabs)
tabs.terraTabItems = listOf("Overview", "Detail", "History")
tabs.terraTabSelectedIndex = 1`,
      note: 'Gunakan list item tab yang sama dengan konfigurasi XML agar behavior konsisten.',
    },
  },
  textField: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.form.TerraTextFieldView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraTextFieldHelper="Helper Type Here"
    app:terraTextFieldLabel="Label"
    app:terraTextFieldPlaceholder="Input text" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_forms_overlay.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val textField = findViewById<TerraTextFieldView>(R.id.terraTextField)
textField.terraTextFieldLabel = "Label"
textField.terraTextFieldPlaceholder = "Input text"
textField.terraTextFieldHelper = "Helper Type Here"`,
    },
  },
  searchField: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.form.TerraSearchBarView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraSearchBarState="default"
    app:terraSearchBarText="Search something" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_forms_overlay.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val searchBar = findViewById<TerraSearchBarView>(R.id.terraSearchBar)
searchBar.terraSearchBarState = TerraSearchBarState.DEFAULT
searchBar.terraSearchBarText = "Search something"`,
    },
  },
  selectField: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.form.TerraSelectView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraSelectFilled="true"
    app:terraSelectPlaceholder="Choose item" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_forms_overlay.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val select = findViewById<TerraSelectView>(R.id.terraSelect)
select.terraSelectPlaceholder = "Choose item"
select.terraSelectFilled = true`,
    },
  },
  quantityEditor: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.form.TerraQuantityEditorView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:terraQuantityState="default"
    app:terraQuantityValue="1.000" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_forms_overlay.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val quantityEditor = findViewById<TerraQuantityEditorView>(R.id.terraQuantityEditor)
quantityEditor.terraQuantityState = TerraQuantityState.DEFAULT
quantityEditor.terraQuantityValue = "1.000"`,
    },
  },
  progressBar: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.feedback.TerraProgressBarView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraProgressBarType="bar1" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_feedback_selection.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val progressBar = findViewById<TerraProgressBarView>(R.id.terraProgressBar)
progressBar.terraProgressBarType = TerraProgressBarType.BAR1`,
    },
  },
  stepper: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.feedback.TerraStepperView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:terraStepperCount="4"
    app:terraStepperCurrentStep="2" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_feedback_selection.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val stepper = findViewById<TerraStepperView>(R.id.terraStepper)
stepper.terraStepperCount = 4
stepper.terraStepperCurrentStep = 2`,
    },
  },
  ticker: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.feedback.TerraTickerView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraTickerDetailed="true"
    app:terraTickerTitle="Warning Title Text"
    app:terraTickerMessage="Warning information maximum 2 line."
    app:terraTickerTone="warning" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_feedback_selection.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val ticker = findViewById<TerraTickerView>(R.id.terraTicker)
ticker.terraTickerTitle = "Warning Title Text"
ticker.terraTickerMessage = "Warning information maximum 2 line."
ticker.terraTickerTone = TerraTickerTone.WARNING
ticker.terraTickerDetailed = true`,
    },
  },
  loading: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.feedback.TerraLoadingView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraLoadingMessage="Dalam Proses..."
    app:terraLoadingProgress="80" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_feedback_selection.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val loading = findViewById<TerraLoadingView>(R.id.terraLoading)
loading.terraLoadingMessage = "Dalam Proses..."
loading.terraLoadingProgress = 80`,
    },
  },
  pageControl: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.feedback.TerraPageControlView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:terraPageCount="5"
    app:terraSelectedPage="2" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_feedback_selection.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val pageControl = findViewById<TerraPageControlView>(R.id.terraPageControl)
pageControl.terraPageCount = 5
pageControl.terraSelectedPage = 2`,
    },
  },
  emptyState: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.feedback.TerraEmptyStateView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraEmptyStateVariant="connectionError" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_feedback_selection.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val emptyState = findViewById<TerraEmptyStateView>(R.id.terraEmptyState)
emptyState.terraEmptyStateVariant = TerraEmptyStateVariant.CONNECTION_ERROR`,
    },
  },
  header: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.header.TerraHeaderView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraHeaderTitle="Title"
    app:terraHeaderModuleName="Put Main Module name"
    app:terraHeaderApkName="Put APK Name"
    app:terraHeaderShowStatusBar="true" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_header_icon.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val header = findViewById<TerraHeaderView>(R.id.terraHeader)
header.terraHeaderTitle = "Title"
header.terraHeaderModuleName = "Put Main Module name"
header.terraHeaderApkName = "Put APK Name"
header.terraHeaderShowStatusBar = true`,
      note: 'Disesuaikan ke API property TerraHeaderView yang ada di source Android saat ini.',
    },
  },
  navbar: {
    xml: {
      language: 'xml' as const,
      code: `<com.terra.ds.selection.TerraNavbarView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraNavbarSelectedItem="home" />`,
      note: 'Diambil dari android/catalog/src/main/res/layout/terra_catalog_feedback_selection.xml.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val navbar = findViewById<TerraNavbarView>(R.id.terraNavbar)
navbar.terraNavbarSelectedItem = "home"`,
    },
  },
  timer: {
    xml: {
      language: 'xml' as const,
      note: 'Belum ada contoh XML timer di sample layout Android saat ini.',
    },
    kotlin: {
      language: 'kotlin' as const,
      code: `val timer = findViewById<TerraTimerView>(R.id.terraTimer)
timer.terraTimerLabel = "00:30"
timer.terraTimerState = TerraTimerState.ACTIVE`,
      note: 'Disusun dari API TerraTimerView di source Android karena sample XML belum tersedia.',
    },
  },
};

/* ------------------------------------------------------------------ */
/* Registry                                                            */
/* ------------------------------------------------------------------ */

export const registry: DocEntry[] = [
  /* ---------------- Foundations ---------------- */
  {
    slug: 'foundations',
    name: 'Foundations',
    group: 'Foundations',
    platform: 'Web + Android',
    description: 'Core TERRA roles for color, shadow, disabled state, and CTA typography.',
    bareDemo: true,
    demo: (
      <div className="specimen specimen--foundations">
        <div className="specimen-swatch-row">
          <span style={{ background: 'var(--color-bg-fill-secondary-default)' }} />
          <span style={{ background: 'var(--color-bg-fill-primary-default)' }} />
          <span style={{ background: 'var(--color-bg-fill-danger-default)' }} />
          <span style={{ background: 'var(--color-bg-disabled-default)' }} />
        </div>
        <div className="specimen-shadow-row">
          <div className="specimen-shadow-card">Card Shadow</div>
          <div className="specimen-shadow-card specimen-shadow-card--inner">Inner Shadow</div>
        </div>
        <div className="specimen-type-row">
          <span>CTA / Medium</span>
          <span>CTA / Small</span>
        </div>
      </div>
    ),
    androidSnippet: androidSnippets.foundations,
    usage: {
      do: [
        'Reference semantic tokens (e.g. --color-bg-fill-primary-default) rather than raw hex.',
        'Use the teal secondary fill for primary brand actions, orange for accent/secondary.',
      ],
      dont: [
        'Hardcode color values inside component CSS.',
        'Introduce new shadow or radius values outside the token scale.',
      ],
    },
  },
  {
    slug: 'tokens',
    name: 'Tokens',
    group: 'Foundations',
    platform: 'Web + Android',
    description: 'Design token reference for colors, semantic aliases, and cross-platform mapping.',
    bareDemo: true,
    demo: <TerraTokenShowcase />,
    androidSnippet: androidSnippets.foundations,
  },
  {
    slug: 'icon',
    name: 'Icon',
    group: 'Foundations',
    platform: 'Web + Android',
    description: 'Grouped operational iconography across action, navigation, status, info, domain, and system.',
    demoAlign: 'start',
    demo: (
      <div className="specimen specimen--icons">
        {iconGroups.map((group) => (
          <div key={group.category} className="specimen-icon-group">
            <strong>{group.label}</strong>
            <div className="specimen-icon-grid">
              {group.names.map((name) => (
                <span key={`${group.category}-${name}`} className="specimen-icon-cell">
                  <TerraIcon category={group.category} name={name} size="sm" />
                </span>
              ))}
            </div>
          </div>
        ))}
      </div>
    ),
    props: [
      { name: 'name', type: 'TerraIconName', description: 'Icon identifier (40+ named icons).' },
      { name: 'category', type: "'action' | 'navigation' | 'status' | 'info' | 'domain' | 'system'", default: "'system'", description: 'Icon category namespace.' },
      { name: 'size', type: "'sm' | 'md' | 'lg' | number", default: "'md'", description: 'Keyword size class, or pixel size for a number.' },
      { name: 'decorative', type: 'boolean', default: 'true', description: 'Hides from a11y tree; set false + title for meaningful icons.' },
      { name: 'title', type: 'string', description: 'Accessible label for the svg.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terra-icon', description: 'Root span wrapper (+ size modifier).' },
      { part: 'svg', description: '24×24 icon vector.' },
      { part: 'title', description: 'Optional accessible title element.' },
    ],
    androidSnippet: androidSnippets.icon,
    usage: {
      do: ['Set decorative={false} and a title when an icon conveys meaning on its own.', 'Use the category that matches the icon’s purpose.'],
      dont: ['Use icons smaller than 16px for interactive targets.', 'Recolor icons with hardcoded hex — rely on currentColor/tokens.'],
    },
  },

  /* ---------------- Actions ---------------- */
  {
    slug: 'button',
    name: 'Button',
    group: 'Actions',
    platform: 'Web + Android',
    description: 'Primary CTA family with filled, outlined, danger, and text variants in normal and small sizes.',
    demo: (
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: 12, alignItems: 'center' }}>
        <TerraButton variant="primary">Button</TerraButton>
        <TerraButton variant="secondary">Button</TerraButton>
        <TerraButton variant="outlined-primary">Button</TerraButton>
        <TerraButton variant="danger">Button</TerraButton>
        <TerraButton variant="text">Button</TerraButton>
        <TerraButton variant="primary" size="small">Small</TerraButton>
        <TerraButton variant="primary" disabled>Disabled</TerraButton>
      </div>
    ),
    props: [
      { name: 'variant', type: "'primary' | 'secondary' | 'outlined-primary' | 'outlined-secondary' | 'danger' | 'text'", default: "'primary'", description: 'Visual style of the button.' },
      { name: 'size', type: "'normal' | 'small'", default: "'normal'", description: 'Button size.' },
      { name: 'fullWidth', type: 'boolean', default: 'false', description: 'Stretches button to full container width.' },
      { name: 'disabled', type: 'boolean', default: 'false', description: 'Disables the button.' },
      { name: 'children', type: 'ReactNode', description: 'Button label/content.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [{ part: 'terraButton', description: 'Root button element; label rendered inside.' }],
    states: [
      { name: 'Primary', description: 'Default high-emphasis action.', demo: <TerraButton variant="primary">Button</TerraButton> },
      { name: 'Secondary', description: 'Orange accent action.', demo: <TerraButton variant="secondary">Button</TerraButton> },
      { name: 'Outlined', description: 'Medium-emphasis bordered action.', demo: <TerraButton variant="outlined-primary">Button</TerraButton> },
      { name: 'Danger', description: 'Destructive action.', demo: <TerraButton variant="danger">Button</TerraButton> },
      { name: 'Text', description: 'Low-emphasis inline action.', demo: <TerraButton variant="text">Button</TerraButton> },
      { name: 'Small', description: 'Compact size for tight layouts.', demo: <TerraButton variant="primary" size="small">Button</TerraButton> },
      { name: 'Disabled', description: 'Non-interactive state.', demo: <TerraButton variant="primary" disabled>Button</TerraButton> },
    ],
    androidSnippet: androidSnippets.button,
    usage: {
      do: ['Use one primary button per view to signal the main action.', 'Keep labels short and action-oriented (verb first).'],
      dont: ['Stack multiple primary buttons competing for attention.', 'Use danger styling for non-destructive actions.'],
    },
  },

  /* ---------------- Selection ---------------- */
  {
    slug: 'checkbox',
    name: 'Checkbox',
    group: 'Selection',
    platform: 'Web + Android',
    description: 'Labeled checkbox control with checked, unchecked, and disabled states.',
    demoAlign: 'start',
    demo: (
      <div style={{ display: 'grid', gap: 16 }}>
        <TerraCheckbox label="Checkbox" defaultChecked />
        <TerraCheckbox label="Unchecked" />
        <TerraCheckbox label="Disabled checkbox" disabled />
      </div>
    ),
    props: [
      { name: 'label', type: 'string', description: 'Text label beside the control.' },
      { name: 'helperText', type: 'string', description: 'Helper text below the control.' },
      { name: 'disabled', type: 'boolean', default: 'false', description: 'Disables the input.' },
      { name: 'id', type: 'string', default: 'auto', description: 'Input id (auto-generated if omitted).' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraChoiceField', description: 'Root label wrapper.' },
      { part: 'terraChoiceField__control--checkbox', description: 'Visual 24×24 checkbox box.' },
      { part: 'terraChoiceField__indicator', description: 'Checkmark indicator.' },
      { part: 'terraChoiceField__label', description: 'Text label.' },
      { part: 'terraChoiceField__helper', description: 'Helper text.' },
    ],
    androidSnippet: androidSnippets.checkbox,
    usage: {
      do: ['Use for selecting zero or more options from a set.', 'Keep labels on a single tappable row with the box.'],
      dont: ['Use a checkbox for mutually exclusive choices — use Radio.', 'Use for an on/off setting that applies instantly — use Toggle.'],
    },
  },
  {
    slug: 'radio',
    name: 'Radio Button',
    group: 'Selection',
    platform: 'Web + Android',
    description: 'Single-selection control with labeled options and disabled support.',
    demoAlign: 'start',
    demo: (
      <div style={{ display: 'grid', gap: 16 }}>
        <TerraRadio label="Radio" name="radio-demo" defaultChecked />
        <TerraRadio label="Alternative" name="radio-demo" />
        <TerraRadio label="Disabled" name="radio-demo" disabled />
      </div>
    ),
    props: [
      { name: 'label', type: 'string', description: 'Text label beside the control.' },
      { name: 'helperText', type: 'string', description: 'Helper text below the control.' },
      { name: 'disabled', type: 'boolean', default: 'false', description: 'Disables the input.' },
      { name: 'id', type: 'string', default: 'auto', description: 'Input id (auto-generated if omitted).' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraChoiceField', description: 'Root label wrapper.' },
      { part: 'terraChoiceField__control--radio', description: 'Visual 24×24 radio circle.' },
      { part: 'terraChoiceField__indicator', description: 'Selected dot indicator.' },
      { part: 'terraChoiceField__label', description: 'Text label.' },
    ],
    androidSnippet: androidSnippets.radio,
    usage: {
      do: ['Group radios with a shared name for single selection.', 'Provide a sensible default selection where possible.'],
      dont: ['Use radios for more than ~6 options — consider a Select.', 'Use a single radio in isolation — use a checkbox.'],
    },
  },
  {
    slug: 'toggle',
    name: 'Toggle',
    group: 'Selection',
    platform: 'Web + Android',
    description: 'Switch control with label and active state for preference and setting flows.',
    demoAlign: 'start',
    demo: (
      <div style={{ display: 'grid', gap: 16, width: 320 }}>
        <TerraToggle label="Toggle" checked readOnly />
        <TerraToggle label="Off" readOnly />
        <TerraToggle label="Disabled" disabled />
      </div>
    ),
    props: [
      { name: 'label', type: 'string', description: 'Text label.' },
      { name: 'helperText', type: 'string', description: 'Helper text below label.' },
      { name: 'checked', type: 'boolean', default: 'false', description: 'On/off state.' },
      { name: 'disabled', type: 'boolean', default: 'false', description: 'Disables the toggle.' },
      { name: 'id', type: 'string', default: 'auto', description: 'Input id (auto-generated if omitted).' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraToggleField__row', description: 'Row layout (copy + switch).' },
      { part: 'terraToggleField__track', description: 'Switch track.' },
      { part: 'terraToggleField__thumb', description: 'Sliding thumb.' },
      { part: 'terraToggleField__label', description: 'Text label.' },
    ],
    androidSnippet: androidSnippets.toggle,
    usage: {
      do: ['Use for settings that take effect immediately.', 'Pair with a clear, stable label describing the on state.'],
      dont: ['Use a toggle inside a form that requires a Save action — use a checkbox.', 'Use ambiguous labels that don’t indicate what “on” means.'],
    },
  },
  {
    slug: 'chip',
    name: 'Chip',
    group: 'Selection',
    platform: 'Web + Android',
    description: 'Selectable chip with optional icons and counter badge for filters and statuses.',
    demo: (
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: 12, alignItems: 'center' }}>
        <TerraChip leftIcon={<TerraIcon category="action" name="check" size="sm" />} counter={2} selected>
          This is Chips
        </TerraChip>
        <TerraChip>Pending</TerraChip>
        <TerraChip rightIcon={<TerraIcon category="navigation" name="chevron-down" size="sm" />}>Verified</TerraChip>
      </div>
    ),
    props: [
      { name: 'selected', type: 'boolean', default: 'false', description: 'Selected/active state of the chip.' },
      { name: 'leftIcon', type: 'ReactNode', description: 'Icon before the label.' },
      { name: 'rightIcon', type: 'ReactNode', description: 'Icon after the label/counter.' },
      { name: 'counter', type: 'number', description: 'Numeric counter badge.' },
      { name: 'children', type: 'ReactNode', description: 'Chip label content.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraChip', description: 'Root button (8px radius, 8/16px padding).' },
      { part: 'terraChip__icon', description: 'Left/right icon slot.' },
      { part: 'terraChip__label', description: 'Chip text label.' },
      { part: 'terraChip__counter', description: 'Numeric counter badge.' },
    ],
    states: [
      { name: 'Default', description: 'Unselected — neutral border and text.', demo: <TerraChip>This is Chips</TerraChip> },
      { name: 'Selected', description: 'Teal border, tinted fill, bold teal text.', demo: <TerraChip selected>This is Chips</TerraChip> },
    ],
    androidSnippet: androidSnippets.chip,
    usage: {
      do: ['Use chips for filters, tags, and quick multi-select.', 'Show a counter when the chip aggregates items.'],
      dont: ['Use chips as primary page actions — use Button.', 'Mix selected and unselected styling within the same chip.'],
    },
  },
  {
    slug: 'tabs',
    name: 'Tabs',
    group: 'Selection',
    platform: 'Web + Android',
    description: 'Tab bar with configurable labels, selected state, and a scrollable option.',
    demoAlign: 'stretch',
    demo: <TerraTabs items={previewTabs} selectedId="detail" />,
    props: [
      { name: 'items', type: 'TerraTabItem[]', description: 'Tab items ({ id, label }).' },
      { name: 'selectedId', type: 'string', description: 'Id of the selected tab.' },
      { name: 'onChange', type: '(id: string) => void', description: 'Tab change handler.' },
      { name: 'scrollable', type: 'boolean', default: 'false', description: 'Horizontally scrollable variant.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraTabs', description: 'Root tablist container (40px tall).' },
      { part: 'terraTabs__item--selected', description: 'Active tab with underline indicator.' },
    ],
    androidSnippet: androidSnippets.tabs,
    usage: {
      do: ['Use tabs to switch between peer views of the same context.', 'Keep labels to one or two words.'],
      dont: ['Use tabs for sequential steps — use Stepper.', 'Exceed the visible width without enabling scrollable.'],
    },
  },

  /* ---------------- Forms ---------------- */
  {
    slug: 'text-field',
    name: 'Text Field',
    group: 'Forms',
    platform: 'Web + Android',
    description: 'Configurable text input with label, helper, prefix/suffix, and icon variations.',
    demoAlign: 'start',
    demo: (
      <div style={{ display: 'grid', gap: 20, ...formStyle }}>
        <TerraTextField label="Label" defaultValue="Input text" helperText="Helper Type Here" />
        <TerraTextField
          label="Amount"
          defaultValue="10.000"
          prefix="Rp"
          rightIcon={<TerraIcon category="action" name="edit" size="sm" />}
        />
        <TerraTextField label="Disabled" defaultValue="Read only" disabled />
      </div>
    ),
    props: [
      { name: 'label', type: 'string', description: 'Field label.' },
      { name: 'helperText', type: 'string', description: 'Helper text below the field.' },
      { name: 'prefix', type: 'ReactNode', description: 'Inline prefix affix before input.' },
      { name: 'suffix', type: 'ReactNode', description: 'Inline suffix affix after input.' },
      { name: 'leftIcon', type: 'ReactNode', description: 'Leading icon.' },
      { name: 'rightIcon', type: 'ReactNode', description: 'Trailing icon.' },
      { name: 'disabled', type: 'boolean', default: 'false', description: 'Disables the input.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraField__label', description: 'Field label.' },
      { part: 'terraField__control', description: 'Bordered control wrapper.' },
      { part: 'terraField__affix', description: 'Prefix/suffix affix.' },
      { part: 'terraField__icon', description: 'Leading/trailing icon slot.' },
      { part: 'terraField__helper', description: 'Helper text.' },
    ],
    androidSnippet: androidSnippets.textField,
    usage: {
      do: ['Always pair an input with a visible label.', 'Use helper text to clarify format requirements.'],
      dont: ['Use placeholder text as the only label.', 'Disable fields without explaining why.'],
    },
  },
  {
    slug: 'search-field',
    name: 'Search Bar',
    group: 'Forms',
    platform: 'Web + Android',
    description: 'Search input shell with default, focus, result, and disabled visual states.',
    demoAlign: 'start',
    demo: (
      <div style={formStyle}>
        <TerraSearchField label="Search" placeholder="Search something" showClearButton defaultValue="Mandiri" />
      </div>
    ),
    props: [
      { name: 'label', type: 'string', description: 'Field label.' },
      { name: 'helperText', type: 'string', description: 'Helper text below the field.' },
      { name: 'showClearButton', type: 'boolean', default: 'false', description: 'Shows clear button when value present.' },
      { name: 'disabled', type: 'boolean', default: 'false', description: 'Disables the field.' },
      { name: 'value', type: 'string', description: 'Input value (used to detect non-empty).' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraField__icon', description: 'Leading search icon.' },
      { part: 'terraField__input', description: 'Native search input.' },
      { part: 'terraSearchField__clear', description: 'Clear button (shown when non-empty).' },
    ],
    androidSnippet: androidSnippets.searchField,
    usage: {
      do: ['Show the clear button once the user has typed.', 'Place search at the top of the content it filters.'],
      dont: ['Trigger heavy queries on every keystroke without debounce.', 'Hide the leading search icon.'],
    },
  },
  {
    slug: 'select-field',
    name: 'Select',
    group: 'Forms',
    platform: 'Web + Android',
    description: 'Dropdown field with placeholder, filled state, and optional search treatment.',
    demoAlign: 'start',
    demo: (
      <div style={formStyle}>
        <TerraSelectField
          label="Select"
          placeholder="Choose item"
          value="item-1"
          options={[
            { value: 'item-1', label: 'Item one' },
            { value: 'item-2', label: 'Item two' },
          ]}
        />
      </div>
    ),
    props: [
      { name: 'label', type: 'string', description: 'Field label.' },
      { name: 'placeholder', type: 'string', default: "'Select option'", description: 'Placeholder option text.' },
      { name: 'value', type: 'string', default: "''", description: 'Selected value.' },
      { name: 'options', type: 'TerraSelectOption[]', default: '[]', description: 'Select options ({ value, label }).' },
      { name: 'helperText', type: 'string', description: 'Helper text below the field.' },
      { name: 'disabled', type: 'boolean', default: 'false', description: 'Disables the select.' },
      { name: 'onChange', type: '(value: string) => void', description: 'Change handler (passes selected value).' },
    ],
    anatomy: [
      { part: 'terraField__control--select', description: 'Bordered control wrapper.' },
      { part: 'terraField__input--select', description: 'Native select element.' },
      { part: 'terraField__icon--select', description: 'Trailing chevron-down icon.' },
    ],
    androidSnippet: androidSnippets.selectField,
    usage: {
      do: ['Use for choosing one option from a known list.', 'Provide a clear placeholder describing the choice.'],
      dont: ['Use a select for fewer than ~4 options — consider radios.', 'Use for multi-select.'],
    },
  },
  {
    slug: 'quantity-editor',
    name: 'Quantity Editor',
    group: 'Forms',
    platform: 'Web + Android',
    description: 'Stepper-style quantity control with minimum, maximum, and editable amount states.',
    demo: <TerraQuantityEditor value="1.000" />,
    props: [
      { name: 'value', type: 'string', description: 'Current quantity display value.' },
      { name: 'onDecrement', type: '() => void', description: 'Decrement handler.' },
      { name: 'onIncrement', type: '() => void', description: 'Increment handler.' },
      { name: 'decrementDisabled', type: 'boolean', default: 'false', description: 'Disables decrement button.' },
      { name: 'incrementDisabled', type: 'boolean', default: 'false', description: 'Disables increment button.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraQuantityEditor__button', description: 'Minus and plus buttons.' },
      { part: 'terraQuantityEditor__value', description: 'Current value display.' },
    ],
    androidSnippet: androidSnippets.quantityEditor,
    usage: {
      do: ['Disable decrement at the minimum and increment at the maximum.', 'Use for adjusting a numeric amount in small steps.'],
      dont: ['Use for large numbers better entered via a text field.', 'Allow the value to go below zero unintentionally.'],
    },
  },

  /* ---------------- Feedback ---------------- */
  {
    slug: 'progress-bar',
    name: 'Progress Bar',
    group: 'Feedback',
    platform: 'Web + Android',
    description: 'Horizontal progress treatment with multiple bar styles for workflow states.',
    demoAlign: 'stretch',
    demo: (
      <div style={{ display: 'grid', gap: 16 }}>
        <TerraProgressBar variant="base" value={42} />
        <TerraProgressBar variant="bar1" value={66} />
        <TerraProgressBar variant="bar2" value={84} />
      </div>
    ),
    props: [
      { name: 'variant', type: "'base' | 'bar1' | 'bar2'", default: "'base'", description: 'Visual variant.' },
      { name: 'value', type: 'number', default: '100', description: 'Progress percentage (clamped 0–100).' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraProgressBar', description: 'Root track (10px base, 14px bar1/bar2).' },
      { part: 'terraProgressBar__fill', description: 'Filled portion driven by value.' },
    ],
    androidSnippet: androidSnippets.progressBar,
    usage: {
      do: ['Use for determinate progress where a percentage is known.', 'Keep the track full-width within its container.'],
      dont: ['Use for indeterminate waits — use Loading.', 'Animate the value backwards.'],
    },
  },
  {
    slug: 'stepper',
    name: 'Stepper',
    group: 'Feedback',
    platform: 'Android',
    description: 'Multi-step progress indicator with completed, current, and upcoming markers.',
    demoAlign: 'stretch',
    demo: (
      <TerraStepper
        steps={[{ label: 'Caption 1' }, { label: 'Caption 2' }, { label: 'Caption 3' }, { label: 'Caption 4' }]}
        activeStep={1}
      />
    ),
    props: [
      { name: 'steps', type: 'TerraStepperStep[]', description: 'Ordered list of steps ({ label }).' },
      { name: 'activeStep', type: 'number', default: '0', description: 'Zero-based index of the in-progress step.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraStepper__bullet', description: 'Circular step bullet (number or check).' },
      { part: 'terraStepper__caption', description: 'Step label caption.' },
      { part: 'terraStepper__line', description: 'Connector line between steps.' },
    ],
    states: [
      { name: 'Complete', description: 'Green bullet with check (steps before active).' },
      { name: 'Progress', description: 'Orange bullet with number (current step).' },
      { name: 'Inactive', description: 'Grey bullet with number (upcoming steps).' },
    ],
    androidSnippet: androidSnippets.stepper,
    usage: {
      do: ['Use for linear, sequential flows like checkout or onboarding.', 'Keep step captions short.'],
      dont: ['Use for non-sequential navigation — use Tabs.', 'Exceed ~5 steps on a single tablet row.'],
    },
  },
  {
    slug: 'ticker',
    name: 'Ticker',
    group: 'Feedback',
    platform: 'Web + Android',
    description: 'Inline alert and information banner with tone variants and detailed messaging.',
    demoAlign: 'stretch',
    demo: (
      <div style={{ display: 'grid', gap: 16 }}>
        <TerraTicker tone="information" message="Warning information maximum 1 line." />
        <TerraTicker
          tone="warning"
          detailed
          title="Warning Title Text"
          message="Warning information maximum 2 lines warning information maximum 2 lines."
        />
        <TerraTicker tone="error" message="Something went wrong. Please try again." />
      </div>
    ),
    props: [
      { name: 'tone', type: "'information' | 'warning' | 'error'", default: "'information'", description: 'Semantic tone/color.' },
      { name: 'detailed', type: 'boolean', default: 'false', description: 'Shows title in addition to message.' },
      { name: 'title', type: 'string', description: 'Optional title (shown only when detailed).' },
      { name: 'message', type: 'string', description: 'Main message text.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraTicker__icon', description: 'Leading tone icon.' },
      { part: 'terraTicker__title', description: 'Title (detailed only).' },
      { part: 'terraTicker__message', description: 'Message text.' },
    ],
    states: [
      { name: 'Information', description: 'Teal — neutral informational tone.' },
      { name: 'Warning', description: 'Yellow — caution that needs attention.' },
      { name: 'Error', description: 'Red — a problem that blocks the user.' },
    ],
    androidSnippet: androidSnippets.ticker,
    usage: {
      do: ['Match the tone to the severity of the message.', 'Keep messages concise; use detailed mode for a title + body.'],
      dont: ['Stack many tickers at once.', 'Use error tone for informational notes.'],
    },
  },
  {
    slug: 'timer',
    name: 'Timer',
    group: 'Feedback',
    platform: 'Web + Android',
    description: 'Compact labeled timer used to surface active countdown and duration states.',
    demo: (
      <div style={{ display: 'flex', gap: 16 }}>
        <TerraTimer label="Timer" value="00:45" active />
        <TerraTimer label="Inactive" value="05:00" />
      </div>
    ),
    props: [
      { name: 'label', type: 'string', default: "'Label'", description: 'Label text above value.' },
      { name: 'value', type: 'string', description: 'Timer value display.' },
      { name: 'active', type: 'boolean', default: 'false', description: 'Active/running state.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraTimer__label', description: 'Label text.' },
      { part: 'terraTimer__dot', description: 'Status dot indicator.' },
      { part: 'terraTimer__value', description: 'Timer value text.' },
    ],
    androidSnippet: androidSnippets.unavailable,
    usage: {
      do: ['Use the active state to draw attention to a running countdown.', 'Format the value consistently (mm:ss).'],
      dont: ['Use for long-running background progress — use Progress Bar.'],
    },
  },
  {
    slug: 'loading',
    name: 'Loading',
    group: 'Feedback',
    platform: 'Web + Android',
    description: 'Progress and wait-state indicator with inline and stacked messaging treatment.',
    demo: <TerraLoading message="Preparing account verification" />,
    props: [
      { name: 'message', type: 'string', default: "'Dalam Proses...'", description: 'Loading text.' },
      { name: 'inline', type: 'boolean', default: 'false', description: 'Inline layout variant.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraLoading__spinner', description: 'Animated spinner element.' },
      { part: 'terraLoading__message', description: 'Loading message text.' },
    ],
    androidSnippet: androidSnippets.loading,
    usage: {
      do: ['Use for indeterminate waits where duration is unknown.', 'Provide a short message describing what is happening.'],
      dont: ['Use when a determinate percentage is available — use Progress Bar.'],
    },
  },
  {
    slug: 'page-control',
    name: 'Page Control',
    group: 'Feedback',
    platform: 'Web + Android',
    description: 'Pagination dots for onboarding, carousel, and multi-page content flows.',
    demo: <TerraPageControl count={5} selectedPage={2} />,
    props: [
      { name: 'count', type: 'number', description: 'Total number of dots/pages.' },
      { name: 'selectedPage', type: 'number', default: '0', description: 'Zero-based index of selected dot.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [{ part: 'terraPageControl__dot', description: 'Individual page dot (active dot elongates).' }],
    androidSnippet: androidSnippets.pageControl,
    usage: {
      do: ['Use for a small number of pages (~3–6).', 'Keep in sync with the visible page.'],
      dont: ['Use for long lists — use numbered pagination.'],
    },
  },
  {
    slug: 'empty-state',
    name: 'Empty State',
    group: 'Feedback',
    platform: 'Android',
    description: 'Illustrated empty, error, and success state panel with optional call-to-action.',
    demo: (
      <TerraEmptyState
        title="Connection Error"
        description="Please check your internet connection and try again."
        ctaLabel="Retry"
        illustration={errorIllustration}
      />
    ),
    props: [
      { name: 'title', type: 'string', description: 'Main title text (required).' },
      { name: 'description', type: 'string', description: 'Descriptive body text (required).' },
      { name: 'ctaLabel', type: 'string', description: 'Call-to-action button label.' },
      { name: 'onCtaClick', type: '() => void', description: 'CTA click handler.' },
      { name: 'illustration', type: 'ReactNode', description: 'Optional illustration element.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraEmptyState__illustration', description: 'Illustration wrapper.' },
      { part: 'terraEmptyState__title', description: 'Title text.' },
      { part: 'terraEmptyState__description', description: 'Description text.' },
      { part: 'terraEmptyState__cta', description: 'Optional CTA button.' },
    ],
    androidSnippet: androidSnippets.emptyState,
    usage: {
      do: ['Explain why the area is empty and what to do next.', 'Offer a clear recovery action when one exists.'],
      dont: ['Leave a blank screen with no guidance.', 'Use a generic message that doesn’t fit the context.'],
    },
  },

  /* ---------------- Navigation ---------------- */
  {
    slug: 'header',
    name: 'Header',
    group: 'Navigation',
    platform: 'Android',
    description: 'Mobile app header with status strip, leading icon, title, and page identifier metadata.',
    demoAlign: 'stretch',
    demo: (
      <TerraHeader
        title="Title"
        icon={<TerraIcon category="system" name="placeholder" />}
        pageIdentifier={{ moduleName: 'Put Main Module name', apkName: 'Put APK Name' }}
      />
    ),
    props: [
      { name: 'title', type: 'string', description: 'Main title text (required).' },
      { name: 'icon', type: 'ReactNode', default: 'placeholder', description: 'Left-side icon.' },
      { name: 'pageIdentifier', type: '{ moduleName?: string; apkName?: string }', description: 'Page identifier info.' },
      { name: 'showStatusBar', type: 'boolean', default: 'true', description: 'Shows mobile status bar.' },
      { name: 'backgroundColor', type: 'string', default: 'brand', description: 'Background color override.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terra-header__status-bar', description: 'Top mobile status bar.' },
      { part: 'terra-header__title-row', description: 'Icon + title row.' },
      { part: 'terra-page-identifier', description: 'Module / APK identifier.' },
    ],
    androidSnippet: androidSnippets.header,
    usage: {
      do: ['Keep the title short and descriptive of the screen.', 'Use the page identifier to clarify module context.'],
      dont: ['Overload the header with multiple actions.', 'Hide the status bar without reason.'],
    },
  },
  {
    slug: 'navbar',
    name: 'Navbar',
    group: 'Navigation',
    platform: 'Android',
    description: 'Bottom navigation shell for mobile destinations such as home, activity, and notifications.',
    demoAlign: 'stretch',
    demo: <TerraNavbar items={navbarItems} activeId="home" />,
    props: [
      { name: 'items', type: 'TerraNavbarItem[]', description: 'Navigation items ({ id, label, icon }).' },
      { name: 'activeId', type: 'string', description: 'Id of the active item.' },
      { name: 'onItemClick', type: '(id: string) => void', description: 'Item click handler.' },
      { name: 'className', type: 'string', description: 'Extra CSS class.' },
    ],
    anatomy: [
      { part: 'terraNavbar__item', description: 'Individual nav button (+ active).' },
      { part: 'terraNavbar__icon', description: 'Item icon slot.' },
      { part: 'terraNavbar__label', description: 'Item text label.' },
    ],
    androidSnippet: androidSnippets.navbar,
    usage: {
      do: ['Use 3–5 top-level destinations.', 'Keep the active item clearly highlighted.'],
      dont: ['Use the navbar for transient actions.', 'Exceed 5 items — they become hard to tap.'],
    },
  },
];

export function findEntry(slug: string): DocEntry | undefined {
  return registry.find((e) => e.slug === slug);
}
