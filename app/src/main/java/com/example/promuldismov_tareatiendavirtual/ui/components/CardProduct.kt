package com.example.promuldismov_tareatiendavirtual.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.promuldismov_tareatiendavirtual.viewmodel.ShopViewModel


@Composable
fun CardProduct(
    viewModel: ShopViewModel,
    productName: String,
    productPrice: Double,
    productDescription: String,
    imageUrl: String,
    onProductClick: () -> Unit
) {
    Card( modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = productName,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$productPrice €",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Button(
                onClick = {
                    viewModel.updateName(productName)
                    viewModel.updatePrice(productPrice)
                    viewModel.updateDescription(productDescription)
                    viewModel.updateImage(imageUrl)
                    onProductClick()
                }
            ) {
                Text(text = "View")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CardPreview() {
//    ProMulDisMov_TareaTiendaVirtualTheme {
//        CardProducto(
//            id = R.drawable.gorra,
//            nombreProducto = "Gorra",
//            precioProducto = "14'99"
//        )
//    }
//}


