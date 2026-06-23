package com.terra.ds.navbar

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
import com.terra.ds.internal.applyTerraTextAppearance
import com.terra.ds.R
import com.terra.tokens.R as TokensR
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraNavbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val homeItem = item("Home", TerraIconName.HOME)
    private val activityItem = item("Aktivitas Harian", TerraIconName.SCOOTER)
    private val notificationItem = item("Notifikasi", TerraIconName.NOTIFICATION)

    var terraNavbarSelectedItem: TerraNavbarItem = TerraNavbarItem.HOME
        set(value) {
            field = value
            renderSelection()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        minimumHeight = resources.getDimensionPixelSize(TokensR.dimen.terra_navbar_height)
        setBackgroundColor(ContextCompat.getColor(context, TokensR.color.terra_color_surface))
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
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Caption)
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
                if (selected) TokensR.color.terra_color_secondary else TokensR.color.terra_color_text_secondary,
            ),
        )
        (text as TextView).setTextColor(
            ContextCompat.getColor(
                context,
                if (selected) TokensR.color.terra_color_secondary else TokensR.color.terra_color_text_secondary,
            ),
        )
    }
}
enum class TerraNavbarItem {
    HOME,
    ACTIVITY,
    NOTIFICATION,
    NONE,
}
