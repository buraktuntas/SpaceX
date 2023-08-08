package com.burak.rocket.navigation

sealed class Screen(val route: String) {
    object TabScreen : Screen("tab_layout_screen")
    object RocketListScreen : Screen("rocket_list_screen")
    object RocketDetailScreen: Screen("rocket_details_screen")
    object FavoriteScreen: Screen("favorite_screen")

}