package com.mpos.parking.data.mapper

import com.mpos.parking.data.datasources.local.entities.VehicleEntity
import com.mpos.parking.domain.models.Vehicle

fun VehicleEntity.toDomain(): Vehicle {
    return Vehicle(
        id = id,
        license = license,
        brand = brand,
        model = model,
        color = color
    )
}

fun Vehicle.toEntity(): VehicleEntity {
    return VehicleEntity(
        id = id,
        license = license,
        brand = brand,
        model = model,
        color = color
    )
}