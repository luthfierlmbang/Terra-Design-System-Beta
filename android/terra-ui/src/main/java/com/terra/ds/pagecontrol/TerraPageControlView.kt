package com.terra.ds.pagecontrol

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
import com.terra.ds.R
import com.terra.tokens.R as TokensR
import com.terra.ds.button.TerraButtonType
import com.terra.ds.button.TerraButtonView
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraPageControlView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    var terraPageCount: Int = 4
        set(value) {
            field = value.coerceAtLeast(1)
            render()
        }

    var terraSelectedPage: Int = 0
        set(value) {
            field = value.coerceIn(0, terraPageCount - 1)
            render()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.START
        parseAttributes(attrs)
        render()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraPageControlView)
        terraPageCount = typedArray.getInt(R.styleable.TerraPageControlView_terraPageCount, terraPageCount)
        terraSelectedPage = typedArray.getInt(R.styleable.TerraPageControlView_terraSelectedPage, terraSelectedPage)
        typedArray.recycle()
    }

    private fun render() {
        removeAllViews()
        repeat(terraPageCount) { index ->
            val selected = index == terraSelectedPage
            val dot = View(context)
            val width = if (selected) resources.getDimensionPixelSize(TokensR.dimen.terra_page_control_selected_width) else resources.getDimensionPixelSize(TokensR.dimen.terra_page_control_dot_size)
            val height = resources.getDimensionPixelSize(TokensR.dimen.terra_page_control_dot_size)
            dot.setBackgroundColor(ContextCompat.getColor(context, TokensR.color.terra_color_secondary))
            addView(dot, LayoutParams(width, height).apply {
                marginEnd = if (index == terraPageCount - 1) 0 else resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_4)
            })
        }
    }
}
