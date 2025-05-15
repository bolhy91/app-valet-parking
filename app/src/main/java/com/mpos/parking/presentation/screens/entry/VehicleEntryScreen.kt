package com.mpos.parking.presentation.screens.entry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.mpos.parking.presentation.screens.entry.composables.EntryVehicleForm

@Composable
fun VehicleEntryScreen(
    viewModel: EntryViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    EntryVehicleForm(
        license = uiState.vehicleLicense,
        brand = uiState.vehicleBrand,
        model = uiState.vehicleModel,
        color = uiState.vehicleColor,
        position = uiState.position,
        availableParkingSpots = uiState.availableParkingSpots,
        isLoading = uiState.isLoading,
        error = uiState.error,
        onLicenseChanged = viewModel::updateVehicleLicense,
        onBrandChanged = viewModel::updateVehicleBrand,
        onModelChanged = viewModel::updateVehicleModel,
        onColorChanged = viewModel::updateVehicleColor,
        onPositionChanged = viewModel::updatePosition,
        onRetryLoadingSpots = viewModel::retryLoadingParkingSpots,
        onRegisterClick = {
            if (viewModel.isVehicleFormValid()) {
                navigateToHome()
            }
        }
    )
}