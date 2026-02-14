package com.techun.dev.pruebatecnicaintecap2026.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun PruebTecIntecapButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    onclick: () -> Unit,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
    textColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Button(
        modifier = modifier,
        colors = colors,
        shape = shape,
        onClick = { onclick() },
        enabled = enabled
    ) {
        PruebTecIntecapText(
            modifier = Modifier.padding(vertical = 4.dp),
            text = text,
            color = textColor,
        )
    }
}