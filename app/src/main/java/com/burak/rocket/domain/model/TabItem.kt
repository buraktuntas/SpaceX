package com.burak.rocket.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

data class TabItem (
    val title: String,
    val icon: ImageVector,
    val screen: @Composable (NavController) -> Unit
)