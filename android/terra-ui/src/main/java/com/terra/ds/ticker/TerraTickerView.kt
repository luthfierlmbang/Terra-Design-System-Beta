package com.terra.ds.ticker

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.terra.ds.internal.applyTerraTextAppearance
import com.terra.ds.R
import com.terra.ds.internal.roundedFillBackground
import com.terra.tokens.R as TokensR
import com.terra.ds.button.TerraButtonType
import com.terra.ds.button.TerraButtonView
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraTickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val iconView: TerraIconView
    private val contentLayout: LinearLayout
    private val titleView: TextView
    private val messageView: TextView

    var terraTickerTone: TerraTickerTone = TerraTickerTone.INFORMATION
        set(value) {
            field = value
            updateAppearance()
        }

    var terraTickerDetailed: Boolean = false
        set(value) {
            field = value
            updateAppearance()
        }

    var terraTickerTitle: String = "Information Title Text"
        set(value) {
            field = value
            titleView.text = value
        }

    var terraTickerMessage: String = "Warning information maximum 1 line warning information maximum 1 line"
        set(value) {
            field = value
            messageView.text = value
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        minimumHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_ticker_min_height)
        setPaddingRelative(
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_24),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
        )

        iconView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.INFO
        }
        contentLayout = LinearLayout(context).apply {
            orientation = VERTICAL
        }
        titleView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Body_Small_Bold)
            visibility = View.GONE
        }
        messageView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Caption)
        }

        contentLayout.addView(titleView)
        contentLayout.addView(messageView)
        addView(iconView)
        addView(contentLayout, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f).apply {
            marginStart = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8)
        })

        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraTickerView)
        terraTickerTone = when (typedArray.getInt(R.styleable.TerraTickerView_terraTickerTone, 0)) {
            1 -> TerraTickerTone.WARNING
            2 -> TerraTickerTone.ERROR
            else -> TerraTickerTone.INFORMATION
        }
        terraTickerDetailed = typedArray.getBoolean(R.styleable.TerraTickerView_terraTickerDetailed, false)
        terraTickerTitle = typedArray.getString(R.styleable.TerraTickerView_terraTickerTitle) ?: terraTickerTitle
        terraTickerMessage = typedArray.getString(R.styleable.TerraTickerView_terraTickerMessage) ?: terraTickerMessage
        typedArray.recycle()
    }

    private fun bind() {
        titleView.text = terraTickerTitle
        messageView.text = terraTickerMessage
        updateAppearance()
    }

    private fun updateAppearance() {
        titleView.visibility = if (terraTickerDetailed) View.VISIBLE else View.GONE
        gravity = if (terraTickerDetailed) Gravity.TOP else Gravity.CENTER_VERTICAL
        val palette = tickerPalette(terraTickerTone)
        background = roundedFillBackground(palette.backgroundColor)
        iconView.terraIconName = when (terraTickerTone) {
            TerraTickerTone.INFORMATION -> TerraIconName.INFORMATION
            TerraTickerTone.WARNING -> TerraIconName.EXCLAMATION
            TerraTickerTone.ERROR -> TerraIconName.EXCLAMATION
        }
        iconView.setIconTint(ContextCompat.getColor(context, palette.textColor))
        titleView.setTextColor(ContextCompat.getColor(context, palette.textColor))
        messageView.setTextColor(ContextCompat.getColor(context, palette.textColor))
        messageView.maxLines = if (terraTickerDetailed) 2 else 1
    }
}

private data class TerraTickerPalette(
    val backgroundColor: Int,
    val textColor: Int,
)

private fun tickerPalette(tone: TerraTickerTone): TerraTickerPalette {
    return when (tone) {
        TerraTickerTone.INFORMATION -> TerraTickerPalette(TokensR.color.terra_color_info_light, TokensR.color.terra_color_info)
        TerraTickerTone.WARNING -> TerraTickerPalette(TokensR.color.terra_color_secondary_light, TokensR.color.terra_color_secondary_hover)
        TerraTickerTone.ERROR -> TerraTickerPalette(TokensR.color.terra_color_error_surface, TokensR.color.terra_color_error)
    }
}

enum class TerraTickerTone {
    INFORMATION,
    WARNING,
    ERROR,
}
