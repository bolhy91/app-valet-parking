package com.mpos.parking.presentation.screens.entry

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(EntryUiState())
    val uiState: StateFlow<EntryUiState> = _uiState.asStateFlow()

    fun updateDriverName(name: String) {
        _uiState.update { it.copy(driverName = name) }
    }

    fun updateDriverDni(dni: String) {
        _uiState.update { it.copy(driverDni = dni) }
    }

    fun updateDriverPhone(phone: String) {
        _uiState.update { it.copy(driverPhone = phone) }
    }

    fun updateVehicleLicense(license: String) {
        _uiState.update { it.copy(vehicleLicense = license) }
    }

    fun updateVehicleBrand(brand: String) {
        _uiState.update { it.copy(vehicleBrand = brand) }
    }

    fun updateVehicleModel(model: String) {
        _uiState.update { it.copy(vehicleModel = model) }
    }

    fun updateVehicleColor(color: String) {
        _uiState.update { it.copy(vehicleColor = color) }
    }

    fun updatePosition(position: String) {
        _uiState.update { it.copy(position = position) }
    }

    fun updateObservation(observation: String) {
        _uiState.update { it.copy(observation = observation) }
    }

    fun isDriverFormValid(): Boolean {
        return _uiState.value.driverName.isNotBlank() &&
                _uiState.value.driverDni.isNotBlank() &&
                _uiState.value.driverPhone.isNotBlank()
    }

    fun isVehicleFormValid(): Boolean {
        return _uiState.value.vehicleLicense.isNotBlank() &&
                _uiState.value.vehicleBrand.isNotBlank() &&
                _uiState.value.vehicleModel.isNotBlank() &&
                _uiState.value.vehicleColor.isNotBlank()
    }
}