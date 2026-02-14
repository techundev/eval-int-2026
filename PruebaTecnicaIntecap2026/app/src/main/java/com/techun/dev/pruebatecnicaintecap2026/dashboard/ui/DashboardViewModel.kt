package com.techun.dev.pruebatecnicaintecap2026.dashboard.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techun.dev.pruebatecnicaintecap2026.core.domain.User
import com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.usecase.CreateUser
import com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.usecase.GetAllUsers
import com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.usecase.UpdateUser
import com.techun.dev.pruebatecnicaintecap2026.dashboard.ui.DashboardUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getAllUsers: GetAllUsers,
    private val createUser: CreateUser,
    private val updateUser: UpdateUser
) : ViewModel() {
    private val _showDialog = MutableStateFlow(value = false)
    val showDialog: StateFlow<Boolean> get() = _showDialog

    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<DashboardUiState> =
        refreshTrigger.onStart { emit(Unit) } // Carga inicial
            .flatMapLatest {
                getAllUsers()
            }.map<List<User>, DashboardUiState> { Success(it) }
            .catch { emit(DashboardUiState.Error(it)) }.stateIn(
                viewModelScope, SharingStarted.WhileSubscribed(5000), DashboardUiState.Loading
            )

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch { refreshTrigger.emit(Unit) }
    }


    fun onItemEdited(userInfo: User) = viewModelScope.launch {
        updateUser(userInfo.id, userInfo).onSuccess {
            refresh()
        }.onFailure {
            Log.e("ERROR", "onItemEdited: $it")
        }
    }

    fun onItemRemove(userInfo: User) = viewModelScope.launch {
        updateUser(userInfo.id, userInfo.copy(status = 0)).onSuccess {
            refresh()
        }.onFailure {
            Log.e("ERROR", "onItemRemove: $it")
        }
    }

    fun onDialogClose() {
        _showDialog.update { false }
    }

    fun onShowDialog() {
        _showDialog.update { true }
    }

    fun onCreateUser(user: User) = viewModelScope.launch {
        onDialogClose()
        val result = createUser(user.id, user)

        result.onSuccess {
            refresh()
        }.onFailure { error ->
            Log.e("ERROR", "onCreateUser: $error")
        }
    }

}

sealed interface DashboardUiState {
    object Loading : DashboardUiState
    data class Error(val throwable: Throwable) : DashboardUiState
    data class Success(val users: List<User>) : DashboardUiState
}