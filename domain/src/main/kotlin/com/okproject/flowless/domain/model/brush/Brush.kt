package com.okproject.flowless.domain.model.brush

data class Brush(
    val size: Float,
    val color: Long,
    val epsilon: Float,
    val brushType: BrushType
) {
    companion object {
        const val DEFAULT_BRUSH_COLOR: Long = 0xFF0000FF
        const val DEFAULT_BRUSH_SIZE = 5f
        const val DEFAULT_BRUSH_EPSILON = 0.1f
        val DEFAULT_BRUSH_TYPE: BrushType = BrushType.PRESSURE_PEN_V1
        val defaultValue = Brush(
            size = DEFAULT_BRUSH_SIZE,
            color = DEFAULT_BRUSH_COLOR,
            epsilon = DEFAULT_BRUSH_EPSILON,
            brushType = DEFAULT_BRUSH_TYPE
        )
    }
}