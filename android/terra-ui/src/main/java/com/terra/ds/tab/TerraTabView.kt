package com.terra.ds.tab

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.terra.ds.internal.applyTerraTextAppearance
import com.terra.ds.R
import com.terra.tokens.R as TokensR

class TerraTabView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    var terraTabItems: String = "Tab 1,Tab 2"
        set(value) {
            field = value
            render()
        }

    var terraTabSelectedIndex: Int = 0
        set(value) {
            field = value.coerceAtLeast(0)
            render()
        }

    var terraTabScrollable: Boolean = false
        set(value) {
            field = value
            render()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        minimumHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_tab_height)
        parseAttributes(attrs)
        render()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraTabView)
        terraTabItems = typedArray.getString(R.styleable.TerraTabView_terraTabItems) ?: terraTabItems
        terraTabSelectedIndex = typedArray.getInt(R.styleable.TerraTabView_terraTabSelectedIndex, 0)
        terraTabScrollable = typedArray.getBoolean(R.styleable.TerraTabView_terraTabScrollable, false)
        typedArray.recycle()
    }

    private fun render() {
        removeAllViews()
        val items = terraTabItems.split(',').map { it.trim() }.filter { it.isNotEmpty() }.ifEmpty { listOf("Tab 1", "Tab 2") }
        val selectedIndex = terraTabSelectedIndex.coerceIn(0, items.lastIndex)

        items.forEachIndexed { index, label ->
            val selected = index == selectedIndex
            addView(createTabItem(label, selected), LayoutParams(if (terraTabScrollable) LayoutParams.WRAP_CONTENT else 0, LayoutParams.WRAP_CONTENT, if (terraTabScrollable) 0f else 1f))
        }
    }

    private fun createTabItem(label: String, selected: Boolean): LinearLayout {
        val horizontalPadding = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16)
        val topPadding = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8)
        val indicatorGap = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_6)
        val indicatorHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_2)
        val selectedTextColor = TokensR.color.terra_color_secondary
        val defaultTextColor = TokensR.color.terra_color_text_secondary

        return LinearLayout(context).apply {
            orientation = VERTICAL
            gravity = Gravity.CENTER
            setPaddingRelative(horizontalPadding, topPadding, horizontalPadding, 0)
            minimumHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_tab_height)

            addView(AppCompatTextView(context).apply {
                applyTerraTextAppearance(R.style.TextAppearance_Terra_CTA_Medium)
                text = label
                gravity = Gravity.CENTER
                setTextColor(
                    ContextCompat.getColor(
                        context,
                        if (selected) selectedTextColor else defaultTextColor,
                    ),
                )
            }, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))

            addView(View(context).apply {
                visibility = if (selected) View.VISIBLE else View.INVISIBLE
                background = underlineBackground(context)
            }, LayoutParams(LayoutParams.MATCH_PARENT, indicatorHeight).apply {
                topMargin = indicatorGap
            })
        }
    }
}

private fun underlineBackground(context: Context): GradientDrawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        setColor(ContextCompat.getColor(context, TokensR.color.terra_color_secondary))
        cornerRadius = context.resources.getDimension(TokensR.dimen.terra_radius_medium)
    }
}
