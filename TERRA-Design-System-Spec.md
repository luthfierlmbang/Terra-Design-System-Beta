# TERRA Design System Spec

## Version
- Spec Version: 1.0-draft
- Date: 2026-06-17
- Scope: Initial cross-platform design system contract based on currently detected Figma components

---

## 1. Purpose

This document defines the initial specification for the TERRA design system so it can be used consistently across:
- Figma library
- Web preview/catalog implementation
- Android XML/View-based implementation
- Java and Kotlin integration on Android

Current detected design-system scope from Figma:
- Button
- Header
- Shadow
- Icon

This spec is intentionally implementation-oriented so design assets can map cleanly to code.

---

## 2. Design System Structure

Recommended Figma page structure:

1. `00. Cover`
2. `01. Foundations`
3. `02. Assets`
4. `03. Components`
5. `04. Patterns`
6. `05. Templates`
7. `06. Screens`

Recommended top-level grouping:
- Foundations
- Assets
- Components
- Patterns
- Templates
- Screens

---

## 3. Final Naming Convention

### 3.1 General Rules

Use naming that is:
- explicit
- stable
- scalable
- platform-mappable

Rules:
- Use **Title Case** for Figma component names and variant values
- Use **slash hierarchy** for grouping
- Use short, descriptive property names
- Avoid generic names like `Frame 1`, `Group 2`, `Container`, `Copy`
- Avoid duplicate concepts expressed in different ways

---

### 3.2 Foundations Naming

#### Color
Format:
- `Color/{Role}/{Scale}`

Examples:
- `Color/Primary/500`
- `Color/Secondary/500`
- `Color/Neutral/900`
- `Color/Warning/500`

Short-term compatibility examples from current file:
- `Color/Primary/Main`
- `Color/Secondary/Main`
- `Color/Warning/Main`
- `Color/Neutral/90`

Recommendation:
- migrate to numeric scale over time

#### Typography
Format:
- `Text/{Category}/{Variant}`

Examples:
- `Text/Heading/H1`
- `Text/Heading/H2`
- `Text/Body/Regular`
- `Text/Body/Medium`
- `Text/Caption/Regular`
- `Text/Label/Medium`

#### Shadow
Format:
- `Shadow/{Name}`

Examples:
- `Shadow/Card`
- `Shadow/Inner`

#### Radius
Format:
- `Radius/{Size}`

Examples:
- `Radius/Small`
- `Radius/Medium`
- `Radius/Large`
- `Radius/Pill`

#### Spacing
Format:
- `Spacing/{Value}`

Examples:
- `Spacing/4`
- `Spacing/8`
- `Spacing/12`
- `Spacing/16`
- `Spacing/24`

---

### 3.3 Asset Naming

#### Icons
Final format:
- `Icon/{Category}/{Name}`

Examples:
- `Icon/Action/Search`
- `Icon/Action/Delete`
- `Icon/Navigation/Arrow-Left`
- `Icon/Status/Done`
- `Icon/Info/Questionmark`
- `Icon/Domain/Cash-In`

#### Logos
Format:
- `Logo/{Brand}/{Variant}`

Examples:
- `Logo/Terra/Primary`
- `Logo/Terra/Monochrome`

---

### 3.4 Component Naming

Format:
- `Button`
- `Header`
- `Text Field`
- `Badge`
- `Card`

Do not use:
- `Button Copy`
- `Button Final`
- `Header 2`
- `Component New`

#### Variant Property Names
Use these canonical property keys when applicable:
- `Type`
- `Size`
- `State`
- `Icon`
- `Status`
- `Theme`
- `Layout`
- `Leading`
- `Trailing`
- `Identifier`
- `Action`

Rules:
- property names in Title Case
- property values in Title Case
- keep values concise and explicit

Examples:
- `Type=Primary`
- `Size=Small`
- `State=Disabled`
- `Leading=Back`

---

### 3.5 Final Component Naming for Current Library

