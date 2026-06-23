package com.terra.catalog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "TERRA Catalog"
        setContentView(R.layout.activity_catalog_list)

        val recyclerView = findViewById<RecyclerView>(R.id.catalogRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CatalogEntryAdapter(CatalogEntry.entries.toList()) { entry ->
            startActivity(Intent(this, PreviewActivity::class.java).apply {
                putExtra(PreviewActivity.EXTRA_TITLE, entry.title)
                putExtra(PreviewActivity.EXTRA_LAYOUT_RES, entry.layoutRes)
            })
        }
    }
}

private class CatalogEntryAdapter(
    private val items: List<CatalogEntry>,
    private val onClick: (CatalogEntry) -> Unit,
) : RecyclerView.Adapter<CatalogEntryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogEntryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_catalog_entry, parent, false)
        return CatalogEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatalogEntryViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }

    override fun getItemCount(): Int = items.size
}

private class CatalogEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleView: TextView = itemView.findViewById(R.id.catalogEntryTitle)
    private val descriptionView: TextView = itemView.findViewById(R.id.catalogEntryDescription)

    fun bind(entry: CatalogEntry, onClick: (CatalogEntry) -> Unit) {
        titleView.text = entry.title
        descriptionView.text = entry.description
        itemView.setOnClickListener { onClick(entry) }
    }
}

enum class CatalogEntry(
    val title: String,
    val description: String,
    val layoutRes: Int,
) {
    CARD(
        title = "Card",
        description = "Preview menu card dan information card variants.",
        layoutRes = R.layout.terra_catalog_card,
    ),
    BUTTONS(
        title = "Buttons",
        description = "Preview button variants dan ukuran utama.",
        layoutRes = R.layout.terra_catalog_buttons,
    ),
    FEEDBACK_SELECTION(
        title = "Feedback & Selection",
        description = "Preview chip, navbar, checkbox, radio, toggle, tabs, ticker, stepper, dan empty state.",
        layoutRes = R.layout.terra_catalog_feedback_selection,
    ),
    FORMS_OVERLAY(
        title = "Forms & Overlay",
        description = "Preview search bar, select, text field, quantity editor, image upload, bottom sheet, dan date picker.",
        layoutRes = R.layout.terra_catalog_forms_overlay,
    ),
    HEADER_ICON(
        title = "Header & Icon",
        description = "Preview header module dan kumpulan icon dasar.",
        layoutRes = R.layout.terra_catalog_header_icon,
    ),
}
