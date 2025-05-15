package com.mpos.parking.data.datasources.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mpos.parking.data.datasources.local.entities.DriverEntity

@Dao
interface DriverDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(driver: DriverEntity): Long

    @Query("SELECT * FROM drivers WHERE id = :id")
    suspend fun getDriverById(id: Int): DriverEntity?
}