#### Button
Component set name:
- `Button`

Final properties:
- `Type=Primary | Secondary | Outlined Primary | Outlined Secondary | Danger | Text`
- `Size=Normal | Small`
- `State=Default | Disabled`

Normalization note:
- rename `Text Button` to `Text`

Canonical example:
- `Button / Type=Primary / Size=Normal / State=Default`

#### Header
Component name:
- `Header`

Recommended properties:
- `Leading=None | Back | Menu | Icon`
- `Title=True | False`
- `Subtitle=True | False`
- `Identifier=True | False`
- `Action=None | One | Two`
- `StatusBar=True | False`

#### Shadow
Not a UI component. Keep as foundation token:
- `Shadow/Card`
- `Shadow/Inner`

#### Icon
Format:
- `Icon/{Category}/{Name}`

---

## 4. Master Inventory Table

### 4.1 Core Inventory

| Category | Canonical Name | Current Figma Source | Variants / Structure | Web Target | Android Target | Notes |
|---|---|---|---|---|---|---|
| Component | Button | `Button` | Type, Size, State | `TerraButton` | `TerraButtonView` | Primary action component |
| Component | Header | `Header` | Status bar, title, icon, identifier | `TerraHeader` | `TerraHeaderView` | Top app layout component |
| Foundation | Shadow/Card | `Card Shadow` | Token | CSS shadow token | Android shadow style/drawable | Elevation token |
| Foundation | Shadow/Inner | `Inner Shadow` | Token | CSS inset shadow token | Android drawable/background | Effect token |
| Asset | Icon | `Icon` | Categorized icon set | `TerraIcon` | drawable resource or `TerraIconView` | Large icon library |

---

### 4.2 Button Variant Inventory

