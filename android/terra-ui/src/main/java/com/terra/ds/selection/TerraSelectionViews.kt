package com.terra.ds.selection

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.terra.ds.R
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraChipView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val leftIconView: TerraIconView
    private val textView: TextView
    private val rightIconView: TerraIconView
    private val counterView: TextView

    var terraChipText: String = "This is Chips"
        set(value) {
            field = value
            textView.text = value
        }

    var terraChipSelected: Boolean = false
        set(value) {
            field = value
            updateAppearance()
        }

    var terraChipShowLeftIcon: Boolean = false
        set(value) {
            field = value
            leftIconView.visibility = if (value) View.VISIBLE else View.GONE
        }

    var terraChipShowRightIcon: Boolean = false
        set(value) {
            field = value
            rightIconView.visibility = if (value) View.VISIBLE else View.GONE
        }

    var terraChipShowCounter: Boolean = false
        set(value) {
            field = value
            counterView.visibility = if (value) View.VISIBLE else View.GONE
        }

    var terraChipCounterValue: Int = 2
        set(value) {
            field = value
            counterView.text = value.toString()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        minimumHeight = resources.getDimensionPixelSize(R.dimen.terra_chip_height)
        setPaddingRelative(
            resources.getDimensionPixelSize(R.dimen.terra_spacing_16),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_16),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_8),
        )

        leftIconView = iconView()
        rightIconView = iconView()
        textView = AppCompatTextView(context).apply {
            textSize = 14f
        }
        counterView = AppCompatTextView(context).apply {
            textSize = 12f
            setTypeface(typeface, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_10))
            setBackgroundColor(ContextCompat.getColor(context, R.color.terra_color_red_500))
            gravity = Gravity.CENTER
            visibility = View.GONE
            minWidth = resources.getDimensionPixelSize(R.dimen.terra_spacing_16)
            minHeight = resources.getDimensionPixelSize(R.dimen.terra_spacing_16)
        }

        addView(leftIconView)
        addView(textView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            marginStart = resources.getDimensionPixelSize(R.dimen.terra_spacing_4)
            marginEnd = resources.getDimensionPixelSize(R.dimen.terra_spacing_4)
        })
        addView(rightIconView)
        addView(counterView)

        parseAttributes(attrs)
        bind()
    }

    private fun iconView(): TerraIconView {
        return TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.ACTION
            terraIconName = TerraIconName.PLUS
            visibility = View.GONE
            setIconTint(ContextCompat.getColor(context, R.color.terra_color_teal_700))
        }
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraChipView)
        terraChipText = typedArray.getString(R.styleable.TerraChipView_terraChipText) ?: terraChipText
        terraChipSelected = typedArray.getBoolean(R.styleable.TerraChipView_terraChipSelected, false)
        terraChipShowLeftIcon = typedArray.getBoolean(R.styleable.TerraChipView_terraChipShowLeftIcon, false)
        terraChipShowRightIcon = typedArray.getBoolean(R.styleable.TerraChipView_terraChipShowRightIcon, false)
        terraChipShowCounter = typedArray.getBoolean(R.styleable.TerraChipView_terraChipShowCounter, false)
        terraChipCounterValue = typedArray.getInt(R.styleable.TerraChipView_terraChipCounterValue, terraChipCounterValue)
        typedArray.recycle()
    }

    private fun bind() {
        textView.text = terraChipText
        counterView.text = terraChipCounterValue.toString()
        updateAppearance()
        terraChipShowLeftIcon = terraChipShowLeftIcon
        terraChipShowRightIcon = terraChipShowRightIcon
        terraChipShowCounter = terraChipShowCounter
    }

    private fun updateAppearance() {
        if (terraChipSelected) {
            setBackgroundColor(ContextCompat.getColor(context, R.color.terra_color_teal_100))
            textView.setTextColor(ContextCompat.getColor(context, R.color.terra_color_teal_700))
            textView.setTypeface(textView.typeface, Typeface.BOLD)
            minimumHeight = resources.getDimensionPixelSize(R.dimen.terra_chip_selected_height)
        } else {
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            textView.setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_60))
            textView.setTypeface(textView.typeface, Typeface.NORMAL)
            minimumHeight = resources.getDimensionPixelSize(R.dimen.terra_chip_height)
        }
    }
}

class TerraNavbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val homeItem = item("Home", TerraIconName.HOME)
    private val activityItem = item("Aktivitas Harian", TerraIconName.USER)
    private val notificationItem = item("Notifikasi", TerraIconName.NOTIFICATION)

    var terraNavbarSelectedItem: TerraNavbarItem = TerraNavbarItem.HOME
        set(value) {
            field = value
            renderSelection()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        minimumHeight = resources.getDimensionPixelSize(R.dimen.terra_navbar_height)
        setBackgroundColor(ContextCompat.getColor(context, R.color.terra_color_neutral_10))
        addView(homeItem)
        addView(activityItem)
        addView(notificationItem)
        parseAttributes(attrs)
        renderSelection()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraNavbarView)
        terraNavbarSelectedItem = when (typedArray.getInt(R.styleable.TerraNavbarView_terraNavbarSelectedItem, 0)) {
            1 -> TerraNavbarItem.ACTIVITY
            2 -> TerraNavbarItem.NOTIFICATION
            3 -> TerraNavbarItem.NONE
            else -> TerraNavbarItem.HOME
        }
        typedArray.recycle()
    }

    private fun item(label: String, iconName: TerraIconName): LinearLayout {
        val container = LinearLayout(context).apply {
            orientation = VERTICAL
            gravity = Gravity.CENTER
            layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f)
        }
        val icon = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.SYSTEM
            terraIconName = iconName
        }
        val text = AppCompatTextView(context).apply {
            textSize = 12f
            textAlignment = TEXT_ALIGNMENT_CENTER
            this.text = label
        }
        container.addView(icon)
        container.addView(text)
        container.tag = Pair(icon, text)
        return container
    }

    private fun renderSelection() {
        updateItem(homeItem, terraNavbarSelectedItem == TerraNavbarItem.HOME)
        updateItem(activityItem, terraNavbarSelectedItem == TerraNavbarItem.ACTIVITY)
        updateItem(notificationItem, terraNavbarSelectedItem == TerraNavbarItem.NOTIFICATION)
    }

    private fun updateItem(item: LinearLayout, selected: Boolean) {
        val (icon, text) = item.tag as Pair<*, *>
        (icon as TerraIconView).setIconTint(
            ContextCompat.getColor(
                context,
                if (selected) R.color.terra_color_orange_500 else R.color.terra_color_neutral_80,
            ),
        )
        (text as TextView).setTextColor(
            ContextCompat.getColor(
                context,
                if (selected) R.color.terra_color_orange_500 else R.color.terra_color_neutral_80,
            ),
        )
    }
}


class TerraCheckboxView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val boxView: TextView
    private val labelView: TextView

    var terraSelectionLabel: String = "Label"
        set(value) {
            field = value
            labelView.text = value
        }

    var terraSelectionChecked: Boolean = false
        set(value) {
            field = value
            updateAppearance()
        }

    var terraSelectionEnabled: Boolean = true
        set(value) {
            field = value
            isEnabled = value
            updateAppearance()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        boxView = AppCompatTextView(context).apply {
            gravity = Gravity.CENTER
            textSize = 14f
        }
        labelView = labelText()
        val size = resources.getDimensionPixelSize(R.dimen.terra_selection_control_size)
        addView(boxView, LayoutParams(size, size))
        addView(labelView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            marginStart = resources.getDimensionPixelSize(R.dimen.terra_spacing_8)
        })
        parseAttributes(attrs, R.styleable.TerraCheckboxView)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?, styleable: IntArray) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, styleable)
        terraSelectionLabel = typedArray.getString(R.styleable.TerraCheckboxView_terraSelectionLabel) ?: terraSelectionLabel
        terraSelectionChecked = typedArray.getBoolean(R.styleable.TerraCheckboxView_terraSelectionChecked, false)
        terraSelectionEnabled = typedArray.getBoolean(R.styleable.TerraCheckboxView_terraSelectionEnabled, true)
        typedArray.recycle()
    }

    private fun bind() {
        labelView.text = terraSelectionLabel
        updateAppearance()
    }

    private fun updateAppearance() {
        boxView.text = if (terraSelectionChecked) "✓" else ""
        boxView.background = selectionBackground(context, terraSelectionChecked, terraSelectionEnabled, R.dimen.terra_radius_small)
        boxView.setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_10))
        labelView.setTextColor(ContextCompat.getColor(context, if (terraSelectionEnabled) R.color.terra_color_neutral_90 else R.color.terra_color_neutral_60))
    }
}

