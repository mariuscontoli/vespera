package com.absurddevs.vespera.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.absurddevs.vespera.feature.home.navigation.HOME_ROUTE
import com.absurddevs.vespera.feature.home.navigation.homeScreen
import com.absurddevs.vespera.ui.VesperaAppState

@Composable
fun VesperaNavHost(
    appState: VesperaAppState,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE
) {
    val navController = appState.navController

    NavHost(navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen()
    }
}