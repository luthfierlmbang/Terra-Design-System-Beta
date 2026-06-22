package com.oasis.designsystem.toast

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.oasis.designsystem.R

class OasisToastView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val textView: TextView

    init {
        orientation = HORIZONTAL
        gravity = android.view.Gravity.CENTER_VERTICAL
        val pad = context.resources.getDimensionPixelSize(com.oasis.designsystem.tokens.R.dimen.oasis_spacing_12)
        setPadding(pad, pad, pad, pad)
        setBackgroundColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_neutral_900))
        
        textView = TextView(context).apply {
            setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_text_inverse))
        }
        addView(textView)
        
        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisToastView)
        val message = typedArray.getString(R.styleable.OasisToastView_oasisToastMessage)
        val duration = typedArray.getInt(R.styleable.OasisToastView_oasisToastDuration, 3000)
        
        if (message != null) textView.text = message
        typedArray.recycle()
    }

    fun setMessage(message: String) {
        textView.text = message
    }
}