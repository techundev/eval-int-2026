package com.techun.dev.pruebatecnicaintecap2026.login.data.repository

import android.util.Log
import com.techun.dev.pruebatecnicaintecap2026.login.data.source.api.AuthApiService
import com.techun.dev.pruebatecnicaintecap2026.login.domain.model.UserRemote
import com.techun.dev.pruebatecnicaintecap2026.login.domain.model.toDomain
import com.techun.dev.pruebatecnicaintecap2026.login.domain.repository.AuthRepository
import jakarta.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: AuthApiService) : AuthRepository {
    override suspend fun doLogin(
        user: String, password: String
    ): UserRemote? {
        return try {
            val response = api.doLogin()

            val userFound = response.values.find { userData ->
                (userData.usuario == user || userData.email == user) && userData.password == password
            }

            if (userFound?.estado == 1) userFound.toDomain() else null
        } catch (e: Exception) {
            Log.e("AuthRepositoryImpl", "Error doLogin: ${e.message}")
            null
        }

    }
}