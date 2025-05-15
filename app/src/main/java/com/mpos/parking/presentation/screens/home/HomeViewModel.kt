package com.mpos.parking.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpos.parking.domain.usecases.GetActiveRecordsWithDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getActiveRecordsWithDetailsUseCase: GetActiveRecordsWithDetailsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        onEvent(HomeEvent.LoadActiveRecords)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadActiveRecords -> {
                loadActiveRecords()
            }

            is HomeEvent.RefreshActiveRecords -> {
                loadActiveRecords()
            }
        }
    }

    private fun loadActiveRecords() {
        _state.update { it.copy(isLoading = true) }

        getActiveRecordsWithDetailsUseCase().onEach { result ->
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