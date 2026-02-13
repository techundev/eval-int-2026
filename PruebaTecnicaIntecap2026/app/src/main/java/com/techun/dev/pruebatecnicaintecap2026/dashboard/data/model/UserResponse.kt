package com.techun.dev.pruebatecnicaintecap2026.dashboard.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("nombre") val name: String,
    @SerialName("apellido") val lastName: String,
    @SerialName("rol") val role: Int,
    @SerialName("correo") val email: String,
    @SerialName("telefono") val phoneNumber: String
)