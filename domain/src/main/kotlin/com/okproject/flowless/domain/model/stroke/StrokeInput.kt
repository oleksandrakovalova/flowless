package com.okproject.flowless.domain.model.stroke

data class StrokeInput(
    val x: Float,
    val y: Float,
    val timeMillis: Long,
    val pressure: Float,
    val tiltRadians: Float,
    val orientationRadians: Float,
    val strokeUnitLengthCm: Float
)