package com.techun.dev.pruebatecnicaintecap2026.core.domain

import com.techun.dev.pruebatecnicaintecap2026.core.data.UserResponse
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val lastName: String,
    val username: String,
    val email: String,
    val password: String,
    val role: UserRole,
    val phoneNumber: String,
    val status: Int
)

fun UserResponse.toDomain() = User(
    id = email,
    name = name,
    lastName = id,
    username = password,
    email = email,
    password = password,
    role = UserRole.fromInt(role),
    phoneNumber = phoneNumber,
    status = status
)
