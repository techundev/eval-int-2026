package com.techun.dev.pruebatecnicaintecap2026.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techun.dev.pruebatecnicaintecap2026.login.domain.usecase.DoLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(private val doLogin: DoLogin) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> get() = _uiState

    fun onUserChanged(newUser: String) {
        _uiState.update { it.copy(user = newUser) }
    }

    fun onPasswordChanged(newPass: String) {
        _uiState.update { it.copy(password = newPass) }
    }

    fun doLogin() {
        val currentUser = _uiState.value.user
        val currentPassword = _uiState.value.password

        if (currentUser.isBlank() || currentPassword.isBlank()) {
            _uiState.update {
                it.copy(error = "El usuario y la contraseña no pueden estar vacíos")
            }
            return
        }

        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {

            try {
                val userFound = doLogin(currentUser, currentPassword)

                if (userFound != null) {
                    val userRol = userFound.rol
                    _uiState.update {
                        it.copy(
                            isLoading = false, isSuccess = true, user = "", password = ""
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(isLoading = false, error = "Credenciales inválidas")
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = "Error de conexión: ${e.message}")
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun resetNavigation() {
        _uiState.update { it.copy(isSuccess = false) }
    }
}

data class LoginUiState(
    val isSuccess: Boolean = false,
    val user: String = "admin@empresa.com",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)