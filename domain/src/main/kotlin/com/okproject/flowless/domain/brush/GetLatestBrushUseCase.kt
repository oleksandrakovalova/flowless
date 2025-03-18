package com.okproject.flowless.domain.brush

import com.okproject.flowless.domain.model.brush.Brush
import kotlinx.coroutines.flow.Flow

interface GetLatestBrushUseCase {
    operator fun invoke(): Flow<Brush>
}