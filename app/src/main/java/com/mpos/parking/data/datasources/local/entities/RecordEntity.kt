package com.mpos.parking.data.datasources.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(
    tableName = "records",
    foreignKeys = [
        ForeignKey(
            entity = VehicleEntity::class,
            parentColumns = ["id"],
            childColumns = ["vehicle_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DriverEntity::class,
            parentColumns = ["id"],
            childColumns = ["driver_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ParkingEntity::class,
            parentColumns = ["number"],
            childColumns = ["position_number"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index("position_number")]
)
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "vehicle_id", index = true) val vehicleId: Int,
    @ColumnInfo(name = "driver_id", index = true) val driverId: Int,
    @ColumnInfo(name = "entry_time") val entryTime: LocalDateTime,
    @ColumnInfo(name = "exit_time") val exitTime: LocalDateTime? = null,
    @ColumnInfo(name = "position_number") val positionNumber: String,
    val observation: String? = null,
)
