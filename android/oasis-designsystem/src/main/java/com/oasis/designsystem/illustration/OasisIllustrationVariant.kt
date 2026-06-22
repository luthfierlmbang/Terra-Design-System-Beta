package com.oasis.designsystem.illustration

import androidx.annotation.DrawableRes
import com.oasis.designsystem.R

enum class OasisIllustrationVariant(@DrawableRes val resId: Int) {
    ACTIVATION(R.drawable.oasis_illustration_activation),
    AGREE(R.drawable.oasis_illustration_agree),
    CONNECTION_PROBLEM(R.drawable.oasis_illustration_connection_problem),
    FAILURE_1(R.drawable.oasis_illustration_failure_1),
    FAILURE_2(R.drawable.oasis_illustration_failure_2),
    INTRODUCING(R.drawable.oasis_illustration_introducing),
    NOT_FOUND(R.drawable.oasis_illustration_not_found),
    OTP(R.drawable.oasis_illustration_otp),
    PIN(R.drawable.oasis_illustration_pin),
    SPLASH_CAMPAIGN(R.drawable.oasis_illustration_splash_campaign),
    SUCCESS_1(R.drawable.oasis_illustration_success_1),
    SUCCESS_2(R.drawable.oasis_illustration_success_2),
    TOP_UP_LIMIT(R.drawable.oasis_illustration_top_up_limit),
    WELCOME(R.drawable.oasis_illustration_welcome);

    companion object {
        fun from(value: Int): OasisIllustrationVariant =
            values().getOrElse(value) { WELCOME }
    }
}