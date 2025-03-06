package com.okproject.flowless.domain.model.brush

data class Brush(
    val size: Float,
    val color: Long,
    val epsilon: Float,
    val brushType: BrushType
)