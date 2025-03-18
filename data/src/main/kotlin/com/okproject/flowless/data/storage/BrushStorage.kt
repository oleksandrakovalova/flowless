package com.okproject.flowless.data.storage

import com.okproject.flowless.data.model.BrushOption
import com.okproject.flowless.data.model.BrushTypeOption
import kotlinx.coroutines.flow.Flow

interface BrushStorage {
    val storedBrushOption: Flow<BrushOption>
    suspend fun updateBrushColor(color: Long)
    suspend fun updateBrushSize(size: Float)
    suspend fun updateBrushType(type: BrushTypeOption)
}