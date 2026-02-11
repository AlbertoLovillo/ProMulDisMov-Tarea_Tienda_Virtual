package com.example.promuldismov_tareatiendavirtual.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.promuldismov_tareatiendavirtual.model.AuthenticationUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthenticationViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthenticationUiState())
    val uiState: StateFlow<AuthenticationUiState> = _uiState.asStateFlow()

    fun defaultValues() {
        _uiState.value = _uiState.value.copy(
            email = "",
            password = "",
            confirmPassword = "",
            showDialogError = false
        )
    }

    fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _uiState.update { it.copy(confirmPassword = confirmPassword) }
    }


    fun clickLogin(auth: FirebaseAuth, onNavigateToHome: () -> Unit) {
        if (_uiState.value.password.length >= 6) {
            auth.signInWithEmailAndPassword(_uiState.value.email, _uiState.value.password)
                .addOnSuccessListener {
                    onNavigateToHome()
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase", "Error en login: ${e.message}", e)
                    showError()
                }
        } else showError()
    }

    fun clickRegister(auth: FirebaseAuth, onNavigateToHome: () -> Unit) {
        if (_uiState.value.password.length >= 6 && _uiState.value.confirmPassword.length >= 6) {
            if (_uiState.value.password == _uiState.value.confirmPassword) {
                auth.createUserWithEmailAndPassword(_uiState.value.email, _uiState.value.password)
                    .addOnSuccessListener { user ->
                        onNavigateToHome()
                    }
                    .addOnFailureListener { e ->
                        Log.e("Firebase", "Error en registro: ${e.message}", e)
                        showError()
                    }
            }
        } else showError()
    }

    fun showError() {
        _uiState.value = _uiState.value.copy(showDialogError = true)
    }


    fun dismissError() {
        _uiState.value = _uiState.value.copy(showDialogError = false)
    }
}
