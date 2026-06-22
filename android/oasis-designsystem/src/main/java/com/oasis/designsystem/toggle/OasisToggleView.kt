package com.oasis.designsystem.toggle

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SwitchCompat
import com.oasis.designsystem.R

class OasisToggleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : SwitchCompat(context, attrs, defStyleAttr) {

    init {
        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisToggleView)
        val labelText = typedArray.getString(R.styleable.OasisToggleView_oasisToggleLabel)
        val checked = typedArray.getBoolean(R.styleable.OasisToggleView_oasisToggleChecked, false)
        
        if (labelText != null) {
            text = labelText
        }
        isChecked = checked
        
        typedArray.recycle()
    }
}
