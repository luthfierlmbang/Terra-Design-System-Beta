package com.oasis.designsystem.catalog

object OasisCatalogRegistry {
    data class Entry(val title: String, val layoutRes: Int)

    val entries = listOf(
        Entry("Button", R.layout.oasis_catalog_button),
        Entry("Icon", R.layout.oasis_catalog_icon),
        Entry("Illustration", R.layout.oasis_catalog_illustration),
        Entry("Tag", R.layout.oasis_catalog_tag),
    )
}
