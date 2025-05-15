package com.mpos.parking.data.datasources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parking")
data class ParkingEntity(
    @PrimaryKey val number: String,
    val busy: Boolean,
)
