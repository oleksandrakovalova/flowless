package com.okproject.flowless.domain.brush

import com.okproject.flowless.domain.model.brush.Brush
import kotlinx.coroutines.flow.Flow

class GetLatestBrushUseCaseImpl(
    private val repository: BrushRepository
): GetLatestBrushUseCase {
    override fun invoke(): Flow<Brush> =
        repository.latestBrush
}