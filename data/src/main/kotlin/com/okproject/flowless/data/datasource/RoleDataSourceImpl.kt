package com.okproject.flowless.data.datasource

import com.okproject.flowless.data.storage.RoleStorage
import kotlinx.coroutines.flow.Flow

class RoleDataSourceImpl(
    private val roleStorage: RoleStorage
): RoleDataSource {
    override val isRoleRequested: Flow<Boolean> =
        roleStorage.isNotesRoleRequested

    override suspend fun setIsRoleRequested(isRequested: Boolean) =
        roleStorage.setIsNotesRoleRequested(isRequested = isRequested)
}