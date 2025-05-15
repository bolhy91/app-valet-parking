package com.mpos.parking.data.datasources.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mpos.parking.data.datasources.local.daos.DriverDao
import com.mpos.parking.data.datasources.local.daos.ParkingDao
import com.mpos.parking.data.datasources.local.daos.RecordDao
import com.mpos.parking.data.datasources.local.daos.VehicleDao
import com.mpos.parking.data.datasources.local.entities.DriverEntity
import com.mpos.parking.data.datasources.local.entities.ParkingEntity
import com.mpos.parking.data.datasources.local.entities.RecordEntity
import com.mpos.parking.data.datasources.local.entities.VehicleEntity
import com.mpos.parking.utils.Constant.DATABASE_NAME
import com.mpos.parking.utils.DateTimeConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [ParkingEntity::class, VehicleEntity::class, DriverEntity::class, RecordEntity::class],
    version = 1
)
@TypeConverters(DateTimeConverter::class)
abstract class ParkingDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun driverDao(): DriverDao
    abstract fun recordDao(): RecordDao
    abstract fun parkingDao(): ParkingDao

    companion object {
        @Volatile
        private var INSTANCE: ParkingDatabase? = null
        fun getDatabase(context: Context): ParkingDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ParkingDatabase::class.java,
                    DATABASE_NAME
                ).addCallback(ParkingDatabaseCallback()).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class ParkingDatabaseCallback : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        parkingDummy(database.parkingDao())
                    } catch (e: Exception) {
                        Log.e("ParkingDatabaseCallback", "Error inserting dummy data", e)
                    }
                }
            }
        }

        private suspend fun parkingDummy(parkingDao: ParkingDao) {
            val dummy = (10..20).map { number ->
                val formattedNumber = "PA$number"
                ParkingEntity(number = formattedNumber, busy = false)
            }
            parkingDao.insert(dummy)
        }
    }
}