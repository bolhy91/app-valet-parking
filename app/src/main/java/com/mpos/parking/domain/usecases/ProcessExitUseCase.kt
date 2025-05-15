package com.mpos.parking.domain.usecases

import com.mpos.parking.domain.repository.ParkingRepository
import com.mpos.parking.domain.repository.RecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

class ProcessExitUseCase @Inject constructor(
    private val recordRepository: RecordRepository,
    private val parkingRepository: ParkingRepository,
) {
    suspend operator fun invoke(recordId: Int): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val currentRecord = recordRepository.getRecordById(recordId) ?: return@withContext Result.failure(
                    Exception("Registro no encontrado")
                )
            val exitTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            val updatedRecord = currentRecord.copy(exitTime = exitTime)
            recordRepository.updateRecord(updatedRecord)
            parkingRepository.updateParkingAvailability(currentRecord.positionNumber, true)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 