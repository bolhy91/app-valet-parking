package com.mpos.parking.domain.repository

import com.mpos.parking.domain.models.Parking
import kotlinx.coroutines.flow.Flow

interface ParkingRepository {
    suspend fun getAvailableParkingSpots(): List<Parking>
} 