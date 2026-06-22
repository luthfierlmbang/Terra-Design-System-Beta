package com.oasis.designsystem.alert

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.oasis.designsystem.R

class OasisAlertView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    enum class Variant { INFO, SUCCESS, WARNING, DANGER }

    private val titleView: TextView
    private val messageView: TextView
    private var currentVariant: Variant = Variant.INFO

    init {
        orientation = VERTICAL
        val pad = context.resources.getDimensionPixelSize(com.oasis.designsystem.tokens.R.dimen.oasis_spacing_16)
        setPadding(pad, pad, pad, pad)
        
        titleView = TextView(context).apply {
            setTypeface(typeface, android.graphics.Typeface.BOLD)
        }
        messageView = TextView(context)
        addView(titleView)
        addView(messageView)
        
        parseAttributes(attrs)
        applyVariant()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisAlertView)
        val variantOrdinal = typedArray.getInt(R.styleable.OasisAlertView_oasisAlertVariant, 0)
        currentVariant = Variant.entries[variantOrdinal]
        val title = typedArray.getString(R.styleable.OasisAlertView_oasisAlertTitle)
        val message = typedArray.getString(R.styleable.OasisAlertView_oasisAlertMessage)
        if (title != null) titleView.text = title
        if (message != null) messageView.text = message
        typedArray.recycle()
    }

    private fun applyVariant() {
        when (currentVariant) {
            Variant.INFO -> {
                setBackgroundColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_info_surface))
                titleView.setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_info_text))
                messageView.setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_text_primary))
            }
            Variant.SUCCESS -> {
                setBackgroundColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_success_surface))
                titleView.setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_success_text))
                messageView.setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_text_primary))
            }
            Variant.WARNING -> {
                setBackgroundColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_warning_surface))
                titleView.setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_warning_text))
                messageView.setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_text_primary))
            }
            Variant.DANGER -> {
                setBackgroundColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_danger_surface))
                titleView.setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_danger_text))
                messageView.setTextColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_text_primary))
            }
        }
    }

    fun setVariant(variant: Variant) {
        currentVariant = variant
        applyVariant()
    }

    fun setTitle(title: String) { titleView.text = title }
    fun setMessage(message: String) { messageView.text = message }
}