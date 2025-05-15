package com.mpos.parking.data.mapper

import com.mpos.parking.data.datasources.local.entities.DriverEntity
import com.mpos.parking.domain.models.Driver

fun DriverEntity.toDomain(): Driver {
    return Driver(
        id = id,
        name = name,
        phone = phone
    )
}

fun Driver.toEntity(): DriverEntity {
    return DriverEntity(
        id = id,
        name = name,
        phone = phone
    )
}