package com.oasis.designsystem.catalog

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.oasis.designsystem.catalog.databinding.ActivityPreviewBinding

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
        }
    }
}
