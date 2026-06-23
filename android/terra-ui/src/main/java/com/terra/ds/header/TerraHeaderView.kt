package com.terra.ds.header

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.terra.ds.R
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView
import com.terra.tokens.R as TokensR

class TerraHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val statusBar: LinearLayout
    private val mainContainer: LinearLayout
    private val centerContent: LinearLayout
    private val textStack: LinearLayout
    private val titleRow: LinearLayout
    private val titleView: TextView
    private val subtitleView: TextView
    private val pageIdentifierView: TextView
    private val logoView: ImageView
    private val leadingContainer: FrameLayout
    private val actionsContainer: LinearLayout
    private val actionOneContainer: FrameLayout
    private val actionTwoContainer: FrameLayout

    var terraHeaderVariant: TerraHeaderVariant = TerraHeaderVariant.MAIN
        set(value) {
            field = value
            bind()
        }

    var terraHeaderLeading: TerraHeaderLeading = TerraHeaderLeading.ICON
        set(value) {
            field = value
            bindLeading()
            bindLayout()
        }

    var terraHeaderTitle: String = "Title"
        set(value) {
            field = value
            titleView.text = value
        }

    var terraHeaderSubtitleText: String? = null
        set(value) {
            field = value
            bindSubtitle()
        }

    var terraHeaderIdentifierText: String? = null
        set(value) {
            field = value
            bindIdentifier()
        }

    var terraHeaderModuleName: String = "Put Main Module name"
        set(value) {
            field = value
            if (terraHeaderIdentifierText.isNullOrBlank()) {
                bindIdentifier()
            }
        }

    var terraHeaderApkName: String = "Put APK Name"
        set(value) {
            field = value
            if (terraHeaderIdentifierText.isNullOrBlank()) {
                bindIdentifier()
            }
        }

    var terraHeaderShowIdentifier: Boolean = true
        set(value) {
            field = value
            bindIdentifier()
        }

    var terraHeaderShowActionOne: Boolean = true
        set(value) {
            field = value
            bindActions()
        }

    var terraHeaderShowActionTwo: Boolean = true
        set(value) {
            field = value
            bindActions()
        }

    var terraHeaderShowStatusBar: Boolean = true
        set(value) {
            field = value
            statusBar.visibility = if (value) View.VISIBLE else View.GONE
        }

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.terra_view_header, this, true)

        statusBar = findViewById(R.id.terraHeaderStatusBar)
        mainContainer = findViewById(R.id.terraHeaderMain)
        centerContent = findViewById(R.id.terraHeaderCenterContent)
        textStack = findViewById(R.id.terraHeaderTextStack)
        titleRow = findViewById(R.id.terraHeaderTitleRow)
        titleView = findViewById(R.id.terraHeaderTitle)
        subtitleView = findViewById(R.id.terraHeaderSubtitle)
        pageIdentifierView = findViewById(R.id.terraHeaderPageIdentifier)
        logoView = findViewById(R.id.terraHeaderLogo)
        leadingContainer = findViewById(R.id.terraHeaderLeadingContainer)
        actionsContainer = findViewById(R.id.terraHeaderActionsContainer)
        actionOneContainer = findViewById(R.id.terraHeaderActionOneContainer)
        actionTwoContainer = findViewById(R.id.terraHeaderActionTwoContainer)

        parseAttributes(attrs)
        bind()
    }

    fun setVariant(variant: TerraHeaderVariant) {
        terraHeaderVariant = variant
    }

    fun setLeading(leading: TerraHeaderLeading) {
        terraHeaderLeading = leading
    }

    fun setTitle(title: String) {
        terraHeaderTitle = title
    }

    fun setSubtitle(subtitle: String?) {
        terraHeaderSubtitleText = subtitle
    }

    fun setIdentifier(identifier: String?) {
        terraHeaderIdentifierText = identifier
    }

    fun setIcon(view: View) {
        terraHeaderLeading = TerraHeaderLeading.ICON
        setContainerContent(leadingContainer, view)
        bindLayout()
    }

    fun setActionOne(view: View?) {
        setContainerContent(actionOneContainer, view ?: createDefaultActionIcon(TerraIconName.HOME))
        terraHeaderShowActionOne = view != null || terraHeaderShowActionOne
        bindActions()
    }

    fun setActionTwo(view: View?) {
        setContainerContent(actionTwoContainer, view ?: createDefaultActionIcon(TerraIconName.NOTIFICATION))
        terraHeaderShowActionTwo = view != null || terraHeaderShowActionTwo
        bindActions()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraHeaderView)
        terraHeaderVariant = TerraHeaderVariant.fromAttrValue(
            typedArray.getInt(R.styleable.TerraHeaderView_terraHeaderVariant, TerraHeaderVariant.MAIN.attrValue),
        )
        terraHeaderLeading = TerraHeaderLeading.fromAttrValue(
            typedArray.getInt(R.styleable.TerraHeaderView_terraHeaderLeading, TerraHeaderLeading.ICON.attrValue),
        )
        terraHeaderTitle = typedArray.getString(R.styleable.TerraHeaderView_terraHeaderTitleText)
            ?: typedArray.getString(R.styleable.TerraHeaderView_terraHeaderTitle)
            ?: terraHeaderTitle
        terraHeaderSubtitleText = typedArray.getString(R.styleable.TerraHeaderView_terraHeaderSubtitleText)
        terraHeaderIdentifierText = typedArray.getString(R.styleable.TerraHeaderView_terraHeaderIdentifierText)
        terraHeaderModuleName = typedArray.getString(R.styleable.TerraHeaderView_terraHeaderModuleName) ?: terraHeaderModuleName
        terraHeaderApkName = typedArray.getString(R.styleable.TerraHeaderView_terraHeaderApkName) ?: terraHeaderApkName
        terraHeaderShowIdentifier = typedArray.getBoolean(R.styleable.TerraHeaderView_terraHeaderShowIdentifier, true)
        terraHeaderShowActionOne = typedArray.getBoolean(R.styleable.TerraHeaderView_terraHeaderShowActionOne, true)
        terraHeaderShowActionTwo = typedArray.getBoolean(R.styleable.TerraHeaderView_terraHeaderShowActionTwo, true)
        terraHeaderShowStatusBar = typedArray.getBoolean(R.styleable.TerraHeaderView_terraHeaderShowStatusBar, true)
        typedArray.recycle()
    }

    private fun bind() {
        titleView.text = terraHeaderTitle
        statusBar.visibility = if (terraHeaderShowStatusBar) View.VISIBLE else View.GONE
        bindLeading()
        bindSubtitle()
        bindIdentifier()
        bindActions()
        bindLayout()
    }

    private fun bindLayout() {
        val isMain = terraHeaderVariant == TerraHeaderVariant.MAIN
        logoView.visibility = if (isMain) View.VISIBLE else View.GONE
        textStack.visibility = if (isMain) View.GONE else View.VISIBLE
        centerContent.gravity = if (isMain) android.view.Gravity.CENTER else android.view.Gravity.NO_GRAVITY
        titleRow.setPadding(0, 0, 0, 0)
        val leadingVisible = leadingContainer.visibility == View.VISIBLE
        val actionVisible = actionsContainer.visibility == View.VISIBLE
        mainContainer.gravity = if (isMain) android.view.Gravity.CENTER_VERTICAL else android.view.Gravity.CENTER_VERTICAL
        if (isMain) {
            titleView.textAlignment = TEXT_ALIGNMENT_VIEW_START
        } else {
            titleView.textAlignment = TEXT_ALIGNMENT_VIEW_START
        }
        if (!leadingVisible && !actionVisible) {
            centerContent.setPadding(0, 0, 0, 0)
        }
    }

    private fun bindLeading() {
        when (terraHeaderLeading) {
            TerraHeaderLeading.NONE -> {
                leadingContainer.removeAllViews()
                leadingContainer.visibility = View.INVISIBLE
            }
            TerraHeaderLeading.BACK -> {
                leadingContainer.visibility = View.VISIBLE
                setContainerContent(leadingContainer, createNavigationIcon(TerraIconName.CHEVRON_LEFT))
            }
            TerraHeaderLeading.MENU -> {
                leadingContainer.visibility = View.VISIBLE
                setContainerContent(leadingContainer, createNavigationIcon(TerraIconName.HAMBURGER_MENU))
            }
            TerraHeaderLeading.ICON -> {
                leadingContainer.visibility = View.VISIBLE
                if (leadingContainer.childCount == 0) {
                    setContainerContent(leadingContainer, createSystemIcon(TerraIconName.PLACEHOLDER))
                }
            }
        }
    }

    private fun bindSubtitle() {
        val subtitle = terraHeaderSubtitleText?.takeIf { it.isNotBlank() }
        subtitleView.text = subtitle
        subtitleView.visibility = if (subtitle == null || terraHeaderVariant == TerraHeaderVariant.MAIN) View.GONE else View.VISIBLE
    }

    private fun bindIdentifier() {
        val identifier = terraHeaderIdentifierText?.takeIf { it.isNotBlank() } ?: buildLegacyIdentifier()
        pageIdentifierView.text = identifier
        val visible = terraHeaderShowIdentifier && terraHeaderVariant == TerraHeaderVariant.SECONDARY && identifier.isNotBlank()
        pageIdentifierView.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun bindActions() {
        ensureDefaultActionIcons()
        actionOneContainer.visibility = if (terraHeaderShowActionOne) View.VISIBLE else View.GONE
        actionTwoContainer.visibility = if (terraHeaderShowActionTwo) View.VISIBLE else View.GONE
        val anyVisible = terraHeaderShowActionOne || terraHeaderShowActionTwo
        actionsContainer.visibility = if (anyVisible) View.VISIBLE else View.GONE
    }

    private fun ensureDefaultActionIcons() {
        if (actionOneContainer.childCount == 0) {
            setContainerContent(actionOneContainer, createDefaultActionIcon(TerraIconName.HOME))
        }
        if (actionTwoContainer.childCount == 0) {
            setContainerContent(actionTwoContainer, createDefaultActionIcon(TerraIconName.NOTIFICATION))
        }
        if (logoView.drawable == null) {
            logoView.setImageResource(R.drawable.terra_header_logo)
        }
    }

    private fun buildLegacyIdentifier(): String {
        val module = terraHeaderModuleName.trim()
        val apk = terraHeaderApkName.trim()
        return when {
            module.isNotEmpty() && apk.isNotEmpty() -> "$module | $apk"
            module.isNotEmpty() -> module
            apk.isNotEmpty() -> apk
            else -> ""
        }
    }

    private fun createDefaultActionIcon(name: TerraIconName): TerraIconView {
        val category = when (name) {
            TerraIconName.HOME, TerraIconName.NOTIFICATION, TerraIconName.SCOOTER -> TerraIconCategory.SYSTEM
            else -> TerraIconCategory.ACTION
        }
        return TerraIconView(context).apply {
            terraIconCategory = category
            terraIconName = name
            setIconTint(ContextCompat.getColor(context, TokensR.color.terra_color_text_on_primary))
            layoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
    }

    private fun createNavigationIcon(name: TerraIconName): TerraIconView {
        return TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.NAVIGATION
            terraIconName = name
            setIconTint(ContextCompat.getColor(context, TokensR.color.terra_color_text_on_primary))
            layoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
    }

    private fun createSystemIcon(name: TerraIconName): TerraIconView {
        return TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.SYSTEM
            terraIconName = name
            setIconTint(ContextCompat.getColor(context, TokensR.color.terra_color_text_on_primary))
            layoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
    }

    private fun setContainerContent(container: FrameLayout, view: View) {
        container.removeAllViews()
        container.addView(view)
    }
}

enum class TerraHeaderVariant(val attrValue: Int) {
    MAIN(0),
    SECONDARY(1),
    ;

    companion object {
        fun fromAttrValue(value: Int): TerraHeaderVariant {
            return entries.firstOrNull { it.attrValue == value } ?: MAIN
        }
    }
}

enum class TerraHeaderLeading(val attrValue: Int) {
    NONE(0),
    BACK(1),
    MENU(2),
    ICON(3),
    ;

    companion object {
        fun fromAttrValue(value: Int): TerraHeaderLeading {
            return entries.firstOrNull { it.attrValue == value } ?: ICON
        }
    }
}
