package com.oasis.designsystem.chip

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.chip.Chip
import com.oasis.designsystem.R

class OasisChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : Chip(context, attrs, defStyleAttr) {

    init {
        isClickable = true
        isCheckable = true
        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisChip)
        val label = typedArray.getString(R.styleable.OasisChip_oasisChipLabel)
        val selected = typedArray.getBoolean(R.styleable.OasisChip_oasisChipSelected, false)
        val closable = typedArray.getBoolean(R.styleable.OasisChip_oasisChipClosable, false)
        
        if (label != null) text = label
        isChecked = selected
        isCloseIconVisible = closable
        
        typedArray.recycle()
    }
}