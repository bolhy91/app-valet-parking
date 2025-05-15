package com.mpos.parking.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpos.parking.domain.models.RecordWithDetails
import com.mpos.parking.domain.usecases.CalculateParkingCostUseCase
import com.mpos.parking.domain.usecases.ProcessExitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

@HiltViewModel
class RecordDetailViewModel @Inject constructor(
    private val processExitUseCase: ProcessExitUseCase,
    private val calculateParkingCostUseCase: CalculateParkingCostUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(RecordDetailState())
    val state: StateFlow<RecordDetailState> = _state.asStateFlow()

    fun onEvent(event: RecordDetailEvent) {
        when (event) {
            is RecordDetailEvent.Initialize -> {
                initialize(event.recordWithDetails)
            }

            is RecordDetailEvent.ProcessExit -> {
                processExit()
            }

            is RecordDetailEvent.DismissDialog -> {
                _state.update { it.copy(error = null) }
            }

            is RecordDetailEvent.DismissMessage -> {
                _state.update { it.copy(successMessage = null) }
            }
        }
    }

    private fun initialize(record: RecordWithDetails) {
        _state.update { it.copy(record = record) }
        record.exitTime?.let { exitTime ->
            val cost = calculateParkingCostUseCase(record.entryTime, exitTime)
            val duration = calculateParkingCostUseCase.getFormattedDuration(record.entryTime, exitTime)
            _state.update {
                it.copy(
                    parkingCost = cost,
                    parkingDuration = duration
                )
            }
        } ?: run {
            val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            val cost = calculateParkingCostUseCase(record.entryTime, currentTime)
            val duration = calculateParkingCostUseCase
                .getFormattedDuration(record.entryTime, currentTime)
            _state.update {
                it.copy(
                    parkingCost = cost,
                    parkingDuration = duration
                )
            }
        }
    }

    private fun processExit() {
        val recordId = _state.value.record?.id ?: return
        _state.update { it.copy(isExitProcessing = true) }
        viewModelScope.launch {
            processExitUseCase(recordId).fold(
                onSuccess = {
                    _state.update {
                        it.copy(
                            successMessage = "Salida procesada correctamente",
                            isExitProcessing = false
                        )
                    }
                },
                onFailure = { error ->
                    _state.update {
                        it.copy(
                            error = "Error al procesar la salida: ${error.message}",
                            isExitProcessing = false
                        )
                    }
                }
            )
        }
    }
} 