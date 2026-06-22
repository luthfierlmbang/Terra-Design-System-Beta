package com.oasis.designsystem.button

enum class OasisButtonVariant {
    PRIMARY,
    SECONDARY,
    TERTIARY;

    companion object {
        fun from(value: Int): OasisButtonVariant = when (value) {
            1 -> SECONDARY
            2 -> TERTIARY
            else -> PRIMARY
        }
    }
}