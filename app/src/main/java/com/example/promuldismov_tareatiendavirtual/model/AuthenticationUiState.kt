package com.example.promuldismov_tareatiendavirtual.model

data class AuthenticationUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val showDialogError: Boolean = false,
)
