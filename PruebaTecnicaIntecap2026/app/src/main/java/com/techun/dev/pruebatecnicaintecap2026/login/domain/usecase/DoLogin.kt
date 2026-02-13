package com.techun.dev.pruebatecnicaintecap2026.login.domain.usecase

import com.techun.dev.pruebatecnicaintecap2026.login.domain.model.UserRemote
import com.techun.dev.pruebatecnicaintecap2026.login.domain.repository.AuthRepository
import javax.inject.Inject

class DoLogin @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(user: String, password: String): UserRemote? =
        authRepository.doLogin(user, password)
}