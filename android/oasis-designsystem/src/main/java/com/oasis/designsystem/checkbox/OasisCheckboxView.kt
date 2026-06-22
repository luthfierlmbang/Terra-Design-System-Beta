package com.oasis.designsystem.checkbox

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.oasis.designsystem.R

class OasisCheckboxView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatCheckBox(context, attrs, defStyleAttr) {

    init {
        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisCheckboxView)
        val labelText = typedArray.getString(R.styleable.OasisCheckboxView_oasisCheckboxLabel)
        val checked = typedArray.getBoolean(R.styleable.OasisCheckboxView_oasisCheckboxChecked, false)
        
        if (labelText != null) {
            text = labelText
        }
        isChecked = checked
        
        typedArray.recycle()
    }
}
