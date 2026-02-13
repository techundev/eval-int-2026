package com.techun.dev.pruebatecnicaintecap2026.core.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.techun.dev.pruebatecnicaintecap2026.core.ex.back
import com.techun.dev.pruebatecnicaintecap2026.core.ex.navigateAndClear
import com.techun.dev.pruebatecnicaintecap2026.core.navigation.Routes.Dashboard
import com.techun.dev.pruebatecnicaintecap2026.core.navigation.Routes.Employee
import com.techun.dev.pruebatecnicaintecap2026.core.navigation.Routes.ErrorScreen
import com.techun.dev.pruebatecnicaintecap2026.core.navigation.Routes.Login
import com.techun.dev.pruebatecnicaintecap2026.dashboard.ui.DashboardScreen
import com.techun.dev.pruebatecnicaintecap2026.employee.EmployeeScreen
import com.techun.dev.pruebatecnicaintecap2026.core.domain.UserRole
import com.techun.dev.pruebatecnicaintecap2026.login.ui.LoginScreen

@Composable
fun Navigation3Wrapper() {
    val backStack = rememberNavBackStack(Login)

    NavDisplay(backStack = backStack, onBack = { backStack.back() }, entryProvider = entryProvider {
        entry<Login> {
            LoginScreen(navigateToDashboard = { userRole ->
                when (userRole) {
                    UserRole.ADMIN -> {
                        backStack.navigateAndClear(Dashboard)
                    }

                    UserRole.EMPLOYEE -> {
                        backStack.navigateAndClear(Employee)
                    }

                    UserRole.UNKNOWN -> {
                        backStack.navigateAndClear(Dashboard)
                    }
                }

            })
        }
        entry<Dashboard> {
            DashboardScreen()
        }

        entry<Employee> {
            EmployeeScreen(navigateBack = { backStack.navigateAndClear(Login)})
        }

        entry<ErrorScreen> {
            Text(text = "Modulo en mantenimiento")
        }
    })
}