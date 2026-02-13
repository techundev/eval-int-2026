package com.techun.dev.pruebatecnicaintecap2026.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.model.UserModel
import com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.usecase.GetAllUsers
import com.techun.dev.pruebatecnicaintecap2026.dashboard.ui.DashboardUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class DashboardViewModel @Inject constructor(private val getAllUsers: GetAllUsers) : ViewModel() {
    val uiState: StateFlow<DashboardUiState>
        get() = getAllUsers().map(::Success)
            .catch { DashboardUiState.Error(it) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DashboardUiState.Loading)

    fun onItemEdited(userInfo: UserModel) {

    }

    fun onItemRemove(userInfo: UserModel) {

    }

}

sealed interface DashboardUiState {
    object Loading : DashboardUiState
    data class Error(val throwable: Throwable) : DashboardUiState
    data class Success(val users: List<UserModel>) : DashboardUiState
}