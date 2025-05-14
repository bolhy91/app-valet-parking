package com.mpos.parking.data.datasources.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mpos.parking.data.datasources.local.entities.VehicleEntity

@Dao
interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vehicle: VehicleEntity): Long

    @Query("SELECT * FROM vehicles WHERE license = :license LIMIT 1")
    suspend fun getByLicense(license: String): VehicleEntity?

    @Query("SELECT * FROM vehicles")
    suspend fun getAll(): List<VehicleEntity>
}
