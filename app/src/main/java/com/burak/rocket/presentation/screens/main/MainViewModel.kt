package com.burak.rocket.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.burak.rocket.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(mainUiEvent: MainUiEvent) {
        when (mainUiEvent) {
            is MainUiEvent.OnUpdateLoading -> {
                updateLoading(mainUiEvent.state)
            }
        }
    }

    private fun updateLoading(showLoading: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = showLoading
            )
        }
    }
}