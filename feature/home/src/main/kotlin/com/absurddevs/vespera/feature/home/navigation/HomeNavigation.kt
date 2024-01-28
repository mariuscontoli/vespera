package com.absurddevs.vespera.feature.home.navigation

import HomeRoute
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

private const val HOME_GRAPH_ROUTE_PATTERN = "home_graph"
const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(HOME_GRAPH_ROUTE_PATTERN, navOptions)

fun NavGraphBuilder.homeScreen() {
    composable(route = HOME_ROUTE) {
        HomeRoute()
    }
}