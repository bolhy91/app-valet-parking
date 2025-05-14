package com.mpos.parking.data.datasources.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.mpos.parking.data.datasources.local.entities.DriverEntity
import com.mpos.parking.data.datasources.local.entities.ParkingEntity
import com.mpos.parking.data.datasources.local.entities.RecordEntity
import com.mpos.parking.data.datasources.local.entities.VehicleEntity

data class RecordWithDetails(
    @Embedded val entry: RecordEntity,

    @Relation(
        parentColumn = "position_number",
        entityColumn = "number"
    )
    val parking: ParkingEntity,

    @Relation(
        parentColumn = "vehicle_id",
        entityColumn = "id"
    )
    val vehicle: VehicleEntity,

    @Relation(
        parentColumn = "driver_id",
        entityColumn = "id"
    )
    val driver: DriverEntity,
)