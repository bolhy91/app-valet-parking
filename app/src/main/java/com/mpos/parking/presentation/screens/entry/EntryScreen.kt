package com.mpos.parking.presentation.screens.entry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.mpos.parking.presentation.screens.entry.composables.EntryDriverForm

@Composable
fun EntryScreen(
    viewModel: EntryViewModel = hiltViewModel(),
    navigateToVehicle: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    EntryDriverForm(
        name = uiState.driverName,
        dni = uiState.driverDni,
        phone = uiState.driverPhone,
        onNameChanged = viewModel::updateDriverName,
        onDniChanged = viewModel::updateDriverDni,
        onPhoneChanged = viewModel::updateDriverPhone,
        onContinueClick = {
            if (viewModel.isDriverFormValid()) {
                navigateToVehicle()
            }
        }
    )
}