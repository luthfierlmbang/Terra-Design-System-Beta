package com.oasis.designsystem.indicator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.oasis.designsystem.R

class OasisIndicatorGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var count: Int = 3
    private var selectedIndex: Int = 0

    init {
        orientation = HORIZONTAL
        parseAttributes(attrs)
        renderIndicators()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisIndicatorGroup)
        count = typedArray.getInt(R.styleable.OasisIndicatorGroup_oasisIndicatorCount, 3)
        selectedIndex = typedArray.getInt(R.styleable.OasisIndicatorGroup_oasisIndicatorSelectedIndex, 0)
        typedArray.recycle()
    }

    private fun renderIndicators() {
        removeAllViews()
        repeat(count) { index ->
            val dot = View(context).apply {
                val size = 16
                layoutParams = LayoutParams(size, size).apply {
                    marginEnd = 8
                }
                setBackgroundColor(ContextCompat.getColor(
                    context,
                    if (index == selectedIndex) com.oasis.designsystem.tokens.R.color.oasis_primary
                    else com.oasis.designsystem.tokens.R.color.oasis_border_default
                ))
            }
            addView(dot)
        }
    }

    fun setSelectedIndex(index: Int) {
        selectedIndex = index.coerceIn(0, count - 1)
        renderIndicators()
    }
}