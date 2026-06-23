package com.oasis.designsystem.tag

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import com.oasis.designsystem.R
import com.oasis.designsystem.tokens.R as TokensR

class OasisTagView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatTextView(context, attrs, defStyleAttr) {

    var variant: OasisTagVariant = OasisTagVariant.INFO
        set(value) {
            field = value
            applyVariant()
        }

    init {
        val res = context.resources
        val hPad = res.getDimensionPixelSize(TokensR.dimen.oasis_tag_padding_horizontal)
        val vPad = res.getDimensionPixelSize(TokensR.dimen.oasis_tag_padding_vertical)
        setPadding(hPad, vPad, hPad, vPad)
        gravity = Gravity.CENTER
        TextViewCompat.setTextAppearance(this, R.style.TextAppearance_Oasis_Tag)

        parseAttributes(attrs)
        applyVariant()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisTagView)
        variant = OasisTagVariant.from(typedArray.getInt(R.styleable.OasisTagView_oasisTagVariant, 0))
        val tagText = typedArray.getString(R.styleable.OasisTagView_oasisTagText)
        if (tagText != null) text = tagText
        typedArray.recycle()
    }

    private fun applyVariant() {
        val bgRes = when (variant) {
            OasisTagVariant.INFO -> R.drawable.oasis_bg_tag_info
            OasisTagVariant.SUCCESS -> R.drawable.oasis_bg_tag_success
            OasisTagVariant.WARNING -> R.drawable.oasis_bg_tag_warning
            OasisTagVariant.DANGER -> R.drawable.oasis_bg_tag_danger
        }
        setBackgroundResource(bgRes)
    }
}
