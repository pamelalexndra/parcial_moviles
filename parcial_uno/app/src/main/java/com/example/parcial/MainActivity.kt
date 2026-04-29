package com.example.parcial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.parcial.modelo.ItemOrder
import com.example.parcial.modelo.Producto
import com.example.parcial.navigation.MainNavigation
import com.example.parcial.ui.theme.ParcialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParcialTheme {
                val orden = remember { mutableStateListOf<ItemOrder>() }

                val agregarProducto: (Producto) -> Unit = { producto ->
                    val index = orden.indexOfFirst { it.producto.id == producto.id }
                    if (index >= 0) {
                        orden[index] = orden[index].copy(cantidad = orden[index].cantidad + 1)
                    } else {
                        orden.add(ItemOrder(producto, 1))
                    }
                }

                val eliminarProducto: (Int) -> Unit = { id ->
                    orden.removeAll { it.producto.id == id }
                }

                val limpiarOrden: () -> Unit = { orden.clear() }

                MainNavigation(
                    orden = orden,
                    onAgregar = agregarProducto,
                    onEliminar = eliminarProducto,
                    onLimpiar = limpiarOrden)
            }
        }
    }
}

