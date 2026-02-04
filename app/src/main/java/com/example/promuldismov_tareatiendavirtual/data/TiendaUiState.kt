package com.example.promuldismov_tareatiendavirtual.data

import androidx.annotation.DrawableRes

data class TiendaUiState(
    @DrawableRes val drawableId: Int = 0,
    val nombreProducto: String = "",
    val precioProducto: String = ""
)