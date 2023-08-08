package com.burak.rocket.util


sealed class UiEvent {
    data class Navigate<T>(val navigationType: NavigationType, val data: Map<String, T?>? = null) : UiEvent()
    data class ShowError(val throwable: Throwable?) : UiEvent()
    object ShowLoading : UiEvent()
    object HideLoading : UiEvent()
}