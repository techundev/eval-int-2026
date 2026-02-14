package com.techun.dev.pruebatecnicaintecap2026.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import com.techun.dev.pruebatecnicaintecap2026.R
import com.techun.dev.pruebatecnicaintecap2026.core.domain.User
import com.techun.dev.pruebatecnicaintecap2026.core.components.PruebTecIntecapText


@Composable
fun PruebTecIntecapItem(
    user: User, onEditSelected: (User) -> Unit, onDeleteSelected: (User) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.large
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (header, imgProfile, userInfo) = createRefs()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .constrainAs(header) {
                        top.linkTo(parent.top)
                    }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically) {
                PruebTecIntecapText(
                    text = user.role.name, color = Color.White, fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                PruebTecIntecapIconAction(
                    icon = Icons.Filled.Edit, onIconSelected = { onEditSelected(user) })
                Spacer(modifier = Modifier.width(10.dp))
                PruebTecIntecapIconAction(
                    onIconSelected = { onDeleteSelected(user) }, icon = Icons.Filled.Delete
                )
            }

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .border(4.dp, Color.White, CircleShape)
                    .constrainAs(imgProfile) {
                        top.linkTo(header.bottom)
                        bottom.linkTo(header.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                AsyncImage(
                    model = "https://images.vexels.com/media/users/3/137047/isolated/preview/5831a17a290077c646a48c4db78a81bb-user-profile-blue-icon.png",
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 16.dp)
                    .constrainAs(userInfo) {
                        top.linkTo(header.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PruebTecIntecapText(
                    text = "${user.name} ${user.lastName}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                PruebTecIntecapText(
                    text = user.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                PruebTecIntecapText(
                    text = user.phoneNumber,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}
