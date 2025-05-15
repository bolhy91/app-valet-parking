package com.mpos.parking.domain.repository

import com.mpos.parking.domain.models.Record
import com.mpos.parking.domain.models.RecordWithDetails
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    suspend fun insertRecord(record: Record): Long
    suspend fun updateRecord(record: Record): Int
    suspend fun getRecordById(id: Int): Record?
    suspend fun getActiveRecords(): List<Record>
    fun getRecordsWithDetails(active: Boolean = true): Flow<List<RecordWithDetails>>
}