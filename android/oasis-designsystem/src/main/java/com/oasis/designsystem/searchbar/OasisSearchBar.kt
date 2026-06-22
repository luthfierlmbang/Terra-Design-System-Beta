package com.oasis.designsystem.searchbar

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.oasis.designsystem.R

class OasisSearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private val editText: EditText

    init {
        orientation = HORIZONTAL
        val padding = context.resources.getDimensionPixelSize(com.oasis.designsystem.tokens.R.dimen.oasis_spacing_12)
        setPadding(padding, padding, padding, padding)
        setBackgroundColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_surface_subtle))
        
        editText = EditText(context).apply {
            inputType = InputType.TYPE_CLASS_TEXT
            background = null
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            isSingleLine = true
            hint = "Search..."
        }
        addView(editText)
        
        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisSearchBar)
        val hint = typedArray.getString(R.styleable.OasisSearchBar_oasisSearchHint)
        val query = typedArray.getString(R.styleable.OasisSearchBar_oasisSearchQuery)
        
        if (hint != null) {
            editText.hint = hint
        } else {
            editText.hint = "Search..."
        }
        if (query != null) {
            editText.setText(query)
        }
        
        typedArray.recycle()
    }

    fun setQuery(query: String) {
        editText.setText(query)
    }

    fun getQuery(): String = editText.text?.toString() ?: ""

    fun setOnQueryChangeListener(listener: (String) -> Unit) {
        editText.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                listener(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        })
    }
}