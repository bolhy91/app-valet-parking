package com.mpos.parking.domain.usecases

import com.mpos.parking.domain.repository.RecordRepository
import com.mpos.parking.domain.repository.VehicleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckVehicleParkedUseCase @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val recordRepository: RecordRepository,
) {
    suspend operator fun invoke(license: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val vehicle = vehicleRepository.getVehicleByLicense(license) ?: return@withContext false
            val activeRecords = recordRepository.getActiveRecords()
            return@withContext activeRecords.any { it.vehicleId == vehicle.id }
        } catch (e: Exception) {
            return@withContext false
        }
    }
} 