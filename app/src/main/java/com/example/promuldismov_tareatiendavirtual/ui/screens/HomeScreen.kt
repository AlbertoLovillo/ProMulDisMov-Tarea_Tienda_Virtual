package com.example.promuldismov_tareatiendavirtual.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.promuldismov_tareatiendavirtual.ui.components.CardProduct
import com.example.promuldismov_tareatiendavirtual.ui.components.UpMessage
import com.example.promuldismov_tareatiendavirtual.ui.theme.ProMulDisMov_TareaTiendaVirtualTheme
import com.example.promuldismov_tareatiendavirtual.viewmodel.ShopViewModel

@Composable
fun HomeScreen(
    viewModel: ShopViewModel = viewModel<ShopViewModel>(),
    onProductClick: () -> Unit,
    onAddProductClick: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val products by viewModel.products.collectAsState()

    Scaffold(
        topBar = { UpMessage(onNavigateToLogin = onNavigateToLogin) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            Text(
                text = "Catalog available:",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .height(50.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .weight(1f)
            ) {
                items(products, key = { it.id }) { product ->
                    CardProduct(
                        viewModel = viewModel,
                        productName = product.name,
                        productPrice = product.price,
                        onProductClick = onProductClick,
                        imageUrl = product.imageUrl,
                        productDescription = product.description
                    )
                }
            }

            Button(
                onClick = onAddProductClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(50.dp)
            ) {
                Text("Add product")
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProMulDisMov_TareaTiendaVirtualTheme {

    }
}