package com.mpos.parking.presentation.screens.record

sealed class RecordEvent {
    data object LoadOldRecords : RecordEvent()
}