package com.example.promuldismov_tareatiendavirtual.data

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TiendaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TiendaUiState())
    val uiState: StateFlow<TiendaUiState> = _uiState.asStateFlow()

    fun actualizarImagen(@DrawableRes valor: Int) {
        _uiState.update { it.copy(drawableId = valor) }
    }

    fun actualizarNombre(valor: String) {
        _uiState.update { it.copy(nombreProducto = valor) }
    }

    fun actualizarPrecio(valor: String) {
        _uiState.update { it.copy(precioProducto = valor) }
    }

}