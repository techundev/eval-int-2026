package com.techun.dev.pruebatecnicaintecap2026.dashboard.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun PruebTecIntecapIconAction(
    icon: ImageVector, contentDescription: String? = null, onIconSelected: () -> Unit
) {
    IconButton(onClick = { onIconSelected() }) {
        Icon(
            imageVector = icon, contentDescription = contentDescription, tint = Color.White
        )
    }
}