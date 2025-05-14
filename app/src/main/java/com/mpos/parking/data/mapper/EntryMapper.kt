package com.mpos.parking.data.mapper

import com.mpos.parking.data.datasources.local.entities.RecordEntity
import com.mpos.parking.domain.models.Driver
import com.mpos.parking.domain.models.Entry
import com.mpos.parking.domain.models.Vehicle

fun RecordEntity.toDomain(vehicle: Vehicle, driver: Driver) = Entry(
    id = id,
    vehicle = vehicle,
    driver = driver,
    entryTime = entryTime,
    exitTime = exitTime,
    positionNumber = positionNumber,
    observation = observation
)