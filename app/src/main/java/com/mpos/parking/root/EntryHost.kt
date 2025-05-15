package com.mpos.parking.root

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mpos.parking.presentation.screens.entry.EntryScreen
import com.mpos.parking.presentation.screens.entry.EntryViewModel
import com.mpos.parking.presentation.screens.entry.VehicleEntryScreen

@Composable
fun EntryNav() {
    val navigator = rememberNavController()
    val viewModel: EntryViewModel = hiltViewModel()
    NavHost(navController = navigator, startDestination = EntryRoute) {
        composable<EntryRoute> {
            EntryScreen(
                viewModel = viewModel,
                navigateToVehicle = { navigator.navigate(VehicleEntryRoute) },
            )
        }
        composable<VehicleEntryRoute> {
            VehicleEntryScreen(
                viewModel = viewModel,
                navigateToEntry = { navigator.navigate(EntryRoute) },
            )
        }
    }
}