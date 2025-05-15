package com.mpos.parking.domain.repository

import com.mpos.parking.domain.models.Vehicle
 
interface VehicleRepository {
    suspend fun insertVehicle(vehicle: Vehicle): Long
    suspend fun getVehicleById(id: Int): Vehicle?
    suspend fun getVehicleByLicense(license: String): Vehicle?
} 