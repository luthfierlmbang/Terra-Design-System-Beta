package com.terra.ds.feedback

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
import com.terra.ds.button.TerraButtonType
import com.terra.ds.button.TerraButtonView
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

class TerraTimerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val labelView: TextView
    private val valueView: TextView

    var terraTimerLabel: String = "Label"
        set(value) {
            field = value
            labelView.text = value
        }

    var terraTimerValue: String = "00:00:12"
        set(value) {
            field = value
            valueView.text = value
        }

    var terraTimerActive: Boolean = true
        set(value) {
            field = value
            updateAppearance()
        }

    init {
        orientation = VERTICAL
        setPaddingRelative(
            resources.getDimensionPixelSize(R.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_4),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_4),
        )
        setBackgroundResource(R.color.terra_color_teal_050)
        setWillNotDraw(false)

        labelView = AppCompatTextView(context).apply {
            textSize = 12f
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_teal_700))
            gravity = Gravity.CENTER_HORIZONTAL
        }
        addView(labelView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))

        val row = LinearLayout(context).apply {
            orientation = HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        val dot = View(context).apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.terra_color_teal_700))
        }
        val dotSize = resources.getDimensionPixelSize(R.dimen.terra_timer_dot_size)
        row.addView(dot, LayoutParams(dotSize, dotSize).apply {
            marginEnd = resources.getDimensionPixelSize(R.dimen.terra_spacing_4)
        })
        valueView = AppCompatTextView(context).apply {
            textSize = 12f
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_teal_700))
            gravity = Gravity.CENTER_VERTICAL
        }
        row.addView(valueView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
        addView(row, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))

        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraTimerView)
        terraTimerLabel = typedArray.getString(R.styleable.TerraTimerView_terraTimerLabel) ?: terraTimerLabel
        terraTimerValue = typedArray.getString(R.styleable.TerraTimerView_terraTimerValue) ?: terraTimerValue
        terraTimerActive = typedArray.getBoolean(R.styleable.TerraTimerView_terraTimerActive, true)
        typedArray.recycle()
    }

    private fun bind() {
        labelView.text = terraTimerLabel
        valueView.text = terraTimerValue
        updateAppearance()
    }

    private fun updateAppearance() {
        val backgroundColor = if (terraTimerActive) R.color.terra_color_teal_050 else android.R.color.transparent
        setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
    }
}

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
            resources.getDimensionPixelSize(R.dimen.terra_spacing_42),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_36),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_42),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_36),
        )
        setBackgroundColor(ContextCompat.getColor(context, R.color.terra_color_neutral_10))

        progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleLarge)
        messageView = AppCompatTextView(context).apply {
            textSize = 14f
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_80))
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

class TerraPageControlView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    var terraPageCount: Int = 4
        set(value) {
            field = value.coerceAtLeast(1)
            render()
        }

    var terraSelectedPage: Int = 0
        set(value) {
            field = value.coerceIn(0, terraPageCount - 1)
            render()
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.START
        parseAttributes(attrs)
        render()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraPageControlView)
        terraPageCount = typedArray.getInt(R.styleable.TerraPageControlView_terraPageCount, terraPageCount)
        terraSelectedPage = typedArray.getInt(R.styleable.TerraPageControlView_terraSelectedPage, terraSelectedPage)
        typedArray.recycle()
    }

    private fun render() {
        removeAllViews()
        repeat(terraPageCount) { index ->
            val selected = index == terraSelectedPage
            val dot = View(context)
            val width = if (selected) resources.getDimensionPixelSize(R.dimen.terra_page_control_selected_width) else resources.getDimensionPixelSize(R.dimen.terra_page_control_dot_size)
            val height = resources.getDimensionPixelSize(R.dimen.terra_page_control_dot_size)
            dot.setBackgroundColor(ContextCompat.getColor(context, R.color.terra_color_orange_500))
            addView(dot, LayoutParams(width, height).apply {
                marginEnd = if (index == terraPageCount - 1) 0 else resources.getDimensionPixelSize(R.dimen.terra_spacing_4)
            })
        }
    }
}

class TerraProgressBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    var terraProgressBarType: TerraProgressBarType = TerraProgressBarType.BASE
        set(value) {
            field = value
            render()
        }

    init {
        parseAttributes(attrs)
        render()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraProgressBarView)
        terraProgressBarType = when (typedArray.getInt(R.styleable.TerraProgressBarView_terraProgressBarType, 0)) {
            1 -> TerraProgressBarType.BAR_1
            2 -> TerraProgressBarType.BAR_2
            else -> TerraProgressBarType.BASE
        }
        typedArray.recycle()
    }

    private fun render() {
        removeAllViews()
        val base = View(context).apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.terra_color_neutral_30))
        }
        val bar = View(context).apply {
            setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    when (terraProgressBarType) {
                        TerraProgressBarType.BASE -> R.color.terra_color_neutral_30
                        TerraProgressBarType.BAR_1 -> R.color.terra_color_blue_500
                        TerraProgressBarType.BAR_2 -> R.color.terra_color_teal_700
                    },
                ),
            )
        }
        val height = resources.getDimensionPixelSize(
            when (terraProgressBarType) {
                TerraProgressBarType.BASE -> R.dimen.terra_progress_height_small
                else -> R.dimen.terra_progress_height_medium
            },
        )
        addView(base, LayoutParams(LayoutParams.MATCH_PARENT, height).apply { gravity = Gravity.CENTER_VERTICAL })
        addView(bar, LayoutParams(LayoutParams.MATCH_PARENT, height))
    }
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
        repeat(terraStepperCount) { index ->
            val state = when {
                index == terraStepperErrorStep -> StepperState.ERROR
                index < terraStepperCurrentStep -> StepperState.COMPLETE
                index == terraStepperCurrentStep -> StepperState.PROGRESS
                else -> StepperState.INACTIVE
            }
            addView(stepDot(state))
            if (index < terraStepperCount - 1) {
                addView(stepLine(state))
            }
        }
    }

    private fun stepDot(state: StepperState): View {
        return View(context).apply {
            val size = resources.getDimensionPixelSize(R.dimen.terra_stepper_bullet_size)
            layoutParams = LayoutParams(size, size)
            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(ContextCompat.getColor(context, when (state) {
                    StepperState.COMPLETE -> R.color.terra_color_green_500
                    StepperState.PROGRESS -> R.color.terra_color_teal_700
                    StepperState.ERROR -> R.color.terra_color_red_500
                    StepperState.INACTIVE -> R.color.terra_color_neutral_40
                }))
            }
        }
    }

    private fun stepLine(state: StepperState): View {
        return View(context).apply {
            layoutParams = LayoutParams(resources.getDimensionPixelSize(R.dimen.terra_spacing_24), resources.getDimensionPixelSize(R.dimen.terra_spacing_4)).apply {
                marginStart = resources.getDimensionPixelSize(R.dimen.terra_spacing_4)
                marginEnd = resources.getDimensionPixelSize(R.dimen.terra_spacing_4)
            }
            setBackgroundColor(ContextCompat.getColor(context, when (state) {
                StepperState.COMPLETE -> R.color.terra_color_green_500
                StepperState.PROGRESS -> R.color.terra_color_teal_700
                StepperState.ERROR -> R.color.terra_color_red_500
                StepperState.INACTIVE -> R.color.terra_color_neutral_40
            }))
        }
    }
}

class TerraTickerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val iconView: TerraIconView
    private val contentLayout: LinearLayout
    private val titleView: TextView
    private val messageView: TextView

    var terraTickerTone: TerraTickerTone = TerraTickerTone.INFORMATION
        set(value) {
            field = value
            updateAppearance()
        }

    var terraTickerDetailed: Boolean = false
        set(value) {
            field = value
            updateAppearance()
        }

    var terraTickerTitle: String = "Information Title Text"
        set(value) {
            field = value
            titleView.text = value
        }

    var terraTickerMessage: String = "Warning information maximum 1 line warning information maximum 1 line"
        set(value) {
            field = value
            messageView.text = value
        }

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        minimumHeight = resources.getDimensionPixelSize(R.dimen.terra_ticker_min_height)
        setPaddingRelative(
            resources.getDimensionPixelSize(R.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_8),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_24),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_8),
        )

        iconView = TerraIconView(context).apply {
            terraIconCategory = TerraIconCategory.INFO
        }
        contentLayout = LinearLayout(context).apply {
            orientation = VERTICAL
        }
        titleView = AppCompatTextView(context).apply {
            textSize = 14f
            setTypeface(typeface, Typeface.BOLD)
            visibility = View.GONE
        }
        messageView = AppCompatTextView(context).apply {
            textSize = 12f
        }

        contentLayout.addView(titleView)
        contentLayout.addView(messageView)
        addView(iconView)
        addView(contentLayout, LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f).apply {
            marginStart = resources.getDimensionPixelSize(R.dimen.terra_spacing_8)
        })

        parseAttributes(attrs)
        bind()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraTickerView)
        terraTickerTone = when (typedArray.getInt(R.styleable.TerraTickerView_terraTickerTone, 0)) {
            1 -> TerraTickerTone.WARNING
            2 -> TerraTickerTone.ERROR
            else -> TerraTickerTone.INFORMATION
        }
        terraTickerDetailed = typedArray.getBoolean(R.styleable.TerraTickerView_terraTickerDetailed, false)
        terraTickerTitle = typedArray.getString(R.styleable.TerraTickerView_terraTickerTitle) ?: terraTickerTitle
        terraTickerMessage = typedArray.getString(R.styleable.TerraTickerView_terraTickerMessage) ?: terraTickerMessage
        typedArray.recycle()
    }

    private fun bind() {
        titleView.text = terraTickerTitle
        messageView.text = terraTickerMessage
        updateAppearance()
    }

    private fun updateAppearance() {
        titleView.visibility = if (terraTickerDetailed) View.VISIBLE else View.GONE
        gravity = if (terraTickerDetailed) Gravity.TOP else Gravity.CENTER_VERTICAL
        val palette = tickerPalette(terraTickerTone)
        background = roundedFillBackground(palette.backgroundColor)
        iconView.terraIconName = when (terraTickerTone) {
            TerraTickerTone.INFORMATION -> TerraIconName.INFORMATION
            TerraTickerTone.WARNING -> TerraIconName.EXCLAMATION
            TerraTickerTone.ERROR -> TerraIconName.EXCLAMATION
        }
        iconView.setIconTint(ContextCompat.getColor(context, palette.textColor))
        titleView.setTextColor(ContextCompat.getColor(context, palette.textColor))
        messageView.setTextColor(ContextCompat.getColor(context, palette.textColor))
        messageView.maxLines = if (terraTickerDetailed) 2 else 1
    }
}

class TerraEmptyStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val illustrationView: ImageView
    private val titleView: TextView
    private val messageView: TextView
    private val buttonView: TerraButtonView

    var terraEmptyStateVariant: TerraEmptyStateVariant = TerraEmptyStateVariant.SEARCH_NOT_FOUND
        set(value) {
            field = value
            applyVariant()
        }

    var terraEmptyStateButtonVisible: Boolean = true
        set(value) {
            field = value
            buttonView.visibility = if (value) View.VISIBLE else View.GONE
        }

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        setPaddingRelative(
            resources.getDimensionPixelSize(R.dimen.terra_spacing_32),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_32),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_32),
            resources.getDimensionPixelSize(R.dimen.terra_spacing_32),
        )

        illustrationView = ImageView(context).apply {
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setImageResource(R.drawable.terra_ic_placeholder)
        }
        titleView = AppCompatTextView(context).apply {
            textSize = 20f
            gravity = Gravity.CENTER
            setTypeface(typeface, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_90))
        }
        messageView = AppCompatTextView(context).apply {
            textSize = 16f
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(context, R.color.terra_color_neutral_90))
        }
        buttonView = TerraButtonView(context).apply {
            text = "Coba Lagi"
            terraButtonType = TerraButtonType.PRIMARY
        }

        addView(illustrationView, LayoutParams(
            resources.getDimensionPixelSize(R.dimen.terra_empty_state_illustration_width),
            resources.getDimensionPixelSize(R.dimen.terra_empty_state_illustration_height),
        ))
        addView(titleView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            topMargin = resources.getDimensionPixelSize(R.dimen.terra_spacing_24)
        })
        addView(messageView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            topMargin = resources.getDimensionPixelSize(R.dimen.terra_spacing_24)
        })
        addView(buttonView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            topMargin = resources.getDimensionPixelSize(R.dimen.terra_spacing_24)
        })

        parseAttributes(attrs)
        applyVariant()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TerraEmptyStateView)
        terraEmptyStateVariant = when (typedArray.getInt(R.styleable.TerraEmptyStateView_terraEmptyStateVariant, 0)) {
            1 -> TerraEmptyStateVariant.HAPPY_BIRTHDAY
            2 -> TerraEmptyStateVariant.CONNECTION_ERROR
            3 -> TerraEmptyStateVariant.UNDER_MAINTENANCE
            4 -> TerraEmptyStateVariant.DEFAULT_SEARCH_STATE
            5 -> TerraEmptyStateVariant.PAGE_NOT_FOUND
            6 -> TerraEmptyStateVariant.SERVER_BUSY
            7 -> TerraEmptyStateVariant.DATA_NOT_FOUND
            8 -> TerraEmptyStateVariant.TIME_OUT
            9 -> TerraEmptyStateVariant.SUCCESS_SUBMIT
            else -> TerraEmptyStateVariant.SEARCH_NOT_FOUND
        }
        terraEmptyStateButtonVisible = typedArray.getBoolean(R.styleable.TerraEmptyStateView_terraEmptyStateButtonVisible, true)
        typedArray.recycle()
    }

    private fun applyVariant() {
        val config = emptyStateConfig(terraEmptyStateVariant)
        titleView.text = config.title
        messageView.text = config.message
        buttonView.text = config.buttonLabel
        buttonView.visibility = if (terraEmptyStateButtonVisible && config.buttonVisible) View.VISIBLE else View.GONE
        buttonView.terraButtonType = if (config.primaryButton) TerraButtonType.PRIMARY else TerraButtonType.SECONDARY
        illustrationView.setColorFilter(ContextCompat.getColor(context, config.accentColor))
    }
}

private enum class StepperState {
    INACTIVE,
    PROGRESS,
    COMPLETE,
    ERROR,
}

enum class TerraProgressBarType {
    BASE,
    BAR_1,
    BAR_2,
}

enum class TerraTickerTone {
    INFORMATION,
    WARNING,
    ERROR,
}

enum class TerraEmptyStateVariant {
    SEARCH_NOT_FOUND,
    HAPPY_BIRTHDAY,
    CONNECTION_ERROR,
    UNDER_MAINTENANCE,
    DEFAULT_SEARCH_STATE,
    PAGE_NOT_FOUND,
    SERVER_BUSY,
    DATA_NOT_FOUND,
    TIME_OUT,
    SUCCESS_SUBMIT,
}

private data class TerraTickerPalette(
    val backgroundColor: Int,
    val textColor: Int,
)

private data class TerraEmptyStateConfig(
    val title: String,
    val message: String,
    val buttonLabel: String,
    val buttonVisible: Boolean,
    val primaryButton: Boolean,
    val accentColor: Int,
)

private fun View.roundedFillBackground(fillColor: Int): GradientDrawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = resources.getDimension(R.dimen.terra_radius_medium)
        setColor(ContextCompat.getColor(context, fillColor))
    }
}

private fun tickerPalette(tone: TerraTickerTone): TerraTickerPalette {
    return when (tone) {
        TerraTickerTone.INFORMATION -> TerraTickerPalette(R.color.terra_color_blue_100, R.color.terra_color_blue_500)
        TerraTickerTone.WARNING -> TerraTickerPalette(R.color.terra_color_orange_100, R.color.terra_color_orange_600)
        TerraTickerTone.ERROR -> TerraTickerPalette(R.color.terra_color_red_050, R.color.terra_color_red_500)
    }
}

