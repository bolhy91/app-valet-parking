package com.mpos.parking.data.mapper

import com.mpos.parking.data.datasources.local.entities.ParkingEntity
import com.mpos.parking.domain.models.Parking

fun ParkingEntity.toDomain(): Parking {
    return Parking(
        number = number,
        busy = busy
    )
}

fun Parking.toEntity(): ParkingEntity {
    return ParkingEntity(
        number = number,
        busy = busy
    )
} 