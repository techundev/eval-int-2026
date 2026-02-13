package com.techun.dev.pruebatecnicaintecap2026.login.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val email: String,
    val estado: Int,
    val id: String,
    val password: String,
    val rol: Int,
    val usuario: String
)

