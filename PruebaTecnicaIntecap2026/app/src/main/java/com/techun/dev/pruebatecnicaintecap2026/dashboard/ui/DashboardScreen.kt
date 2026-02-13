package com.techun.dev.pruebatecnicaintecap2026.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techun.dev.pruebatecnicaintecap2026.dashboard.components.PruebTecIntecapList

@Composable
fun DashboardScreen(dashboardViewModel: DashboardViewModel = hiltViewModel()) {
    val tasks by dashboardViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(floatingActionButton = {
//        ToDoFabDialog(
//            onAddTask = {
//                taskViewModel.onShowDialog()
//            })
    }) { paddingValues ->
        when (tasks) {
            is DashboardUiState.Error -> {}
            DashboardUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is DashboardUiState.Success -> {
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    PruebTecIntecapList(
                        (tasks as DashboardUiState.Success).users,
                        onEditSelected = { userInfo -> dashboardViewModel.onItemEdited(userInfo) },
                        onDeleteSelected = { userInfo -> dashboardViewModel.onItemRemove(userInfo) })
                }
            }
        }
    }
}