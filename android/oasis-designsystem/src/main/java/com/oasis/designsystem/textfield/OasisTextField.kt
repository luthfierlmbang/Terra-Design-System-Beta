package com.oasis.designsystem.textfield

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText
import com.oasis.designsystem.R

class OasisTextField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : TextInputLayout(context, attrs, defStyleAttr) {

    private val editText: TextInputEditText

    init {
        editText = TextInputEditText(context)
        addView(editText)
        boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisTextField)
        val hint = typedArray.getString(R.styleable.OasisTextField_oasisTextFieldHint)
        val helper = typedArray.getString(R.styleable.OasisTextField_oasisTextFieldHelper)
        val error = typedArray.getString(R.styleable.OasisTextField_oasisTextFieldError)
        val input = typedArray.getString(R.styleable.OasisTextField_oasisTextFieldText)
        val password = typedArray.getBoolean(R.styleable.OasisTextField_oasisTextFieldPassword, false)
        val multiline = typedArray.getBoolean(R.styleable.OasisTextField_oasisTextFieldMultiline, false)
        
        if (hint != null) {
            this.hint = hint
        }
        if (helper != null) {
            this.helperText = helper
        }
        if (error != null) {
            this.error = error
        }
        if (input != null) {
            editText.setText(input)
        }
        
        editText.inputType = when {
            password -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            multiline -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
            else -> InputType.TYPE_CLASS_TEXT
        }
        
        typedArray.recycle()
    }

    fun setText(text: String) {
        editText.setText(text)
    }

    fun getText(): String = editText.text?.toString() ?: ""
}