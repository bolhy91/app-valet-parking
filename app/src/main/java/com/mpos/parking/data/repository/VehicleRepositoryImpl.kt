package com.mpos.parking.data.repository

import com.mpos.parking.data.datasources.local.daos.VehicleDao
import com.mpos.parking.data.mapper.toDomain
import com.mpos.parking.data.mapper.toEntity
import com.mpos.parking.domain.models.Vehicle
import com.mpos.parking.domain.repository.VehicleRepository
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val vehicleDao: VehicleDao
) : VehicleRepository {
    override suspend fun insertVehicle(vehicle: Vehicle): Long {
        return vehicleDao.insert(vehicle.toEntity())
    }

    override suspend fun getVehicleById(id: Int): Vehicle? {
        return vehicleDao.getVehicleById(id)?.toDomain()
    }

    override suspend fun getVehicleByLicense(license: String): Vehicle? {
        return vehicleDao.getVehicleByLicense(license)?.toDomain()
    }
} 