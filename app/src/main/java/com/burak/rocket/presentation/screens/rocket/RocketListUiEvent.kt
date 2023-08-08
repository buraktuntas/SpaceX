package com.burak.rocket.presentation.screens.rocket

import com.burak.rocket.data.local.entity.RocketInfo

sealed class RocketListUiEvent {
    data class OnRocketItemClick(val rocketInfo: RocketInfo) : RocketListUiEvent()
    data class OnFavoriteClick(val item: RocketInfo) : RocketListUiEvent()

}