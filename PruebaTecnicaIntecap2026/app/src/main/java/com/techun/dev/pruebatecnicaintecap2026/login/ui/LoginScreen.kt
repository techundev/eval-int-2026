package com.techun.dev.pruebatecnicaintecap2026.login.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techun.dev.pruebatecnicaintecap2026.R
import com.techun.dev.pruebatecnicaintecap2026.core.components.PruebTecIntecapButton
import com.techun.dev.pruebatecnicaintecap2026.core.components.PruebTecIntecapText
import com.techun.dev.pruebatecnicaintecap2026.core.components.PruebTecIntecapTextField
import com.techun.dev.pruebatecnicaintecap2026.core.domain.UserRole

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(), navigateToDashboard: (UserRole) -> Unit
) {
    val context = LocalContext.current
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.error) {
        uiState.error?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            loginViewModel.clearError()
        }
    }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            val role = uiState.role
            navigateToDashboard(role)
            loginViewModel.resetNavigation()
        }
    }

    Scaffold { padding ->
        Column(
            Modifier
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(1f))
            PruebTecIntecapTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.login_screen_textfield_email),
                value = uiState.user,
                onValueChange = { loginViewModel.onUserChanged(it) })
            Spacer(modifier = Modifier.height(10.dp))
            PruebTecIntecapTextField(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.login_screen_textfield_password),
                value = uiState.password,
                isPasswordField = true,
                onValueChange = { loginViewModel.onPasswordChanged(it) })
            Spacer(modifier = Modifier.height(10.dp))
            PruebTecIntecapButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.login_screen_button_login),
                onclick = {
                    loginViewModel.doLogin()
                })
            Spacer(modifier = Modifier.weight(1f))
            PruebTecIntecapText(
                text = "Technical Assessment Build",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
        }
    }
}