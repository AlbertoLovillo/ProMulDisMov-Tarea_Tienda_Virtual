package com.example.promuldismov_tareatiendavirtual.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.promuldismov_tareatiendavirtual.viewmodel.ShopViewModel


@Composable
fun EditCardProduct(
    viewModel: ShopViewModel,
    modifier: Modifier = Modifier,
    productName: String,
    productPrice: Double,
    productDescription: String,
    imageUrl: String,
    onView: () -> Unit,
    onUpdate: () -> Unit,
    onDelete: () -> Unit
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = productName,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "$productPrice €")
            }

            Row {
                IconButton(onClick = {
                    viewModel.updateName(productName)
                    viewModel.updatePrice(productPrice)
                    viewModel.updateDescription(productDescription)
                    viewModel.updateImage(imageUrl)
                    onView()
                }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { onUpdate() }) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { onDelete() }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }
    }
}
