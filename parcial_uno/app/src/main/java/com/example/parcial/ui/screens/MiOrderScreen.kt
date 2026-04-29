package com.example.parcial.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.parcial.modelo.ItemOrder
@Composable
fun MiOrdenScreen(
    orden: List<ItemOrder>,
    onEliminarProducto: (Int) -> Unit,
    onConfirmarOrden: () -> Unit,
    onNavigateBack: () -> Unit
) {
    var totalGeneral = orden.sumOf { it.producto.precio * it.cantidad }

    @OptIn(ExperimentalMaterial3Api::class)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Orden") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(orden) { item ->
                    ListItem(
                        headlineContent = { Text(item.producto.nombre) },
                        supportingContent = {
                            Text(
                                "${item.cantidad} x $${
                                    String.format(
                                        "%.2f",
                                        item.producto.precio
                                    )
                                }"
                            )
                        },
                        trailingContent = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    "$${
                                        String.format(
                                            "%.2f",
                                            item.producto.precio * item.cantidad
                                        )
                                    }"
                                )
                                IconButton(onClick = { onEliminarProducto(item.producto.id) }) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = "Eliminar",
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    )
                }
            }
            Card() {
                Column() {
                    Text("Total a pagar: $${String.format("%.2f", totalGeneral)}")
                    Button(
                        onClick = {
                            onConfirmarOrden()
                            onNavigateBack()
                        },
                        enabled = orden.isNotEmpty()
                    ) {
                        Text("Confirmar orden")
                    }
                }
            }
        }
    }
}

        
