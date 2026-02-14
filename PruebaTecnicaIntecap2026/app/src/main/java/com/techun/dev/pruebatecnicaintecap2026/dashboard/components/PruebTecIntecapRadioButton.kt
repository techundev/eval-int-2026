package com.techun.dev.pruebatecnicaintecap2026.dashboard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.techun.dev.pruebatecnicaintecap2026.core.components.PruebTecIntecapText

@Composable
fun PruebTecIntecapRadioButton(
    name: String, selectedName: String, onItemSelected: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = name == selectedName, onClick = { onItemSelected(name) })
        PruebTecIntecapText(text = name, modifier = Modifier.clickable { onItemSelected(name) })
    }
}
