package com.mpos.parking.domain.usecases

import com.mpos.parking.domain.models.Parking
import com.mpos.parking.domain.repository.ParkingRepository
import javax.inject.Inject

class GetAvailableParkingUseCase @Inject constructor(
    private val parkingRepository: ParkingRepository,
) {
    suspend operator fun invoke(): List<Parking> {
        return parkingRepository.getAvailableParking()
    }
} 