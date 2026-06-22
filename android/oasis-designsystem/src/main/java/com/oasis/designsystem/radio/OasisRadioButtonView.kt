package com.oasis.designsystem.radio

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.oasis.designsystem.R

class OasisRadioButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatRadioButton(context, attrs, defStyleAttr) {

    init {
        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisRadioButtonView)
        val labelText = typedArray.getString(R.styleable.OasisRadioButtonView_oasisRadioLabel)
        val checked = typedArray.getBoolean(R.styleable.OasisRadioButtonView_oasisRadioChecked, false)
        
        if (labelText != null) {
            text = labelText
        }
        isChecked = checked
        
        typedArray.recycle()
    }
}
