package com.oasis.designsystem.catalog

object OasisCatalogRegistry {
    data class Entry(val title: String, val layoutRes: Int)

    val entries = listOf(
        Entry("Button", R.layout.oasis_catalog_button),
        Entry("Icon", R.layout.oasis_catalog_icon),
        Entry("Illustration", R.layout.oasis_catalog_illustration),
        Entry("Avatar", R.layout.oasis_catalog_avatar),
        Entry("Tag", R.layout.oasis_catalog_tag),
        Entry("Checkbox", R.layout.oasis_catalog_checkbox),
        Entry("Radio", R.layout.oasis_catalog_radio),
        Entry("Toggle", R.layout.oasis_catalog_toggle),
        Entry("TextField", R.layout.oasis_catalog_textfield),
        Entry("SearchBar", R.layout.oasis_catalog_searchbar),
        Entry("NumericInput", R.layout.oasis_catalog_numeric),
        Entry("TextArea", R.layout.oasis_catalog_textarea),
        Entry("Chips", R.layout.oasis_catalog_chips),
        Entry("Dropdown", R.layout.oasis_catalog_dropdown),
        Entry("QtyInput", R.layout.oasis_catalog_qtyinput),
        Entry("Table", R.layout.oasis_catalog_table),
        Entry("Toast", R.layout.oasis_catalog_toast),
        Entry("Alert", R.layout.oasis_catalog_alert),
        Entry("ProgressBar", R.layout.oasis_catalog_progressbar),
        Entry("Tabs", R.layout.oasis_catalog_tabs),
        Entry("IndicatorGroup", R.layout.oasis_catalog_indicator),
        Entry("BottomNav", R.layout.oasis_catalog_bottomnav),
        Entry("Header", R.layout.oasis_catalog_header),
        Entry("Modal", R.layout.oasis_catalog_modal),
        Entry("FileUpload", R.layout.oasis_catalog_fileupload),
    )
}
