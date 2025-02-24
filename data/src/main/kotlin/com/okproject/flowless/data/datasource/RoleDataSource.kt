package com.okproject.flowless.data.datasource

import kotlinx.coroutines.flow.Flow

interface RoleDataSource {
    val isRoleRequested: Flow<Boolean>
    suspend fun setIsRoleRequested(isRequested: Boolean)
}