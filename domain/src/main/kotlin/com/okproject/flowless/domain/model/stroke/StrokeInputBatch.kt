package com.okproject.flowless.domain.model.stroke

data class StrokeInputBatch(
    val toolType: ToolType,
    val strokeUnitLengthCm: Float,
    val inputs: List<StrokeInput>
)
