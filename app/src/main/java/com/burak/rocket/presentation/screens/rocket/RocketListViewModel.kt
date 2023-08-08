package com.burak.rocket.presentation.screens.rocket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burak.rocket.common.ResultState
import com.burak.rocket.data.local.RocketDataRepository
import com.burak.rocket.data.local.entity.RocketInfo
import com.burak.rocket.domain.use_case.GetRocketListUseCase
import com.burak.rocket.navigation.Screen
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
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RocketListViewModel @Inject constructor(
    private val getRocketListUseCase: GetRocketListUseCase,
    private val rocketDataRepository: RocketDataRepository

) : ViewModel() {

    private val _uiState = MutableStateFlow(RocketListUiState())
    val uiState: StateFlow<RocketListUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        callGetRocketList()
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

    private fun callGetRocketList() {
        viewModelScope.launch(ExceptionHandler.handler) {
            getRocketListUseCase(
                onResult = {
                    when (it) {
                        is ResultState.Success -> {
                            it.data.let { dataList ->
                                rocketDataRepository.allList().collect { list ->
                                    if (list.isNotEmpty()) {
                                        updateRocketList(list)
                                    } else {
                                        rocketDataRepository.addAll(dataList)
                                        updateRocketList(dataList)
                                    }
                                }
                            }
                        }

                        is ResultState.Error -> {
                            _uiEvent.send(UiEvent.ShowError(it.exception))
                        }

                        else -> {}
                    }
                }
            )
        }
    }

    private fun updateRocketList(allList: List<RocketInfo>) {
        viewModelScope.launch(ExceptionHandler.handler) {
            _uiState.update { currentState ->
                currentState.copy(
                    allList = allList
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
}