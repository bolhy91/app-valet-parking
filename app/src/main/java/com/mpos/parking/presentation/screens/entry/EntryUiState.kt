package com.mpos.parking.presentation.screens.entry

import com.mpos.parking.domain.models.Parking

data class EntryUiState(
    val driverName: String = "",
    val driverDni: String = "",
    val driverPhone: String = "",
    val vehicleLicense: String = "",
    val vehicleBrand: String = "",
    val vehicleModel: String = "",
    val vehicleColor: String = "",
    val position: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val availableParkingSpots: List<Parking> = emptyList()
)