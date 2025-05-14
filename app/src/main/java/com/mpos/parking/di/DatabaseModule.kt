package com.mpos.parking.di

import android.content.Context
import com.mpos.parking.data.datasources.local.ParkingDatabase
import com.mpos.parking.data.datasources.local.daos.DriverDao
import com.mpos.parking.data.datasources.local.daos.RecordDao
import com.mpos.parking.data.datasources.local.daos.VehicleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): ParkingDatabase {
        return ParkingDatabase.getDatabase(appContext)
    }

    @Provides
    fun provideRecordDao(db: ParkingDatabase): RecordDao = db.recordDao()

    @Provides
    fun provideDriverDao(db: ParkingDatabase): DriverDao = db.driverDao()

    @Provides
    fun provideVehicleDao(db: ParkingDatabase): VehicleDao = db.vehicleDao()

    @Provides
    fun provideParkingDao(db: ParkingDatabase) = db.parkingDao()
}