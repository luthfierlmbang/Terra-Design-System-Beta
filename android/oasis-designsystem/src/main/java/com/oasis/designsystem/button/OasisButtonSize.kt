package com.oasis.designsystem.button

enum class OasisButtonSize {
    LARGE,
    MEDIUM,
    SMALL;

    companion object {
        fun from(value: Int): OasisButtonSize = when (value) {
            1 -> MEDIUM
            2 -> SMALL
            else -> LARGE
        }
    }
}