package com.burak.rocket.util

sealed class NavigationType {
    data class Navigate(val route: String) : NavigationType()
    data class ClearBackStackNavigate(val route: String, val popUpRoute: String? = null) : NavigationType()
    data class PopBackNavigate(val route: String) : NavigationType()
    data class PopUpToNavigate(val popUpRoute: String, val route: String) : NavigationType()
    data class BottomBarNavigate(val route: String) : NavigationType()
    data class ToRouteNotInclusivePopUp(val route: String) : NavigationType()
    data class PopBack(val route: String) : NavigationType()
}