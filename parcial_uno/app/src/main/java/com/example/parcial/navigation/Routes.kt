package com.example.parcial.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
    @Serializable
    data object Menu : Routes()

    @Serializable
    data object MiOrden : Routes()
}