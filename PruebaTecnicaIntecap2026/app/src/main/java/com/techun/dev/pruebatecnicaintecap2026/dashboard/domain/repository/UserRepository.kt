package com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.repository

import com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val users: Flow<List<UserModel>>
}