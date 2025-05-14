package com.mpos.parking.domain.models

data class Vehicle(
    val id: Int,
    val license: String,
    val brand: String,
    val model: String,
    val color: String,
)
