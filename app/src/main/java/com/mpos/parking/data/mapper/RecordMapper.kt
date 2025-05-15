package com.mpos.parking.data.mapper

import com.mpos.parking.data.datasources.local.entities.RecordEntity
import com.mpos.parking.domain.models.Record

fun RecordEntity.toDomain(): Record {
    return Record(
        id = id,
        vehicleId = vehicleId,
        driverId = driverId,
        entryTime = entryTime,
        exitTime = exitTime,
        positionNumber = positionNumber,
        observation = observation
    )
}

fun Record.toEntity(): RecordEntity {
    return RecordEntity(
        id = id,
        vehicleId = vehicleId,
        driverId = driverId,
        entryTime = entryTime,
        exitTime = exitTime,
        positionNumber = positionNumber,
        observation = observation
    )
} 