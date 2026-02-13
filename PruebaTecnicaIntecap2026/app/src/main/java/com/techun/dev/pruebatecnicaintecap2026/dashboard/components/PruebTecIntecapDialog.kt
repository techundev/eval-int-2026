package com.techun.dev.pruebatecnicaintecap2026.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.techun.dev.pruebatecnicaintecap2026.core.domain.User
import com.techun.dev.pruebatecnicaintecap2026.core.domain.UserRole
import com.techun.dev.pruebatecnicaintecap2026.login.components.PruebTecIntecapButton
import com.techun.dev.pruebatecnicaintecap2026.login.components.PruebTecIntecapText
import com.techun.dev.pruebatecnicaintecap2026.login.components.PruebTecIntecapTextField


@Composable
fun PruebTecIntecapDialog(
    show: Boolean, onDismiss: () -> Unit, onUserAdded: (User) -> Unit
) {
    var name by remember { mutableStateOf("Armando") }
    var userName by remember { mutableStateOf("smartemboser") }
    var lastName by remember { mutableStateOf("Santos") }
    var email by remember { mutableStateOf("smartemboser@gmail.com") }
    var phoneNumber by remember { mutableStateOf("5555-5555") }
    var userRole by remember { mutableStateOf(UserRole.ADMIN) }
    var selectedName by remember { mutableStateOf("") }

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
                    text = "Add New User",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
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
                PruebTecIntecapButton(
                    text = "Create user", modifier = Modifier.fillMaxWidth(), onclick = {
                        val idUser = System.currentTimeMillis().toString()
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