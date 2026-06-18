package com.terra.ds.button

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.terra.ds.R

enum class TerraButtonType {
    PRIMARY,
    SECONDARY,
    OUTLINED_PRIMARY,
    OUTLINED_SECONDARY,
    DANGER,
    TEXT,
}

enum class TerraButtonSize {
    NORMAL,
    SMALL,
}

class TerraButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.buttonStyle,
) : AppCompatButton(context, attrs, defStyleAttr) {

    var terraButtonType: TerraButtonType = TerraButtonType.PRIMARY
        set(value) {
            field = value
            applyStyle()
        }

    var terraButtonSize: TerraButtonSize = TerraButtonSize.NORMAL
        set(value) {
            field = value
            applyStyle()
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

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraButtonView)
        terraButtonType = when (typedArray.getInt(R.styleable.TerraButtonView_terraButtonType, 0)) {
            1 -> TerraButtonType.SECONDARY
            2 -> TerraButtonType.OUTLINED_PRIMARY
            3 -> TerraButtonType.OUTLINED_SECONDARY
            4 -> TerraButtonType.DANGER
            5 -> TerraButtonType.TEXT
            else -> TerraButtonType.PRIMARY
        }

        terraButtonSize = when (typedArray.getInt(R.styleable.TerraButtonView_terraButtonSize, 0)) {
            1 -> TerraButtonSize.SMALL
            else -> TerraButtonSize.NORMAL
        }

        typedArray.recycle()
    }

    private fun applySize() {
        when (terraButtonSize) {
            TerraButtonSize.NORMAL -> {
                minHeight = context.resources.getDimensionPixelSize(R.dimen.terra_button_height_normal)
                val horizontalPadding = context.resources.getDimensionPixelSize(R.dimen.terra_spacing_16)
                setPaddingRelative(horizontalPadding, paddingTop, horizontalPadding, paddingBottom)
                textSize = 14f
            }
            TerraButtonSize.SMALL -> {
                minHeight = context.resources.getDimensionPixelSize(R.dimen.terra_button_height_small)
                val horizontalPadding = context.resources.getDimensionPixelSize(R.dimen.terra_spacing_12)
                setPaddingRelative(horizontalPadding, paddingTop, horizontalPadding, paddingBottom)
                textSize = 12f
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
        return when (terraButtonType) {
            TerraButtonType.PRIMARY -> ButtonAppearance(
                R.drawable.terra_bg_button_primary,
                R.color.terra_color_neutral_10,
            )
            TerraButtonType.SECONDARY -> ButtonAppearance(
                R.drawable.terra_bg_button_secondary,
                R.color.terra_color_neutral_10,
            )
            TerraButtonType.OUTLINED_PRIMARY -> ButtonAppearance(
                R.drawable.terra_bg_button_outlined_primary,
                R.color.terra_color_teal_700,
            )
            TerraButtonType.OUTLINED_SECONDARY -> ButtonAppearance(
                R.drawable.terra_bg_button_outlined_secondary,
                R.color.terra_color_orange_500,
            )
            TerraButtonType.DANGER -> ButtonAppearance(
                R.drawable.terra_bg_button_danger,
                R.color.terra_color_neutral_10,
            )
            TerraButtonType.TEXT -> ButtonAppearance(
                android.R.color.transparent,
                R.color.terra_color_teal_700,
            )
        }
    }

    private fun resolveDisabledAppearance(): ButtonAppearance {
        return when (terraButtonType) {
            TerraButtonType.OUTLINED_PRIMARY,
            TerraButtonType.OUTLINED_SECONDARY -> ButtonAppearance(
                R.drawable.terra_bg_button_disabled,
                R.color.terra_color_neutral_50,
            )
            TerraButtonType.TEXT -> ButtonAppearance(
                android.R.color.transparent,
                R.color.terra_color_neutral_50,
            )
            else -> ButtonAppearance(
                R.drawable.terra_bg_button_disabled,
                R.color.terra_color_neutral_10,
            )
        }
    }

    private data class ButtonAppearance(
        @DrawableRes val backgroundRes: Int,
        @ColorRes val textColorRes: Int,
    )
}
