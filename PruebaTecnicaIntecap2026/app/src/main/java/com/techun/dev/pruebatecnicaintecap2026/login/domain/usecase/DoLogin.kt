package com.techun.dev.pruebatecnicaintecap2026.login.domain.usecase

import com.techun.dev.pruebatecnicaintecap2026.core.domain.User
import com.techun.dev.pruebatecnicaintecap2026.login.domain.repository.AuthRepository
import javax.inject.Inject

class DoLogin @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(user: String, password: String): User? =
        authRepository.doLogin(user, password)
}