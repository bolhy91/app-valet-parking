package com.mpos.parking.di

import com.mpos.parking.data.repository.ParkingRepositoryImpl
import com.mpos.parking.domain.repository.ParkingRepository
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
} 