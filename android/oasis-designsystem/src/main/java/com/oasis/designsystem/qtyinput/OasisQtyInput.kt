package com.oasis.designsystem.qtyinput

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.oasis.designsystem.R

class OasisQtyInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val valueText: TextView
    private val minusButton: AppCompatButton
    private val plusButton: AppCompatButton
    private var minVal: Int = 0
    private var maxVal: Int = Int.MAX_VALUE
    private var currentValue: Int = 1
    private var listener: ((Int) -> Unit)? = null

    init {
        orientation = HORIZONTAL
        gravity = android.view.Gravity.CENTER_VERTICAL
        
        minusButton = AppCompatButton(context).apply {
            text = "−"
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }
        valueText = TextView(context).apply {
            text = "1"
            gravity = android.view.Gravity.CENTER
            setPadding(32, 0, 32, 0)
        }
        plusButton = AppCompatButton(context).apply {
            text = "+"
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }
        
        addView(minusButton)
        addView(valueText)
        addView(plusButton)
        
        minusButton.setOnClickListener { decrement() }
        plusButton.setOnClickListener { increment() }
        
        parseAttributes(attrs)
        updateValue()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisQtyInput)
        currentValue = typedArray.getInt(R.styleable.OasisQtyInput_oasisQtyValue, 1)
        minVal = typedArray.getInt(R.styleable.OasisQtyInput_oasisQtyMin, 0)
        maxVal = typedArray.getInt(R.styleable.OasisQtyInput_oasisQtyMax, Int.MAX_VALUE)
        typedArray.recycle()
    }

    fun setValue(value: Int) {
        currentValue = value.coerceIn(minVal, maxVal)
        updateValue()
    }

    fun getValue(): Int = currentValue

    fun setOnValueChangeListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    private fun increment() {
        if (currentValue < maxVal) {
            currentValue++
            updateValue()
        }
    }

    private fun decrement() {
        if (currentValue > minVal) {
            currentValue--
            updateValue()
        }
    }

    private fun updateValue() {
        valueText.text = currentValue.toString()
        minusButton.isEnabled = currentValue > minVal
        plusButton.isEnabled = currentValue < maxVal
        listener?.invoke(currentValue)
    }
}