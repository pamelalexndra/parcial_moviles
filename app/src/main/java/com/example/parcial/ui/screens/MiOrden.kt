package com.example.parcial.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial.components.CardProducto
import com.example.parcial.data.DataProducto
import com.example.parcial.model.Producto
import com.example.parcial.navigation.calcularTotal

@Composable
fun MiOrdenScreen(
    navigateBack: () -> Unit,
    listaPedidos: List<Producto>,
    onRestart : () -> Unit
) {

    Scaffold() { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

            Button(
                onClick = {

                }
            ) {
                Text(text = "Confirmar orden")
            }

            Button(
                onClick = onRestart,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    text = "Volver a menu y reiniciar orden",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                items(listaPedidos) { producto ->
                    Text(text = producto.nombre)
                }
                Text(
                    text = producto.nombre,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                
            }
        }
    }
}
