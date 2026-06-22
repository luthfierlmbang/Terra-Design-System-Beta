package com.oasis.designsystem.icon

import com.oasis.designsystem.tokens.R as TokensR

enum class OasisIconSize(val dimenRes: Int) {
    SIZE_16(TokensR.dimen.oasis_icon_size_16),
    SIZE_20(TokensR.dimen.oasis_icon_size_20),
    SIZE_24(TokensR.dimen.oasis_icon_size_24),
    SIZE_32(TokensR.dimen.oasis_icon_size_32);

    companion object {
        fun from(value: Int): OasisIconSize = when (value) {
            1 -> SIZE_20
            2 -> SIZE_24
            3 -> SIZE_32
            else -> SIZE_16
        }
    }
}