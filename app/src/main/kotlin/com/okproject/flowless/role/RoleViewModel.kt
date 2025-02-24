package com.okproject.flowless.role

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okproject.flowless.domain.role.IsRoleRequestedUseCase
import com.okproject.flowless.domain.role.SetIsRoleRequestedUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RoleViewModel(
    isRoleRequestedFlow: IsRoleRequestedUseCase,
    private val setIsRoleRequested: SetIsRoleRequestedUseCase
): ViewModel() {
    val isRoleRequested: StateFlow<Boolean> = isRoleRequestedFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false
        )

    init {
        viewModelScope.launch {
            isRoleRequested.collect()
        }
    }

    fun onRoleRequested() {
        viewModelScope.launch {
            setIsRoleRequested(isRequested = true)
        }
    }
}