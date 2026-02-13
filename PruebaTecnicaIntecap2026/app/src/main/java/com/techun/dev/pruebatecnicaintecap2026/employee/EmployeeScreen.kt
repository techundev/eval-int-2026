package com.techun.dev.pruebatecnicaintecap2026.employee

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.techun.dev.pruebatecnicaintecap2026.R
import com.techun.dev.pruebatecnicaintecap2026.core.components.PruebTecIntecapButtonSeconday
import com.techun.dev.pruebatecnicaintecap2026.login.components.PruebTecIntecapText

@Composable
fun EmployeeScreen(navigateBack: () -> Unit) {
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
            PruebTecIntecapText(
                text = stringResource(R.string.employee_screen_feature_in_progress),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            PruebTecIntecapButtonSeconday(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.employee_screen_button_logout),
                onClick = { navigateBack() })
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}