| Component | Type | Size | State | Current Figma Name | Canonical Figma Variant | Web Mapping | Android Mapping |
|---|---|---|---|---|---|---|---|
| Button | Primary | Normal | Default | `Type=Primary, Size=Normal, State=Default` | `Button / Type=Primary / Size=Normal / State=Default` | `type="primary" size="normal"` | `terraButtonType="primary" terraButtonSize="normal"` |
| Button | Primary | Normal | Disabled | `Type=Primary, Size=Normal, State=Disabled` | `Button / Type=Primary / Size=Normal / State=Disabled` | `disabled` | `terraButtonState="disabled"` |
| Button | Secondary | Normal | Default | `Type=Secondary, Size=Normal, State=Default` | `Button / Type=Secondary / Size=Normal / State=Default` | `type="secondary"` | `terraButtonType="secondary"` |
| Button | Secondary | Normal | Disabled | `Type=Secondary, Size=Normal, State=Disabled` | `Button / Type=Secondary / Size=Normal / State=Disabled` | `disabled` | `terraButtonState="disabled"` |
| Button | Outlined Primary | Normal | Default | `Type=Outlined Primary, Size=Normal, State=Default` | `Button / Type=Outlined Primary / Size=Normal / State=Default` | `type="outlined-primary"` | `terraButtonType="outlinedPrimary"` |
| Button | Outlined Primary | Normal | Disabled | `Type=Outlined Primary, Size=Normal, State=Disabled` | `Button / Type=Outlined Primary / Size=Normal / State=Disabled` | `disabled` | `terraButtonState="disabled"` |
| Button | Danger | Normal | Default | `Type=Danger, Size=Normal, State=Default` | `Button / Type=Danger / Size=Normal / State=Default` | `type="danger"` | `terraButtonType="danger"` |
| Button | Danger | Normal | Disabled | `Type=Danger, Size=Normal, State=Disabled` | `Button / Type=Danger / Size=Normal / State=Disabled` | `disabled` | `terraButtonState="disabled"` |
| Button | Text | Normal | Default | `Type=Text Button, Size=Normal, State=Default` | `Button / Type=Text / Size=Normal / State=Default` | `type="text"` | `terraButtonType="text"` |
| Button | Text | Normal | Disabled | `Type=Text Button, Size=Normal, State=Disabled` | `Button / Type=Text / Size=Normal / State=Disabled` | `disabled` | `terraButtonState="disabled"` |
| Button | Outlined Secondary | Normal | Default | `Type=Outlined Secondary, Size=Normal, State=Default` | `Button / Type=Outlined Secondary / Size=Normal / State=Default` | `type="outlined-secondary"` | `terraButtonType="outlinedSecondary"` |
| Button | Outlined Secondary | Normal | Disabled | `Type=Outlined Secondary, Size=Normal, State=Disabled` | `Button / Type=Outlined Secondary / Size=Normal / State=Disabled` | `disabled` | `terraButtonState="disabled"` |
| Button | Primary | Small | Default | `Type=Primary, Size=Small, State=Default` | `Button / Type=Primary / Size=Small / State=Default` | `type="primary" size="small"` | `terraButtonType="primary" terraButtonSize="small"` |
| Button | Primary | Small | Disabled | `Type=Primary, Size=Small, State=Disabled` | `Button / Type=Primary / Size=Small / State=Disabled` | `disabled` | `terraButtonState="disabled"` |
| Button | Secondary | Small | Default | `Type=Secondary, Size=Small, State=Default` | `Button / Type=Secondary / Size=Small / State=Default` | `type="secondary" size="small"` | `terraButtonType="secondary" terraButtonSize="small"` |
| Button | Secondary | Small | Disabled | `Type=Secondary, Size=Small, State=Disabled` | `Button / Type=Secondary / Size=Small / State=Disabled` | `disabled` | `terraButtonState="disabled"` |
| Button | Outlined Primary | Small | Default | `Type=Outlined Primary, Size=Small, State=Default` | `Button / Type=Outlined Primary / Size=Small / State=Default` | `type="outlined-primary" size="small"` | `terraButtonType="outlinedPrimary" terraButtonSize="small"` |
| Button | Outlined Primary | Small | Disabled | `Type=Outlined Primary, Size=Small, State=Disabled` | `Button / Type=Outlined Primary / Size=Small / State=Disabled` | `disabled` | `terraButtonState="disabled"` |
| Button | Danger | Small | Default | `Type=Danger, Size=Small, State=Default` | `Button / Type=Danger / Size=Small / State=Default` | `type="danger" size="small"` | `terraButtonType="danger" terraButtonSize="small"` |
| Button | Danger | Small | Disabled | `Type=Danger, Size=Small, State=Disabled` | `Button / Type=Danger / Size=Small / State=Disabled` | `disabled` | `terraButtonState="disabled"` |
| Button | Text | Small | Default | `Type=Text Button, Size=Small, State=Default` | `Button / Type=Text / Size=Small / State=Default` | `type="text" size="small"` | `terraButtonType="text" terraButtonSize="small"` |
| Button | Text | Small | Disabled | `Type=Text Button, Size=Small, State=Disabled` | `Button / Type=Text / Size=Small / State=Disabled` | `disabled` | `terraButtonState="disabled"` |
| Button | Outlined Secondary | Small | Default | `Type=Outlined Secondary, Size=Small, State=Default` | `Button / Type=Outlined Secondary / Size=Small / State=Default` | `type="outlined-secondary" size="small"` | `terraButtonType="outlinedSecondary" terraButtonSize="small"` |
| Button | Outlined Secondary | Small | Disabled | `Type=Outlined Secondary, Size=Small, State=Disabled` | `Button / Type=Outlined Secondary / Size=Small / State=Disabled` | `disabled` | `terraButtonState="disabled"` |

---

### 4.3 Header Inventory

