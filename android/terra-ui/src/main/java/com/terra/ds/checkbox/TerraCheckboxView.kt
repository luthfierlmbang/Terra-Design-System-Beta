package com.terra.ds.checkbox

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.terra.ds.internal.applyTerraTextAppearance
import com.terra.ds.R
import com.terra.tokens.R as TokensR
import com.terra.ds.internal.defaultLabelText
import com.terra.ds.internal.selectionBackground

class TerraCheckboxView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val boxView: TextView
    private val labelView: TextView

    var terraSelectionLabel: String = "Label"
        set(value) {
            field = value
            labelView.text = value
        }

    var terraSelectionChecked: Boolean = false
        set(value) {
            field = value
            updateAppearance()
        }

    var terraSelectionEnabled: Boolean = true
        set(value) {
            field = value
            isEnabled = value
            updateAppearance()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        boxView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Body_Small)
            gravity = Gravity.CENTER
        }
        labelView = defaultLabelText()
        val size = resources.getDimensionPixelSize(TokensR.dimen.terra_selection_control_size)
        addView(boxView, LayoutParams(size, size))
        addView(labelView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            marginStart = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8)
        })
        parseAttributes(attrs, R.styleable.TerraCheckboxView)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?, styleable: IntArray) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, styleable)
        terraSelectionLabel = typedArray.getString(R.styleable.TerraCheckboxView_terraSelectionLabel) ?: terraSelectionLabel
        terraSelectionChecked = typedArray.getBoolean(R.styleable.TerraCheckboxView_terraSelectionChecked, false)
        terraSelectionEnabled = typedArray.getBoolean(R.styleable.TerraCheckboxView_terraSelectionEnabled, true)
        typedArray.recycle()
    }

    private fun bind() {
        labelView.text = terraSelectionLabel
        updateAppearance()
    }

    private fun updateAppearance() {
        boxView.text = if (terraSelectionChecked) "✓" else ""
        boxView.background = selectionBackground(context, terraSelectionChecked, terraSelectionEnabled, TokensR.dimen.terra_radius_small)
        boxView.setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_on_primary))
        labelView.setTextColor(ContextCompat.getColor(context, if (terraSelectionEnabled) TokensR.color.terra_color_text_primary else TokensR.color.terra_color_text_tertiary))
    }
}
