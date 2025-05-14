package com.mpos.parking.di

import android.content.Context
import androidx.room.Room
import com.mpos.parking.data.datasources.local.ParkingDatabase
import com.mpos.parking.data.datasources.local.daos.DriverDao
import com.mpos.parking.data.datasources.local.daos.RecordDao
import com.mpos.parking.data.datasources.local.daos.VehicleDao
import com.mpos.parking.utils.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(appContext: Context): ParkingDatabase {
        return Room.databaseBuilder(
            appContext,
            ParkingDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideRecordDao(db: ParkingDatabase): RecordDao = db.recordDao()

    @Provides
    fun provideDriverDao(db: ParkingDatabase): DriverDao = db.driverDao()

    @Provides
    fun provideVehicleDao(db: ParkingDatabase): VehicleDao = db.vehicleDao()
}