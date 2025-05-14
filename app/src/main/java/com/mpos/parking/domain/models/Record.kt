package com.mpos.parking.domain.models

import kotlinx.datetime.LocalDateTime

data class Record(
    val id: Int,
    val vehicle: Vehicle,
    val driver: Driver,
    val entryTime: LocalDateTime,
    val exitTime: LocalDateTime? = null,
    val positionNumber: String? = null,
    val observation: String? = null,
)
