package com.mpos.parking.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mpos.parking.data.datasources.local.daos.DriverDao
import com.mpos.parking.data.datasources.local.daos.RecordDao
import com.mpos.parking.data.datasources.local.daos.VehicleDao
import com.mpos.parking.data.datasources.local.entities.DriverEntity
import com.mpos.parking.data.datasources.local.entities.RecordEntity
import com.mpos.parking.data.datasources.local.entities.VehicleEntity
import com.mpos.parking.utils.DateTimeConverter

@Database(
    entities = [VehicleEntity::class, DriverEntity::class, RecordEntity::class],
    version = 1
)
@TypeConverters(DateTimeConverter::class)
abstract class ParkingDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun driverDao(): DriverDao
    abstract fun recordDao(): RecordDao
}