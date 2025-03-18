package com.okproject.flowless.data.datasource

import com.okproject.flowless.data.mapper.BrushTypeOptionMapper
import com.okproject.flowless.data.mapper.mapToBrush
import com.okproject.flowless.data.storage.BrushStorage
import com.okproject.flowless.domain.model.brush.Brush
import com.okproject.flowless.domain.model.brush.BrushType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BrushDataSourceImpl(
    private val brushStorage: BrushStorage
): BrushDataSource {
    override val storedBrush: Flow<Brush> =
        brushStorage.storedBrushOption.map { it.mapToBrush() }

    override suspend fun updateBrushColor(color: Long) =
        brushStorage.updateBrushColor(color = color)

    override suspend fun updateBrushSize(size: Float) =
        brushStorage.updateBrushSize(size = size)


    override suspend fun updateBrushType(type: BrushType) =
        brushStorage.updateBrushType(type = BrushTypeOptionMapper.mapToTypeOption(type))
}