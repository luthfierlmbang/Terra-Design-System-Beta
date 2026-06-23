package com.terra.ds.quantityeditor

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.terra.ds.internal.applyTerraTextAppearance
import com.terra.ds.R
import com.terra.ds.internal.roundedBackground
import com.terra.tokens.R as TokensR
import com.terra.ds.button.TerraButtonType
import com.terra.ds.button.TerraButtonView
import com.terra.ds.timer.TerraTimerView
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraQuantityEditorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val minusView: TextView
    private val valueView: TextView
    private val plusView: TextView
    private val leftDivider: View
    private val rightDivider: View

    var terraQuantityValue: String = "1.000"
        set(value) {
            field = value
            valueView.text = value
        }

    var terraQuantityState: TerraQuantityEditorState = TerraQuantityEditorState.DEFAULT
        set(value) {
            field = value
            updateAppearance()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        minimumWidth = resources.getDimensionPixelSize(TokensR.dimen.terra_quantity_editor_width)
        minimumHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_text_field_height)
        background = roundedBackground(context, TokensR.color.terra_color_surface, TokensR.color.terra_color_border, 1, TokensR.dimen.terra_radius_medium)

        minusView = quantityActionText("−")
        valueView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Body_Medium)
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_primary))
        }
        plusView = quantityActionText("+")
        leftDivider = dividerView()
        rightDivider = dividerView()

        addView(minusView, LayoutParams(resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_40), LayoutParams.MATCH_PARENT))
        addView(leftDivider, LayoutParams(1, LayoutParams.MATCH_PARENT))
        addView(valueView, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f))
        addView(rightDivider, LayoutParams(1, LayoutParams.MATCH_PARENT))
        addView(plusView, LayoutParams(resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_40), LayoutParams.MATCH_PARENT))

        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraQuantityEditorView)
        terraQuantityValue = typedArray.getString(R.styleable.TerraQuantityEditorView_terraQuantityValue) ?: terraQuantityValue
        terraQuantityState = when (typedArray.getInt(R.styleable.TerraQuantityEditorView_terraQuantityState, 0)) {
            1 -> TerraQuantityEditorState.MINIMUM
            2 -> TerraQuantityEditorState.MAXIMUM
            else -> TerraQuantityEditorState.DEFAULT
        }
        typedArray.recycle()
    }

    private fun bind() {
        valueView.text = terraQuantityValue
        updateAppearance()
    }

    private fun updateAppearance() {
        val disabledMinus = terraQuantityState == TerraQuantityEditorState.MINIMUM
        val disabledPlus = terraQuantityState == TerraQuantityEditorState.MAXIMUM
        minusView.setTextColor(ContextCompat.getColor(context, if (disabledMinus) TokensR.color.terra_color_disabled else TokensR.color.terra_color_text_primary))
        plusView.setTextColor(ContextCompat.getColor(context, if (disabledPlus) TokensR.color.terra_color_disabled else TokensR.color.terra_color_text_primary))
        valueView.setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_primary))
        leftDivider.setBackgroundColor(ContextCompat.getColor(context, TokensR.color.terra_color_border))
        rightDivider.setBackgroundColor(ContextCompat.getColor(context, TokensR.color.terra_color_border))
    }

    private fun quantityActionText(symbol: String): TextView {
        return AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Header_L)
            text = symbol
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_primary))
        }
    }

    private fun dividerView(): View {
        return View(context).apply {
            setBackgroundColor(ContextCompat.getColor(context, TokensR.color.terra_color_border))
        }
    }
}
enum class TerraQuantityEditorState {
    DEFAULT,
    MINIMUM,
    MAXIMUM,
}
