package com.mpos.parking.domain.models

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

data class RecordWithDetails(
    val id: Int,
    val vehicleLicense: String,
    val vehicleBrand: String,
    val vehicleModel: String,
    val vehicleColor: String,
    val driverName: String,
    val driverPhone: String,
    val parkingNumber: String,
    val entryTime: LocalDateTime,
    val exitTime: LocalDateTime? = null,
) {
    fun formatDateTime(dateTime: LocalDateTime): String {
        return dateTime.toJavaLocalDateTime()
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    }
}