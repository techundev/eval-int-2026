package com.techun.dev.pruebatecnicaintecap2026.login.domain.model

import com.techun.dev.pruebatecnicaintecap2026.login.data.model.UserResponse
import kotlinx.serialization.Serializable

@Serializable
data class UserRemote(
    val email: String,
    val estado: Int,
    val id: String,
    val password: String,
    val rol: UserRole,
    val usuario: String
)

fun UserResponse.toDomain() = UserRemote(
    email = email,
    estado = estado,
    id = id,
    password = password,
    rol = UserRole.fromInt(rol),
    usuario = usuario
)
