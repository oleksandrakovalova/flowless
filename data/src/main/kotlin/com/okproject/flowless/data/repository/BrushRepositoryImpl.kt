package com.okproject.flowless.data.repository

import com.okproject.flowless.data.datasource.BrushDataSource
import com.okproject.flowless.domain.brush.BrushRepository
import com.okproject.flowless.domain.model.brush.Brush
import com.okproject.flowless.domain.model.brush.BrushType
import kotlinx.coroutines.flow.Flow

class BrushRepositoryImpl(
    private val dataSource: BrushDataSource
): BrushRepository {
    override val latestBrush: Flow<Brush> = dataSource.storedBrush

    override suspend fun updateBrushColor(color: Long) =
        dataSource.updateBrushColor(color = color)

    override suspend fun updateBrushSize(size: Float) =
        dataSource.updateBrushSize(size = size)

    override suspend fun updateBrushType(type: BrushType) =
        dataSource.updateBrushType(type = type)
}