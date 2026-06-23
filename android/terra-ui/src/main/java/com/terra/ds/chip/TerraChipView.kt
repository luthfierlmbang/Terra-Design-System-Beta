package com.terra.ds.chip

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
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraChipView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val leftIconView: TerraIconView
    private val textView: TextView
    private val rightIconView: TerraIconView
    private val counterView: TextView

    var terraChipText: String = "This is Chips"
        set(value) {
            field = value
            textView.text = value
        }

    var terraChipSelected: Boolean = false
        set(value) {
            field = value
            updateAppearance()
        }

    var terraChipShowLeftIcon: Boolean = false
        set(value) {
            field = value
            leftIconView.visibility = if (value) View.VISIBLE else View.GONE
        }

    var terraChipShowRightIcon: Boolean = false
        set(value) {
            field = value
            rightIconView.visibility = if (value) View.VISIBLE else View.GONE
        }

    var terraChipShowCounter: Boolean = false
        set(value) {
            field = value
            counterView.visibility = if (value) View.VISIBLE else View.GONE
        }

    var terraChipCounterValue: Int = 2
        set(value) {
            field = value
            counterView.text = value.toString()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        minimumHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_chip_height)
        setPaddingRelative(
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
        )

        leftIconView = iconView()
        rightIconView = iconView()
        textView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Body_Small)
        }
        counterView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Caption)
            setTypeface(typeface, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_on_primary))
            setBackgroundColor(ContextCompat.getColor(context, TokensR.color.terra_color_error))
            gravity = Gravity.CENTER
            visibility = View.GONE
            minWidth = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16)
            minHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16)
        }

        addView(leftIconView)
        addView(textView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            marginStart = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_4)
            marginEnd = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_4)
        })
        addView(rightIconView)
        addView(counterView)

        parseAttributes(attrs)
        bind()
    }

    private fun iconView(): TerraIconView {
        return TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.ACTION
            terraIconName = TerraIconName.PLUS
            visibility = View.GONE
            setIconTint(ContextCompat.getColor(context, TokensR.color.terra_color_primary))
        }
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraChipView)
        terraChipText = typedArray.getString(R.styleable.TerraChipView_terraChipText) ?: terraChipText
        terraChipSelected = typedArray.getBoolean(R.styleable.TerraChipView_terraChipSelected, false)
        terraChipShowLeftIcon = typedArray.getBoolean(R.styleable.TerraChipView_terraChipShowLeftIcon, false)
        terraChipShowRightIcon = typedArray.getBoolean(R.styleable.TerraChipView_terraChipShowRightIcon, false)
        terraChipShowCounter = typedArray.getBoolean(R.styleable.TerraChipView_terraChipShowCounter, false)
        terraChipCounterValue = typedArray.getInt(R.styleable.TerraChipView_terraChipCounterValue, terraChipCounterValue)
        typedArray.recycle()
    }

    private fun bind() {
        textView.text = terraChipText
        counterView.text = terraChipCounterValue.toString()
        updateAppearance()
        terraChipShowLeftIcon = terraChipShowLeftIcon
        terraChipShowRightIcon = terraChipShowRightIcon
        terraChipShowCounter = terraChipShowCounter
    }

    private fun updateAppearance() {
        if (terraChipSelected) {
            setBackgroundColor(ContextCompat.getColor(context, TokensR.color.terra_color_primary_light))
            textView.setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_primary))
            textView.setTypeface(textView.typeface, Typeface.BOLD)
            minimumHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_chip_selected_height)
        } else {
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            textView.setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_tertiary))
            textView.setTypeface(textView.typeface, Typeface.NORMAL)
            minimumHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_chip_height)
        }
    }
}
