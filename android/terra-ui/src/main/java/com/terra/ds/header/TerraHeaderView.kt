package com.terra.ds.header

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.terra.ds.R
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val statusBar: LinearLayout
    private val titleView: TextView
    private val pageIdentifierView: TextView
    private val iconContainer: FrameLayout

    var terraHeaderTitle: String = "Title"
        set(value) {
            field = value
            titleView.text = value
        }

    var terraHeaderModuleName: String = "Put Main Module name"
        set(value) {
            field = value
            updatePageIdentifier()
        }

    var terraHeaderApkName: String = "Put APK Name"
        set(value) {
            field = value
            updatePageIdentifier()
        }

    var terraHeaderShowStatusBar: Boolean = true
        set(value) {
            field = value
            statusBar.visibility = if (value) View.VISIBLE else View.GONE
        }

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.terra_view_header, this, true)

        statusBar = findViewById(R.id.terraHeaderStatusBar)
        titleView = findViewById(R.id.terraHeaderTitle)
        pageIdentifierView = findViewById(R.id.terraHeaderPageIdentifier)
        iconContainer = findViewById(R.id.terraHeaderIconContainer)

        parseAttributes(attrs)
        ensureDefaultIcon()
        bind()
    }

    fun setIcon(view: View) {
        iconContainer.removeAllViews()
        iconContainer.addView(view)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraHeaderView)
        terraHeaderTitle = typedArray.getString(R.styleable.TerraHeaderView_terraHeaderTitle) ?: terraHeaderTitle
        terraHeaderModuleName = typedArray.getString(R.styleable.TerraHeaderView_terraHeaderModuleName) ?: terraHeaderModuleName
        terraHeaderApkName = typedArray.getString(R.styleable.TerraHeaderView_terraHeaderApkName) ?: terraHeaderApkName
        terraHeaderShowStatusBar = typedArray.getBoolean(R.styleable.TerraHeaderView_terraHeaderShowStatusBar, true)
        typedArray.recycle()
    }

    private fun bind() {
        titleView.text = terraHeaderTitle
        updatePageIdentifier()
        statusBar.visibility = if (terraHeaderShowStatusBar) View.VISIBLE else View.GONE
    }

    private fun updatePageIdentifier() {
        pageIdentifierView.text = "$terraHeaderModuleName | $terraHeaderApkName"
    }

    private fun ensureDefaultIcon() {
        if (iconContainer.childCount > 0) return

        val iconView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.SYSTEM
            terraIconName = TerraIconName.PLACEHOLDER
            setIconTint(ContextCompat.getColor(context, R.color.terra_color_neutral_10))
        }
        iconContainer.addView(iconView)
    }
}

enum class TerraHeaderVariant {
    DEFAULT,
}
