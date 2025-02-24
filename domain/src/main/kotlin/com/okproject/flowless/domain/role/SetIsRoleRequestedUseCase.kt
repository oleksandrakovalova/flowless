package com.okproject.flowless.domain.role

interface SetIsRoleRequestedUseCase {
    suspend operator fun invoke(isRequested: Boolean)
}