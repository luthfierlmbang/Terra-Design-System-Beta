package com.oasis.designsystem.tabs

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout
import com.oasis.designsystem.R

class OasisTabs @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : TabLayout(context, attrs, defStyleAttr) {

    init {
        parseAttributes(attrs)
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OasisTabs)
        val selectedIndex = typedArray.getInt(R.styleable.OasisTabs_oasisTabsSelectedIndex, 0)
        typedArray.recycle()
        post {
            getTabAt(selectedIndex)?.select()
        }
    }

    fun setTabs(items: List<String>) {
        removeAllTabs()
        items.forEach { addTab(newTab().setText(it)) }
    }
}