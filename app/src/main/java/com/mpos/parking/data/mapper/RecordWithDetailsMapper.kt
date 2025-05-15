package com.mpos.parking.data.mapper

import com.mpos.parking.data.datasources.local.relations.RecordWithDetails as EntityRecordWithDetails
import com.mpos.parking.domain.models.RecordWithDetails as DomainRecordWithDetails

fun EntityRecordWithDetails.toDomain(): DomainRecordWithDetails {
    return DomainRecordWithDetails(
        id = entry.id,
        vehicleLicense = vehicle.license,
        vehicleBrand = vehicle.brand,
        vehicleModel = vehicle.model,
        vehicleColor = vehicle.color,
        driverName = driver.name,
        driverPhone = driver.phone,
        parkingNumber = parking.number,
        entryTime = entry.entryTime,
        exitTime = entry.exitTime
    )
} 