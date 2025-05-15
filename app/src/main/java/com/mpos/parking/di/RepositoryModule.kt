package com.mpos.parking.di

import com.mpos.parking.data.repository.DriverRepositoryImpl
import com.mpos.parking.data.repository.ParkingRepositoryImpl
import com.mpos.parking.data.repository.RecordRepositoryImpl
import com.mpos.parking.data.repository.VehicleRepositoryImpl
import com.mpos.parking.domain.repository.DriverRepository
import com.mpos.parking.domain.repository.ParkingRepository
import com.mpos.parking.domain.repository.RecordRepository
import com.mpos.parking.domain.repository.VehicleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindParkingRepository(
        parkingRepositoryImpl: ParkingRepositoryImpl
    ): ParkingRepository

    @Binds
    abstract fun bindDriverRepository(
        driverRepositoryImpl: DriverRepositoryImpl
    ): DriverRepository

    @Binds
    abstract fun bindVehicleRepository(
        vehicleRepositoryImpl: VehicleRepositoryImpl
    ): VehicleRepository

    @Binds
    abstract fun bindRecordRepository(
        recordRepositoryImpl: RecordRepositoryImpl
    ): RecordRepository
} 