package com.mpos.parking.domain.usecases

import com.mpos.parking.domain.models.RecordWithDetails
import com.mpos.parking.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetActiveRecordsWithDetailsUseCase @Inject constructor(
    private val recordRepository: RecordRepository
) {
    operator fun invoke(): Flow<Result<List<RecordWithDetails>>> {
        return recordRepository.getActiveRecordsWithDetails()
            .map { records -> 
                Result.success(records) 
            }
            .catch { e ->
                emit(Result.failure(e))
            }
    }
} 