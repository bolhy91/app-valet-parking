package com.mpos.parking.domain.repository

import com.mpos.parking.domain.models.Driver

interface DriverRepository {
    suspend fun insertDriver(driver: Driver): Long
    suspend fun getDriverById(id: Int): Driver?
}