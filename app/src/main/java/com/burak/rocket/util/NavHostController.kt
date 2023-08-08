package com.burak.rocket.util

import androidx.navigation.NavHostController
import com.burak.rocket.navigation.Screen

fun NavHostController.handleNavigation(navigation: NavigationType, data: Map<String, Any?>?) {
    val route = when (navigation) {
        is NavigationType.Navigate -> {
            navigation.route
        }
        is NavigationType.PopBackNavigate -> {
            navigation.route
        }
        is NavigationType.ClearBackStackNavigate -> {
            navigation.route
        }
        is NavigationType.BottomBarNavigate -> {
            navigation.route
        }
        is NavigationType.ToRouteNotInclusivePopUp -> {
            navigation.route
        }
        is NavigationType.PopBack -> {
            navigation.route
        }
        is NavigationType.PopUpToNavigate -> {
            navigation.route
        }
    }

    when (navigation) {
        is NavigationType.PopBack -> {
            previousBackStackEntry?.apply {
                data?.forEach { (key, value) ->
                    savedStateHandle.set(
                        key = key,
                        value = value
                    )
                }
            }
            popBackStack()

        }
        is NavigationType.ToRouteNotInclusivePopUp -> {
            popBackStack(
                route = navigation.route,
                inclusive = false
            )
        }
        is NavigationType.Navigate,
        is NavigationType.PopBackNavigate,
        is NavigationType.ClearBackStackNavigate,
        is NavigationType.BottomBarNavigate -> {
            if (navigation is NavigationType.PopBackNavigate) {
                popBackStack()
            }
            navigate(
                route = route
            ) {
                when (navigation) {
                    is NavigationType.ClearBackStackNavigate -> {
                        val popUpRoute = navigation.popUpRoute
                        if (popUpRoute != null) {
                            popUpTo(popUpRoute)
                        } else {
                            popUpTo(0)
                        }
                    }
                    else -> {}
                }
            }
            getBackStackEntry(route = route).apply {
                data?.forEach { (key, value) ->
                    savedStateHandle.set(
                        key = key,
                        value = value
                    )
                }
            }
        }
        is NavigationType.PopUpToNavigate -> {
            popBackStack(
                route = navigation.popUpRoute,
                inclusive = false
            )

            navigate(
                route = navigation.route
            )

            getBackStackEntry(route = route).apply {
                data?.forEach { (key, value) ->
                    savedStateHandle.set(
                        key = key,
                        value = value
                    )
                }
            }
        }
    }
}