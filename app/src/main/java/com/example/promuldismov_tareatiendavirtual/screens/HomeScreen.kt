package com.example.promuldismov_tareatiendavirtual.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.promuldismov_tareatiendavirtual.AppNavigation
import com.example.promuldismov_tareatiendavirtual.components.CardProducto
import com.example.promuldismov_tareatiendavirtual.R
import com.example.promuldismov_tareatiendavirtual.ui.theme.ProMulDisMov_TareaTiendaVirtualTheme

@Composable
fun HomeScreen(onProductClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Catálogo disponible:",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        CardProducto(
            viewModel = viewModel(),
            onProductClick = onProductClick,
            id = R.drawable.gorra,
            nombreProducto = "Gorra",
            precioProducto = "14'99"
        )
        CardProducto(
            viewModel = viewModel(),
            onProductClick = onProductClick,
            id = R.drawable.sudadera,
            nombreProducto = "Sudadera",
            precioProducto = "34'99"
        )
        CardProducto(
            viewModel = viewModel(),
            onProductClick = onProductClick,
            id = R.drawable.pantalon,
            nombreProducto = "Pantalon",
            precioProducto = "24'99"
        )
        CardProducto(
            viewModel = viewModel(),
            onProductClick = onProductClick,
            id = R.drawable.zapato,
            nombreProducto = "Zapato",
            precioProducto = "39'99"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProMulDisMov_TareaTiendaVirtualTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppNavigation(padding =  innerPadding)
        }
    }
}