package com.oasis.designsystem.catalog

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oasis.designsystem.catalog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Oasis Catalog"

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CatalogAdapter(OasisCatalogRegistry.entries) { entry ->
            startActivity(
                Intent(this, PreviewActivity::class.java)
                    .putExtra(PreviewActivity.EXTRA_LAYOUT_RES, entry.layoutRes)
                    .putExtra(PreviewActivity.EXTRA_TITLE, entry.title)
            )
        }
    }
}