package com.techun.dev.pruebatecnicaintecap2026.dashboard.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.techun.dev.pruebatecnicaintecap2026.core.domain.User
import com.techun.dev.pruebatecnicaintecap2026.login.components.PruebTecIntecapButton
import com.techun.dev.pruebatecnicaintecap2026.login.components.PruebTecIntecapText


@Composable
fun PruebTecIntecapConfirmDialog(
    show: Boolean, userToEdit: User? = null, onDismiss: () -> Unit, onConfirm: (User) -> Unit
) {
    if (show) {
        AlertDialog(
            shape = MaterialTheme.shapes.medium,
            onDismissRequest = { onDismiss() },
            title = { PruebTecIntecapText(text = "Confirmar eliminación") },
            text = { PruebTecIntecapText(text = "¿Estás seguro de eliminar este registro?") },
            confirmButton = {
                PruebTecIntecapButton(
                    text = "Acaptar", onclick = { onConfirm(userToEdit!!) })
            },
            dismissButton = {
                TextButton(
                    onClick = { onDismiss() }, colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Red
                    )
                ) {
                    PruebTecIntecapText(
                        text = "Cancelar", color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
        )
    }
}