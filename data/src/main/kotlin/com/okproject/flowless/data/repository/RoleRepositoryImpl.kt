package com.okproject.flowless.data.repository

import com.okproject.flowless.data.datasource.RoleDataSource
import com.okproject.flowless.domain.role.RoleRepository
import kotlinx.coroutines.flow.Flow

class RoleRepositoryImpl(
    private val dataSource: RoleDataSource
): RoleRepository {

    override val isRoleRequested: Flow<Boolean> =
        dataSource.isRoleRequested

    override suspend fun setIsRoleRequested(isRequested: Boolean) =
        dataSource.setIsRoleRequested(isRequested = isRequested)
}