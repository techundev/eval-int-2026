package com.techun.dev.pruebatecnicaintecap2026.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {

    @Serializable
    object Login : Routes()

    @Serializable
    object Dashboard : Routes()

    @Serializable
    object Employee : Routes()

    @Serializable
    object ErrorScreen : Routes()

}