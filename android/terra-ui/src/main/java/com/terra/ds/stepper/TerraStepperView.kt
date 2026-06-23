package com.terra.ds.stepper

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.terra.ds.R
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView
import com.terra.ds.internal.applyTerraTextAppearance
import com.terra.tokens.R as TokensR

private enum class StepperState {
    INACTIVE,
    PROGRESS,
    COMPLETE,
    ERROR,
}

class TerraStepperView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    var terraStepperCount: Int = 3
        set(value) {
            field = value.coerceAtLeast(1)
            render()
        }

    var terraStepperCurrentStep: Int = 0
        set(value) {
            field = value.coerceIn(0, terraStepperCount - 1)
            render()
        }

    var terraStepperErrorStep: Int = -1
        set(value) {
            field = value
            render()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        parseAttributes(attrs)
        render()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraStepperView)
        terraStepperCount = typedArray.getInt(R.styleable.TerraStepperView_terraStepperCount, terraStepperCount)
        terraStepperCurrentStep = typedArray.getInt(R.styleable.TerraStepperView_terraStepperCurrentStep, terraStepperCurrentStep)
        terraStepperErrorStep = typedArray.getInt(R.styleable.TerraStepperView_terraStepperErrorStep, terraStepperErrorStep)
        typedArray.recycle()
    }

    private fun render() {
        removeAllViews()
        val stepStates = List(terraStepperCount) { index -> stateForIndex(index) }

        stepStates.forEachIndexed { index, state ->
            addView(stepItem(index, state))
            if (index < stepStates.lastIndex) {
                addView(stepLine(lineStateFor(state, stepStates[index + 1])))
            }
        }
    }

    private fun stateForIndex(index: Int): StepperState {
        return when {
            index == terraStepperErrorStep -> StepperState.ERROR
            index < terraStepperCurrentStep -> StepperState.COMPLETE
            index == terraStepperCurrentStep -> StepperState.PROGRESS
            else -> StepperState.INACTIVE
        }
    }

    private fun lineStateFor(current: StepperState, next: StepperState): StepperState {
        return when {
            current == StepperState.COMPLETE -> StepperState.COMPLETE
            current == StepperState.PROGRESS || next == StepperState.PROGRESS -> StepperState.PROGRESS
            else -> StepperState.INACTIVE
        }
    }

    private fun stepItem(index: Int, state: StepperState): View {
        return LinearLayout(context).apply {
            orientation = VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            minimumWidth = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_24) * 2

            addView(stepBullet(index, state))
            addView(AppCompatTextView(context).apply {
                applyTerraTextAppearance(R.style.TextAppearance_Terra_Caption)
                text = "Caption ${index + 1}"
                gravity = Gravity.CENTER
                setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_primary))
            }, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                topMargin = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_10)
            })
        }
    }

    private fun stepBullet(index: Int, state: StepperState): View {
        return LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER
            setPadding(
                resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_10),
                resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
                resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_10),
                resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_8),
            )
            background = bulletBackground(state)

            when (state) {
                StepperState.COMPLETE -> addView(stepIcon(TerraIconName.CHECK))
                StepperState.ERROR -> addView(stepIcon(TerraIconName.CLOSE))
                StepperState.PROGRESS,
                StepperState.INACTIVE,
                -> addView(AppCompatTextView(context).apply {
                    applyTerraTextAppearance(R.style.TextAppearance_Terra_CTA_Medium)
                    text = (index + 1).toString()
                    gravity = Gravity.CENTER
                    minWidth = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_20)
                    setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_on_primary))
                })
            }
        }
    }

    private fun stepIcon(name: TerraIconName): View {
        return TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.ACTION
            terraIconName = name
            layoutParams = LayoutParams(
                resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_24),
                resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_24),
            )
            setIconTint(ContextCompat.getColor(context, TokensR.color.terra_color_text_on_primary))
        }
    }

    private fun bulletBackground(state: StepperState): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(ContextCompat.getColor(context, when (state) {
                StepperState.COMPLETE -> TokensR.color.terra_color_success
                StepperState.PROGRESS -> TokensR.color.terra_color_secondary
                StepperState.ERROR -> TokensR.color.terra_color_error
                StepperState.INACTIVE -> TokensR.color.terra_color_disabled
            }))
            cornerRadius = resources.getDimension(TokensR.dimen.terra_radius_medium)
        }
    }

    private fun stepLine(state: StepperState): View {
        return View(context).apply {
            layoutParams = LayoutParams(resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_24), resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_4)).apply {
                width = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_24)
                topMargin = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_24)
                marginStart = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_4)
                marginEnd = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_4)
            }
            background = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                setColor(ContextCompat.getColor(context, when (state) {
                    StepperState.COMPLETE -> TokensR.color.terra_color_success
                    StepperState.PROGRESS -> TokensR.color.terra_color_secondary
                    StepperState.ERROR,
                    StepperState.INACTIVE,
                    -> TokensR.color.terra_color_disabled
                }))
                cornerRadius = resources.getDimension(TokensR.dimen.terra_radius_medium)
            }
        }
    }
}
