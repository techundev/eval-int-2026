package com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.repository

import com.techun.dev.pruebatecnicaintecap2026.core.data.UserResponse
import com.techun.dev.pruebatecnicaintecap2026.core.domain.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val users: Flow<List<User>>

    suspend fun saveAndUpdateUser(
        id: String,
        user: UserResponse
    )

}