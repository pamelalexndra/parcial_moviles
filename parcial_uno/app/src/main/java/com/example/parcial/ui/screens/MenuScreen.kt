package com.example.parcial.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.parcial.components.ProductoCard
import com.example.parcial.data.menu
import com.example.parcial.modelo.ItemOrder
import com.example.parcial.modelo.Producto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    orden: List<ItemOrder>,
    onAgregarProducto: (Producto) -> Unit,
    onNavigateToOrden: () -> Unit
) {
    val totalItems = orden.sumOf { it.cantidad }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pupusería La Ronda") },
                actions = {
                    BadgedBox(
                        badge = {
                            if (totalItems > 0) Badge { Text(totalItems.toString()) }
                        }
                    ) {
                        IconButton(onClick = onNavigateToOrden) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Ver mi orden")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValues
        ) {
            items(menu) { producto ->
                val itemEnOrden = orden.find { it.producto.id == producto.id }
                ProductoCard(
                    producto = producto,
                    cantidad = itemEnOrden?.cantidad ?: 0,
                    onClick = { onAgregarProducto(producto) }
                )
            }
        }
    }
}