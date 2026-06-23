package com.terra.ds.loading

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
import com.terra.ds.R
import com.terra.tokens.R as TokensR
import com.terra.ds.button.TerraButtonType
import com.terra.ds.button.TerraButtonView
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraLoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val progressBar: ProgressBar
    private val messageView: TextView

    var terraLoadingProgress: Int = 100
        set(value) {
            field = value.coerceIn(0, 100)
            messageView.text = "Dalam Proses..."
        }

    var terraLoadingMessage: String = "Dalam Proses..."
        set(value) {
            field = value
            messageView.text = value
        }

    var terraLoadingInline: Boolean = false
        set(value) {
            field = value
            orientation = if (value) HORIZONTAL else VERTICAL
        }

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        setPadding(
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_42),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_36),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_42),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_36),
        )
        setBackgroundColor(ContextCompat.getColor(context, TokensR.color.terra_color_surface))

        progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleLarge)
        messageView = AppCompatTextView(context).apply {
            textSize = 14f
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_overlay))
        }

        addView(progressBar, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
        addView(messageView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))

        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraLoadingView)
        terraLoadingProgress = typedArray.getInt(R.styleable.TerraLoadingView_terraLoadingProgress, terraLoadingProgress)
        terraLoadingMessage = typedArray.getString(R.styleable.TerraLoadingView_terraLoadingMessage) ?: terraLoadingMessage
        terraLoadingInline = typedArray.getBoolean(R.styleable.TerraLoadingView_terraLoadingInline, false)
        typedArray.recycle()
    }

    private fun bind() {
        messageView.text = terraLoadingMessage
    }
}
