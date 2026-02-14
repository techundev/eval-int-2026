package com.techun.dev.pruebatecnicaintecap2026.dashboard.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PruebTecIntecapFab(onCreateUser: () -> Unit) {
    FloatingActionButton(
        onClick = { onCreateUser() },
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}