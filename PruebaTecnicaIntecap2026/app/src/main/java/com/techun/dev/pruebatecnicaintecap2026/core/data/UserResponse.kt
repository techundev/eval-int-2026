package com.techun.dev.pruebatecnicaintecap2026.core.data

import com.techun.dev.pruebatecnicaintecap2026.core.domain.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    @SerialName("nombre") val name: String,
    @SerialName("apellido") val lastName: String,
    @SerialName("usuario") val username: String,
    @SerialName("correo") val email: String,
    val password: String,
    @SerialName("rol") val role: Int,
    @SerialName("telefono") val phoneNumber: String,
    @SerialName("estado") val status: Int
)

fun User.toNetwork() = UserResponse(
    id = email,
    name = name,
    lastName = lastName,
    username = username,
    email = email,
    password = password,
    role = role.value,
    phoneNumber = phoneNumber,
    status = status
)