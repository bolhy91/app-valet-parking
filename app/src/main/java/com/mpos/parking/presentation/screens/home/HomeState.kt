package com.mpos.parking.presentation.screens.home

import com.mpos.parking.domain.models.RecordWithDetails

data class HomeState(
    val activeRecords: List<RecordWithDetails> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedRecord: RecordWithDetails? = null,
    val isBottomSheetVisible: Boolean = false
)