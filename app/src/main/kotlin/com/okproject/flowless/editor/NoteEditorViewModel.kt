package com.okproject.flowless.editor

import androidx.annotation.ColorLong
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class NoteEditorViewModel: ViewModel() {
    private val _brushColor: MutableStateFlow<Long> = MutableStateFlow(DEFAULT_BRUSH_COLOR)
    val brushColor: StateFlow<Long> = _brushColor.asStateFlow()

    private val _brushSize: MutableStateFlow<Float> = MutableStateFlow(DEFAULT_BRUSH_SIZE)
    val brushSize: StateFlow<Float> = _brushSize.asStateFlow()
    val brushSizeRange = MIN_BRUSH_SIZE..MAX_BRUSH_SIZE

    private val _brushType: MutableStateFlow<BrushType> = MutableStateFlow(DEFAULT_BRUSH_TYPE)
    val brushType: StateFlow<BrushType> = _brushType.asStateFlow()
    val brushTypes = BrushType.entries

    fun onBrushColorChanged(color: Long) {
        viewModelScope.launch {
            _brushColor.emit(color)
        }
    }

    fun onBrushSizeChanged(size: Float) {
        viewModelScope.launch {
            _brushSize.emit(size)
        }
    }

    fun onBrushTypeSelected(type: BrushType) {
        viewModelScope.launch {
            _brushType.emit(type)
        }
    }

    companion object {
        @ColorLong
        private const val DEFAULT_BRUSH_COLOR: Long = 0xFF000000
        private const val DEFAULT_BRUSH_SIZE = 5f
        private const val MIN_BRUSH_SIZE = 1f
        private const val MAX_BRUSH_SIZE = 25f
        private val DEFAULT_BRUSH_TYPE: BrushType = BrushType.PRESSURE_PEN
    }
}