package com.okproject.flowless.editor

import androidx.annotation.DrawableRes
import com.okproject.flowless.R
import com.okproject.flowless.domain.model.brush.BrushType
import com.okproject.flowless.ui.component.chipgroup.ChipItem

sealed interface BrushTypeChipItem : ChipItem<BrushType> {
    data object PressingPen: BrushTypeChipItem {
        override val id: BrushType = BrushType.PRESSURE_PEN_V1
        override val labelRes: Int = R.string.pressing_pen
        @DrawableRes override val iconRes: Int = R.drawable.ink_pen
    }
    data object Marker: BrushTypeChipItem {
        override val id: BrushType = BrushType.MARKER_V1
        override val labelRes: Int = R.string.marker
        @DrawableRes override val iconRes: Int = R.drawable.ink_marker
    }
    data object Highlighter: BrushTypeChipItem {
        override val id: BrushType = BrushType.HIGHLIGHTER_V1
        override val labelRes: Int = R.string.highlighter
        @DrawableRes override val iconRes: Int = R.drawable.ink_highlighter
    }
    data object DashedLine: BrushTypeChipItem {
        override val id: BrushType = BrushType.DASHED_LINE_V1
        override val labelRes: Int = R.string.dashed_line
        @DrawableRes override val iconRes: Int = R.drawable.ink_dashed_line
    }
}