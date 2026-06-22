package com.oasis.designsystem.button

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.oasis.designsystem.R
import com.oasis.designsystem.tokens.R as TokensR

class OasisButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.buttonStyle,
) : AppCompatButton(context, attrs, defStyleAttr) {

    var variant: OasisButtonVariant = OasisButtonVariant.PRIMARY
        set(value) {
            field = value
            applyStyle()
        }

    var size: OasisButtonSize = OasisButtonSize.MEDIUM
        set(value) {
            field = value
            applySize()
        }

    init {
        isAllCaps = false
        parseAttributes(attrs)
        applySize()
        applyStyle()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        applyStyle()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisButtonView)
        variant = OasisButtonVariant.from(
            typedArray.getInt(R.styleable.OasisButtonView_oasisButtonVariant, 0)
        )
        size = OasisButtonSize.from(
            typedArray.getInt(R.styleable.OasisButtonView_oasisButtonSize, 1)
        )
        typedArray.recycle()
    }

    private fun applySize() {
        when (size) {
            OasisButtonSize.LARGE -> {
                minHeight = context.resources.getDimensionPixelSize(TokensR.dimen.oasis_button_height_large)
                val horizontalPadding = context.resources.getDimensionPixelSize(TokensR.dimen.oasis_button_padding_horizontal)
                setPaddingRelative(horizontalPadding, paddingTop, horizontalPadding, paddingBottom)
                TextViewCompat.setTextAppearance(this, R.style.TextAppearance_Oasis_Button_Large)
            }
            OasisButtonSize.MEDIUM -> {
                minHeight = context.resources.getDimensionPixelSize(TokensR.dimen.oasis_button_height_medium)
                val horizontalPadding = context.resources.getDimensionPixelSize(TokensR.dimen.oasis_button_padding_horizontal)
                setPaddingRelative(horizontalPadding, paddingTop, horizontalPadding, paddingBottom)
                TextViewCompat.setTextAppearance(this, R.style.TextAppearance_Oasis_Button_Medium)
            }
            OasisButtonSize.SMALL -> {
                minHeight = context.resources.getDimensionPixelSize(TokensR.dimen.oasis_button_height_small)
                val horizontalPadding = context.resources.getDimensionPixelSize(TokensR.dimen.oasis_spacing_16)
                setPaddingRelative(horizontalPadding, paddingTop, horizontalPadding, paddingBottom)
                TextViewCompat.setTextAppearance(this, R.style.TextAppearance_Oasis_Button_Small)
            }
        }
    }

    private fun applyStyle() {
        val appearance = when {
            !isEnabled -> resolveDisabledAppearance()
            else -> resolveEnabledAppearance()
        }

        background = ContextCompat.getDrawable(context, appearance.backgroundRes)
        setTextColor(ContextCompat.getColor(context, appearance.textColorRes))
    }

    private fun resolveEnabledAppearance(): ButtonAppearance {
        return when (variant) {
            OasisButtonVariant.PRIMARY -> ButtonAppearance(
                R.drawable.oasis_bg_button_primary,
                TokensR.color.oasis_text_on_primary,
            )
            OasisButtonVariant.SECONDARY -> ButtonAppearance(
                R.drawable.oasis_bg_button_secondary,
                TokensR.color.oasis_text_on_primary,
            )
            OasisButtonVariant.TERTIARY -> ButtonAppearance(
                R.drawable.oasis_bg_button_tertiary,
                TokensR.color.oasis_primary,
            )
        }
    }

    private fun resolveDisabledAppearance(): ButtonAppearance {
        return ButtonAppearance(
            R.drawable.oasis_bg_button_disabled,
            TokensR.color.oasis_text_disabled,
        )
    }

    private data class ButtonAppearance(
        @DrawableRes val backgroundRes: Int,
        @ColorRes val textColorRes: Int,
    )
}