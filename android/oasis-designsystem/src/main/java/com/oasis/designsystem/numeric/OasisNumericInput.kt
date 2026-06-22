package com.oasis.designsystem.numeric

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText
import com.oasis.designsystem.R

class OasisNumericInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : TextInputLayout(context, attrs, defStyleAttr) {

    private val editText: TextInputEditText

    init {
        editText = TextInputEditText(context)
        addView(editText)
        boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
        editText.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisNumericInput)
        val hint = typedArray.getString(R.styleable.OasisNumericInput_oasisNumericHint)
        val value = typedArray.getString(R.styleable.OasisNumericInput_oasisNumericValue)
        val minVal = typedArray.getFloat(R.styleable.OasisNumericInput_oasisNumericMin, Float.NEGATIVE_INFINITY)
        val maxVal = typedArray.getFloat(R.styleable.OasisNumericInput_oasisNumericMax, Float.POSITIVE_INFINITY)
        val decimal = typedArray.getBoolean(R.styleable.OasisNumericInput_oasisNumericDecimal, false)
        val error = typedArray.getString(R.styleable.OasisNumericInput_oasisNumericError)
        
        if (hint != null) this.hint = hint
        if (value != null) editText.setText(value)
        if (error != null) this.error = error
        
        editText.inputType = if (decimal) {
            InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        } else {
            InputType.TYPE_CLASS_NUMBER
        }
        
        // Store min/max for validation reference
        editText.tag = if (minVal != Float.NEGATIVE_INFINITY && maxVal != Float.POSITIVE_INFINITY) {
            "min=$minVal,max=$maxVal"
        } else null
        
        typedArray.recycle()
    }

    fun setValue(value: Number) {
        editText.setText(value.toString())
    }

    fun getValue(): Double = editText.text?.toString()?.toDoubleOrNull() ?: 0.0
}