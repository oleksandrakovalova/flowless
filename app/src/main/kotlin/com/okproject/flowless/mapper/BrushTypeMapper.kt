package com.okproject.flowless.mapper

import androidx.ink.brush.BrushFamily
import androidx.ink.brush.StockBrushes
import com.okproject.flowless.domain.model.brush.BrushType
import com.okproject.flowless.editor.BrushTypeChipItem

object BrushTypeMapper {
    private val brushFamilyToBrushType =
        mapOf(
            StockBrushes.pressurePenV1 to BrushType.PRESSURE_PEN_V1,
            StockBrushes.markerV1 to BrushType.MARKER_V1,
            StockBrushes.highlighterV1 to BrushType.HIGHLIGHTER_V1,
            StockBrushes.dashedLineV1 to BrushType.DASHED_LINE_V1
        )
    private val brushTypeToBrushFamily =
        brushFamilyToBrushType.entries.associate { (key, value) -> value to key }

    fun mapToBrushType(family: BrushFamily): BrushType =
        brushFamilyToBrushType[family] ?: BrushType.PRESSURE_PEN_V1

    fun mapToBrushFamily(type: BrushType): BrushFamily =
        brushTypeToBrushFamily[type] ?: StockBrushes.pressurePenV1

    fun mapToChipItem(type: BrushType): BrushTypeChipItem =
        when(type) {
            BrushType.PRESSURE_PEN_V1 -> BrushTypeChipItem.PressingPen
            BrushType.MARKER_V1 -> BrushTypeChipItem.Marker
            BrushType.HIGHLIGHTER_V1 -> BrushTypeChipItem.Highlighter
            BrushType.DASHED_LINE_V1 -> BrushTypeChipItem.DashedLine
        }

    fun mapToChipItems(types: List<BrushType>): List<BrushTypeChipItem> =
        types.map { mapToChipItem(it) }
}