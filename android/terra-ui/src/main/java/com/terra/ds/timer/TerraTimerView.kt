package com.terra.ds.timer

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.terra.ds.internal.applyTerraTextAppearance
import com.terra.ds.R
import com.terra.tokens.R as TokensR
import com.terra.ds.button.TerraButtonType
import com.terra.ds.button.TerraButtonView
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraTimerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val labelView: TextView
    private val valueView: TextView

    var terraTimerLabel: String = "Label"
        set(value) {
            field = value
            labelView.text = value
        }

    var terraTimerValue: String = "00:00:12"
        set(value) {
            field = value
            valueView.text = value
        }

    var terraTimerActive: Boolean = true
        set(value) {
            field = value
            updateAppearance()
        }

    init {
        orientation = VERTICAL
        setPaddingRelative(
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_4),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_4),
        )
        setBackgroundResource(TokensR.color.terra_color_primary_surface)
        setWillNotDraw(false)

        labelView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Caption)
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_primary))
            gravity = Gravity.CENTER_HORIZONTAL
        }
        addView(labelView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))

        val row = LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        val dot = View(context).apply {
            setBackgroundColor(ContextCompat.getColor(context, TokensR.color.terra_color_primary))
        }
        val dotSize = resources.getDimensionPixelSize(TokensR.dimen.terra_timer_dot_size)
        row.addView(dot, LayoutParams(dotSize, dotSize).apply {
            marginEnd = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_4)
        })
        valueView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Caption)
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_primary))
            gravity = Gravity.CENTER_VERTICAL
        }
        row.addView(valueView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
        addView(row, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))

        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraTimerView)
        terraTimerLabel = typedArray.getString(R.styleable.TerraTimerView_terraTimerLabel) ?: terraTimerLabel
        terraTimerValue = typedArray.getString(R.styleable.TerraTimerView_terraTimerValue) ?: terraTimerValue
        terraTimerActive = typedArray.getBoolean(R.styleable.TerraTimerView_terraTimerActive, true)
        typedArray.recycle()
    }

    private fun bind() {
        labelView.text = terraTimerLabel
        valueView.text = terraTimerValue
        updateAppearance()
    }

    private fun updateAppearance() {
        val backgroundColor = if (terraTimerActive) TokensR.color.terra_color_primary_surface else android.R.color.transparent
        setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
    }
}
