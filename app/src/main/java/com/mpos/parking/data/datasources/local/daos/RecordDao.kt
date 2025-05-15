package com.mpos.parking.data.datasources.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.mpos.parking.data.datasources.local.entities.RecordEntity
import com.mpos.parking.data.datasources.local.relations.RecordWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: RecordEntity): Long

    @Update
    suspend fun update(entry: RecordEntity)

    @Query("SELECT * FROM records WHERE id = :id")
    suspend fun getRecordById(id: Int): RecordEntity?

    @Query("SELECT * FROM records WHERE exit_time IS NULL")
    suspend fun getActiveRecords(): List<RecordEntity>

    @Query("SELECT * FROM records")
    suspend fun getAll(): List<RecordEntity>

    @Transaction
    @Query("SELECT * FROM records")
    suspend fun getAllRecordsWithDetails(): List<RecordWithDetails>
    
    @Transaction
    @Query("SELECT * FROM records WHERE exit_time IS NULL ORDER BY entry_time DESC")
    fun getActiveRecordsWithDetails(): Flow<List<RecordWithDetails>>
}
