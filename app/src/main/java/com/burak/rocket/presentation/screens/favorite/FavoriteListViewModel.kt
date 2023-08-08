package com.burak.rocket.presentation.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burak.rocket.data.local.RocketDataRepository
import com.burak.rocket.data.local.entity.RocketInfo
import com.burak.rocket.navigation.Screen
import com.burak.rocket.presentation.screens.rocket.RocketListUiEvent
import com.burak.rocket.presentation.screens.rocket.RocketListUiState
import com.burak.rocket.util.ExceptionHandler
import com.burak.rocket.util.NavigationType
import com.burak.rocket.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val rocketDataRepository: RocketDataRepository

) : ViewModel() {

    private val _uiState = MutableStateFlow(RocketListUiState())
    val uiState: StateFlow<RocketListUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        readFavoriteList()
    }

    fun onEvent(rocketListUiEvent: RocketListUiEvent) {
        when (rocketListUiEvent) {
            is RocketListUiEvent.OnRocketItemClick -> {
                onItemClick(rocketListUiEvent.rocketInfo)
            }
            is RocketListUiEvent.OnFavoriteClick -> {
                updateFavoriteItem(rocketListUiEvent.item)
            }
        }
    }

    private fun onItemClick(rocketInfo: RocketInfo) {
        viewModelScope.launch(ExceptionHandler.handler) {
            _uiEvent.send(
                UiEvent.Navigate(
                    navigationType = NavigationType.Navigate(Screen.RocketDetailScreen.route),
                    data = mapOf(
                        "data" to rocketInfo
                    )
                )
            )
        }
    }

    private fun updateFavoriList(favoriteList: List<RocketInfo>) {
        viewModelScope.launch(ExceptionHandler.handler) {
            _uiState.update { currentState ->
                currentState.copy(
                    favoriteList = favoriteList
                )
            }
        }
    }

    private fun updateFavoriteItem(item: RocketInfo) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val updatedItem = item.copy(isFavorite = !item.isFavorite)
                rocketDataRepository.updateRocket(updatedItem)
            }
        }
    }

    private fun readFavoriteList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                rocketDataRepository.readAllfavorite().collect{
                    updateFavoriList(it)
                }
            }
        }
    }
}