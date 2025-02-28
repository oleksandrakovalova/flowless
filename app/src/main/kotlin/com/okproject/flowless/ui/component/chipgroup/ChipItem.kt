package com.okproject.flowless.ui.component.chipgroup

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ChipItem<T> {
    val id: T
    @get:StringRes val labelRes: Int
    @get:DrawableRes val iconRes: Int
}
