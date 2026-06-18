package com.terra.ds.icon

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.terra.ds.R

class TerraIconView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatImageView(context, attrs, defStyleAttr) {

    var terraIconCategory: TerraIconCategory = TerraIconCategory.SYSTEM
        set(value) {
            field = value
            applyIcon()
        }

    var terraIconName: TerraIconName = TerraIconName.PLACEHOLDER
        set(value) {
            field = value
            applyIcon()
        }

    init {
        scaleType = ScaleType.FIT_CENTER
        parseAttributes(attrs)
        applyIcon()
    }

    fun setIconTint(color: Int) {
        imageTintList = ColorStateList.valueOf(color)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraIconView)
        terraIconCategory = TerraIconCategory.fromAttrValue(
            typedArray.getInt(R.styleable.TerraIconView_terraIconCategory, 0),
        )
        terraIconName = TerraIconName.fromAttrValue(
            typedArray.getInt(R.styleable.TerraIconView_terraIconName, 0),
        )

        if (typedArray.hasValue(R.styleable.TerraIconView_terraIconTint)) {
            setIconTint(
                typedArray.getColor(
                    R.styleable.TerraIconView_terraIconTint,
                    ContextCompat.getColor(context, R.color.terra_color_neutral_90),
                ),
            )
        }

        if (typedArray.hasValue(R.styleable.TerraIconView_terraIconSize)) {
            val size = typedArray.getDimensionPixelSize(
                R.styleable.TerraIconView_terraIconSize,
                LayoutParams.WRAP_CONTENT,
            )
            layoutParams = layoutParams?.apply {
                width = size
                height = size
            } ?: LayoutParams(size, size)
        }

        typedArray.recycle()
    }

    private fun applyIcon() {
        setImageResource(resolveDrawableRes())
    }

    @DrawableRes
    private fun resolveDrawableRes(): Int {
        return when (terraIconCategory) {
            TerraIconCategory.ACTION -> when (terraIconName) {
                TerraIconName.PLUS -> R.drawable.terra_ic_plus
                TerraIconName.CHECK -> R.drawable.terra_ic_check
                TerraIconName.CLOSE -> R.drawable.terra_ic_close
                TerraIconName.DELETE -> R.drawable.terra_ic_delete
                TerraIconName.EDIT -> R.drawable.terra_ic_edit
                TerraIconName.REFRESH -> R.drawable.terra_ic_refresh
                TerraIconName.SEARCH -> R.drawable.terra_ic_search
                TerraIconName.MINUS -> R.drawable.terra_ic_minus
                else -> R.drawable.terra_ic_placeholder
            }
            TerraIconCategory.NAVIGATION -> when (terraIconName) {
                TerraIconName.ARROW_LEFT -> R.drawable.terra_ic_arrow_left
                TerraIconName.ARROW_RIGHT -> R.drawable.terra_ic_arrow_right
                TerraIconName.CHEVRON_DOWN -> R.drawable.terra_ic_chevron_down
                TerraIconName.CHEVRON_LEFT -> R.drawable.terra_ic_chevron_left
                TerraIconName.CHEVRON_RIGHT -> R.drawable.terra_ic_chevron_right
                TerraIconName.CHEVRON_UP -> R.drawable.terra_ic_chevron_up
                else -> R.drawable.terra_ic_placeholder
            }
            TerraIconCategory.STATUS -> when (terraIconName) {
                TerraIconName.DONE -> R.drawable.terra_ic_check
                TerraIconName.IN_PROGRESS -> R.drawable.terra_ic_time
                TerraIconName.OFFLINE -> R.drawable.terra_ic_notification
                else -> R.drawable.terra_ic_placeholder
            }
            TerraIconCategory.INFO -> when (terraIconName) {
                TerraIconName.EXCLAMATION -> R.drawable.terra_ic_exclamation
                TerraIconName.INFORMATION -> R.drawable.terra_ic_info
                TerraIconName.QUESTIONMARK -> R.drawable.terra_ic_question
                TerraIconName.COUNTER -> R.drawable.terra_ic_info
                else -> R.drawable.terra_ic_placeholder
            }
            TerraIconCategory.DOMAIN -> when (terraIconName) {
                TerraIconName.CASH_IN -> R.drawable.terra_ic_plus
                TerraIconName.CASH_OUT -> R.drawable.terra_ic_minus
                TerraIconName.NASABAH -> R.drawable.terra_ic_user
                TerraIconName.BANK -> R.drawable.terra_ic_placeholder
                TerraIconName.REPORT -> R.drawable.terra_ic_placeholder
                TerraIconName.REPORT_TRANSACTION -> R.drawable.terra_ic_placeholder
                TerraIconName.AKUSISI -> R.drawable.terra_ic_placeholder
                else -> R.drawable.terra_ic_placeholder
            }
            TerraIconCategory.SYSTEM -> when (terraIconName) {
                TerraIconName.PLACEHOLDER -> R.drawable.terra_ic_placeholder
                TerraIconName.HOME -> R.drawable.terra_ic_home
                TerraIconName.USER -> R.drawable.terra_ic_user
                TerraIconName.SETTINGS -> R.drawable.terra_ic_settings
                TerraIconName.NOTIFICATION -> R.drawable.terra_ic_notification
                TerraIconName.TIME -> R.drawable.terra_ic_time
                TerraIconName.CALENDAR -> R.drawable.terra_ic_calendar
                else -> R.drawable.terra_ic_placeholder
            }
        }
    }
}

enum class TerraIconCategory(val attrValue: Int) {
    SYSTEM(0),
    ACTION(1),
    NAVIGATION(2),
    STATUS(3),
    INFO(4),
    DOMAIN(5),
    ;

    companion object {
        fun fromAttrValue(value: Int): TerraIconCategory {
            return entries.firstOrNull { it.attrValue == value } ?: SYSTEM
        }
    }
}

enum class TerraIconName(val attrValue: Int) {
    PLACEHOLDER(0),
    PLUS(1),
    CHECK(2),
    CLOSE(3),
    DELETE(4),
    EDIT(5),
    REFRESH(6),
    SEARCH(7),
    SORT(8),
    QR_SCAN(9),
    MINUS(10),
    ARROW_LEFT(11),
    ARROW_RIGHT(12),
    CHEVRON_DOWN(13),
    CHEVRON_LEFT(14),
    CHEVRON_RIGHT(15),
    CHEVRON_UP(16),
    KEBAB_HORIZONTAL(17),
    KEBAB_HORIZONTAL_2(18),
    HAMBURGER_MENU(19),
    DONE(20),
    IN_PROGRESS(21),
    OFFLINE(22),
    EXCLAMATION(23),
    INFORMATION(24),
    QUESTIONMARK(25),
    COUNTER(26),
    CASH_IN(27),
    CASH_OUT(28),
    NASABAH(29),
    BANK(30),
    REPORT(31),
    REPORT_TRANSACTION(32),
    AKUSISI(33),
    HOME(34),
    USER(35),
    SETTINGS(36),
    NOTIFICATION(37),
    TIME(38),
    CALENDAR(39),
    ;

    companion object {
        fun fromAttrValue(value: Int): TerraIconName {
            return entries.firstOrNull { it.attrValue == value } ?: PLACEHOLDER
        }
    }
}