class TerraRadioView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val radioView: TextView
    private val labelView: TextView

    var terraSelectionLabel: String = "Label"
        set(value) {
            field = value
            labelView.text = value
        }

    var terraSelectionChecked: Boolean = false
        set(value) {
            field = value
            updateAppearance()
        }

    var terraSelectionEnabled: Boolean = true
        set(value) {
            field = value
            isEnabled = value
            updateAppearance()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        radioView = AppCompatTextView(context).apply {
            gravity = Gravity.CENTER
            textSize = 12f
        }
        labelView = labelText()
        val size = resources.getDimensionPixelSize(R.dimen.terra_selection_control_size)
        addView(radioView, LayoutParams(size, size))
        addView(labelView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            marginStart = resources.getDimensionPixelSize(R.dimen.terra_spacing_8)
        })
        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraRadioView)
        terraSelectionLabel = typedArray.getString(R.styleable.TerraRadioView_terraSelectionLabel) ?: terraSelectionLabel
        terraSelectionChecked = typedArray.getBoolean(R.styleable.TerraRadioView_terraSelectionChecked, false)
        terraSelectionEnabled = typedArray.getBoolean(R.styleable.TerraRadioView_terraSelectionEnabled, true)
        typedArray.recycle()
    }

    private fun bind() {
        labelView.text = terraSelectionLabel
        updateAppearance()
    }

    private fun updateAppearance() {
        radioView.text = if (terraSelectionChecked) "●" else ""
        radioView.background = selectionBackground(context, terraSelectionChecked, terraSelectionEnabled, R.dimen.terra_radius_pill)
        radioView.setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_10))
        labelView.setTextColor(ContextCompat.getColor(context, if (terraSelectionEnabled) R.color.terra_color_neutral_90 else R.color.terra_color_neutral_60))
    }
}

class TerraToggleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val trackView: LinearLayout
    private val thumbView: View
    private val spacerView: View
    private val labelView: TextView

    var terraSelectionLabel: String = "Label"
        set(value) {
            field = value
            labelView.text = value
        }

    var terraSelectionChecked: Boolean = false
        set(value) {
            field = value
            updateAppearance()
        }

    var terraSelectionEnabled: Boolean = true
        set(value) {
            field = value
            isEnabled = value
            updateAppearance()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        trackView = LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(resources.getDimensionPixelSize(R.dimen.terra_spacing_2), 0, resources.getDimensionPixelSize(R.dimen.terra_spacing_2), 0)
        }
        thumbView = View(context)
        spacerView = View(context)
        labelView = labelText()
        trackView.addView(thumbView)
        trackView.addView(spacerView)
        addView(trackView, LayoutParams(resources.getDimensionPixelSize(R.dimen.terra_spacing_40), resources.getDimensionPixelSize(R.dimen.terra_selection_control_size)))
        addView(labelView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            marginStart = resources.getDimensionPixelSize(R.dimen.terra_spacing_8)
        })
        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraToggleView)
        terraSelectionLabel = typedArray.getString(R.styleable.TerraToggleView_terraSelectionLabel) ?: terraSelectionLabel
        terraSelectionChecked = typedArray.getBoolean(R.styleable.TerraToggleView_terraSelectionChecked, false)
        terraSelectionEnabled = typedArray.getBoolean(R.styleable.TerraToggleView_terraSelectionEnabled, true)
        typedArray.recycle()
    }

    private fun bind() {
        labelView.text = terraSelectionLabel
        updateAppearance()
    }

    private fun updateAppearance() {
        trackView.background = roundedBackground(context, if (terraSelectionChecked && terraSelectionEnabled) R.color.terra_color_teal_700 else R.color.terra_color_neutral_40, 0, R.dimen.terra_radius_pill)
        thumbView.background = roundedBackground(context, R.color.terra_color_neutral_10, 0, R.dimen.terra_radius_pill)
        val thumbSize = resources.getDimensionPixelSize(R.dimen.terra_spacing_20)
        thumbView.layoutParams = LinearLayout.LayoutParams(thumbSize, thumbSize)
        spacerView.layoutParams = LinearLayout.LayoutParams(0, 1, 1f)
        if (terraSelectionChecked) {
            trackView.removeAllViews()
            trackView.addView(spacerView)
            trackView.addView(thumbView)
        } else {
            trackView.removeAllViews()
            trackView.addView(thumbView)
            trackView.addView(spacerView)
        }
        labelView.setTextColor(ContextCompat.getColor(context, if (terraSelectionEnabled) R.color.terra_color_neutral_90 else R.color.terra_color_neutral_60))
    }
}

