package com.mpos.parking.presentation.screens.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpos.parking.domain.usecases.GetRecordsWithDetailsUseCase
import com.mpos.parking.presentation.screens.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getRecordsWithDetailsUseCase: GetRecordsWithDetailsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(RecordState())
    val state: StateFlow<RecordState> = _state.asStateFlow()

    init {
        onEvent(RecordEvent.LoadOldRecords)
    }

    fun onEvent(event: RecordEvent) {
        when (event) {
            is RecordEvent.LoadOldRecords -> {
                loadOldRecords()
            }
        }
    }

    private fun loadOldRecords() {
        _state.update { it.copy(isLoading = true) }

        getRecordsWithDetailsUseCase().onEach { result ->
            result.fold(
                onSuccess = { records ->
                    _state.update {
                        it.copy(
                            activeRecords = records,
                            isLoading = false,
                            error = null
                        )
                    }
                },
                onFailure = { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = "Error al cargar registros: ${error.message}"
                        )
                    }
                }
            )
        }.launchIn(viewModelScope)
    }
}