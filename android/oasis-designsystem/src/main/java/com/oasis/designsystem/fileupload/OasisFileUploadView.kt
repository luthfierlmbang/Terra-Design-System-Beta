package com.oasis.designsystem.fileupload

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.oasis.designsystem.R

class OasisFileUploadView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val labelView: TextView
    private val helperView: TextView
    private val buttonView: AppCompatButton

    init {
        orientation = VERTICAL
        val pad = context.resources.getDimensionPixelSize(com.oasis.designsystem.tokens.R.dimen.oasis_spacing_16)
        setPadding(pad, pad, pad, pad)
        setBackgroundColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_surface_subtle))

        labelView = TextView(context).apply {
            setTypeface(typeface, android.graphics.Typeface.BOLD)
        }
        helperView = TextView(context)
        buttonView = AppCompatButton(context).apply {
            text = "Choose file"
        }

        addView(labelView)
        addView(helperView)
        addView(buttonView)

        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisFileUploadView)
        labelView.text = typedArray.getString(R.styleable.OasisFileUploadView_oasisFileUploadLabel) ?: "Upload file"
        helperView.text = typedArray.getString(R.styleable.OasisFileUploadView_oasisFileUploadHelper) ?: "PDF, JPG, PNG up to 10MB"
        buttonView.text = typedArray.getString(R.styleable.OasisFileUploadView_oasisFileUploadButtonText) ?: "Choose file"
        typedArray.recycle()
    }
}
