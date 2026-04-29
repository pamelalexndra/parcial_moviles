package com.example.parcial.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.parcial.modelo.Producto

@Composable
fun ProductoCard(
    producto: Producto,
    cantidad: Int,
    onClick : () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = producto.imagenUrl,
                contentDescription = producto.nombre,
            )
            Column() {
                Text(
                    text = producto.nombre
                )
                Text(
                    text = "$${String.format("%.2f", producto.precio)}"
                )
            }
            if (cantidad > 0) {
                Badge() {
                    Text(
                        text = cantidad.toString()
                    )
                }
            }
        }
    }
}