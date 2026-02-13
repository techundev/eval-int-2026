package com.techun.dev.pruebatecnicaintecap2026.login.domain.model

enum class UserRole(val value: Int) {
    ADMIN(0),
    EMPLOYEE(1),
    UNKNOWN(-1);

    companion object {
        // Función para convertir el Int del servidor al Enum
        fun fromInt(value: Int): UserRole {
            return entries.find { it.value == value } ?: UNKNOWN
        }
    }
}