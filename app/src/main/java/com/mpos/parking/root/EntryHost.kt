package com.mpos.parking.root

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mpos.parking.presentation.screens.entry.EntryScreen
import com.mpos.parking.presentation.screens.entry.VehicleEntryScreen

@Composable
fun EntryNav(
    navigateToHome: () -> Unit,
) {
    val navigator = rememberNavController()
    NavHost(navController = navigator, startDestination = EntryRoute) {
        composable<EntryRoute> {
            EntryScreen(
                navigateToVehicle = { navigator.navigate(VehicleEntryRoute) },
                navigateToHome = navigateToHome,
            )
        }
        composable<VehicleEntryRoute> {
            VehicleEntryScreen(
                navigateToHome = navigateToHome,
            )
        }
    }
}