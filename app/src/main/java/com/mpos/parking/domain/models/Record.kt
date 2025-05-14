package com.mpos.parking.domain.models

import java.time.LocalDateTime

data class Record(
    val id: String,
    val vehicle: Vehicle,
    val driver: Driver,
    val entryTime: LocalDateTime,
    val exitTime: LocalDateTime? = null,
    val positionNumber: String? = null,
    val observation: String? = null,
)
