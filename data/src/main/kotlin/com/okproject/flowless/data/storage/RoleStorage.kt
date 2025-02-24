package com.okproject.flowless.data.storage

import kotlinx.coroutines.flow.Flow

interface RoleStorage {
    val isNotesRoleRequested: Flow<Boolean>
    suspend fun setIsNotesRoleRequested(isRequested: Boolean)
}