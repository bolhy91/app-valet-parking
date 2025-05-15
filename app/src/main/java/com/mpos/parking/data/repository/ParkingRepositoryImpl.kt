package com.mpos.parking.data.repository

import com.mpos.parking.data.datasources.local.daos.ParkingDao
import com.mpos.parking.data.mapper.toDomain
import com.mpos.parking.domain.models.Parking
import com.mpos.parking.domain.repository.ParkingRepository
import javax.inject.Inject

class ParkingRepositoryImpl @Inject constructor(
    private val parkingDao: ParkingDao
) : ParkingRepository {
    override suspend fun getAvailableParkingSpots(): List<Parking> {
        return parkingDao.getAvailableSpots().map { it.toDomain() }
    }
} 