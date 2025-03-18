package com.okproject.flowless.ext

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces

fun Color.colorValue(): Long =
    when(this.colorSpace) {
        ColorSpaces.Srgb -> (value shr 32)
        ColorSpaces.AdobeRgb,
        ColorSpaces.CieXyz,
        ColorSpaces.CieLab -> (value shr 6)
        else -> value
    }.toLong()