package com.mpos.parking.data.datasources.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mpos.parking.data.datasources.local.entities.RecordEntity
import com.mpos.parking.data.datasources.local.relations.RecordWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: RecordEntity): Long

    @Query("SELECT * FROM records WHERE id = :id")
    suspend fun getRecordById(id: Int): RecordEntity?

    @Query("SELECT * FROM records WHERE exit_time IS NULL")
    suspend fun getActiveRecords(): List<RecordEntity>

    @Transaction
    @Query("SELECT * FROM records WHERE exit_time IS NULL ORDER BY entry_time DESC")
    fun getActiveRecordsWithDetails(): Flow<List<RecordWithDetails>>

    @Transaction
    @Query("SELECT * FROM records WHERE exit_time IS NOT NULL ORDER BY entry_time DESC")
    fun getRecordsWithDetails(): Flow<List<RecordWithDetails>>
}
