package com.mpos.parking.presentation.screens.record

import com.mpos.parking.domain.models.RecordWithDetails

data class RecordState(
    val activeRecords: List<RecordWithDetails> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)