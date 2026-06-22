package com.oasis.designsystem.modal

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.oasis.designsystem.R

class OasisModalView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val titleView: TextView
    private val messageView: TextView
    private val primaryButton: AppCompatButton
    private val secondaryButton: AppCompatButton

    init {
        orientation = VERTICAL
        val pad = context.resources.getDimensionPixelSize(com.oasis.designsystem.tokens.R.dimen.oasis_spacing_16)
        setPadding(pad, pad, pad, pad)
        setBackgroundColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_surface_default))

        titleView = TextView(context).apply {
            setTypeface(typeface, android.graphics.Typeface.BOLD)
            textSize = 20f
        }
        messageView = TextView(context)
        primaryButton = AppCompatButton(context).apply { text = "Confirm" }
        secondaryButton = AppCompatButton(context).apply { text = "Cancel" }

        addView(titleView)
        addView(messageView)
        addView(primaryButton)
        addView(secondaryButton)

        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisModalView)
        titleView.text = typedArray.getString(R.styleable.OasisModalView_oasisModalTitle) ?: "Modal Title"
        messageView.text = typedArray.getString(R.styleable.OasisModalView_oasisModalMessage) ?: "Modal message"
        primaryButton.text = typedArray.getString(R.styleable.OasisModalView_oasisModalPrimaryText) ?: "Confirm"
        secondaryButton.text = typedArray.getString(R.styleable.OasisModalView_oasisModalSecondaryText) ?: "Cancel"
        typedArray.recycle()
    }
}
