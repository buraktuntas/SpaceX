package com.burak.rocket.util

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.navigation.NavHostController
import com.burak.rocket.presentation.screens.main.MainUiEvent
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalTextApi
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
fun UiEvent.handleUiEvent(
    navController: NavHostController,
    onEvent: (MainUiEvent) -> Unit
) {
    when (this) {
        is UiEvent.Navigate<*> -> {
            navController.handleNavigation(navigationType, data)
        }
        is UiEvent.ShowLoading -> {
            handleLoading(true) {
                onEvent(it)
            }
        }
        is UiEvent.HideLoading -> {
            handleLoading(false) {
                onEvent(it)
            }
        }

        else -> {}
    }
}

fun handleLoading(isLoading: Boolean, onEvent: (MainUiEvent) -> Unit) {
    onEvent(
        MainUiEvent.OnUpdateLoading(isLoading)
    )
}