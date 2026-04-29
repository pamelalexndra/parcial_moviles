package com.example.parcial.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.parcial.modelo.ItemOrder
import com.example.parcial.modelo.Producto
import com.example.parcial.ui.screens.MenuScreen
import com.example.parcial.ui.screens.MiOrdenScreen

@Composable
fun MainNavigation(
    orden: List<ItemOrder>,
    onAgregar: (Producto) -> Unit,
    onEliminar: (Int) -> Unit,
    onLimpiar: () -> Unit
) {
    val backStack = rememberNavBackStack(Routes.Menu)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { ruta ->
            when (ruta as Routes) {
                is Routes.Menu -> NavEntry(ruta) {
                    MenuScreen(
                        orden = orden,
                        onAgregarProducto = onAgregar,
                        onNavigateToOrden = { backStack.add(Routes.MiOrden) }
                    )
                }
                is Routes.MiOrden -> NavEntry(ruta) {
                    MiOrdenScreen(
                        orden = orden,
                        onEliminarProducto = onEliminar,
                        onConfirmarOrden = onLimpiar,
                        onNavigateBack = { backStack.removeLastOrNull() }
                    )
                }
            }
        }
    )
}