| Category | Canonical Name | Current Figma Parts | Recommended Properties | Web Target | Android Target | Notes |
|---|---|---|---|---|---|---|
| Component | Header | Status bar, title icon frame, page identifier | Leading, Title, Subtitle, Identifier, Action, StatusBar | `TerraHeader` | `TerraHeaderView` | Component should be formalized into variants |
| Subpart | Header / Leading Icon | `Icon System / Placeholder` | `Leading=None | Back | Menu | Icon` | `leadingIcon` | `app:terraHeaderLeading` | Leading slot |
| Subpart | Header / Identifier | `Page Identifier` | `Identifier=True | False` | `identifier` | `app:terraHeaderIdentifierText` | Secondary label |
| Subpart | Header / Status Bar | `status-bar-content` | `StatusBar=True | False` | `showStatusBar` | internal flag | Mobile-specific segment |

---

### 4.4 Shadow Inventory

| Category | Canonical Name | Current Figma Source | Web Target | Android Target | Notes |
|---|---|---|---|---|---|
| Foundation | Shadow/Card | `Card Shadow` | CSS token `shadow-card` | style or drawable token | Primary surface shadow |
| Foundation | Shadow/Inner | `Inner Shadow` | CSS token `shadow-inner` | drawable/background token | Used for inset effects |

---

### 4.5 Icon Inventory

#### Action
| Canonical Name | Current Figma Source | Android Resource |
|---|---|---|
| Icon/Action/Plus | `Select=Plus` | `terra_ic_action_plus` |
| Icon/Action/Check | `Select=Check` | `terra_ic_action_check` |
| Icon/Action/Close | `Select=Close` | `terra_ic_action_close` |
| Icon/Action/Delete | `Select=Delete` | `terra_ic_action_delete` |
| Icon/Action/Edit | `Select=Edit` | `terra_ic_action_edit` |
| Icon/Action/Refresh | `Select=Refresh` | `terra_ic_action_refresh` |
| Icon/Action/Search | `Select=Search` | `terra_ic_action_search` |
| Icon/Action/Sort | `Select=Sort` | `terra_ic_action_sort` |
| Icon/Action/Qr-Scan | `Select=QR Scan` | `terra_ic_action_qr_scan` |

#### Navigation
| Canonical Name | Current Figma Source | Android Resource |
|---|---|---|
| Icon/Navigation/Arrow-Left | `Select=Arrow-Left` | `terra_ic_navigation_arrow_left` |
| Icon/Navigation/Arrow-Right | `Select=Arrow-Right` | `terra_ic_navigation_arrow_right` |
| Icon/Navigation/Chevron-Down | `Select=Chevron-Down` | `terra_ic_navigation_chevron_down` |
| Icon/Navigation/Chevron-Left | `Select=Chevron-Left` | `terra_ic_navigation_chevron_left` |
| Icon/Navigation/Chevron-Right | `Select=Chevron-Right` | `terra_ic_navigation_chevron_right` |
| Icon/Navigation/Chevron-Up | `Select=Chevron-Up` | `terra_ic_navigation_chevron_up` |
| Icon/Navigation/Kebab-Horizontal | `Select=Kebab-Horizontal` | `terra_ic_navigation_kebab_horizontal` |
| Icon/Navigation/Kebab-Horizontal-2 | `Select=Kebab-Horizontal 2` | `terra_ic_navigation_kebab_horizontal_2` |
| Icon/Navigation/Hamburger-Menu | `Select=Hamburg Menu` | `terra_ic_navigation_hamburger_menu` |

#### Status
| Canonical Name | Current Figma Source | Android Resource |
|---|---|---|
| Icon/Status/Done | `Select=Done` | `terra_ic_status_done` |
| Icon/Status/In-Progress | `Select=In Progress` | `terra_ic_status_in_progress` |
| Icon/Status/Offline | `Select=Offline` | `terra_ic_status_offline` |

#### Info
| Canonical Name | Current Figma Source | Android Resource |
|---|---|---|
| Icon/Info/Exclamation | `Select=Exclamation` | `terra_ic_info_exclamation` |
| Icon/Info/Information | `Select=Information` | `terra_ic_info_information` |
| Icon/Info/Questionmark | `Select=Questionmark` | `terra_ic_info_questionmark` |
| Icon/Info/Counter | `Select=Counter` | `terra_ic_info_counter` |

