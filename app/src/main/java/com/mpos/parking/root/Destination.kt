package com.mpos.parking.root

import kotlinx.serialization.Serializable

sealed interface Route

@Serializable
object HomeRoute : Route

@Serializable
object EntryRoute : Route

@Serializable
object RecordRoute : Route

@Serializable
object VehicleEntryRoute : Route