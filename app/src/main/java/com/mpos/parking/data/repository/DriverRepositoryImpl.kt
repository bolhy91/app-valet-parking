package com.mpos.parking.data.repository

import com.mpos.parking.data.datasources.local.daos.DriverDao
import com.mpos.parking.data.mapper.toDomain
import com.mpos.parking.data.mapper.toEntity
import com.mpos.parking.domain.models.Driver
import com.mpos.parking.domain.repository.DriverRepository
import javax.inject.Inject

class DriverRepositoryImpl @Inject constructor(
    private val driverDao: DriverDao
) : DriverRepository {
    override suspend fun insertDriver(driver: Driver): Long {
        return driverDao.insert(driver.toEntity())
    }

    override suspend fun getDriverById(id: Int): Driver? {
        return driverDao.getDriverById(id)?.toDomain()
    }
} 