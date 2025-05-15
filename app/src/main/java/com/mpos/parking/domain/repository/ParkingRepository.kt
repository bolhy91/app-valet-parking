package com.mpos.parking.domain.repository

import com.mpos.parking.domain.models.Parking
import kotlinx.coroutines.flow.Flow

interface ParkingRepository {
    suspend fun getAvailableParking(): List<Parking>
    suspend fun updateParkingStatus(spotNumber: String, isFree: Boolean)
}