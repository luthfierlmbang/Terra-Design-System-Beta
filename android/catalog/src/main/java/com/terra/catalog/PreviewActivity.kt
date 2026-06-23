package com.terra.catalog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val titleText = intent.getStringExtra(EXTRA_TITLE) ?: "Preview"
        val layoutRes = intent.getIntExtra(EXTRA_LAYOUT_RES, 0)

        title = titleText

        require(layoutRes != 0) { "Preview layout resource is required." }
        setContentView(layoutRes)
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_LAYOUT_RES = "extra_layout_res"
    }
}
