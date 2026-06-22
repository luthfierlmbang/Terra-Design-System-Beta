package com.oasis.designsystem.icon

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat

class OasisIconView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatImageView(context, attrs, defStyleAttr) {

    var variant: OasisIconVariant = OasisIconVariant.ARROW_LEFT
        set(value) {
            field = value
            applyIcon()
        }

    var size: OasisIconSize = OasisIconSize.SIZE_24
        set(value) {
            field = value
            applySize()
        }

    init {
        parseAttributes(attrs)
        applySize()
        applyIcon()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return

        val typedArray = context.obtainStyledAttributes(attrs, com.oasis.designsystem.R.styleable.OasisIconView)
        variant = OasisIconVariant.from(
            typedArray.getInt(com.oasis.designsystem.R.styleable.OasisIconView_oasisIconVariant, 0)
        )
        size = OasisIconSize.from(
            typedArray.getInt(com.oasis.designsystem.R.styleable.OasisIconView_oasisIconSize, 2)
        )
        typedArray.recycle()
    }

    private fun applySize() {
        val px = context.resources.getDimensionPixelSize(size.dimenRes)
        layoutParams = layoutParams?.apply {
            width = px
            height = px
        }
        requestLayout()
    }

    private fun applyIcon() {
        setImageDrawable(ContextCompat.getDrawable(context, variant.resId))
    }
}