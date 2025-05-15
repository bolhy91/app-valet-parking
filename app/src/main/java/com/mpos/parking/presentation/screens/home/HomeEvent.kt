package com.mpos.parking.presentation.screens.home

sealed class HomeEvent {
    data object LoadActiveRecords : HomeEvent()
    data object RefreshActiveRecords : HomeEvent()
}