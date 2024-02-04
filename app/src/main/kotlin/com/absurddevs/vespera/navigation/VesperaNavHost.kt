package com.absurddevs.vespera.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.absurddevs.vespera.ui.VesperaAppState

@Composable
fun VesperaNavHost(
    appState: VesperaAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController

}