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
import com.techun.dev.pruebatecnicaintecap2026.dashboard.components.PruebTecIntecapDialog
import com.techun.dev.pruebatecnicaintecap2026.dashboard.components.PruebTecIntecapFab
import com.techun.dev.pruebatecnicaintecap2026.dashboard.components.PruebTecIntecapList

@Composable
fun DashboardScreen(dashboardViewModel: DashboardViewModel = hiltViewModel()) {
    val dashState by dashboardViewModel.uiState.collectAsStateWithLifecycle()
    val showDialog by dashboardViewModel.showDialog.collectAsStateWithLifecycle()

    Scaffold(floatingActionButton = {
        PruebTecIntecapFab(
            onCreateUser = {
                dashboardViewModel.onShowDialog()
            })
    }) { paddingValues ->
        when (dashState) {
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
                    PruebTecIntecapDialog(
                        title = "Add New User",
                        textConfirmButtom =  "Create user",
                        show = showDialog,
                        onUserAdded = { auth -> dashboardViewModel.onCreateUser(auth) },
                        onDismiss = { dashboardViewModel.onDialogClose() })

                    PruebTecIntecapList(
                        (dashState as DashboardUiState.Success).users,
                        onEditSelected = { userInfo -> dashboardViewModel.onItemEdited(userInfo) },
                        onDeleteSelected = { userInfo -> dashboardViewModel.onItemRemove(userInfo) })
                }
            }
        }
    }
}