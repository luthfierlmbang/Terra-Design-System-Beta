# TERRA UI component package structure

Recommended package structure is one component per folder:

- `button/`
- `header/`
- `icon/`
- `timer/`
- `loading/`
- `pagecontrol/`
- `progressbar/`
- `stepper/`
- `ticker/`
- `emptystate/`
- `chip/`
- `navbar/`
- `checkbox/`
- `radio/`
- `toggle/`
- `tab/`
- `imageupload/`
- `bottomsheet/`
- `datepicker/`
- `searchbar/`
- `select/`
- `textfield/`
- `quantityeditor/`

Current status:
- `button`, `header`, and `icon` already use dedicated packages.
- Some components are still grouped in shared source files under:
  - `feedback/TerraFeedbackViews.kt`
  - `form/TerraFormViews.kt`
  - `selection/TerraSelectionViews.kt`

Next cleanup step:
- Split grouped files into one Kotlin file per component and move each into its matching package folder.
- Add matching `res/layout`, `res/values/attrs_*`, and catalog demo layout files per component where needed.
