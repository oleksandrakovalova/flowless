package com.okproject.flowless.domain.brush

import com.okproject.flowless.domain.model.brush.Brush
import com.okproject.flowless.domain.model.brush.BrushType
import kotlinx.coroutines.flow.Flow

interface BrushRepository {
    val latestBrush: Flow<Brush>
    suspend fun updateBrushColor(color: Long)
    suspend fun updateBrushSize(size: Float)
    suspend fun updateBrushType(type: BrushType)
}