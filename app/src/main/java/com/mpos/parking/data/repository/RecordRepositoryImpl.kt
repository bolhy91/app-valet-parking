package com.mpos.parking.data.repository

import com.mpos.parking.data.datasources.local.daos.RecordDao
import com.mpos.parking.data.mapper.toDomain
import com.mpos.parking.data.mapper.toEntity
import com.mpos.parking.domain.models.Record
import com.mpos.parking.domain.models.RecordWithDetails
import com.mpos.parking.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecordRepositoryImpl @Inject constructor(
    private val recordDao: RecordDao,
) : RecordRepository {
    override suspend fun insertRecord(record: Record): Long {
        return recordDao.insert(record.toEntity())
    }

    override suspend fun updateRecord(record: Record): Int {
        return recordDao.update(record.toEntity())
    }

    override suspend fun getRecordById(id: Int): Record? {
        return recordDao.getRecordById(id)?.toDomain()
    }

    override suspend fun getActiveRecords(): List<Record> {
        return recordDao.getActiveRecords().map { it.toDomain() }
    }

    override fun getRecordsWithDetails(active: Boolean): Flow<List<RecordWithDetails>> {
        return if (active) {
            recordDao.getActiveRecordsWithDetails().map { records ->
                records.map { it.toDomain() }
            }
        } else {
            recordDao.getRecordsWithDetails().map { records ->
                records.map { it.toDomain() }
            }
        }
    }
} 