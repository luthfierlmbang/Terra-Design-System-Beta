package com.terra.ds.internal

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.terra.tokens.R as TokensR

internal fun selectionBackground(
    context: Context,
    selected: Boolean,
    enabled: Boolean,
    @DimenRes radius: Int,
): GradientDrawable {
    val fillColor = when {
        selected && enabled -> TokensR.color.terra_color_primary
        selected -> TokensR.color.terra_color_text_tertiary
        else -> TokensR.color.terra_color_transparent
    }
    val strokeColor = if (enabled) TokensR.color.terra_color_border_focus else TokensR.color.terra_color_text_tertiary
    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        setColor(ContextCompat.getColor(context, fillColor))
        setStroke(1, ContextCompat.getColor(context, strokeColor))
        cornerRadius = context.resources.getDimension(radius)
    }
}

internal fun roundedBackground(
    context: Context,
    @ColorRes fillColor: Int,
    @ColorRes strokeColor: Int,
    strokeWidth: Int,
    @DimenRes radius: Int,
): GradientDrawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        setColor(ContextCompat.getColor(context, fillColor))
        if (strokeWidth > 0) {
            setStroke(strokeWidth, ContextCompat.getColor(context, strokeColor))
        }
        cornerRadius = context.resources.getDimension(radius)
    }
}

internal fun View.roundedFillBackground(@ColorRes fillColor: Int): GradientDrawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = resources.getDimension(TokensR.dimen.terra_radius_medium)
        setColor(ContextCompat.getColor(context, fillColor))
    }
}

internal fun TextView.applyTerraTextAppearance(styleRes: Int) {
    TextViewCompat.setTextAppearance(this, styleRes)
}

internal fun View.defaultLabelText(): TextView {
    return AppCompatTextView(context).apply {
        applyTerraTextAppearance(com.terra.ds.R.style.TextAppearance_Terra_Body_Small)
        setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_primary))
    }
}
