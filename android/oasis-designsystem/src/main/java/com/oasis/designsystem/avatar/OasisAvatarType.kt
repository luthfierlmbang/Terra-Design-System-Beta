package com.oasis.designsystem.avatar

import androidx.annotation.DrawableRes
import com.oasis.designsystem.R

enum class OasisAvatarType(@DrawableRes val defaultIconRes: Int) {
    INITIAL(R.drawable.oasis_ic_avatar_user_initial),
    ICON(R.drawable.oasis_ic_avatar_user_icon),
    PICTURE(R.drawable.oasis_ic_avatar_user_picture);

    companion object {
        fun from(value: Int): OasisAvatarType = when (value) {
            1 -> ICON
            2 -> PICTURE
            else -> INITIAL
        }
    }
}