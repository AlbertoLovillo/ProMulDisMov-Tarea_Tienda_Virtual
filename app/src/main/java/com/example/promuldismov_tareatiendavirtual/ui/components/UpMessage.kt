package com.example.promuldismov_tareatiendavirtual.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.promuldismov_tareatiendavirtual.viewmodel.AuthenticationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpMessage(
    viewModel: AuthenticationViewModel = viewModel<AuthenticationViewModel>(),
    onNavigateToLogin: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    TopAppBar(
        title = {
            Text(
                text = "Bienvenido, ${uiState.email}",
                fontSize = 15.sp
            )
        },
        actions = {
            IconButton(
                onClick = {
                    onNavigateToLogin()
                    viewModel.defaultValues()
                }
            ){
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = "Cerrar sesión"
                )
            }
        }
    )
}