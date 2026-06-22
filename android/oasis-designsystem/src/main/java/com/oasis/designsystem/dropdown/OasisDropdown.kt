package com.oasis.designsystem.dropdown

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import com.oasis.designsystem.R

class OasisDropdown @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : TextInputLayout(context, attrs, defStyleAttr) {

    private val autoComplete: MaterialAutoCompleteTextView
    private var options: List<String> = emptyList()

    init {
        autoComplete = MaterialAutoCompleteTextView(context)
        addView(autoComplete)
        boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
        autoComplete.inputType = 0
        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisDropdown)
        val hint = typedArray.getString(R.styleable.OasisDropdown_oasisDropdownHint)
        val selected = typedArray.getString(R.styleable.OasisDropdown_oasisDropdownSelected)
        val optionsArray = typedArray.getTextArray(R.styleable.OasisDropdown_oasisDropdownOptions)
        
        if (hint != null) this.hint = hint
        if (optionsArray != null) {
            setOptions(optionsArray.map { it.toString() })
        }
        if (selected != null) {
            autoComplete.setText(selected, false)
        }
        
        typedArray.recycle()
    }

    fun setOptions(items: List<String>) {
        options = items
        val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, options)
        autoComplete.setAdapter(adapter)
    }

    fun setSelected(value: String) {
        autoComplete.setText(value, false)
    }

    fun getSelected(): String = autoComplete.text?.toString() ?: ""

    fun setOnItemSelectedListener(listener: (String) -> Unit) {
        autoComplete.setOnItemClickListener { _, _, position, _ ->
            listener(options[position])
        }
    }
}