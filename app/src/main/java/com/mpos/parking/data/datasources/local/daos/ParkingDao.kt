package com.mpos.parking.data.datasources.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mpos.parking.data.datasources.local.entities.ParkingEntity

@Dao
interface ParkingDao {
    @Query("SELECT * FROM parking WHERE busy = 0")
    suspend fun getAvailableSpots(): List<ParkingEntity>

    @Query("SELECT * FROM parking")
    suspend fun getAllSpots(): List<ParkingEntity>
    
    @Query("SELECT COUNT(*) FROM parking")
    suspend fun getParkingCount(): Int

    @Update
    suspend fun updateSpot(spot: ParkingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<ParkingEntity>)
}