package com.terra.ds.form

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
import com.terra.ds.R
import com.terra.ds.button.TerraButtonType
import com.terra.ds.button.TerraButtonView
import com.terra.ds.feedback.TerraTimerView
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraImageUploadView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val labelView: TextView
    private val bodyView: LinearLayout
    private val hintView: TextView

    var terraImageUploadLabel: String = "Label"
        set(value) {
            field = value
            labelView.text = value
        }

    var terraImageUploadHint: String = "Helper Type Here"
        set(value) {
            field = value
            hintView.text = value
        }

    var terraImageUploadState: TerraImageUploadState = TerraImageUploadState.UPLOAD
        set(value) {
            field = value
            renderState()
        }

    init {
        orientation = VERTICAL
        labelView = labelText()
        bodyView = LinearLayout(context).apply {
            orientation = VERTICAL
        }
        hintView = helperText()
        addView(labelView)
        addView(bodyView)
        addView(hintView)
        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraImageUploadView)
        terraImageUploadLabel = typedArray.getString(R.styleable.TerraImageUploadView_terraImageUploadLabel) ?: terraImageUploadLabel
        terraImageUploadHint = typedArray.getString(R.styleable.TerraImageUploadView_terraImageUploadHint) ?: terraImageUploadHint
        terraImageUploadState = when (typedArray.getInt(R.styleable.TerraImageUploadView_terraImageUploadState, 0)) {
            1 -> TerraImageUploadState.PROCESSING
            2 -> TerraImageUploadState.ATTACHED_ONE_CTA
            3 -> TerraImageUploadState.ATTACHED_TWO_CTA
            else -> TerraImageUploadState.UPLOAD
        }
        typedArray.recycle()
    }

    private fun bind() {
        labelView.text = terraImageUploadLabel
        hintView.text = terraImageUploadHint
        renderState()
    }

    private fun renderState() {
        bodyView.removeAllViews()
        val container = LinearLayout(context).apply {
            orientation = VERTICAL
            gravity = Gravity.CENTER
            minimumHeight = resources.getDimensionPixelSize(R.dimen.terra_image_upload_height)
            setPaddingRelative(1, resources.getDimensionPixelSize(R.dimen.terra_spacing_16), 1, resources.getDimensionPixelSize(R.dimen.terra_spacing_16))
            setBackgroundColor(ContextCompat.getColor(context, R.color.terra_color_neutral_10))
        }
        when (terraImageUploadState) {
            TerraImageUploadState.UPLOAD -> {
                container.addView(TerraIconView(context).apply {
                    terraIconCategory = TerraIconCategory.ACTION
                    terraIconName = TerraIconName.PLUS
                    setIconTint(ContextCompat.getColor(context, R.color.terra_color_teal_700))
                })
                container.addView(TerraButtonView(context).apply {
                    text = "Ambil Gambar"
                    terraButtonType = TerraButtonType.OUTLINED_PRIMARY
                })
            }
            TerraImageUploadState.PROCESSING -> {
                container.addView(AppCompatTextView(context).apply {
                    text = "Dalam Proses..."
                    setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_80))
                })
            }
            TerraImageUploadState.ATTACHED_ONE_CTA,
            TerraImageUploadState.ATTACHED_TWO_CTA -> {
                container.addView(AppCompatTextView(context).apply {
                    text = "Image attached"
                    setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_80))
                })
                val actionRow = LinearLayout(context).apply {
                    orientation = HORIZONTAL
                    gravity = Gravity.CENTER
                }
                actionRow.addView(textButton(if (terraImageUploadState == TerraImageUploadState.ATTACHED_ONE_CTA) "Lihat" else "Ubah"))
                if (terraImageUploadState == TerraImageUploadState.ATTACHED_TWO_CTA) {
                    actionRow.addView(textButton("Lihat"))
                }
                container.addView(actionRow)
            }
        }
        bodyView.addView(container)
    }

    private fun textButton(label: String): TerraButtonView {
        return TerraButtonView(context).apply {
            text = label
            terraButtonType = TerraButtonType.TEXT
        }
    }

    private fun labelText(): TextView {
        return AppCompatTextView(context).apply {
            textSize = 12f
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_80))
        }
    }

    private fun helperText(): TextView {
        return AppCompatTextView(context).apply {
            textSize = 12f
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_80))
        }
    }
}

class TerraBottomSheetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val titleView: TextView
    private val contentContainer: LinearLayout
    private val timerView: TerraTimerView

    var terraBottomSheetTitle: String = "Title Text"
        set(value) {
            field = value
            titleView.text = value
        }

    var terraBottomSheetVariant: TerraBottomSheetVariant = TerraBottomSheetVariant.OPTIONS
        set(value) {
            field = value
            renderContent()
        }

    var terraBottomSheetSearchEnabled: Boolean = false
        set(value) {
            field = value
            renderContent()
        }

    var terraBottomSheetExpanded: Boolean = false
        set(value) {
            field = value
            renderContent()
        }

    init {
        orientation = VERTICAL
        setBackgroundColor(ContextCompat.getColor(context, R.color.terra_color_neutral_10))
        titleView = AppCompatTextView(context).apply {
            textSize = 18f
            setTypeface(typeface, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_90))
            setPadding(resources.getDimensionPixelSize(R.dimen.terra_spacing_16), resources.getDimensionPixelSize(R.dimen.terra_spacing_16), resources.getDimensionPixelSize(R.dimen.terra_spacing_16), resources.getDimensionPixelSize(R.dimen.terra_spacing_16))
        }
        contentContainer = LinearLayout(context).apply {
            orientation = VERTICAL
        }
        timerView = TerraTimerView(context)
        addView(titleView)
        addView(contentContainer)
        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraBottomSheetView)
        terraBottomSheetTitle = typedArray.getString(R.styleable.TerraBottomSheetView_terraBottomSheetTitle) ?: terraBottomSheetTitle
        terraBottomSheetVariant = when (typedArray.getInt(R.styleable.TerraBottomSheetView_terraBottomSheetVariant, 0)) {
            1 -> TerraBottomSheetVariant.COUNTDOWN
            else -> TerraBottomSheetVariant.OPTIONS
        }
        terraBottomSheetSearchEnabled = typedArray.getBoolean(R.styleable.TerraBottomSheetView_terraBottomSheetSearchEnabled, false)
        terraBottomSheetExpanded = typedArray.getBoolean(R.styleable.TerraBottomSheetView_terraBottomSheetExpanded, false)
        typedArray.recycle()
    }

    private fun bind() {
        titleView.text = terraBottomSheetTitle
        renderContent()
    }

    private fun renderContent() {
        contentContainer.removeAllViews()
        when (terraBottomSheetVariant) {
            TerraBottomSheetVariant.OPTIONS -> {
                if (terraBottomSheetSearchEnabled) {
                    contentContainer.addView(EditText(context).apply {
                        hint = "Input Search Bar"
                        setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.terra_ic_search, 0)
                    })
                }
                val scrollView = ScrollView(context)
                val optionList = LinearLayout(context).apply {
                    orientation = VERTICAL
                    val items = if (terraBottomSheetExpanded) 14 else if (terraBottomSheetSearchEnabled) 5 else 4
                    repeat(items) { index ->
                        addView(AppCompatTextView(context).apply {
                            text = "Option Menu ${index + 1}"
                            textSize = 14f
                            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_90))
                            setPadding(resources.getDimensionPixelSize(R.dimen.terra_spacing_24), resources.getDimensionPixelSize(R.dimen.terra_spacing_10), resources.getDimensionPixelSize(R.dimen.terra_spacing_24), resources.getDimensionPixelSize(R.dimen.terra_spacing_10))
                        })
                    }
                }
                scrollView.addView(optionList)
                contentContainer.addView(scrollView)
            }
            TerraBottomSheetVariant.COUNTDOWN -> {
                contentContainer.addView(AppCompatTextView(context).apply {
                    text = "00:00:12"
                    textSize = 48f
                    gravity = Gravity.CENTER_HORIZONTAL
                    setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_80))
                })
                contentContainer.addView(timerView)
                val buttonRow = LinearLayout(context).apply {
                    orientation = HORIZONTAL
                    gravity = Gravity.END
                }
                buttonRow.addView(TerraButtonView(context).apply {
                    text = "Button"
                    terraButtonType = TerraButtonType.SECONDARY
                })
                contentContainer.addView(buttonRow)
            }
        }
    }
}

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
            textSize = 24f
            setTypeface(typeface, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_90))
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
            setPadding(resources.getDimensionPixelSize(R.dimen.terra_spacing_14), resources.getDimensionPixelSize(R.dimen.terra_spacing_24), resources.getDimensionPixelSize(R.dimen.terra_spacing_14), resources.getDimensionPixelSize(R.dimen.terra_spacing_16))
        }
        month.addView(AppCompatTextView(context).apply {
            text = "$monthName $terraDatePickerYear"
            textSize = 24f
            setTypeface(typeface, Typeface.BOLD)
        })
        repeat(6) { week ->
            val row = LinearLayout(context).apply { orientation = HORIZONTAL }
            repeat(7) { day ->
                row.addView(AppCompatTextView(context).apply {
                    gravity = Gravity.CENTER
                    text = ((week * 7) + day + 1).toString()
                    width = resources.getDimensionPixelSize(R.dimen.terra_date_cell_size)
                    height = resources.getDimensionPixelSize(R.dimen.terra_date_cell_size)
                })
            }
            month.addView(row)
        }
        return month
    }
}


class TerraSearchBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val iconView: TerraIconView
    private val textView: TextView
    private val clearView: TerraIconView

    var terraSearchBarText: String = "Input Search Bar"
        set(value) {
            field = value
            textView.text = value
        }

    var terraSearchBarState: TerraSearchBarState = TerraSearchBarState.DEFAULT
        set(value) {
            field = value
            updateAppearance()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        minimumHeight = resources.getDimensionPixelSize(R.dimen.terra_search_bar_height)
        setPaddingRelative(
            resources.getDimensionPixelSize(R.dimen.terra_spacing_16),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_16),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_8),
        )

        iconView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.ACTION
            terraIconName = TerraIconName.SEARCH
        }
        textView = AppCompatTextView(context).apply {
            textSize = 14f
        }
        clearView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.ACTION
            terraIconName = TerraIconName.CLOSE
            visibility = View.GONE
        }

        addView(iconView)
        addView(textView, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f).apply {
            marginStart = resources.getDimensionPixelSize(R.dimen.terra_spacing_8)
            marginEnd = resources.getDimensionPixelSize(R.dimen.terra_spacing_8)
        })
        addView(clearView)

        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraSearchBarView)
        terraSearchBarText = typedArray.getString(R.styleable.TerraSearchBarView_terraSearchBarText) ?: terraSearchBarText
        terraSearchBarState = when (typedArray.getInt(R.styleable.TerraSearchBarView_terraSearchBarState, 0)) {
            1 -> TerraSearchBarState.FOCUS
            2 -> TerraSearchBarState.RESULT
            3 -> TerraSearchBarState.DISABLED
            else -> TerraSearchBarState.DEFAULT
        }
        typedArray.recycle()
    }

    private fun bind() {
        textView.text = terraSearchBarText
        updateAppearance()
    }

    private fun updateAppearance() {
        val disabled = terraSearchBarState == TerraSearchBarState.DISABLED
        isEnabled = !disabled
        clearView.visibility = if (terraSearchBarState == TerraSearchBarState.RESULT) View.VISIBLE else View.GONE
        val strokeColor = when (terraSearchBarState) {
            TerraSearchBarState.FOCUS -> R.color.terra_color_teal_700
            TerraSearchBarState.DISABLED -> R.color.terra_color_neutral_40
            else -> R.color.terra_color_neutral_60
        }
        background = roundedBackground(context,
            fillColor = if (disabled) R.color.terra_color_neutral_20 else R.color.terra_color_neutral_10,
            strokeColor = strokeColor,
            strokeWidth = 1,
            radius = R.dimen.terra_radius_pill,
        )
        textView.setTextColor(ContextCompat.getColor(context, if (disabled) R.color.terra_color_neutral_60 else R.color.terra_color_neutral_90))
        val iconColor = ContextCompat.getColor(context, if (disabled) R.color.terra_color_neutral_60 else R.color.terra_color_neutral_80)
        iconView.setIconTint(iconColor)
        clearView.setIconTint(iconColor)
    }
}

