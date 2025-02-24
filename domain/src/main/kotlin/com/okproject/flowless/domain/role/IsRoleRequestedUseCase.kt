package com.okproject.flowless.domain.role

import kotlinx.coroutines.flow.Flow

interface IsRoleRequestedUseCase {
    operator fun invoke(): Flow<Boolean>
}