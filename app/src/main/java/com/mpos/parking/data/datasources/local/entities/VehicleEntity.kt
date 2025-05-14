package com.mpos.parking.data.datasources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicles")
data class VehicleEntity(
    @PrimaryKey val id: Int,
    val license: String,
    val brand: String,
    val model: String,
    val color: String,
)
