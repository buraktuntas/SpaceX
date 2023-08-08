package com.burak.rocket.presentation.screens.main


sealed class MainUiEvent {
    data class OnUpdateLoading(val state: Boolean) : MainUiEvent()
}