package com.oasis.designsystem.catalog

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.oasis.designsystem.catalog.databinding.ActivityPreviewBinding
import com.oasis.designsystem.table.OasisTable
import com.oasis.designsystem.tabs.OasisTabs

class PreviewActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LAYOUT_RES = "layout_res"
        const val EXTRA_TITLE = "title"
    }

    private lateinit var binding: ActivityPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = intent.getStringExtra(EXTRA_TITLE) ?: "Preview"
        val layoutRes = intent.getIntExtra(EXTRA_LAYOUT_RES, 0)
        if (layoutRes != 0) {
            LayoutInflater.from(this).inflate(layoutRes, binding.previewContainer, true)
            setupDynamicContent(layoutRes)
        }
    }

    private fun setupDynamicContent(layoutRes: Int) {
        if (layoutRes == R.layout.oasis_catalog_table) {
            binding.previewContainer.findViewById<OasisTable>(R.id.tableDemo)?.setData(
                headers = listOf("Name", "Status", "Role"),
                rows = listOf(
                    listOf("Alya", "Active", "Admin"),
                    listOf("Bimo", "Pending", "Editor"),
                    listOf("Citra", "Inactive", "Viewer"),
                ),
            )
        }

        if (layoutRes == R.layout.oasis_catalog_tabs) {
            binding.previewContainer.findViewById<OasisTabs>(R.id.tabsDemo)?.setTabs(
                listOf("Overview", "Details", "Activity")
            )
        }
    }
}
