package com.mpos.parking.domain.repository

import com.mpos.parking.domain.models.Record
import com.mpos.parking.domain.models.RecordWithDetails
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    suspend fun insertRecord(record: Record): Long
    suspend fun getRecordById(id: Int): Record?
    suspend fun getActiveRecords(): List<Record>
    fun getActiveRecordsWithDetails(): Flow<List<RecordWithDetails>>
} 