package com.terra.ds.radio

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
import com.terra.ds.internal.defaultLabelText
import com.terra.ds.internal.selectionBackground
import com.terra.tokens.R as TokensR
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraRadioView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val radioView: TextView
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
        radioView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Caption)
            gravity = Gravity.CENTER
        }
        labelView = defaultLabelText()
        val size = resources.getDimensionPixelSize(TokensR.dimen.terra_selection_control_size)
        addView(radioView, LayoutParams(size, size))
        addView(labelView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            marginStart = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8)
        })
        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraRadioView)
        terraSelectionLabel = typedArray.getString(R.styleable.TerraRadioView_terraSelectionLabel) ?: terraSelectionLabel
        terraSelectionChecked = typedArray.getBoolean(R.styleable.TerraRadioView_terraSelectionChecked, false)
        terraSelectionEnabled = typedArray.getBoolean(R.styleable.TerraRadioView_terraSelectionEnabled, true)
        typedArray.recycle()
    }

    private fun bind() {
        labelView.text = terraSelectionLabel
        updateAppearance()
    }

    private fun updateAppearance() {
        radioView.text = if (terraSelectionChecked) "●" else ""
        radioView.background = selectionBackground(context, terraSelectionChecked, terraSelectionEnabled, TokensR.dimen.terra_radius_pill)
        radioView.setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_on_primary))
        labelView.setTextColor(ContextCompat.getColor(context, if (terraSelectionEnabled) TokensR.color.terra_color_text_primary else TokensR.color.terra_color_text_tertiary))
    }
}
