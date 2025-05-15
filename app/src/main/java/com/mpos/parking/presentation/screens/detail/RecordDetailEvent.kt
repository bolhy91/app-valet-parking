package com.mpos.parking.presentation.screens.detail

import com.mpos.parking.domain.models.RecordWithDetails

sealed class RecordDetailEvent {
    data class Initialize(val recordWithDetails: RecordWithDetails) : RecordDetailEvent()
    data object ProcessExit : RecordDetailEvent()
    data object DismissDialog : RecordDetailEvent()
    data object DismissMessage : RecordDetailEvent()
} 