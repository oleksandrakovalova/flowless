package com.okproject.flowless.data.mapper

import com.okproject.flowless.data.model.BrushTypeOption
import com.okproject.flowless.domain.model.brush.BrushType

object BrushTypeOptionMapper {
    private val typeOptionToType = mapOf(
        BrushTypeOption.PRESSURE_PEN_V1 to BrushType.PRESSURE_PEN_V1,
        BrushTypeOption.MARKER_V1 to BrushType.MARKER_V1,
        BrushTypeOption.HIGHLIGHTER_V1 to BrushType.HIGHLIGHTER_V1,
        BrushTypeOption.DASHED_LINE_V1 to BrushType.DASHED_LINE_V1
    )
    private val typeToTypeOption = typeOptionToType.entries.associate { (key, value) -> value to key }

    fun mapToType(typeOption: BrushTypeOption): BrushType =
        typeOptionToType[typeOption] ?: BrushType.PRESSURE_PEN_V1

    fun mapToTypeOption(type: BrushType): BrushTypeOption =
        typeToTypeOption[type] ?: BrushTypeOption.PRESSURE_PEN_V1
}