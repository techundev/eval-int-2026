package com.techun.dev.pruebatecnicaintecap2026.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techun.dev.pruebatecnicaintecap2026.core.domain.User
import com.techun.dev.pruebatecnicaintecap2026.login.components.PruebTecIntecapText

@Composable
fun PruebTecIntecapList(
    users: List<User>, onEditSelected: (User) -> Unit, onDeleteSelected: (User) -> Unit
) {
    val listState = rememberLazyListState()
    var isDialogDisplay by remember { mutableStateOf(false) }
    var isConfirmDialogDisplay by remember { mutableStateOf(false) }
    var selectedUser by remember { mutableStateOf<User?>(null) }

    val showTopFade by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 0
        }
    }

    val showBottonFade by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

            lastVisibleItemIndex < totalItemsNumber - 1 || layoutInfo.visibleItemsInfo.lastOrNull()?.offset?.let { it + layoutInfo.visibleItemsInfo.lastOrNull()!!.size > layoutInfo.viewportEndOffset } ?: false
        }
    }

    val fadeGradient = remember(showTopFade, showBottonFade) {
        val topColor = if (showTopFade) Color.Transparent else Color.Black
        val bottomColor = if (showBottonFade) Color.Transparent else Color.Black

        Brush.verticalGradient(
            0f to topColor, 0.05f to Color.Black, 0.95f to Color.Black, 1f to bottomColor
        )
    }

    PruebTecIntecapDialog(
        title = "Edit user",
        textConfirmButtom = "Save",
        userToEdit = selectedUser,
        show = isDialogDisplay,
        onUserAdded = { user ->
            onEditSelected(user)
            isDialogDisplay = !isDialogDisplay
        },
        onDismiss = {
            isDialogDisplay = !isDialogDisplay
            selectedUser = null
        })

    PruebTecIntecapConfirmDialog(
        show = isConfirmDialogDisplay,
        userToEdit = selectedUser,
        onConfirm = {user ->
            onDeleteSelected(user)
            isConfirmDialogDisplay = !isConfirmDialogDisplay
        },
        onDismiss = {
            isConfirmDialogDisplay = !isConfirmDialogDisplay
            selectedUser = null
        })

    LazyColumn(
        state = listState,
        modifier = Modifier.fadingEdge(fadeGradient),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            PruebTecIntecapCardHeaderSection()
        }

        items(users, key = { it.id }) { currentUser ->
            PruebTecIntecapItem(currentUser, onEditSelected = { user ->
                selectedUser = user
                isDialogDisplay = true
            }, onDeleteSelected = { user ->
                selectedUser = user
                isConfirmDialogDisplay = true
            })
        }

    }
}

@Composable
fun PruebTecIntecapCardHeaderSection() {
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        PruebTecIntecapText(
            modifier = Modifier.padding(horizontal = 30.dp),
            text = "Usuarios",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

fun Modifier.fadingEdge(brush: Brush): Modifier =
    this
        .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
        .drawWithContent {
            drawContent()
            drawRect(brush = brush, blendMode = BlendMode.DstIn)
        }