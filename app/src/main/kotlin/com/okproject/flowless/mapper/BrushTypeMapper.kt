package com.okproject.flowless.mapper

import androidx.ink.brush.BrushFamily
import androidx.ink.brush.StockBrushes
import com.okproject.flowless.editor.BrushType
import com.okproject.flowless.editor.BrushTypeChipItem

fun BrushType.toBrushFamily(): BrushFamily =
    when (this) {
        BrushType.PRESSURE_PEN -> StockBrushes.pressurePenLatest
        BrushType.MARKER -> StockBrushes.markerLatest
        BrushType.HIGHLIGHTER -> StockBrushes.highlighterLatest
        BrushType.DASHED_LINE -> StockBrushes.dashedLineLatest
    }

fun BrushType.toChipItem(): BrushTypeChipItem =
    when(this) {
        BrushType.PRESSURE_PEN -> BrushTypeChipItem.PressingPen
        BrushType.MARKER -> BrushTypeChipItem.Marker
        BrushType.HIGHLIGHTER -> BrushTypeChipItem.Highlighter
        BrushType.DASHED_LINE -> BrushTypeChipItem.DashedLine
    }

fun List<BrushType>.toChipItems(): List<BrushTypeChipItem> =
    this.map { it.toChipItem() }