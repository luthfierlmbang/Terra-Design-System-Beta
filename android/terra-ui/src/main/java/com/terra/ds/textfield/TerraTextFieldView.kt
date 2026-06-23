package com.terra.ds.textfield

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
import com.terra.tokens.R as TokensR
import com.terra.ds.button.TerraButtonType
import com.terra.ds.button.TerraButtonView
import com.terra.ds.timer.TerraTimerView
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraTextFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val labelView: TextView
    private val fieldContainer: LinearLayout
    private val prefixView: TextView
    private val leftIconView: TerraIconView
    private val inputView: TextView
    private val suffixView: TextView
    private val rightIconView: TerraIconView
    private val helperView: TextView

    var terraTextFieldLabel: String = "Label"
        set(value) {
            field = value
            labelView.text = value
        }

    var terraTextFieldPlaceholder: String = "Placeholder"
        set(value) {
            field = value
            inputView.text = value
        }

    var terraTextFieldHelper: String = "Helper Type Here"
        set(value) {
            field = value
            helperView.text = value
        }

    var terraTextFieldVariant: TerraTextFieldVariant = TerraTextFieldVariant.DEFAULT
        set(value) {
            field = value
            updateVariant()
        }

    var terraTextFieldShowLabel: Boolean = true
        set(value) {
            field = value
            labelView.visibility = if (value) View.VISIBLE else View.GONE
        }

    var terraTextFieldShowHelper: Boolean = true
        set(value) {
            field = value
            helperView.visibility = if (value) View.VISIBLE else View.GONE
        }

    var terraTextFieldShowLeftIcon: Boolean = false
        set(value) {
            field = value
            updateVariant()
        }

    var terraTextFieldShowRightIcon: Boolean = false
        set(value) {
            field = value
            updateVariant()
        }

    init {
        orientation = VERTICAL
        labelView = labelText()
        fieldContainer = LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            minimumHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_text_field_height)
            setPaddingRelative(
                resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16),
                resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
                resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16),
                resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
            )
            background = roundedBackground(context, TokensR.color.terra_color_surface, TokensR.color.terra_color_border, 1, TokensR.dimen.terra_radius_medium)
        }
        prefixView = AppCompatTextView(context).apply {
            text = "Rp"
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Body_Small)
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_secondary))
            visibility = View.GONE
        }
        leftIconView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.ACTION
            terraIconName = TerraIconName.SEARCH
            visibility = View.GONE
        }
        inputView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Body_Small)
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_tertiary))
        }
        suffixView = AppCompatTextView(context).apply {
            text = "Kg"
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Body_Small)
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_secondary))
            visibility = View.GONE
        }
        rightIconView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.ACTION
            terraIconName = TerraIconName.CLOSE
            visibility = View.GONE
        }
        helperView = helperText()

        fieldContainer.addView(prefixView)
        fieldContainer.addView(leftIconView)
        fieldContainer.addView(inputView, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f).apply {
            marginStart = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8)
            marginEnd = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8)
        })
        fieldContainer.addView(suffixView)
        fieldContainer.addView(rightIconView)
        addView(labelView)
        addView(fieldContainer)
        addView(helperView)

        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraTextFieldView)
        terraTextFieldLabel = typedArray.getString(R.styleable.TerraTextFieldView_terraTextFieldLabel) ?: terraTextFieldLabel
        terraTextFieldPlaceholder = typedArray.getString(R.styleable.TerraTextFieldView_terraTextFieldPlaceholder) ?: terraTextFieldPlaceholder
        terraTextFieldHelper = typedArray.getString(R.styleable.TerraTextFieldView_terraTextFieldHelper) ?: terraTextFieldHelper
        terraTextFieldVariant = when (typedArray.getInt(R.styleable.TerraTextFieldView_terraTextFieldVariant, 0)) {
            1 -> TerraTextFieldVariant.TEXT_PREFIX
            2 -> TerraTextFieldVariant.TEXT_PREFIX_ICON
            3 -> TerraTextFieldVariant.TEXT_SUFFIX
            else -> TerraTextFieldVariant.DEFAULT
        }
        terraTextFieldShowLabel = typedArray.getBoolean(R.styleable.TerraTextFieldView_terraTextFieldShowLabel, true)
        terraTextFieldShowHelper = typedArray.getBoolean(R.styleable.TerraTextFieldView_terraTextFieldShowHelper, true)
        terraTextFieldShowLeftIcon = typedArray.getBoolean(R.styleable.TerraTextFieldView_terraTextFieldShowLeftIcon, false)
        terraTextFieldShowRightIcon = typedArray.getBoolean(R.styleable.TerraTextFieldView_terraTextFieldShowRightIcon, false)
        typedArray.recycle()
    }

    private fun bind() {
        labelView.text = terraTextFieldLabel
        inputView.text = terraTextFieldPlaceholder
        helperView.text = terraTextFieldHelper
        labelView.visibility = if (terraTextFieldShowLabel) View.VISIBLE else View.GONE
        helperView.visibility = if (terraTextFieldShowHelper) View.VISIBLE else View.GONE
        updateVariant()
    }

    private fun updateVariant() {
        prefixView.visibility = if (terraTextFieldVariant == TerraTextFieldVariant.TEXT_PREFIX || terraTextFieldVariant == TerraTextFieldVariant.TEXT_PREFIX_ICON) View.VISIBLE else View.GONE
        suffixView.visibility = if (terraTextFieldVariant == TerraTextFieldVariant.TEXT_SUFFIX) View.VISIBLE else View.GONE
        leftIconView.visibility = if (terraTextFieldShowLeftIcon || terraTextFieldVariant == TerraTextFieldVariant.TEXT_PREFIX_ICON) View.VISIBLE else View.GONE
        rightIconView.visibility = if (terraTextFieldShowRightIcon) View.VISIBLE else View.GONE
    }

    private fun labelText(): TextView {
        return AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Caption)
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_secondary))
        }
    }

    private fun helperText(): TextView {
        return AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Caption)
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_tertiary))
        }
    }
}

private fun roundedBackground(
    context: Context,
    fillColor: Int,
    strokeColor: Int,
    strokeWidth: Int,
    radius: Int,
): GradientDrawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        setColor(ContextCompat.getColor(context, fillColor))
        setStroke(strokeWidth, ContextCompat.getColor(context, strokeColor))
        cornerRadius = context.resources.getDimension(radius)
    }
}
enum class TerraTextFieldVariant {
    DEFAULT,
    TEXT_PREFIX,
    TEXT_PREFIX_ICON,
    TEXT_SUFFIX,
}
