package com.mpos.parking.domain.models

import kotlinx.datetime.LocalDateTime

data class Record(
    val id: Int,
    val vehicleId: Int,
    val driverId: Int,
    val entryTime: LocalDateTime,
    val exitTime: LocalDateTime? = null,
    val positionNumber: String,
    val observation: String? = null,
) 