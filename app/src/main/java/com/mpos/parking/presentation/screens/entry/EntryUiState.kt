package com.mpos.parking.presentation.screens.entry

data class EntryUiState(
    val driverName: String = "",
    val driverDni: String = "",
    val driverPhone: String = "",
    val vehicleLicense: String = "",
    val vehicleBrand: String = "",
    val vehicleModel: String = "",
    val vehicleColor: String = "",
    val position: String = "",
    val observation: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)