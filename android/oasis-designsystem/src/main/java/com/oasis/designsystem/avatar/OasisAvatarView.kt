package com.oasis.designsystem.avatar

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.oasis.designsystem.R

class OasisAvatarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val imageView: AppCompatImageView
    private val textView: TextView

    var type: OasisAvatarType = OasisAvatarType.INITIAL
        set(value) {
            field = value
            applyType()
        }

    var size: OasisAvatarSize = OasisAvatarSize.SIZE_64
        set(value) {
            field = value
            applySize()
        }

    var initialText: String = ""
        set(value) {
            field = value
            textView.text = value.take(2).uppercase()
            applyType()
        }

    var pictureSrc: Int = 0
        set(value) {
            field = value
            applyType()
        }

    init {
        val px = context.resources.getDimensionPixelSize(OasisAvatarSize.SIZE_64.dimenRes)
        layoutParams = layoutParams ?: LayoutParams(px, px)

        imageView = AppCompatImageView(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            scaleType = ImageView.ScaleType.CENTER_INSIDE
        }
        textView = TextView(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            gravity = android.view.Gravity.CENTER
            setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_text_inverse))
            TextViewCompat.setTextAppearance(this, R.style.TextAppearance_Oasis_Title)
        }

        addView(imageView)
        addView(textView)

        parseAttributes(attrs)
        applySize()
        applyType()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisAvatarView)
        type = OasisAvatarType.from(typedArray.getInt(R.styleable.OasisAvatarView_oasisAvatarType, 0))
        size = OasisAvatarSize.from(typedArray.getInt(R.styleable.OasisAvatarView_oasisAvatarSize, 0))
        typedArray.recycle()
    }

    private fun applySize() {
        val px = context.resources.getDimensionPixelSize(size.dimenRes)
        layoutParams = layoutParams?.apply {
            width = px
            height = px
        }
        textView.textSize = px / 3f / resources.displayMetrics.scaledDensity
        requestLayout()
    }

    private fun applyType() {
        when (type) {
            OasisAvatarType.INITIAL -> {
                imageView.visibility = GONE
                textView.visibility = VISIBLE
                setBackgroundColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_brand_secondary))
            }
            OasisAvatarType.ICON -> {
                imageView.visibility = VISIBLE
                textView.visibility = GONE
                imageView.setImageResource(OasisAvatarType.ICON.defaultIconRes)
                setBackgroundColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_neutral_200))
            }
            OasisAvatarType.PICTURE -> {
                imageView.visibility = VISIBLE
                textView.visibility = GONE
                if (pictureSrc != 0) {
                    imageView.setImageResource(pictureSrc)
                } else {
                    imageView.setImageResource(OasisAvatarType.PICTURE.defaultIconRes)
                }
                setBackgroundColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_neutral_100))
            }
        }
    }
}