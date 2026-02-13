package com.techun.dev.pruebatecnicaintecap2026

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.techun.dev.pruebatecnicaintecap2026.core.navigation.Navigation3Wrapper
import com.techun.dev.pruebatecnicaintecap2026.ui.theme.PruebaTecnicaIntecap2026Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaTecnicaIntecap2026Theme {
                Navigation3Wrapper()
            }
        }
    }
}