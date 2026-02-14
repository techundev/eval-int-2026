package com.techun.dev.pruebatecnicaintecap2026.core.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PruebTecIntecapTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    shape: Shape = MaterialTheme.shapes.medium,
    isPasswordField: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val textOpacity by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(400)
    )

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier.graphicsLayer(alpha = textOpacity),
        shape = shape,
        label = { PruebTecIntecapText(text = label) },
        maxLines = 1,
        visualTransformation = if (isPasswordField && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPasswordField) KeyboardType.Password else KeyboardType.Text
        ),
        trailingIcon = {
            if (isPasswordField) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    AnimatedContent(
                        targetState = passwordVisible,
                        transitionSpec = {
                            (fadeIn(animationSpec = tween(200)) + scaleIn())
                                .togetherWith(fadeOut(animationSpec = tween(200)) + scaleOut())
                        },
                        label = "password_icon_animation"
                    ) { isVisible ->
                        Icon(
                            imageVector = if (isVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (isVisible) "Ocultar" else "Mostrar"
                        )
                    }
                }
            }
        })
}