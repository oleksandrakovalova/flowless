package com.okproject.flowless.domain.brush

import com.okproject.flowless.domain.model.brush.BrushType

interface UpdateBrushUseCase {
    suspend operator fun invoke(
        color: Long? = null,
        size: Float? = null,
        type: BrushType? = null
    )
}