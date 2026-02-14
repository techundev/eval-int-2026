package com.techun.dev.pruebatecnicaintecap2026.login.domain.repository

import com.techun.dev.pruebatecnicaintecap2026.core.domain.User

interface AuthRepository {
    suspend fun doLogin(user: String, password: String): User?
}