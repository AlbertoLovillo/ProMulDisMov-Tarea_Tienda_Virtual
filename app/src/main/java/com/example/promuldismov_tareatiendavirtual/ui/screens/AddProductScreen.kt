package com.example.promuldismov_tareatiendavirtual.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.promuldismov_tareatiendavirtual.ui.components.EditCardProduct
import com.example.promuldismov_tareatiendavirtual.ui.components.UpMessage
import com.example.promuldismov_tareatiendavirtual.viewmodel.ShopViewModel

@Composable
fun AddProductScreen(
    viewModel: ShopViewModel = viewModel<ShopViewModel>(),
    onView: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val products by viewModel.products.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UpMessage(onNavigateToLogin = onNavigateToLogin) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Register",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(20.dp)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Name") },
                value = uiState.productName,
                onValueChange = { viewModel.updateName(it) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Price") },
                value = "${uiState.productPrice}",
                onValueChange = { viewModel.updatePrice(it.toDouble()) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Description") },
                value = uiState.productDescription,
                onValueChange = { viewModel.updateDescription(it) }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Image URL") },
                value = uiState.imageUrl,
                onValueChange = { viewModel.updateImage(it) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.addProduct() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = "Add product")
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(products) { product ->
                    EditCardProduct(
                        viewModel = viewModel,
                        productName = product.name,
                        productPrice = product.price,
                        productDescription = product.description,
                        imageUrl = product.imageUrl,
                        onView = onView,
                        onUpdate = { viewModel.updateProduct(id = product.id) },
                        onDelete = { viewModel.deleteProduct(id = product.id) },
                    )
                }
            }
        }
    }
}