private fun emptyStateConfig(variant: TerraEmptyStateVariant): TerraEmptyStateConfig {
    return when (variant) {
        TerraEmptyStateVariant.SEARCH_NOT_FOUND -> TerraEmptyStateConfig(
            title = "Pencarian Tidak Ditemukan",
            message = "Kami tidak menemukan kata kunci yang kamu cari, coba cari dengan kata kunci lainnya.",
            buttonLabel = "Coba Lagi",
            buttonVisible = true,
            primaryButton = true,
            accentColor = R.color.terra_color_teal_700,
        )
        TerraEmptyStateVariant.HAPPY_BIRTHDAY -> TerraEmptyStateConfig(
            title = "Selamat Ulang Tahun!",
            message = "Kami mengucapkan selamat ulang tahun untukmu.",
            buttonLabel = "Tutup",
            buttonVisible = false,
            primaryButton = false,
            accentColor = R.color.terra_color_orange_500,
        )
        TerraEmptyStateVariant.CONNECTION_ERROR -> TerraEmptyStateConfig(
            title = "Wah, Jaringan Internet Kamu Terganggu",
            message = "Coba cek jaringan internet kamu apakah sedang ada gangguan atau cek pulsa. Jika sudah, silahkan coba lagi.",
            buttonLabel = "Coba Lagi",
            buttonVisible = true,
            primaryButton = true,
            accentColor = R.color.terra_color_red_500,
        )
        TerraEmptyStateVariant.UNDER_MAINTENANCE -> TerraEmptyStateConfig(
            title = "Maaf, Sistem Sedang Dalam Perbaikan",
            message = "Sistem sedang dalam perbaikan, kami akan memperbaikinya secepatnya.",
            buttonLabel = "Tutup",
            buttonVisible = false,
            primaryButton = false,
            accentColor = R.color.terra_color_yellow_500,
        )
        TerraEmptyStateVariant.DEFAULT_SEARCH_STATE -> TerraEmptyStateConfig(
            title = "Belum Ada Pencarian",
            message = "Kamu belum melakukan pencarian. Silahkan isi kolom diatas untuk melakukan pencarian.",
            buttonLabel = "Coba Lagi",
            buttonVisible = true,
            primaryButton = true,
            accentColor = R.color.terra_color_teal_700,
        )
        TerraEmptyStateVariant.PAGE_NOT_FOUND -> TerraEmptyStateConfig(
            title = "Halaman Tidak Ditemukan",
            message = "Halaman yang kamu cari tidak ditemukan, silahkan kembali ke halaman sebelumnya.",
            buttonLabel = "Kembali",
            buttonVisible = true,
            primaryButton = true,
            accentColor = R.color.terra_color_blue_500,
        )
        TerraEmptyStateVariant.SERVER_BUSY -> TerraEmptyStateConfig(
            title = "Internal Server Sedang Sibuk",
            message = "Kami kesulitan untuk koneksikan menuju server, coba cek jaringan kamu atau coba lagi.",
            buttonLabel = "Coba Lagi",
            buttonVisible = true,
            primaryButton = true,
            accentColor = R.color.terra_color_orange_600,
        )
        TerraEmptyStateVariant.DATA_NOT_FOUND -> TerraEmptyStateConfig(
            title = "Data Tidak Ditemukan",
            message = "Kami tidak menemukan data yang kamu cari, coba cari kembali.",
            buttonLabel = "Kembali",
            buttonVisible = true,
            primaryButton = true,
            accentColor = R.color.terra_color_teal_700,
        )
        TerraEmptyStateVariant.TIME_OUT -> TerraEmptyStateConfig(
            title = "Wah Waktu Kamu Habis",
            message = "Waktu untuk login kamu telah habis, silahkan coba kembali untuk login.",
            buttonLabel = "Coba Lagi",
            buttonVisible = true,
            primaryButton = true,
            accentColor = R.color.terra_color_red_500,
        )
        TerraEmptyStateVariant.SUCCESS_SUBMIT -> TerraEmptyStateConfig(
            title = "Data Sudah Dikirim",
            message = "Data yang Anda ajukan sudah berhasil di submit, silahkan meneruskan untuk proses selanjutnya.",
            buttonLabel = "Selesai",
            buttonVisible = true,
            primaryButton = true,
            accentColor = R.color.terra_color_green_500,
        )
    }
}
