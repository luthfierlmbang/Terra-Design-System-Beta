package com.oasis.designsystem.textarea

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText
import com.oasis.designsystem.R

class OasisTextArea @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : TextInputLayout(context, attrs, defStyleAttr) {

    private val editText: TextInputEditText

    init {
        editText = TextInputEditText(context)
        addView(editText)
        boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
        editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
        editText.minLines = 3
        editText.maxLines = 6
        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisTextArea)
        val hint = typedArray.getString(R.styleable.OasisTextArea_oasisTextAreaHint)
        val value = typedArray.getString(R.styleable.OasisTextArea_oasisTextAreaText)
        val rows = typedArray.getInt(R.styleable.OasisTextArea_oasisTextAreaRows, 3)
        val maxChars = typedArray.getInt(R.styleable.OasisTextArea_oasisTextAreaMaxChars, 500)
        val helper = typedArray.getString(R.styleable.OasisTextArea_oasisTextAreaHelper)
        
        if (hint != null) this.hint = hint
        if (value != null) editText.setText(value)
        editText.minLines = rows
        editText.maxLines = rows + 3
        if (helper != null) this.helperText = helper
        editText.filters = arrayOf(android.text.InputFilter.LengthFilter(maxChars))
        
        typedArray.recycle()
    }

    fun setText(text: String) {
        editText.setText(text)
    }

    fun getText(): String = editText.text?.toString() ?: ""
}