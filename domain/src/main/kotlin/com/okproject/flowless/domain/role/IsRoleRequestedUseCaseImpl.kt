package com.okproject.flowless.domain.role

import kotlinx.coroutines.flow.Flow

class IsRoleRequestedUseCaseImpl(
    private val roleRepository: RoleRepository
): IsRoleRequestedUseCase {
    override fun invoke(): Flow<Boolean> =
        roleRepository.isRoleRequested
}