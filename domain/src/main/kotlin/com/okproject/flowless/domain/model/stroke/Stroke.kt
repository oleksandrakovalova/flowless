package com.okproject.flowless.domain.model.stroke

import com.okproject.flowless.domain.model.brush.Brush

data class Stroke(
    val inputs: StrokeInputBatch,
    val brush: Brush
)
