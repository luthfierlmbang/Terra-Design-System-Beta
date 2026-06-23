package com.terra.ds.datepicker

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.terra.ds.internal.applyTerraTextAppearance
import com.terra.ds.R
import com.terra.tokens.R as TokensR
import com.terra.ds.button.TerraButtonType
import com.terra.ds.button.TerraButtonView
import com.terra.ds.timer.TerraTimerView
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraDatePickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val titleView: TextView
    private val gridContainer: LinearLayout

    var terraDatePickerMode: TerraDatePickerMode = TerraDatePickerMode.SINGLE
        set(value) {
            field = value
            renderCalendar()
        }

    var terraDatePickerMonth: String = "Desember"
        set(value) {
            field = value
            titleView.text = "$value $terraDatePickerYear"
        }

    var terraDatePickerNextMonth: String = "Januari"
        set(value) {
            field = value
            renderCalendar()
        }

    var terraDatePickerYear: String = "2023"
        set(value) {
            field = value
            titleView.text = "$terraDatePickerMonth $value"
        }

    init {
        orientation = HORIZONTAL
        titleView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Header_L)
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_primary))
        }
        gridContainer = LinearLayout(context).apply {
            orientation = HORIZONTAL
        }
        val wrapper = LinearLayout(context).apply {
            orientation = VERTICAL
            addView(titleView)
            addView(gridContainer)
        }
        addView(wrapper)
        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraDatePickerView)
        terraDatePickerMode = when (typedArray.getInt(R.styleable.TerraDatePickerView_terraDatePickerMode, 0)) {
            1 -> TerraDatePickerMode.DOUBLE
            else -> TerraDatePickerMode.SINGLE
        }
        terraDatePickerMonth = typedArray.getString(R.styleable.TerraDatePickerView_terraDatePickerMonth) ?: terraDatePickerMonth
        terraDatePickerNextMonth = typedArray.getString(R.styleable.TerraDatePickerView_terraDatePickerNextMonth) ?: terraDatePickerNextMonth
        terraDatePickerYear = typedArray.getString(R.styleable.TerraDatePickerView_terraDatePickerYear) ?: terraDatePickerYear
        typedArray.recycle()
    }

    private fun bind() {
        titleView.text = "$terraDatePickerMonth $terraDatePickerYear"
        renderCalendar()
    }

    private fun renderCalendar() {
        gridContainer.removeAllViews()
        gridContainer.addView(calendarMonth(terraDatePickerMonth))
        if (terraDatePickerMode == TerraDatePickerMode.DOUBLE) {
            gridContainer.addView(calendarMonth(terraDatePickerNextMonth))
        }
    }

    private fun calendarMonth(monthName: String): LinearLayout {
        val month = LinearLayout(context).apply {
            orientation = VERTICAL
            setPadding(resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_14), resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_24), resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_14), resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16))
        }
        month.addView(AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Header_L)
            text = "$monthName $terraDatePickerYear"
        })
        repeat(6) { week ->
            val row = LinearLayout(context).apply { orientation = HORIZONTAL }
            repeat(7) { day ->
                row.addView(AppCompatTextView(context).apply {
                    applyTerraTextAppearance(R.style.TextAppearance_Terra_Body_Small)
                    gravity = Gravity.CENTER
                    text = ((week * 7) + day + 1).toString()
                    width = resources.getDimensionPixelSize(TokensR.dimen.terra_date_cell_size)
                    height = resources.getDimensionPixelSize(TokensR.dimen.terra_date_cell_size)
                })
            }
            month.addView(row)
        }
        return month
    }
}
enum class TerraDatePickerMode {
    SINGLE,
    DOUBLE,
}
