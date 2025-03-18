package com.okproject.flowless.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okproject.flowless.domain.brush.GetLatestBrushUseCase
import com.okproject.flowless.domain.brush.UpdateBrushUseCase
import com.okproject.flowless.domain.model.brush.Brush
import com.okproject.flowless.domain.model.brush.BrushType
import com.okproject.flowless.domain.model.stroke.Stroke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class NoteEditorViewModel(
    private val getLatestBrushUseCase: GetLatestBrushUseCase,
    private val updateBrushUseCase: UpdateBrushUseCase
): ViewModel() {

    val brush: StateFlow<Brush> = getLatestBrushUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Brush.defaultValue
        )

    val brushSizeRange = MIN_BRUSH_SIZE..MAX_BRUSH_SIZE
    val brushTypes = BrushType.entries

    private val _finishedStrokes: MutableStateFlow<Set<Stroke>> = MutableStateFlow(emptySet())
    val finishedStrokes: StateFlow<Set<Stroke>> = _finishedStrokes.asStateFlow()

    fun onBrushColorChanged(color: Long) {
        viewModelScope.launch {
            updateBrushUseCase(color = color)
        }
    }

    fun onBrushSizeChanged(size: Float) {
        viewModelScope.launch {
            updateBrushUseCase(size = size)
        }
    }

    fun onBrushTypeSelected(type: BrushType) {
        viewModelScope.launch {
            updateBrushUseCase(type = type)
        }
    }

    fun onStrokeFinished(strokes: Collection<Stroke>) {
        _finishedStrokes.update { it + strokes }
    }

    companion object {
        private const val MIN_BRUSH_SIZE = 1f
        private const val MAX_BRUSH_SIZE = 25f
    }
}