#### Domain
| Canonical Name | Current Figma Source | Android Resource |
|---|---|---|
| Icon/Domain/Cash-In | `Select=Cash In` | `terra_ic_domain_cash_in` |
| Icon/Domain/Cash-Out | `Select=Cash Out` | `terra_ic_domain_cash_out` |
| Icon/Domain/Nasabah | `Select=Nasabah` | `terra_ic_domain_nasabah` |
| Icon/Domain/Bank | `Select=Bank` | `terra_ic_domain_bank` |
| Icon/Domain/Report | `Select=Report` | `terra_ic_domain_report` |
| Icon/Domain/Report-Transaction | `Select=Report Transaction` | `terra_ic_domain_report_transaction` |
| Icon/Domain/Akusisi | `Select=Akusisi` | `terra_ic_domain_akusisi` |

Additional icons detected should follow the same naming scheme.

---

## 5. Web Preview Specification

### 5.1 Web Component Naming

Use explicit component names:
- `TerraButton`
- `TerraHeader`
- `TerraIcon`

Use camelCase props.

---

### 5.2 TerraButton API

Suggested props:
- `type: "primary" | "secondary" | "outlined-primary" | "outlined-secondary" | "danger" | "text"`
- `size: "normal" | "small"`
- `state?: "default" | "disabled"`
- `leadingIcon?: string`
- `trailingIcon?: string`
- `fullWidth?: boolean`
- `loading?: boolean`
- `disabled?: boolean`
- `children`

Example:

```tsx
<TerraButton type="primary" size="normal">Submit</TerraButton>
```

---

### 5.3 TerraHeader API

Suggested props:
- `leading: "none" | "back" | "menu" | "icon"`
- `title: string`
- `subtitle?: string`
- `identifier?: string`
- `showStatusBar?: boolean`
- `actionSlot?: React.ReactNode`

Example:

```tsx
<TerraHeader
  leading="back"
  title="Profile"
  identifier="Customer Detail"
/>
```

---

### 5.4 TerraIcon API

Suggested props:
- `category: string`
- `name: string`
- `size?: number`
- `color?: string`
- `decorative?: boolean`

Example:

```tsx
<TerraIcon category="action" name="search" size={24} />
```

---

## 6. Android XML / Views Specification

### 6.1 Package Naming Recommendation

Suggested package root:
- `com.terra.ds`

Suggested subpackages:
- `com.terra.ds.button`
- `com.terra.ds.header`
- `com.terra.ds.icon`
- `com.terra.ds.tokens`

---

### 6.2 Custom View Names

- `TerraButtonView`
- `TerraHeaderView`
- `TerraIconView`

Enum/model names:
- `TerraButtonType`
- `TerraButtonSize`
- `TerraButtonState`
- `TerraHeaderLeading`
- `TerraIconCategory`

---

### 6.3 XML Style Naming

Format:
- `Widget.Terra.{Component}.{Variant}`

Examples:
- `Widget.Terra.Button.Primary`
- `Widget.Terra.Button.Secondary`
- `Widget.Terra.Button.OutlinedPrimary`
- `Widget.Terra.Button.OutlinedSecondary`
- `Widget.Terra.Button.Danger`
- `Widget.Terra.Button.Text`
- `Widget.Terra.Header.Default`

Text appearance examples:
- `TextAppearance.Terra.Heading.H1`
- `TextAppearance.Terra.Body.Medium`
- `TextAppearance.Terra.Caption.Regular`

Resource naming examples:
- `terra_color_primary_500`
- `terra_color_secondary_500`
- `terra_bg_button_primary`
- `terra_shadow_card`

---

### 6.4 TerraButtonView XML API

Suggested XML:

```xml
<com.terra.ds.button.TerraButtonView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:terraButtonType="primary"
    app:terraButtonSize="normal"
    app:terraButtonState="default"
    android:text="Submit" />
```

