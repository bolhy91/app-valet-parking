package com.mpos.parking.utils

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

object DateTimeConverter {
    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): String? = value?.toString()

    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? = value?.let { LocalDateTime.parse(it) }
}