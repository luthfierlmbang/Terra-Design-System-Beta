package com.oasis.designsystem.tag

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.oasis.designsystem.R

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
        setPadding(
            context.resources.getDimensionPixelSize(com.oasis.designsystem.tokens.R.dimen.oasis_spacing_12),
            context.resources.getDimensionPixelSize(com.oasis.designsystem.tokens.R.dimen.oasis_spacing_4),
            context.resources.getDimensionPixelSize(com.oasis.designsystem.tokens.R.dimen.oasis_spacing_12),
            context.resources.getDimensionPixelSize(com.oasis.designsystem.tokens.R.dimen.oasis_spacing_4)
        )
        TextViewCompat.setTextAppearance(this, R.style.TextAppearance_Oasis_Label)

        parseAttributes(attrs)
        applyVariant()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisTagView)
        variant = OasisTagVariant.from(typedArray.getInt(R.styleable.OasisTagView_oasisTagVariant, 0))
        val tagText = typedArray.getString(R.styleable.OasisTagView_oasisTagText)
        if (tagText != null) {
            text = tagText
        }
        typedArray.recycle()
    }

    private fun applyVariant() {
        when (variant) {
            OasisTagVariant.INFO -> {
                setBackgroundResource(R.drawable.oasis_bg_tag_info)
                setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_info_text))
            }
            OasisTagVariant.SUCCESS -> {
                setBackgroundResource(R.drawable.oasis_bg_tag_success)
                setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_success_text))
            }
            OasisTagVariant.WARNING -> {
                setBackgroundResource(R.drawable.oasis_bg_tag_warning)
                setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_warning_text))
            }
            OasisTagVariant.DANGER -> {
                setBackgroundResource(R.drawable.oasis_bg_tag_danger)
                setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_danger_text))
            }
        }
    }
}
