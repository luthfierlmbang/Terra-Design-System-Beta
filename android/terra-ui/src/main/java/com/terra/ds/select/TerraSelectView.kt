package com.terra.ds.select

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
        minimumHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_text_field_height)
        setPaddingRelative(
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
        )

        searchIconView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.ACTION
            terraIconName = TerraIconName.SEARCH
            visibility = View.GONE
        }
        textView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Body_Small)
        }
        chevronView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.NAVIGATION
            terraIconName = TerraIconName.CHEVRON_DOWN
        }

        addView(searchIconView)
        addView(textView, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f).apply {
            marginStart = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8)
            marginEnd = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8)
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
            fillColor = TokensR.color.terra_color_surface,
            strokeColor = if (terraSelectSearchFocused) TokensR.color.terra_color_border_focus else TokensR.color.terra_color_text_tertiary,
            strokeWidth = 1,
            radius = TokensR.dimen.terra_radius_medium,
        )
        textView.setTextColor(ContextCompat.getColor(context, if (terraSelectFilled) TokensR.color.terra_color_text_primary else TokensR.color.terra_color_text_tertiary))
        val iconColor = ContextCompat.getColor(context, TokensR.color.terra_color_overlay)
        searchIconView.setIconTint(iconColor)
        chevronView.setIconTint(iconColor)
    }
}
