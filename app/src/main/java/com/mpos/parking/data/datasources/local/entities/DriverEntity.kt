package com.mpos.parking.data.datasources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drivers")
data class DriverEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val dni: String,
    val phone: String,
)
