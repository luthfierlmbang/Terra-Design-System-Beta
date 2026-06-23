package com.terra.ds.emptystate

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
import com.terra.ds.internal.applyTerraTextAppearance
import com.terra.ds.R
import com.terra.ds.ticker.TerraTickerTone
import com.terra.tokens.R as TokensR
import com.terra.ds.button.TerraButtonType
import com.terra.ds.button.TerraButtonView
import com.terra.ds.icon.TerraIconCategory
import com.terra.ds.icon.TerraIconName
import com.terra.ds.icon.TerraIconView

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
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_32),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_32),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_32),
            resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_32),
        )

        illustrationView = ImageView(context).apply {
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setImageResource(R.drawable.terra_ic_placeholder)
        }
        titleView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Header_M)
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_primary))
        }
        messageView = AppCompatTextView(context).apply {
            applyTerraTextAppearance(R.style.TextAppearance_Terra_Body_Medium)
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(context, TokensR.color.terra_color_text_primary))
        }
        buttonView = TerraButtonView(context).apply {
            text = "Coba Lagi"
            terraButtonType = TerraButtonType.PRIMARY
        }

        addView(illustrationView, LayoutParams(
            resources.getDimensionPixelSize(TokensR.dimen.terra_empty_state_illustration_width),
            resources.getDimensionPixelSize(TokensR.dimen.terra_empty_state_illustration_height),
        ))
        addView(titleView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            topMargin = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_24)
        })
        addView(messageView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            topMargin = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_24)
        })
        addView(buttonView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            topMargin = resources.getDimensionPixelSize(TokensR.dimen.terra_spacing_24)
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
        cornerRadius = resources.getDimension(TokensR.dimen.terra_radius_medium)
        setColor(ContextCompat.getColor(context, fillColor))
    }
}

private fun tickerPalette(tone: TerraTickerTone): TerraTickerPalette {
    return when (tone) {
        TerraTickerTone.INFORMATION -> TerraTickerPalette(TokensR.color.terra_color_info_light, TokensR.color.terra_color_info)
        TerraTickerTone.WARNING -> TerraTickerPalette(TokensR.color.terra_color_secondary_light, TokensR.color.terra_color_secondary_hover)
        TerraTickerTone.ERROR -> TerraTickerPalette(TokensR.color.terra_color_error_surface, TokensR.color.terra_color_error)
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
            accentColor = TokensR.color.terra_color_primary,
        )
        TerraEmptyStateVariant.HAPPY_BIRTHDAY -> TerraEmptyStateConfig(
            title = "Selamat Ulang Tahun!",
            message = "Kami mengucapkan selamat ulang tahun untukmu.",
            buttonLabel = "Tutup",
            buttonVisible = false,
            primaryButton = false,
            accentColor = TokensR.color.terra_color_secondary,
        )
        TerraEmptyStateVariant.CONNECTION_ERROR -> TerraEmptyStateConfig(
            title = "Wah, Jaringan Internet Kamu Terganggu",
            message = "Coba cek jaringan internet kamu apakah sedang ada gangguan atau cek pulsa. Jika sudah, silahkan coba lagi.",
            buttonLabel = "Coba Lagi",
            buttonVisible = true,
            primaryButton = true,
            accentColor = TokensR.color.terra_color_error,
        )
        TerraEmptyStateVariant.UNDER_MAINTENANCE -> TerraEmptyStateConfig(
            title = "Maaf, Sistem Sedang Dalam Perbaikan",
            message = "Sistem sedang dalam perbaikan, kami akan memperbaikinya secepatnya.",
            buttonLabel = "Tutup",
            buttonVisible = false,
            primaryButton = false,
            accentColor = TokensR.color.terra_color_warning,
        )
        TerraEmptyStateVariant.DEFAULT_SEARCH_STATE -> TerraEmptyStateConfig(
            title = "Belum Ada Pencarian",
            message = "Kamu belum melakukan pencarian. Silahkan isi kolom diatas untuk melakukan pencarian.",
            buttonLabel = "Coba Lagi",
            buttonVisible = true,
            primaryButton = true,
            accentColor = TokensR.color.terra_color_primary,
        )
        TerraEmptyStateVariant.PAGE_NOT_FOUND -> TerraEmptyStateConfig(
            title = "Halaman Tidak Ditemukan",
            message = "Halaman yang kamu cari tidak ditemukan, silahkan kembali ke halaman sebelumnya.",
            buttonLabel = "Kembali",
            buttonVisible = true,
            primaryButton = true,
            accentColor = TokensR.color.terra_color_info,
        )
        TerraEmptyStateVariant.SERVER_BUSY -> TerraEmptyStateConfig(
            title = "Internal Server Sedang Sibuk",
            message = "Kami kesulitan untuk koneksikan menuju server, coba cek jaringan kamu atau coba lagi.",
            buttonLabel = "Coba Lagi",
            buttonVisible = true,
            primaryButton = true,
            accentColor = TokensR.color.terra_color_secondary_hover,
        )
        TerraEmptyStateVariant.DATA_NOT_FOUND -> TerraEmptyStateConfig(
            title = "Data Tidak Ditemukan",
            message = "Kami tidak menemukan data yang kamu cari, coba cari kembali.",
            buttonLabel = "Kembali",
            buttonVisible = true,
            primaryButton = true,
            accentColor = TokensR.color.terra_color_primary,
        )
        TerraEmptyStateVariant.TIME_OUT -> TerraEmptyStateConfig(
            title = "Wah Waktu Kamu Habis",
            message = "Waktu untuk login kamu telah habis, silahkan coba kembali untuk login.",
            buttonLabel = "Coba Lagi",
            buttonVisible = true,
            primaryButton = true,
            accentColor = TokensR.color.terra_color_error,
        )
        TerraEmptyStateVariant.SUCCESS_SUBMIT -> TerraEmptyStateConfig(
            title = "Data Sudah Dikirim",
            message = "Data yang Anda ajukan sudah berhasil di submit, silahkan meneruskan untuk proses selanjutnya.",
            buttonLabel = "Selesai",
            buttonVisible = true,
            primaryButton = true,
            accentColor = TokensR.color.terra_color_success,
        )
    }
}