class TerraSelectView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val textView: TextView
    private val searchIconView: TerraIconView
    private val chevronView: TerraIconView

    var terraSelectPlaceholder: String = "Pilih opsi"
        set(value) {
            field = value
            textView.text = value
        }

    var terraSelectFilled: Boolean = false
        set(value) {
            field = value
            updateAppearance()
        }

    var terraSelectSearchEnabled: Boolean = false
        set(value) {
            field = value
            searchIconView.visibility = if (value) View.VISIBLE else View.GONE
        }

    var terraSelectSearchFocused: Boolean = false
        set(value) {
            field = value
            updateAppearance()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        minimumHeight = resources.getDimensionPixelSize(R.dimen.terra_text_field_height)
        setPaddingRelative(
            resources.getDimensionPixelSize(R.dimen.terra_spacing_16),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_16),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_8),
        )

        searchIconView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.ACTION
            terraIconName = TerraIconName.SEARCH
            visibility = View.GONE
        }
        textView = AppCompatTextView(context).apply {
            textSize = 14f
        }
        chevronView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.NAVIGATION
            terraIconName = TerraIconName.CHEVRON_DOWN
        }

        addView(searchIconView)
        addView(textView, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f).apply {
            marginStart = resources.getDimensionPixelSize(R.dimen.terra_spacing_8)
            marginEnd = resources.getDimensionPixelSize(R.dimen.terra_spacing_8)
        })
        addView(chevronView)

        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraSelectView)
        terraSelectPlaceholder = typedArray.getString(R.styleable.TerraSelectView_terraSelectPlaceholder) ?: terraSelectPlaceholder
        terraSelectFilled = typedArray.getBoolean(R.styleable.TerraSelectView_terraSelectFilled, false)
        terraSelectSearchEnabled = typedArray.getBoolean(R.styleable.TerraSelectView_terraSelectSearchEnabled, false)
        terraSelectSearchFocused = typedArray.getBoolean(R.styleable.TerraSelectView_terraSelectSearchFocused, false)
        typedArray.recycle()
    }

    private fun bind() {
        textView.text = terraSelectPlaceholder
        searchIconView.visibility = if (terraSelectSearchEnabled) View.VISIBLE else View.GONE
        updateAppearance()
    }

    private fun updateAppearance() {
        background = roundedBackground(context,
            fillColor = R.color.terra_color_neutral_10,
            strokeColor = if (terraSelectSearchFocused) R.color.terra_color_teal_700 else R.color.terra_color_neutral_60,
            strokeWidth = 1,
            radius = R.dimen.terra_radius_medium,
        )
        textView.setTextColor(ContextCompat.getColor(context, if (terraSelectFilled) R.color.terra_color_neutral_90 else R.color.terra_color_neutral_60))
        val iconColor = ContextCompat.getColor(context, R.color.terra_color_neutral_80)
        searchIconView.setIconTint(iconColor)
        chevronView.setIconTint(iconColor)
    }
}

class TerraTextFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val labelView: TextView
    private val fieldContainer: LinearLayout
    private val prefixView: TextView
    private val leftIconView: TerraIconView
    private val inputView: TextView
    private val suffixView: TextView
    private val rightIconView: TerraIconView
    private val helperView: TextView

    var terraTextFieldLabel: String = "Label"
        set(value) {
            field = value
            labelView.text = value
        }

    var terraTextFieldPlaceholder: String = "Placeholder"
        set(value) {
            field = value
            inputView.text = value
        }

    var terraTextFieldHelper: String = "Helper Type Here"
        set(value) {
            field = value
            helperView.text = value
        }

    var terraTextFieldVariant: TerraTextFieldVariant = TerraTextFieldVariant.DEFAULT
        set(value) {
            field = value
            updateVariant()
        }

    var terraTextFieldShowLabel: Boolean = true
        set(value) {
            field = value
            labelView.visibility = if (value) View.VISIBLE else View.GONE
        }

    var terraTextFieldShowHelper: Boolean = true
        set(value) {
            field = value
            helperView.visibility = if (value) View.VISIBLE else View.GONE
        }

    var terraTextFieldShowLeftIcon: Boolean = false
        set(value) {
            field = value
            updateVariant()
        }

    var terraTextFieldShowRightIcon: Boolean = false
        set(value) {
            field = value
            updateVariant()
        }

    init {
        orientation = VERTICAL
        labelView = labelText()
        fieldContainer = LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            minimumHeight = resources.getDimensionPixelSize(R.dimen.terra_text_field_height)
            setPaddingRelative(
                resources.getDimensionPixelSize(R.dimen.terra_spacing_16),
                resources.getDimensionPixelSize(R.dimen.terra_spacing_8),
                resources.getDimensionPixelSize(R.dimen.terra_spacing_16),
                resources.getDimensionPixelSize(R.dimen.terra_spacing_8),
            )
            background = roundedBackground(context, R.color.terra_color_neutral_10, R.color.terra_color_neutral_60, 1, R.dimen.terra_radius_medium)
        }
        prefixView = AppCompatTextView(context).apply {
            text = "Rp"
            textSize = 14f
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_80))
            visibility = View.GONE
        }
        leftIconView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.ACTION
            terraIconName = TerraIconName.SEARCH
            visibility = View.GONE
        }
        inputView = AppCompatTextView(context).apply {
            textSize = 14f
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_60))
        }
        suffixView = AppCompatTextView(context).apply {
            text = "Kg"
            textSize = 14f
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_80))
            visibility = View.GONE
        }
        rightIconView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.ACTION
            terraIconName = TerraIconName.CLOSE
            visibility = View.GONE
        }
        helperView = helperText()

        fieldContainer.addView(prefixView)
        fieldContainer.addView(leftIconView)
        fieldContainer.addView(inputView, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f).apply {
            marginStart = resources.getDimensionPixelSize(R.dimen.terra_spacing_8)
            marginEnd = resources.getDimensionPixelSize(R.dimen.terra_spacing_8)
        })
        fieldContainer.addView(suffixView)
        fieldContainer.addView(rightIconView)
        addView(labelView)
        addView(fieldContainer)
        addView(helperView)

        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraTextFieldView)
        terraTextFieldLabel = typedArray.getString(R.styleable.TerraTextFieldView_terraTextFieldLabel) ?: terraTextFieldLabel
        terraTextFieldPlaceholder = typedArray.getString(R.styleable.TerraTextFieldView_terraTextFieldPlaceholder) ?: terraTextFieldPlaceholder
        terraTextFieldHelper = typedArray.getString(R.styleable.TerraTextFieldView_terraTextFieldHelper) ?: terraTextFieldHelper
        terraTextFieldVariant = when (typedArray.getInt(R.styleable.TerraTextFieldView_terraTextFieldVariant, 0)) {
            1 -> TerraTextFieldVariant.TEXT_PREFIX
            2 -> TerraTextFieldVariant.TEXT_PREFIX_ICON
            3 -> TerraTextFieldVariant.TEXT_SUFFIX
            else -> TerraTextFieldVariant.DEFAULT
        }
        terraTextFieldShowLabel = typedArray.getBoolean(R.styleable.TerraTextFieldView_terraTextFieldShowLabel, true)
        terraTextFieldShowHelper = typedArray.getBoolean(R.styleable.TerraTextFieldView_terraTextFieldShowHelper, true)
        terraTextFieldShowLeftIcon = typedArray.getBoolean(R.styleable.TerraTextFieldView_terraTextFieldShowLeftIcon, false)
        terraTextFieldShowRightIcon = typedArray.getBoolean(R.styleable.TerraTextFieldView_terraTextFieldShowRightIcon, false)
        typedArray.recycle()
    }

    private fun bind() {
        labelView.text = terraTextFieldLabel
        inputView.text = terraTextFieldPlaceholder
        helperView.text = terraTextFieldHelper
        labelView.visibility = if (terraTextFieldShowLabel) View.VISIBLE else View.GONE
        helperView.visibility = if (terraTextFieldShowHelper) View.VISIBLE else View.GONE
        updateVariant()
    }

    private fun updateVariant() {
        prefixView.visibility = if (terraTextFieldVariant == TerraTextFieldVariant.TEXT_PREFIX || terraTextFieldVariant == TerraTextFieldVariant.TEXT_PREFIX_ICON) View.VISIBLE else View.GONE
        suffixView.visibility = if (terraTextFieldVariant == TerraTextFieldVariant.TEXT_SUFFIX) View.VISIBLE else View.GONE
        leftIconView.visibility = if (terraTextFieldShowLeftIcon || terraTextFieldVariant == TerraTextFieldVariant.TEXT_PREFIX_ICON) View.VISIBLE else View.GONE
        rightIconView.visibility = if (terraTextFieldShowRightIcon) View.VISIBLE else View.GONE
    }

    private fun labelText(): TextView {
        return AppCompatTextView(context).apply {
            textSize = 12f
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_80))
        }
    }

    private fun helperText(): TextView {
        return AppCompatTextView(context).apply {
            textSize = 12f
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_60))
        }
    }
}

