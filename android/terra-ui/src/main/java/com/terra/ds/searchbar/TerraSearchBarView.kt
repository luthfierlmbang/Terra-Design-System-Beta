package com.terra.ds.searchbar

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
import com.terra.ds.internal.roundedBackground
import com.terra.tokens.R as TokensR
import com.terra.ds.button.TerraButtonType
import com.terra.ds.button.TerraButtonView
import com.terra.ds.timer.TerraTimerView
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

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
        minimumHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_search_bar_height)
        setPaddingRelative(
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
        )

        iconView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.ACTION
            terraIconName = TerraIconName.SEARCH
        }
        textView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Body_Small)
        }
        clearView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.ACTION
            terraIconName = TerraIconName.CLOSE
            visibility = View.GONE
        }

        addView(iconView)
        addView(textView, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f).apply {
            marginStart = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8)
            marginEnd = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8)
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
            TerraSearchBarState.FOCUS -> TokensR.color.terra_color_border_focus
            TerraSearchBarState.DISABLED -> TokensR.color.terra_color_disabled_surface
            else -> TokensR.color.terra_color_text_tertiary
        }
        background = roundedBackground(context,
            fillColor = if (disabled) TokensR.color.terra_color_surface_variant else TokensR.color.terra_color_surface,
            strokeColor = strokeColor,
            strokeWidth = 1,
            radius = TokensR.dimen.terra_radius_pill,
        )
        textView.setTextColor(ContextCompat.getColor(context, if (disabled) TokensR.color.terra_color_text_tertiary else TokensR.color.terra_color_text_primary))
        val iconColor = ContextCompat.getColor(context, if (disabled) TokensR.color.terra_color_text_tertiary else TokensR.color.terra_color_overlay)
        iconView.setIconTint(iconColor)
        clearView.setIconTint(iconColor)
    }
}
enum class TerraSearchBarState {
    DEFAULT,
    FOCUS,
    RESULT,
    DISABLED,
}
