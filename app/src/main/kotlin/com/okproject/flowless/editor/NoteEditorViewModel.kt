package com.okproject.flowless.editor

import androidx.lifecycle.ViewModel
import com.okproject.flowless.domain.model.brush.Brush
import com.okproject.flowless.domain.model.brush.BrushType
import com.okproject.flowless.domain.model.stroke.Stroke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class NoteEditorViewModel: ViewModel() {
    private val _brush: MutableStateFlow<Brush> = MutableStateFlow(
        Brush(
            size = DEFAULT_BRUSH_SIZE,
            color = DEFAULT_BRUSH_COLOR,
            epsilon = DEFAULT_BRUSH_EPSILON,
            brushType = DEFAULT_BRUSH_TYPE
        )
    )
    val brush: StateFlow<Brush> = _brush.asStateFlow()

    val brushSizeRange = MIN_BRUSH_SIZE..MAX_BRUSH_SIZE
    val brushTypes = BrushType.entries

    private val _finishedStrokes: MutableStateFlow<Set<Stroke>> = MutableStateFlow(emptySet())
    val finishedStrokes: StateFlow<Set<Stroke>> = _finishedStrokes.asStateFlow()

    fun onBrushColorChanged(color: Long) {
        _brush.update { it.copy(color = color) }
    }

    fun onBrushSizeChanged(size: Float) {
        _brush.update { it.copy(size = size) }
    }

    fun onBrushTypeSelected(type: BrushType) {
        _brush.update { it.copy(brushType = type) }
    }

    fun onStrokeFinished(strokes: Collection<Stroke>) {
        _finishedStrokes.update { it + strokes }
    }

    companion object {
        private const val DEFAULT_BRUSH_COLOR: Long = 0xFF000000
        private const val DEFAULT_BRUSH_SIZE = 5f
        private const val MIN_BRUSH_SIZE = 1f
        private const val MAX_BRUSH_SIZE = 25f
        private const val DEFAULT_BRUSH_EPSILON = 0.1f
        private val DEFAULT_BRUSH_TYPE: BrushType = BrushType.PRESSURE_PEN_V1
    }
}