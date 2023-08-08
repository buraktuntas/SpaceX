package com.burak.rocket.presentation.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.burak.rocket.data.local.entity.RocketInfo
import com.burak.rocket.navigation.Screen
import com.burak.rocket.presentation.screens.favorite.FavoriteListViewModel
import com.burak.rocket.presentation.screens.rocket.RocketListScreen
import com.burak.rocket.presentation.screens.rocket.RocketListViewModel
import com.burak.rocket.presentation.screens.rocket_detail.RocketDetailScreen
import com.burak.rocket.util.UiEvent
import com.burak.rocket.util.handleUiEvent
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import com.burak.rocket.domain.model.TabItem
import com.burak.rocket.presentation.screens.favorite.FavoriteListScreen
import com.burak.rocket.presentation.screens.rocket_detail.RocketDetailViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@ExperimentalTextApi
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    onEvent: (MainUiEvent) -> Unit,
) {

    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val rocketListViewModel: RocketListViewModel = hiltViewModel()
    val favoriteListViewModel: FavoriteListViewModel = hiltViewModel()
    val rocketDetailViewModel: RocketDetailViewModel = hiltViewModel()


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "SpaceX") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.TabScreen.route
            ) {
                composable(
                    route = Screen.TabScreen.route
                ) {
                    val tabs = listOf(
                        TabItem(
                            title = "Rocket List",
                            icon = Icons.Filled.Home,
                            screen = {
                                RocketListScreen(
                                    uiStateFlow = rocketListViewModel.uiState,
                                    uiEventFlow = rocketListViewModel.uiEvent,
                                    onUiEvent = {
                                        it.handleUiEvent(navController) { commonUiEvent ->
                                            onEvent(commonUiEvent)
                                        }
                                    },
                                    onEvent = {
                                        rocketListViewModel.onEvent(it)
                                    }
                                )
                            }
                        ),
                        TabItem(
                            title = "Favorite",
                            icon = Icons.Filled.Favorite,
                            screen = {
                                FavoriteListScreen(
                                    uiStateFlow = favoriteListViewModel.uiState,
                                    uiEventFlow = favoriteListViewModel.uiEvent,
                                    onUiEvent = {
                                        it.handleUiEvent(navController) { commonUiEvent ->
                                            onEvent(commonUiEvent)
                                        }
                                    },
                                    onEvent = {
                                        favoriteListViewModel.onEvent(it)
                                    }
                                )
                            }
                        ),
                    )

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        TabRow(
                            selectedTabIndex = pagerState.currentPage,
                        ) {
                            tabs.forEachIndexed { index, item ->
                                Tab(
                                    selected = index == pagerState.currentPage,
                                    text = { Text(text = item.title) },
                                    icon = { Icon(item.icon, "") },
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(
                                                index
                                            )
                                        }
                                    },
                                )
                            }
                        }
                        HorizontalPager(
                            pageCount = tabs.size,
                            state = pagerState
                        ) { page ->
                            tabs[page].screen(navController)
                        }
                    }
                }

                composable(
                    route = Screen.RocketDetailScreen.route
                ) {
                    RocketDetailScreen(
                        item = it.savedStateHandle.get<RocketInfo>("data")!!,
                        uiEventFlow = rocketDetailViewModel.uiEvent,
                        onUiEvent = {
                            it.handleUiEvent(navController) { commonUiEvent ->
                                onEvent(commonUiEvent)
                            }
                        },
                        onEvent = {
                            rocketDetailViewModel.onEvent(it)
                        }
                    )
                }
            }
        }

    }

}