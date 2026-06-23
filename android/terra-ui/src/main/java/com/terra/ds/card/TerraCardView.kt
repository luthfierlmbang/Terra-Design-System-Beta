package com.terra.ds.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.terra.ds.R
import com.terra.tokens.R as TokensR
import com.terra.ds.button.TerraButtonType
import com.terra.ds.button.TerraButtonView

class TerraCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val rootContainer: LinearLayout
    private val menuSection: LinearLayout
    private val customerSection: LinearLayout
    private val informationSection: LinearLayout
    private val buttonSection: FrameLayout

    private val menuTitleView: TextView
    private val menuSubtitleView: TextView
    private val customerNameView: TextView
    private val customerMetaView: TextView
    private val customerSecondaryTextView: TextView
    private val primaryInfoRow: LinearLayout
    private val secondaryInfoRow: LinearLayout
    private val primaryInfoLabelView: TextView
    private val primaryInfoValueView: TextView
    private val secondaryInfoLabelView: TextView
    private val secondaryInfoValueView: TextView
    private val buttonView: TerraButtonView

    var terraCardVariant: TerraCardVariant = TerraCardVariant.MENU
        set(value) {
            field = value
            applyVariant()
        }

    var terraCardMenuTitle: String = "Menu Title"
        set(value) {
            field = value
            menuTitleView.text = value
        }

    var terraCardMenuSubtitle: String = ""
        set(value) {
            field = value
            menuSubtitleView.text = value
            menuSubtitleView.visibility = if (value.isBlank()) View.GONE else View.VISIBLE
        }

    var terraCardCustomerName: String = ""
        set(value) {
            field = value
            customerNameView.text = value
            customerNameView.visibility = if (value.isBlank()) View.GONE else View.VISIBLE
        }

    var terraCardCustomerMeta: String = ""
        set(value) {
            field = value
            customerMetaView.text = value
            customerMetaView.visibility = if (value.isBlank()) View.GONE else View.VISIBLE
        }

    var terraCardCustomerSecondaryText: String = ""
        set(value) {
            field = value
            customerSecondaryTextView.text = value
            customerSecondaryTextView.visibility = if (value.isBlank()) View.GONE else View.VISIBLE
        }

    var terraCardInfoPrimaryLabel: String = ""
        set(value) {
            field = value
            primaryInfoLabelView.text = value
            bindInfoRows()
        }

    var terraCardInfoPrimaryValue: String = ""
        set(value) {
            field = value
            primaryInfoValueView.text = value
            bindInfoRows()
        }

    var terraCardInfoSecondaryLabel: String = ""
        set(value) {
            field = value
            secondaryInfoLabelView.text = value
            bindInfoRows()
        }

    var terraCardInfoSecondaryValue: String = ""
        set(value) {
            field = value
            secondaryInfoValueView.text = value
            bindInfoRows()
        }

    var terraCardClickable: Boolean = false
        set(value) {
            field = value
            updateCardClickability()
        }

    var terraCardButtonText: String = "Action"
        set(value) {
            field = value
            buttonView.text = value
            updateButtonContentVisibility()
        }

    var terraCardButtonVisible: Boolean = true
        set(value) {
            field = value
            applyVariant()
        }

    var terraCardButtonEnabled: Boolean = true
        set(value) {
            field = value
            buttonView.isEnabled = value
        }

    var terraCardButtonType: TerraButtonType = TerraButtonType.PRIMARY
        set(value) {
            field = value
            buttonView.terraButtonType = value
        }

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.terra_view_card, this, true)

        rootContainer = findViewById(R.id.terraCardRoot)
        menuSection = findViewById(R.id.terraCardMenuSection)
        customerSection = findViewById(R.id.terraCardCustomerSection)
        informationSection = findViewById(R.id.terraCardInformationSection)
        buttonSection = findViewById(R.id.terraCardButtonSection)

        menuTitleView = findViewById(R.id.terraCardMenuTitle)
        menuSubtitleView = findViewById(R.id.terraCardMenuSubtitle)
        customerNameView = findViewById(R.id.terraCardCustomerName)
        customerMetaView = findViewById(R.id.terraCardCustomerMeta)
        customerSecondaryTextView = findViewById(R.id.terraCardCustomerSecondaryText)
        primaryInfoRow = findViewById(R.id.terraCardPrimaryInfoRow)
        secondaryInfoRow = findViewById(R.id.terraCardSecondaryInfoRow)
        primaryInfoLabelView = findViewById(R.id.terraCardInfoPrimaryLabel)
        primaryInfoValueView = findViewById(R.id.terraCardInfoPrimaryValue)
        secondaryInfoLabelView = findViewById(R.id.terraCardInfoSecondaryLabel)
        secondaryInfoValueView = findViewById(R.id.terraCardInfoSecondaryValue)
        buttonView = findViewById(R.id.terraCardButton)

        parseAttributes(attrs)
        bindContent()
        applyVariant()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraCardView)
        terraCardVariant = TerraCardVariant.fromAttrValue(
            typedArray.getInt(R.styleable.TerraCardView_terraCardVariant, 0),
        )
        terraCardMenuTitle = typedArray.getString(R.styleable.TerraCardView_terraCardMenuTitle) ?: terraCardMenuTitle
        terraCardMenuSubtitle = typedArray.getString(R.styleable.TerraCardView_terraCardMenuSubtitle) ?: terraCardMenuSubtitle
        terraCardCustomerName = typedArray.getString(R.styleable.TerraCardView_terraCardCustomerName) ?: terraCardCustomerName
        terraCardCustomerMeta = typedArray.getString(R.styleable.TerraCardView_terraCardCustomerMeta) ?: terraCardCustomerMeta
        terraCardCustomerSecondaryText = typedArray.getString(R.styleable.TerraCardView_terraCardCustomerSecondaryText) ?: terraCardCustomerSecondaryText
        terraCardInfoPrimaryLabel = typedArray.getString(R.styleable.TerraCardView_terraCardInfoPrimaryLabel) ?: terraCardInfoPrimaryLabel
        terraCardInfoPrimaryValue = typedArray.getString(R.styleable.TerraCardView_terraCardInfoPrimaryValue) ?: terraCardInfoPrimaryValue
        terraCardInfoSecondaryLabel = typedArray.getString(R.styleable.TerraCardView_terraCardInfoSecondaryLabel) ?: terraCardInfoSecondaryLabel
        terraCardInfoSecondaryValue = typedArray.getString(R.styleable.TerraCardView_terraCardInfoSecondaryValue) ?: terraCardInfoSecondaryValue
        terraCardClickable = typedArray.getBoolean(R.styleable.TerraCardView_terraCardClickable, false)
        terraCardButtonText = typedArray.getString(R.styleable.TerraCardView_terraCardButtonText) ?: terraCardButtonText
        terraCardButtonVisible = typedArray.getBoolean(R.styleable.TerraCardView_terraCardButtonVisible, true)
        terraCardButtonEnabled = typedArray.getBoolean(R.styleable.TerraCardView_terraCardButtonEnabled, true)
        terraCardButtonType = when (typedArray.getInt(R.styleable.TerraCardView_terraCardButtonType, 0)) {
            1 -> TerraButtonType.SECONDARY
            2 -> TerraButtonType.OUTLINED_PRIMARY
            3 -> TerraButtonType.OUTLINED_SECONDARY
            4 -> TerraButtonType.DANGER
            5 -> TerraButtonType.TEXT
            else -> TerraButtonType.PRIMARY
        }
        typedArray.recycle()
    }

    private fun bindContent() {
        menuTitleView.text = terraCardMenuTitle
        menuSubtitleView.text = terraCardMenuSubtitle
        menuSubtitleView.visibility = if (terraCardMenuSubtitle.isBlank()) View.GONE else View.VISIBLE

        customerNameView.text = terraCardCustomerName
        customerNameView.visibility = if (terraCardCustomerName.isBlank()) View.GONE else View.VISIBLE
        customerMetaView.text = terraCardCustomerMeta
        customerMetaView.visibility = if (terraCardCustomerMeta.isBlank()) View.GONE else View.VISIBLE
        customerSecondaryTextView.text = terraCardCustomerSecondaryText
        customerSecondaryTextView.visibility = if (terraCardCustomerSecondaryText.isBlank()) View.GONE else View.VISIBLE

        primaryInfoLabelView.text = terraCardInfoPrimaryLabel
        primaryInfoValueView.text = terraCardInfoPrimaryValue
        secondaryInfoLabelView.text = terraCardInfoSecondaryLabel
        secondaryInfoValueView.text = terraCardInfoSecondaryValue
        buttonView.text = terraCardButtonText
        buttonView.isEnabled = terraCardButtonEnabled
        buttonView.terraButtonType = terraCardButtonType

        bindInfoRows()
        updateButtonContentVisibility()
        updateCardClickability()
        updateSectionSpacing()
    }

    private fun bindInfoRows() {
        primaryInfoRow.visibility = if (terraCardInfoPrimaryLabel.isBlank() && terraCardInfoPrimaryValue.isBlank()) View.GONE else View.VISIBLE
        secondaryInfoRow.visibility = if (terraCardInfoSecondaryLabel.isBlank() && terraCardInfoSecondaryValue.isBlank()) View.GONE else View.VISIBLE
        informationSection.visibility = if (shouldShowInformationSection() && hasInformationContent()) View.VISIBLE else View.GONE
        updateSectionSpacing()
    }

    private fun applyVariant() {
        when (terraCardVariant) {
            TerraCardVariant.MENU -> {
                menuSection.visibility = View.VISIBLE
                customerSection.visibility = View.GONE
                informationSection.visibility = View.GONE
                buttonSection.visibility = View.GONE
            }
            TerraCardVariant.INFORMATION_FULL -> {
                menuSection.visibility = View.GONE
                customerSection.visibility = if (hasCustomerContent()) View.VISIBLE else View.GONE
                informationSection.visibility = if (hasInformationContent()) View.VISIBLE else View.GONE
                buttonSection.visibility = resolveButtonVisibility()
            }
            TerraCardVariant.INFORMATION_INFO_BUTTON -> {
                menuSection.visibility = View.GONE
                customerSection.visibility = View.GONE
                informationSection.visibility = if (hasInformationContent()) View.VISIBLE else View.GONE
                buttonSection.visibility = resolveButtonVisibility()
            }
            TerraCardVariant.INFORMATION_CUSTOMER_ONLY -> {
                menuSection.visibility = View.GONE
                customerSection.visibility = if (hasCustomerContent()) View.VISIBLE else View.GONE
                informationSection.visibility = View.GONE
                buttonSection.visibility = View.GONE
            }
            TerraCardVariant.INFORMATION_CUSTOMER_BUTTON -> {
                menuSection.visibility = View.GONE
                customerSection.visibility = if (hasCustomerContent()) View.VISIBLE else View.GONE
                informationSection.visibility = View.GONE
                buttonSection.visibility = resolveButtonVisibility()
            }
            TerraCardVariant.INFORMATION_INFO_ONLY -> {
                menuSection.visibility = View.GONE
                customerSection.visibility = View.GONE
                informationSection.visibility = if (hasInformationContent()) View.VISIBLE else View.GONE
                buttonSection.visibility = View.GONE
            }
        }

        updateButtonContentVisibility()
        updateCardClickability()
        updateSectionSpacing()
    }

    fun setOnCardButtonClickListener(listener: OnClickListener?) {
        buttonView.setOnClickListener(listener)
    }

    fun setOnCardClickListener(listener: OnClickListener?) {
        rootContainer.setOnClickListener(listener)
        terraCardClickable = listener != null
    }

    private fun resolveButtonVisibility(): Int {
        return if (terraCardButtonVisible && terraCardButtonText.isNotBlank()) View.VISIBLE else View.GONE
    }

    private fun hasCustomerContent(): Boolean {
        return terraCardCustomerName.isNotBlank() ||
            terraCardCustomerMeta.isNotBlank() ||
            terraCardCustomerSecondaryText.isNotBlank()
    }

    private fun hasInformationContent(): Boolean {
        return primaryInfoRow.isVisible || secondaryInfoRow.isVisible ||
            terraCardInfoPrimaryLabel.isNotBlank() ||
            terraCardInfoPrimaryValue.isNotBlank() ||
            terraCardInfoSecondaryLabel.isNotBlank() ||
            terraCardInfoSecondaryValue.isNotBlank()
    }

    private fun shouldShowInformationSection(): Boolean {
        return when (terraCardVariant) {
            TerraCardVariant.INFORMATION_FULL,
            TerraCardVariant.INFORMATION_INFO_BUTTON,
            TerraCardVariant.INFORMATION_INFO_ONLY,
            -> true
            TerraCardVariant.MENU,
            TerraCardVariant.INFORMATION_CUSTOMER_ONLY,
            TerraCardVariant.INFORMATION_CUSTOMER_BUTTON,
            -> false
        }
    }

    private fun updateButtonContentVisibility() {
        buttonSection.visibility = when {
            !supportsButtonSection() -> View.GONE
            terraCardButtonVisible && terraCardButtonText.isNotBlank() -> View.VISIBLE
            else -> View.GONE
        }
    }

    private fun updateCardClickability() {
        rootContainer.isClickable = terraCardClickable
        rootContainer.isFocusable = terraCardClickable
        rootContainer.foreground = if (terraCardClickable) {
            ContextCompat.getDrawable(context, android.R.drawable.list_selector_background)
        } else {
            null
        }
    }

    private fun supportsButtonSection(): Boolean {
        return when (terraCardVariant) {
            TerraCardVariant.INFORMATION_FULL,
            TerraCardVariant.INFORMATION_INFO_BUTTON,
            TerraCardVariant.INFORMATION_CUSTOMER_BUTTON,
            -> true
            TerraCardVariant.MENU,
            TerraCardVariant.INFORMATION_CUSTOMER_ONLY,
            TerraCardVariant.INFORMATION_INFO_ONLY,
            -> false
        }
    }

    private fun updateSectionSpacing() {
        updateTopMargin(customerSection, if (menuSection.isVisible) 12 else 0)
        updateTopMargin(informationSection, if (customerSection.isVisible) 12 else 0)
        updateTopMargin(
            buttonSection,
            if (buttonSection.isVisible && (customerSection.isVisible || informationSection.isVisible)) 16 else 0,
        )
    }

    private fun updateTopMargin(view: View, marginTopDp: Int) {
        val layoutParams = view.layoutParams as? MarginLayoutParams ?: return
        val marginTopPx = (marginTopDp * resources.displayMetrics.density).toInt()
        if (layoutParams.topMargin != marginTopPx) {
            layoutParams.topMargin = marginTopPx
            view.layoutParams = layoutParams
        }
    }
}

enum class TerraCardVariant(val attrValue: Int) {
    MENU(0),
    INFORMATION_FULL(1),
    INFORMATION_INFO_BUTTON(2),
    INFORMATION_CUSTOMER_ONLY(3),
    INFORMATION_CUSTOMER_BUTTON(4),
    INFORMATION_INFO_ONLY(5),
    ;

    companion object {
        fun fromAttrValue(value: Int): TerraCardVariant {
            return entries.firstOrNull { it.attrValue == value } ?: MENU
        }
    }
}
