package com.okproject.flowless.mapper

import com.okproject.flowless.InkBrush
import com.okproject.flowless.domain.model.brush.Brush

object BrushMapper {
    fun mapToBrush(brush: InkBrush): Brush =
        Brush(
            size = brush.size,
            color = brush.colorLong,
            epsilon = brush.epsilon,
            brushType = BrushTypeMapper.mapToBrushType(brush.family)
        )

    fun mapToInkBrush(brush: Brush): InkBrush =
        InkBrush.createWithColorLong(
            family = BrushTypeMapper.mapToBrushFamily(brush.brushType),
            colorLong = brush.color,
            size = brush.size,
            epsilon = brush.epsilon
        )
}