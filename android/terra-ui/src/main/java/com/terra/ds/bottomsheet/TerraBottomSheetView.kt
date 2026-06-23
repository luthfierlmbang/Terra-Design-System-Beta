package com.terra.ds.bottomsheet

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
        setBackgroundColor(ContextCompat.getColor(context, TokensR.color.terra_color_surface))
        titleView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Header_S)
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_primary))
            setPadding(resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16), resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16), resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16), resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16))
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
                            applyTerraTextAppearance(R.style.TextAppearance_Terra_Body_Small)
                            text = "Option Menu ${index + 1}"
                            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_primary))
                            setPadding(resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_24), resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_10), resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_24), resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_10))
                        })
                    }
                }
                scrollView.addView(optionList)
                contentContainer.addView(scrollView)
            }
            TerraBottomSheetVariant.COUNTDOWN -> {
                contentContainer.addView(AppCompatTextView(context).apply {
                    applyTerraTextAppearance(R.style.TextAppearance_Terra_Header_XL)
                    text = "00:00:12"
                    gravity = Gravity.CENTER_HORIZONTAL
                    setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_secondary))
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
enum class TerraBottomSheetVariant {
    OPTIONS,
    COUNTDOWN,
}
