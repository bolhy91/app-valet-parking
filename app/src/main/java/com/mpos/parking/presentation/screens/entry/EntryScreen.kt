package com.mpos.parking.presentation.screens.entry

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.mpos.parking.presentation.screens.entry.composables.EntryDriverForm

@Composable
fun EntryScreen(
    viewModel: EntryViewModel,
    navigateToVehicle: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    uiState.successMessage?.let { message ->
        LaunchedEffect(message) {
            snackbarHostState.showSnackbar(message)
        }
    }

    uiState.error?.let { error ->
        LaunchedEffect(error) {
            snackbarHostState.showSnackbar(error)
        }
    }

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

    SnackbarHost(hostState = snackbarHostState)
}