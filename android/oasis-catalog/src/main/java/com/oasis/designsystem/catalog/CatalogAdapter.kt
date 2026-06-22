package com.oasis.designsystem.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oasis.designsystem.catalog.databinding.ItemCatalogEntryBinding

class CatalogAdapter(
    private val items: List<OasisCatalogRegistry.Entry>,
    private val onClick: (OasisCatalogRegistry.Entry) -> Unit,
) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCatalogEntryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCatalogEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleText.text = item.title
        holder.binding.root.setOnClickListener { onClick(item) }
    }

    override fun getItemCount(): Int = items.size
}