Suggested attrs:
- `terraButtonType`
- `terraButtonSize`
- `terraButtonState`
- `terraButtonLeadingIcon`
- `terraButtonTrailingIcon`

Suggested enum values:
- `primary`
- `secondary`
- `outlinedPrimary`
- `outlinedSecondary`
- `danger`
- `text`
- `normal`
- `small`
- `default`
- `disabled`

---

### 6.5 TerraHeaderView XML API

Suggested XML:

```xml
<com.terra.ds.header.TerraHeaderView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:terraHeaderLeading="back"
    app:terraHeaderTitleText="Dashboard"
    app:terraHeaderIdentifierText="Daily Report"
    app:terraHeaderShowStatusBar="true" />
```

Suggested attrs:
- `terraHeaderLeading`
- `terraHeaderTitleText`
- `terraHeaderSubtitleText`
- `terraHeaderIdentifierText`
- `terraHeaderShowStatusBar`

---

### 6.6 TerraIconView XML API

Only needed if icon rendering is wrapped as a reusable view. Otherwise direct drawable use is acceptable.

Suggested XML:

```xml
<com.terra.ds.icon.TerraIconView
    android:layout_width="24dp"
    android:layout_height="24dp"
    app:terraIconCategory="action"
    app:terraIconName="search" />
```

Suggested attrs:
- `terraIconCategory`
- `terraIconName`
- `terraIconSize`
- `terraIconTint`

---

## 7. Kotlin Usage Direction

### TerraButtonView

```kotlin
val button = findViewById<TerraButtonView>(R.id.submitButton)
button.setType(TerraButtonType.PRIMARY)
button.setSize(TerraButtonSize.NORMAL)
button.setState(TerraButtonState.DEFAULT)
button.text = "Submit"
```

### TerraHeaderView

```kotlin
val header = findViewById<TerraHeaderView>(R.id.headerView)
header.setLeading(TerraHeaderLeading.BACK)
header.setTitle("Dashboard")
header.setIdentifier("Daily Report")
```

---

## 8. Java Usage Direction

### TerraButtonView

```java
TerraButtonView button = findViewById(R.id.submitButton);
button.setType(TerraButtonType.PRIMARY);
button.setSize(TerraButtonSize.NORMAL);
button.setState(TerraButtonState.DEFAULT);
button.setText("Submit");
```

### TerraHeaderView

```java
TerraHeaderView header = findViewById(R.id.headerView);
header.setLeading(TerraHeaderLeading.BACK);
header.setTitle("Dashboard");
header.setIdentifier("Daily Report");
```

---

## 9. Recommended Implementation Order

### Phase 1
- Button
- Icon
- Shadow tokens
- Header

### Phase 2
- Web preview/catalog
- Android XML attrs and styles
- Kotlin and Java usage examples

### Phase 3
- Input family
- Card
- Badge
- Modal / dialog
- Feedback components

---

## 10. Governance Recommendations

To keep the system maintainable:
- Use one canonical name per concept
- Keep Figma variant properties aligned with code API names
- Use a component checklist before publishing to the library
- Track status per component: Draft, Ready, Deprecated
- Add usage guidance later: do/don't, accessibility, layout constraints

Suggested status naming:
- `Status=Draft`
- `Status=Ready`
- `Status=Deprecated`

---

## 11. Next Steps

Recommended next deliverables after this spec:
1. Create a dedicated inventory document per component family
2. Build the web preview structure for Button, Header, and Icon
3. Build Android XML attrs/styles and custom view scaffolding
4. Continue Figma audit for additional components such as input, card, badge, modal, tabs, and list item

---

## 12. Summary

This spec establishes the first stable contract for TERRA Design System across:
- design naming
- component inventory
- web preview API
- Android XML/View integration
- Java/Kotlin usage

Initial supported component domains:
- Button
- Header
- Shadow
- Icon

This should be treated as the baseline blueprint before implementation begins.
