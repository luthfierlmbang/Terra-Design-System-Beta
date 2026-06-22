package com.oasis.designsystem.avatar

import com.oasis.designsystem.tokens.R as TokensR

enum class OasisAvatarSize(val dimenRes: Int) {
    SIZE_64(TokensR.dimen.oasis_avatar_size_64),
    SIZE_96(TokensR.dimen.oasis_avatar_size_96),
    SIZE_128(TokensR.dimen.oasis_avatar_size_128);

    companion object {
        fun from(value: Int): OasisAvatarSize = when (value) {
            1 -> SIZE_96
            2 -> SIZE_128
            else -> SIZE_64
        }
    }
}