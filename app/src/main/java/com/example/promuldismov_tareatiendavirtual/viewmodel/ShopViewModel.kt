package com.example.promuldismov_tareatiendavirtual.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.promuldismov_tareatiendavirtual.model.Product
import com.example.promuldismov_tareatiendavirtual.model.ShopUiState
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShopViewModel : ViewModel() {

    private val db = Firebase.firestore
    private val productsCollection = db.collection("productos")

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _uiState = MutableStateFlow(ShopUiState())
    val uiState: StateFlow<ShopUiState> = _uiState.asStateFlow()

    init {
        getProduct()
    }


    fun updateName(valor: String) {
        _uiState.update { it.copy(productName = valor) }
    }

    fun updatePrice(valor: Double) {
        _uiState.update { it.copy(productPrice = valor) }
    }

    fun updateDescription(valor: String) {
        _uiState.update { it.copy(productDescription = valor) }
    }

    fun updateImage(valor: String) {
        _uiState.update { it.copy(imageUrl = valor) }
    }

    fun cleanState() {
        _uiState.value = ShopUiState()
    }

    fun getProduct() {
        productsCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val productsList = snapshot.documents.mapNotNull { doc ->
                    val product = doc.toObject(Product::class.java)
                    product?.id = doc.id
                    product
                }
                _products.value = productsList
            }
        }
    }

    fun addProduct() {
        val product = Product(
            name = _uiState.value.productName,
            price = _uiState.value.productPrice,
            description = _uiState.value.productDescription,
            imageUrl = _uiState.value.imageUrl
        )
        productsCollection.add(product)
            .addOnFailureListener { e ->
                Log.e("Error Firebase", "Error al guardar: ${e.message}", e)
            }
            .addOnSuccessListener { }
            .addOnCompleteListener { }

        cleanState()
    }

    fun deleteProduct(id: String) {
        productsCollection.document(id)
            .delete()
            .addOnSuccessListener {
                Log.i("Firebase", "Borrado correcto")
            }
            .addOnFailureListener { e ->
                Log.e("Error Firebase", "Error al eliminar: ${e.message}", e)
            }
    }

    fun updateProduct(id: String) {
        val datosActualizados = mutableMapOf<String, Any>()

        if (_uiState.value.productName.isNotBlank()) {
            datosActualizados["name"] = _uiState.value.productName
        }
        datosActualizados["price"] = _uiState.value.productPrice
        if (_uiState.value.productDescription.isNotBlank()) {
            datosActualizados["description"] = _uiState.value.productDescription
        }
        if (_uiState.value.imageUrl.isNotBlank()) {
            datosActualizados["imageUrl"] = _uiState.value.imageUrl
        }

        productsCollection.document(id)
            .update(datosActualizados)
            .addOnSuccessListener {
                Log.i("Firebase", "Actualizado correcto")
            }
            .addOnFailureListener { e ->
                Log.e("Error Firebase", "Error al actualizar: ${e.message}", e)
            }

        cleanState()
    }
}
