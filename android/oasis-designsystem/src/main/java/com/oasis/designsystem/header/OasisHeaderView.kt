package com.oasis.designsystem.header

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.oasis.designsystem.R

class OasisHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val titleView: TextView
    private val subtitleView: TextView
    private val iconView: AppCompatImageView

    init {
        orientation = HORIZONTAL
        gravity = android.view.Gravity.CENTER_VERTICAL
        val textContainer = LinearLayout(context).apply { orientation = VERTICAL }
        iconView = AppCompatImageView(context)
        titleView = TextView(context).apply { setTypeface(typeface, android.graphics.Typeface.BOLD) }
        subtitleView = TextView(context)
        textContainer.addView(titleView)
        textContainer.addView(subtitleView)
        addView(iconView)
        addView(textContainer)
        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisHeaderView)
        val title = typedArray.getString(R.styleable.OasisHeaderView_oasisHeaderTitle)
        val subtitle = typedArray.getString(R.styleable.OasisHeaderView_oasisHeaderSubtitle)
        val iconRes = typedArray.getResourceId(R.styleable.OasisHeaderView_oasisHeaderIcon, 0)
        if (title != null) titleView.text = title
        if (subtitle != null) subtitleView.text = subtitle
        if (iconRes != 0) iconView.setImageResource(iconRes)
        typedArray.recycle()
    }
}