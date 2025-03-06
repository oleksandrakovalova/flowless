package com.okproject.flowless.mapper

import com.okproject.flowless.InkStroke
import com.okproject.flowless.domain.model.stroke.Stroke

object StrokeMapper {
    fun mapToStroke(stroke: InkStroke): Stroke =
        Stroke(
            inputs = StrokeInputBatchMapper.mapToStrokeInputBatch(stroke.inputs),
            brush = BrushMapper.mapToBrush(stroke.brush)
        )

    fun mapToStrokes(strokes: Collection<InkStroke>): Collection<Stroke> =
        strokes.map { mapToStroke(it) }

    fun mapToInkStroke(stroke: Stroke): InkStroke =
        InkStroke(
            brush = BrushMapper.mapToInkBrush(stroke.brush),
            inputs = StrokeInputBatchMapper.mapToInkStrokeInputBatch(stroke.inputs)
        )

    fun mapToInkStrokes(strokes: Collection<Stroke>): Set<InkStroke> =
        strokes.map { mapToInkStroke(it) }.toSet()
}