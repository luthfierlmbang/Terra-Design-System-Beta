package com.oasis.designsystem.table

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.oasis.designsystem.R

class OasisTable @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL
        setBackgroundColor(ContextCompat.getColor(context, com.oasis.designsystem.tokens.R.color.oasis_border_default))
    }

    fun setData(headers: List<String>, rows: List<List<String>>) {
        removeAllViews()
        addRow(headers, isHeader = true)
        rows.forEach { addRow(it, isHeader = false) }
    }

    private fun addRow(cells: List<String>, isHeader: Boolean) {
        val row = LinearLayout(context).apply {
            orientation = HORIZONTAL
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        }
        cells.forEach { cell ->
            val tv = TextView(context).apply {
                text = cell
                setPadding(16, 12, 16, 12)
                layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f)
                gravity = Gravity.CENTER_VERTICAL
                setBackgroundColor(ContextCompat.getColor(
                    context,
                    if (isHeader) com.oasis.designsystem.tokens.R.color.oasis_surface_subtle
                    else com.oasis.designsystem.tokens.R.color.oasis_surface_default
                ))
                if (isHeader) {
                    setTypeface(typeface, android.graphics.Typeface.BOLD)
                }
            }
            row.addView(tv)
        }
        addView(row)
    }
}