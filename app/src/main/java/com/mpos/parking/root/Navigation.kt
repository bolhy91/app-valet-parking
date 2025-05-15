package com.mpos.parking.root

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainNavigation(
    val route: Route,
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
) {
    data object Home : MainNavigation(
        route = HomeRoute,
        title = "Inicio",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    )

    data object Entry : MainNavigation(
        route = EntryRoute,
        title = "Nuevo Ingreso",
        selectedIcon = Icons.Filled.DirectionsCar,
        unSelectedIcon = Icons.Outlined.DirectionsCar
    )

    data object Record : MainNavigation(
        route = RecordRoute,
        title = "Historial",
        selectedIcon = Icons.AutoMirrored.Filled.ReceiptLong,
        unSelectedIcon = Icons.AutoMirrored.Outlined.ReceiptLong
    )
}