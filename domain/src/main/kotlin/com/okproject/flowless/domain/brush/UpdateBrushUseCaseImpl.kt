package com.okproject.flowless.domain.brush

import com.okproject.flowless.domain.model.brush.BrushType

class UpdateBrushUseCaseImpl(
    private val repository: BrushRepository
): UpdateBrushUseCase {
    override suspend fun invoke(color: Long?, size: Float?, type: BrushType?) {
        color?.let { repository.updateBrushColor(color = it) }
        size?.let { repository.updateBrushSize(size = it) }
        type?.let { repository.updateBrushType(type = it) }
    }
}