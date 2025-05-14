package com.mpos.parking.data.mapper

import com.mpos.parking.data.datasources.local.entities.DriverEntity
import com.mpos.parking.domain.models.Driver

fun DriverEntity.toDomain() = Driver(id, name, dni, phone)