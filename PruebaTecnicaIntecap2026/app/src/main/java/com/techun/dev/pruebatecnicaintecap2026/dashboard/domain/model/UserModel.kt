package com.techun.dev.pruebatecnicaintecap2026.dashboard.domain.model

import com.techun.dev.pruebatecnicaintecap2026.dashboard.data.model.UserResponse
import com.techun.dev.pruebatecnicaintecap2026.login.domain.model.UserRole

data class UserModel(
    val idUser: String,
    val name: String,
    val lastName: String,
    val role: UserRole,
    val email: String,
    val phoneNumber: String
)