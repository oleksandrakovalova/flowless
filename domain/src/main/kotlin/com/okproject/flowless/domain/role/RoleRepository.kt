package com.okproject.flowless.domain.role

import kotlinx.coroutines.flow.Flow

interface RoleRepository {
    val isRoleRequested: Flow<Boolean>
    suspend fun setIsRoleRequested(isRequested: Boolean)
}