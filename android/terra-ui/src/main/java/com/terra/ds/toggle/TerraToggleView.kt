package com.terra.ds.toggle

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
import com.terra.ds.R
import com.terra.ds.internal.defaultLabelText
import com.terra.ds.internal.roundedBackground
import com.terra.tokens.R as TokensR
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraToggleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val trackView: LinearLayout
    private val thumbView: View
    private val spacerView: View
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
        trackView = LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_2), 0, resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_2), 0)
        }
        thumbView = View(context)
        spacerView = View(context)
        labelView = defaultLabelText()
        trackView.addView(thumbView)
        trackView.addView(spacerView)
        addView(trackView, LayoutParams(resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_40), resources.getDimensionPixelSize(TokensR.dimen.terra_selection_control_size)))
        addView(labelView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            marginStart = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8)
        })
        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraToggleView)
        terraSelectionLabel = typedArray.getString(R.styleable.TerraToggleView_terraSelectionLabel) ?: terraSelectionLabel
        terraSelectionChecked = typedArray.getBoolean(R.styleable.TerraToggleView_terraSelectionChecked, false)
        terraSelectionEnabled = typedArray.getBoolean(R.styleable.TerraToggleView_terraSelectionEnabled, true)
        typedArray.recycle()
    }

    private fun bind() {
        labelView.text = terraSelectionLabel
        updateAppearance()
    }

    private fun updateAppearance() {
        trackView.background = roundedBackground(context, if (terraSelectionChecked && terraSelectionEnabled) TokensR.color.terra_color_primary else TokensR.color.terra_color_disabled_surface, android.R.color.transparent, 0, TokensR.dimen.terra_radius_pill)
        thumbView.background = roundedBackground(context, TokensR.color.terra_color_surface, android.R.color.transparent, 0, TokensR.dimen.terra_radius_pill)
        val thumbSize = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_20)
        thumbView.layoutParams = LinearLayout.LayoutParams(thumbSize, thumbSize)
        spacerView.layoutParams = LinearLayout.LayoutParams(0, 1, 1f)
        if (terraSelectionChecked) {
            trackView.removeAllViews()
            trackView.addView(spacerView)
            trackView.addView(thumbView)
        } else {
            trackView.removeAllViews()
            trackView.addView(thumbView)
            trackView.addView(spacerView)
        }
        labelView.setTextColor(ContextCompat.getColor(context, if (terraSelectionEnabled) TokensR.color.terra_color_text_primary else TokensR.color.terra_color_text_tertiary))
    }
}
