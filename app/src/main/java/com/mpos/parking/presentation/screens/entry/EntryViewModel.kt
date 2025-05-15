package com.mpos.parking.presentation.screens.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpos.parking.domain.usecases.CheckVehicleParkedUseCase
import com.mpos.parking.domain.usecases.GetAvailableParkingUseCase
import com.mpos.parking.domain.usecases.RegisterEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val getAvailableParkingUseCase: GetAvailableParkingUseCase,
    private val registerEntryUseCase: RegisterEntryUseCase,
    private val checkVehicleAlreadyParkedUseCase: CheckVehicleParkedUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(EntryUiState())
    val uiState: StateFlow<EntryUiState> = _uiState.asStateFlow()

    init {
        loadAvailableParkingSpots()
    }

    private fun loadAvailableParkingSpots() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null) }
                val spots = getAvailableParkingUseCase()
                _uiState.update { it.copy(availableParkingSpots = spots, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = "Error al cargar los espacios disponibles: ${e.message}",
                        isLoading = false
                    )
                }
            }
        }
    }

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
        _uiState.update {
            it.copy(
                vehicleLicense = license,
                licenseError = null,
                isCheckingLicense = true
            )
        }
        viewModelScope.launch {
            delay(500)
            if (_uiState.value.vehicleLicense == license && license.isNotBlank()) {
                checkIfVehicleIsAlreadyParked(license)
            } else {
                _uiState.update { it.copy(isCheckingLicense = false) }
            }
        }
    }

    private suspend fun checkIfVehicleIsAlreadyParked(license: String) {
        try {
            val isParked = checkVehicleAlreadyParkedUseCase(license)
            _uiState.update {
                it.copy(
                    licenseError = if (isParked) "Este vehículo ya está registrado y no tiene salida aún" else null,
                    isCheckingLicense = false
                )
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    isCheckingLicense = false
                )
            }
        }
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

    fun isDriverFormValid(): Boolean {
        return _uiState.value.driverName.isNotBlank() &&
                _uiState.value.driverDni.isNotBlank() &&
                _uiState.value.driverPhone.isNotBlank()
    }

    fun isVehicleFormValid(): Boolean {
        val state = _uiState.value
        return state.vehicleLicense.isNotBlank() &&
                state.licenseError == null &&
                state.vehicleBrand.isNotBlank() &&
                state.vehicleModel.isNotBlank() &&
                state.vehicleColor.isNotBlank() &&
                state.position.isNotBlank()
    }

    fun retryLoadingParkingSpots() {
        loadAvailableParkingSpots()
    }

    fun registerEntry(onSuccess: () -> Unit) {
        val state = _uiState.value

        if (!isVehicleFormValid()) {
            _uiState.update { it.copy(error = "Por favor complete todos los campos obligatorios") }
            return
        }

        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null) }

                val result = registerEntryUseCase(
                    driverName = state.driverName,
                    driverPhone = state.driverPhone,
                    vehicleLicense = state.vehicleLicense,
                    vehicleBrand = state.vehicleBrand,
                    vehicleModel = state.vehicleModel,
                    vehicleColor = state.vehicleColor,
                    parkingSpotNumber = state.position
                )

                result.fold(
                    onSuccess = {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                successMessage = "Registro exitoso",
                                driverName = "",
                                driverDni = "",
                                driverPhone = "",
                                vehicleLicense = "",
                                vehicleBrand = "",
                                vehicleModel = "",
                                vehicleColor = "",
                                position = ""
                            )
                        }
                        onSuccess()
                        clearSuccessMessage()
                    },
                    onFailure = { error ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = "Error al registrar: ${error.message}"
                            )
                        }
                        clearErrorMessage()
                    }
                )
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Error al registrar: ${e.message}"
                    )
                }
                clearErrorMessage()
            }
        }
    }

    private fun clearSuccessMessage() {
        viewModelScope.launch {
            delay(3000)
            _uiState.update { it.copy(successMessage = null) }
        }
    }

    private fun clearErrorMessage() {
        viewModelScope.launch {
            delay(3000)
            _uiState.update { it.copy(error = null) }
        }
    }
}