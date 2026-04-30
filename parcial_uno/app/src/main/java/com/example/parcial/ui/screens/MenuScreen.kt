package com.example.parcial.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
        containerColor = Color(0xFFFFF3E0),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Pupusería La Ronda",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFF9800),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = onNavigateToOrden) {
                        BadgedBox(
                            badge = {
                                if (totalItems > 0) {
                                    Badge(
                                        containerColor = Color(0xFFD32F2F),
                                        contentColor = Color.White
                                    ) {
                                        Text(totalItems.toString())
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Ver mi orden",
                                tint = Color.White
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                Text(
                    text = "Menú",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFE65100),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

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