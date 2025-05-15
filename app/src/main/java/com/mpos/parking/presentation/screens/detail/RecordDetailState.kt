package com.mpos.parking.presentation.screens.detail

import com.mpos.parking.domain.models.RecordWithDetails

data class RecordDetailState(
    val record: RecordWithDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null,
    val parkingCost: Double = 0.0,
    val parkingDuration: String = "",
    val isExitProcessing: Boolean = false,
)