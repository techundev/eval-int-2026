package com.techun.dev.pruebatecnicaintecap2026.dashboard.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.techun.dev.pruebatecnicaintecap2026.R
import com.techun.dev.pruebatecnicaintecap2026.core.domain.User
import com.techun.dev.pruebatecnicaintecap2026.core.components.PruebTecIntecapButton
import com.techun.dev.pruebatecnicaintecap2026.core.components.PruebTecIntecapText


@Composable
fun PruebTecIntecapConfirmDialog(
    show: Boolean, userToEdit: User? = null, onDismiss: () -> Unit, onConfirm: (User) -> Unit
) {
    if (show) {
        AlertDialog(
            shape = MaterialTheme.shapes.medium,
            onDismissRequest = { onDismiss() },
            title = { PruebTecIntecapText(text = stringResource(R.string.dialog_confirm_title)) },
            text = { PruebTecIntecapText(text = stringResource(R.string.dialog_confirm_description)) },
            confirmButton = {
                PruebTecIntecapButton(
                    text = stringResource(R.string.button_confirm_text),
                    onclick = { onConfirm(userToEdit!!) })
            },
            dismissButton = {
                TextButton(
                    onClick = { onDismiss() }, colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Red
                    )
                ) {
                    PruebTecIntecapText(
                        text = stringResource(R.string.button_cancel_text),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
        )
    }
}