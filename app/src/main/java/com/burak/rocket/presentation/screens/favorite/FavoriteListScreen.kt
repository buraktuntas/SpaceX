package com.burak.rocket.presentation.screens.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.burak.rocket.presentation.components.RocketListItem
import com.burak.rocket.presentation.screens.rocket.RocketListUiEvent
import com.burak.rocket.presentation.screens.rocket.RocketListUiState
import com.burak.rocket.util.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun FavoriteListScreen(
    uiStateFlow: StateFlow<RocketListUiState>,
    uiEventFlow: Flow<UiEvent>,
    onUiEvent: (UiEvent) -> Unit,
    onEvent: (RocketListUiEvent) -> Unit,
    ) {

    val uiState by uiStateFlow.collectAsState()

    LaunchedEffect(false) {
        uiEventFlow.collect { event ->
            onUiEvent(event)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                4.dp
            ),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        uiState.favoriteList.let { itemList ->
            items(itemList.size) { itemIndex ->
                itemList[itemIndex].let { item ->
                    RocketListItem(
                        item = item,
                        onItemClick = {
                            onEvent(
                                RocketListUiEvent.OnRocketItemClick(
                                    rocketInfo = item
                                )
                            )
                        },
                        onFavoriteClick= {
                            onEvent(
                                RocketListUiEvent.OnFavoriteClick(
                                    item = item
                                )

                            )
                        }
                    )
                }
            }
        }
    }
}