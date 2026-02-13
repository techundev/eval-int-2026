package com.techun.dev.pruebatecnicaintecap2026.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.techun.dev.pruebatecnicaintecap2026.login.components.PruebTecIntecapText

@Composable
fun PruebTecIntecapButtonSeconday(
    text: String,
    modifier: Modifier = Modifier,
    border: BorderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
    textColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier.padding(vertical = 4.dp), border = border, onClick = onClick
    ) {
        PruebTecIntecapText(
            modifier = Modifier.padding(vertical = 4.dp),
            text = text,
            color = textColor,
        )
    }
}
