package com.okproject.flowless.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoleStorageImpl(private val context: Context): RoleStorage {
    override val isNotesRoleRequested: Flow<Boolean> =
        context.ruleDataStore.data.map { preferences ->
            preferences[isNotesRoleRequestedKey] ?: false
        }

    override suspend fun setIsNotesRoleRequested(isRequested: Boolean) {
        context.ruleDataStore.edit { preferences ->
            preferences[isNotesRoleRequestedKey] = isRequested
        }
    }

    companion object {
        private const val ROLE_PREFERENCES_NAME = "role_preferences"
        private val Context.ruleDataStore: DataStore<Preferences> by preferencesDataStore(
            name = ROLE_PREFERENCES_NAME
        )
        private const val IS_NOTES_ROLE_REQUESTED = "is_notes_role_requested"
        private val isNotesRoleRequestedKey = booleanPreferencesKey(IS_NOTES_ROLE_REQUESTED)
    }
}