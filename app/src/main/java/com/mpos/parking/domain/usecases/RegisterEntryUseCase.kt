package com.mpos.parking.domain.usecases

import com.mpos.parking.domain.models.Driver
import com.mpos.parking.domain.models.Record
import com.mpos.parking.domain.models.Vehicle
import com.mpos.parking.domain.repository.DriverRepository
import com.mpos.parking.domain.repository.ParkingRepository
import com.mpos.parking.domain.repository.RecordRepository
import com.mpos.parking.domain.repository.VehicleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

class RegisterEntryUseCase @Inject constructor(
    private val driverRepository: DriverRepository,
    private val vehicleRepository: VehicleRepository,
    private val recordRepository: RecordRepository,
    private val parkingRepository: ParkingRepository,
) {
    suspend operator fun invoke(
        driverName: String,
        driverPhone: String,
        vehicleLicense: String,
        vehicleBrand: String,
        vehicleModel: String,
        vehicleColor: String,
        parkingSpotNumber: String,
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val driver = Driver(
                id = 0,
                name = driverName,
                phone = driverPhone
            )
            val driverId = driverRepository.insertDriver(driver)
            val vehicle = Vehicle(
                id = 0,
                license = vehicleLicense,
                brand = vehicleBrand,
                model = vehicleModel,
                color = vehicleColor
            )
            val vehicleId = vehicleRepository.insertVehicle(vehicle)

            val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            val record = Record(
                id = 0,
                vehicleId = vehicleId.toInt(),
                driverId = driverId.toInt(),
                entryTime = now,
                exitTime = null,
                positionNumber = parkingSpotNumber,
                observation = null
            )
            recordRepository.insertRecord(record)
            parkingRepository.updateParkingStatus(parkingSpotNumber, false)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 