private fun roundedBackground(
    context: Context,
    fillColor: Int,
    strokeColor: Int,
    strokeWidth: Int,
    radius: Int,
): GradientDrawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        setColor(ContextCompat.getColor(context, fillColor))
        setStroke(strokeWidth, ContextCompat.getColor(context, strokeColor))
        cornerRadius = context.resources.getDimension(radius)
    }
}

enum class TerraSearchBarState {
    DEFAULT,
    FOCUS,
    RESULT,
    DISABLED,
}

enum class TerraTextFieldVariant {
    DEFAULT,
    TEXT_PREFIX,
    TEXT_PREFIX_ICON,
    TEXT_SUFFIX,
}

class TerraQuantityEditorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val minusView: TextView
    private val valueView: TextView
    private val plusView: TextView
    private val leftDivider: View
    private val rightDivider: View

    var terraQuantityValue: String = "1.000"
        set(value) {
            field = value
            valueView.text = value
        }

    var terraQuantityState: TerraQuantityEditorState = TerraQuantityEditorState.DEFAULT
        set(value) {
            field = value
            updateAppearance()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        minimumWidth = resources.getDimensionPixelSize(R.dimen.terra_quantity_editor_width)
        minimumHeight = resources.getDimensionPixelSize(R.dimen.terra_text_field_height)
        background = roundedBackground(context, R.color.terra_color_neutral_10, R.color.terra_color_neutral_50, 1, R.dimen.terra_radius_medium)

        minusView = quantityActionText("−")
        valueView = AppCompatTextView(context).apply {
            gravity = Gravity.CENTER
            textSize = 16f
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_90))
        }
        plusView = quantityActionText("+")
        leftDivider = dividerView()
        rightDivider = dividerView()

        addView(minusView, LayoutParams(resources.getDimensionPixelSize(R.dimen.terra_spacing_40), LayoutParams.MATCH_PARENT))
        addView(leftDivider, LayoutParams(1, LayoutParams.MATCH_PARENT))
        addView(valueView, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f))
        addView(rightDivider, LayoutParams(1, LayoutParams.MATCH_PARENT))
        addView(plusView, LayoutParams(resources.getDimensionPixelSize(R.dimen.terra_spacing_40), LayoutParams.MATCH_PARENT))

        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraQuantityEditorView)
        terraQuantityValue = typedArray.getString(R.styleable.TerraQuantityEditorView_terraQuantityValue) ?: terraQuantityValue
        terraQuantityState = when (typedArray.getInt(R.styleable.TerraQuantityEditorView_terraQuantityState, 0)) {
            1 -> TerraQuantityEditorState.MINIMUM
            2 -> TerraQuantityEditorState.MAXIMUM
            else -> TerraQuantityEditorState.DEFAULT
        }
        typedArray.recycle()
    }

    private fun bind() {
        valueView.text = terraQuantityValue
        updateAppearance()
    }

    private fun updateAppearance() {
        val disabledMinus = terraQuantityState == TerraQuantityEditorState.MINIMUM
        val disabledPlus = terraQuantityState == TerraQuantityEditorState.MAXIMUM
        minusView.setTextColor(ContextCompat.getColor(context, if (disabledMinus) R.color.terra_color_neutral_50 else R.color.terra_color_neutral_90))
        plusView.setTextColor(ContextCompat.getColor(context, if (disabledPlus) R.color.terra_color_neutral_50 else R.color.terra_color_neutral_90))
        valueView.setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_90))
        leftDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.terra_color_neutral_50))
        rightDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.terra_color_neutral_50))
    }

    private fun quantityActionText(symbol: String): TextView {
        return AppCompatTextView(context).apply {
            text = symbol
            gravity = Gravity.CENTER
            textSize = 24f
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_90))
        }
    }

    private fun dividerView(): View {
        return View(context).apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.terra_color_neutral_50))
        }
    }
}

enum class TerraQuantityEditorState {
    DEFAULT,
    MINIMUM,
    MAXIMUM,
}

enum class TerraImageUploadState {
    UPLOAD,
    PROCESSING,
    ATTACHED_ONE_CTA,
    ATTACHED_TWO_CTA,
}

enum class TerraBottomSheetVariant {
    OPTIONS,
    COUNTDOWN,
}

enum class TerraDatePickerMode {
    SINGLE,
    DOUBLE,
}
