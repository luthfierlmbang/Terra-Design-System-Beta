package com.oasis.designsystem.illustration

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat

class OasisIllustrationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatImageView(context, attrs, defStyleAttr) {

    var variant: OasisIllustrationVariant = OasisIllustrationVariant.WELCOME
        set(value) {
            field = value
            setImageDrawable(ContextCompat.getDrawable(context, value.resId))
        }

    init {
        parseAttributes(attrs)
        setImageDrawable(ContextCompat.getDrawable(context, variant.resId))
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, com.oasis.designsystem.R.styleable.OasisIllustrationView)
        variant = OasisIllustrationVariant.from(
            typedArray.getInt(com.oasis.designsystem.R.styleable.OasisIllustrationView_oasisIllustrationVariant, 0)
        )
        typedArray.recycle()
    }
}