class TerraTabView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    var terraTabItems: String = "Tab 1,Tab 2"
        set(value) {
            field = value
            render()
        }

    var terraTabSelectedIndex: Int = 0
        set(value) {
            field = value.coerceAtLeast(0)
            render()
        }

    var terraTabScrollable: Boolean = false
        set(value) {
            field = value
            render()
        }

    init {
        orientation = HORIZONTAL
        minimumHeight = resources.getDimensionPixelSize(R.dimen.terra_tab_height)
        parseAttributes(attrs)
        render()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraTabView)
        terraTabItems = typedArray.getString(R.styleable.TerraTabView_terraTabItems) ?: terraTabItems
        terraTabSelectedIndex = typedArray.getInt(R.styleable.TerraTabView_terraTabSelectedIndex, 0)
        terraTabScrollable = typedArray.getBoolean(R.styleable.TerraTabView_terraTabScrollable, false)
        typedArray.recycle()
    }

    private fun render() {
        removeAllViews()
        val items = terraTabItems.split(',').map { it.trim() }.filter { it.isNotEmpty() }.ifEmpty { listOf("Tab 1", "Tab 2") }
        items.forEachIndexed { index, label ->
            val selected = index == terraTabSelectedIndex.coerceIn(0, items.lastIndex)
            addView(AppCompatTextView(context).apply {
                text = label
                gravity = Gravity.CENTER
                textSize = 14f
                setTypeface(typeface, if (selected) Typeface.BOLD else Typeface.NORMAL)
                setTextColor(ContextCompat.getColor(context, if (selected) R.color.terra_color_teal_700 else R.color.terra_color_neutral_70))
                background = roundedBackground(context, if (selected) R.color.terra_color_teal_050 else android.R.color.transparent, 0, R.dimen.terra_radius_medium)
                setPaddingRelative(resources.getDimensionPixelSize(R.dimen.terra_spacing_16), 0, resources.getDimensionPixelSize(R.dimen.terra_spacing_16), 0)
            }, LayoutParams(if (terraTabScrollable) LayoutParams.WRAP_CONTENT else 0, LayoutParams.MATCH_PARENT, if (terraTabScrollable) 0f else 1f))
        }
    }
}

private fun selectionBackground(context: Context, selected: Boolean, enabled: Boolean, radius: Int): GradientDrawable {
    val fillColor = when {
        selected && enabled -> R.color.terra_color_teal_700
        selected -> R.color.terra_color_neutral_60
        else -> android.R.color.transparent
    }
    val strokeColor = if (enabled) R.color.terra_color_teal_700 else R.color.terra_color_neutral_60
    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        setColor(ContextCompat.getColor(context, fillColor))
        setStroke(1, ContextCompat.getColor(context, strokeColor))
        cornerRadius = context.resources.getDimension(radius)
    }
}

private fun roundedBackground(context: Context, fillColor: Int, strokeWidth: Int, radius: Int): GradientDrawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        setColor(ContextCompat.getColor(context, fillColor))
        if (strokeWidth > 0) {
            setStroke(strokeWidth, ContextCompat.getColor(context, R.color.terra_color_neutral_60))
        }
        cornerRadius = context.resources.getDimension(radius)
    }
}

private fun View.labelText(): TextView {
    return AppCompatTextView(context).apply {
        textSize = 14f
        setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_90))
    }
}

enum class TerraNavbarItem {
    HOME,
    ACTIVITY,
    NOTIFICATION,
    NONE,
}
