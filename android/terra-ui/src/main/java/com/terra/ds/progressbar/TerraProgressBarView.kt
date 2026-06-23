package com.terra.ds.progressbar

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

import androidx.core.content.ContextCompat
import com.terra.ds.R
import com.terra.tokens.R as TokensR

class TerraProgressBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    var terraProgressBarType: TerraProgressBarType = TerraProgressBarType.BASE
        set(value) {
            field = value
            render()
        }

    init {
        parseAttributes(attrs)
        render()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraProgressBarView)
        terraProgressBarType = when (typedArray.getInt(R.styleable.TerraProgressBarView_terraProgressBarType, 0)) {
            1 -> TerraProgressBarType.BAR_1
            2 -> TerraProgressBarType.BAR_2
            else -> TerraProgressBarType.BASE
        }
        typedArray.recycle()
    }

    private fun render() {
        removeAllViews()
        val height = when (terraProgressBarType) {
            TerraProgressBarType.BASE -> resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_10)
            else -> resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_14)
        }
        val fillColor = when (terraProgressBarType) {
            TerraProgressBarType.BASE -> 0xFFEDEDED.toInt()
            TerraProgressBarType.BAR_1 -> ContextCompat.getColor(context, TokensR.color.terra_color_info)
            TerraProgressBarType.BAR_2 -> ContextCompat.getColor(context, TokensR.color.terra_color_secondary)
        }

        addView(View(context).apply {
            background = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                setColor(fillColor)
                cornerRadius = 50f
            }
        }, LayoutParams(LayoutParams.MATCH_PARENT, height))
    }
}
enum class TerraProgressBarType {
    BASE,
    BAR_1,
    BAR_2,
}
