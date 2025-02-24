package com.okproject.flowless.domain.role

class SetIsRoleRequestedUseCaseImpl(
    private val roleRepository: RoleRepository
): SetIsRoleRequestedUseCase {
    override suspend fun invoke(isRequested: Boolean) =
        roleRepository.setIsRoleRequested(isRequested = isRequested)
}