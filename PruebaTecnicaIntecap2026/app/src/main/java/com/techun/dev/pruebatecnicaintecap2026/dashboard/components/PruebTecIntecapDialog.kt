package com.techun.dev.pruebatecnicaintecap2026.dashboard.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.techun.dev.pruebatecnicaintecap2026.core.components.PruebTecIntecapButtonSeconday
import com.techun.dev.pruebatecnicaintecap2026.core.domain.User
import com.techun.dev.pruebatecnicaintecap2026.core.domain.UserRole
import com.techun.dev.pruebatecnicaintecap2026.login.components.PruebTecIntecapButton
import com.techun.dev.pruebatecnicaintecap2026.login.components.PruebTecIntecapText
import com.techun.dev.pruebatecnicaintecap2026.login.components.PruebTecIntecapTextField


@Composable
fun PruebTecIntecapDialog(
    title: String,
    textConfirmButtom: String,
    userToEdit: User? = null,
    show: Boolean,
    onDismiss: () -> Unit,
    onUserAdded: (User) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var userRole by remember { mutableStateOf(UserRole.ADMIN) }
    var selectedName by remember { mutableStateOf("Admin") }


    LaunchedEffect(userToEdit, show) {
        if (show) {
            name = userToEdit?.name ?: ""
            userName = userToEdit?.username ?: ""
            lastName = userToEdit?.lastName ?: ""
            email = userToEdit?.email ?: ""
            phoneNumber = userToEdit?.phoneNumber ?: ""
            userRole = userToEdit?.role ?: UserRole.ADMIN
            selectedName = if (userRole == UserRole.ADMIN) "Admin" else "Employee"
        }
    }

    if (show) {
        Dialog(onDismissRequest = onDismiss) {
            Column(
                Modifier
                    .testTag("dialog_create_user")
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.background, shape = MaterialTheme.shapes.medium
                    )
                    .padding(16.dp)
            ) {
                PruebTecIntecapText(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(Modifier.height(16.dp))
                PruebTecIntecapTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    label = "Name",
                    onValueChange = { name = it })
                Spacer(Modifier.height(5.dp))
                PruebTecIntecapTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = lastName,
                    label = "Lastname",
                    onValueChange = { lastName = it })
                Spacer(Modifier.height(5.dp))
                PruebTecIntecapTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = userName,
                    label = "Username",
                    onValueChange = { userName = it })
                Spacer(Modifier.height(5.dp))
                PruebTecIntecapTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    label = "Email",
                    onValueChange = { email = it })
                Spacer(Modifier.height(5.dp))
                PruebTecIntecapTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = phoneNumber,
                    label = "Phone Number",
                    onValueChange = { phoneNumber = it })
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(Modifier.height(16.dp))
                    PruebTecIntecapText(
                        text = "User role"
                    )
                    PruebTecIntecapRadioButton(
                        name = "Admin", selectedName = selectedName
                    ) {
                        userRole = UserRole.ADMIN
                        selectedName = it
                    }
                    PruebTecIntecapRadioButton(
                        name = "Employee", selectedName = selectedName
                    ) {
                        userRole = UserRole.EMPLOYEE
                        selectedName = it
                    }
                }
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PruebTecIntecapButtonSeconday(
                        modifier = Modifier.weight(1f),
                        text = "Cancelar",
                        textColor = Color.Red,
                        border = BorderStroke(1.dp, Color.Red),
                        onClick = { onDismiss() })
                    Spacer(Modifier.width(10.dp))
                    PruebTecIntecapButton(
                        text = textConfirmButtom, modifier = Modifier.weight(1f), onclick = {
                            val idUser =
                                if (userToEdit?.id != null) userToEdit.id else System.currentTimeMillis()
                                    .toString()
                            val user = User(
                                idUser,
                                name,
                                lastName,
                                userName,
                                email,
                                "1234",
                                userRole,
                                phoneNumber,
                                1
                            )

                            onUserAdded(user)
                            name = ""
                            lastName = ""
                            userName = ""
                            email = ""
                            phoneNumber = ""
                            userRole = UserRole.ADMIN
                        })
                }
            }
        }
    }
}