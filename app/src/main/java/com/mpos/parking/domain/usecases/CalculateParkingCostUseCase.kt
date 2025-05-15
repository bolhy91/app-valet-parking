package com.mpos.parking.domain.usecases

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import javax.inject.Inject

class CalculateParkingCostUseCase @Inject constructor() {
    private val hourlyRate = 1.0

    operator fun invoke(entryTime: LocalDateTime, exitTime: LocalDateTime): Double {
        val entryInstant = entryTime.toInstant(TimeZone.currentSystemDefault())
        val exitInstant = exitTime.toInstant(TimeZone.currentSystemDefault())
        val duration = exitInstant - entryInstant
        val hours = duration.inWholeHours
        val minutes = duration.inWholeMinutes % 60
        val totalHours = if (minutes > 0) hours + 1 else hours
        return if (totalHours == 0L) hourlyRate else totalHours * hourlyRate
    }

    fun getFormattedDuration(entryTime: LocalDateTime, exitTime: LocalDateTime): String {
        val entryInstant = entryTime.toInstant(TimeZone.currentSystemDefault())
        val exitInstant = exitTime.toInstant(TimeZone.currentSystemDefault())

        val duration = exitInstant - entryInstant
        val hours = duration.inWholeHours
        val minutes = duration.inWholeMinutes % 60
        return when {
            hours > 0 && minutes > 0 -> "$hours hora${if (hours > 1) "s" else ""} y $minutes minuto${if (minutes > 1) "s" else ""}"
            hours > 0 -> "$hours hora${if (hours > 1) "s" else ""}"
            minutes > 0 -> "$minutes minuto${if (minutes > 1) "s" else ""}"
            else -> "Menos de un minuto"
        }
    }
} 