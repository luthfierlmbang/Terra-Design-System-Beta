package com.oasis.designsystem.progressbar

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import com.oasis.designsystem.R

class OasisProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ProgressBar(context, attrs, defStyleAttr) {

    init {
        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisProgressBar)
        val progress = typedArray.getInt(R.styleable.OasisProgressBar_oasisProgressValue, 0)
        val max = typedArray.getInt(R.styleable.OasisProgressBar_oasisProgressMax, 100)
        val indeterminate = typedArray.getBoolean(R.styleable.OasisProgressBar_oasisProgressIndeterminate, false)
        
        this.max = max
        this.progress = progress
        isIndeterminate = indeterminate
        
        typedArray.recycle()
    }

    fun setProgressValue(value: Int) {
        progress = value.coerceIn(0, max)
    }
}