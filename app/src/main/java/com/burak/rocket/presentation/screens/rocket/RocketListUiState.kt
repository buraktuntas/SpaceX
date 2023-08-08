package com.burak.rocket.presentation.screens.rocket

import com.burak.rocket.data.local.entity.RocketInfo
import com.burak.rocket.data.remote.dto.RocketsDtoItem

data class RocketListUiState(
    val isPageLoading: Boolean = false,
    val allList: List<RocketInfo> = arrayListOf(),
    val favoriteList: List<RocketInfo> = arrayListOf(),
)