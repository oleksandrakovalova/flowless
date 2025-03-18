package com.okproject.flowless.data.datasource

import com.okproject.flowless.domain.model.brush.Brush
import com.okproject.flowless.domain.model.brush.BrushType
import kotlinx.coroutines.flow.Flow

interface BrushDataSource {
    val storedBrush: Flow<Brush>
    suspend fun updateBrushColor(color: Long)
    suspend fun updateBrushSize(size: Float)
    suspend fun updateBrushType(type: BrushType)
}