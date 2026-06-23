package com.terra.ds.imageupload

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
import com.terra.ds.internal.applyTerraTextAppearance
import com.terra.ds.internal.defaultLabelText
import com.terra.tokens.R as TokensR
import com.terra.ds.button.TerraButtonType
import com.terra.ds.button.TerraButtonView
import com.terra.ds.timer.TerraTimerView
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
        labelView = defaultLabelText()
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
            minimumHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_image_upload_height)
            setPaddingRelative(1, resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16), 1, resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_16))
            setBackgroundColor(ContextCompat.getColor(context, TokensR.color.terra_color_surface))
        }
        when (terraImageUploadState) {
            TerraImageUploadState.UPLOAD -> {
                container.addView(TerraIconView(context).apply {
                    terraIconCategory = TerraIconCategory.ACTION
                    terraIconName = TerraIconName.PLUS
                    setIconTint(ContextCompat.getColor(context, TokensR.color.terra_color_primary))
                })
                container.addView(TerraButtonView(context).apply {
                    text = "Ambil Gambar"
                    terraButtonType = TerraButtonType.OUTLINED_PRIMARY
                })
            }
            TerraImageUploadState.PROCESSING -> {
                container.addView(AppCompatTextView(context).apply {
                    applyTerraTextAppearance(R.style.TextAppearance_Terra_Caption)
                    text = "Dalam Proses..."
                    setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_secondary))
                })
            }
            TerraImageUploadState.ATTACHED_ONE_CTA,
            TerraImageUploadState.ATTACHED_TWO_CTA -> {
                container.addView(AppCompatTextView(context).apply {
                    applyTerraTextAppearance(R.style.TextAppearance_Terra_Caption)
                    text = "Image attached"
                    setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_secondary))
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

    private fun helperText(): TextView {
        return AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Caption)
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_secondary))
        }
    }

    private fun mandatoryIndicator(): TextView {
        return AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Caption)
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_secondary))
        }
    }
}
enum class TerraImageUploadState {
    UPLOAD,
    PROCESSING,
    ATTACHED_ONE_CTA,
    ATTACHED_TWO_CTA,
}
