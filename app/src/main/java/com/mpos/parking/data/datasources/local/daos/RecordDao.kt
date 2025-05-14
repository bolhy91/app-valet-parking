package com.mpos.parking.data.datasources.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mpos.parking.data.datasources.local.entities.RecordEntity

@Dao
interface RecordDao {
    @Insert
    suspend fun insert(entry: RecordEntity): Long

    @Update
    suspend fun update(entry: RecordEntity)

    @Query("SELECT * FROM records WHERE id = :id")
    suspend fun getById(id: Long): RecordEntity?

    @Query("SELECT * FROM records WHERE exit_time IS NULL")
    suspend fun getCurrentParked(): List<RecordEntity>

    @Query("SELECT * FROM records")
    suspend fun getAll(): List<RecordEntity>
}
