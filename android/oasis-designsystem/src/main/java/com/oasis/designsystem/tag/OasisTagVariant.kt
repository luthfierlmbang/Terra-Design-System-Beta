package com.oasis.designsystem.tag

enum class OasisTagVariant {
    INFO,
    SUCCESS,
    WARNING,
    DANGER;

    companion object {
        fun from(ordinal: Int): OasisTagVariant = entries[ordinal]
    }
}
