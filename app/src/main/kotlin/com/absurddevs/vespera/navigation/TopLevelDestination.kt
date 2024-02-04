package com.absurddevs.vespera.navigation

import androidx.annotation.StringRes
import androidx.compose.material3.adaptive.navigation.suite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteType
import androidx.compose.ui.graphics.vector.ImageVector
import com.absurddevs.vespera.NavGraph
import com.absurddevs.vespera.NavGraphs
import com.absurddevs.vespera.R
import com.absurddevs.vespera.core.designsystem.icon.VesperaIcons
import com.absurddevs.vespera.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec


/**
 * Type for the top level destination in the app. Each of these destinations can contain one
 * or more screens (based on the window size). Navigation from one screen to the next within
 * a single destination will be handled directly in composables.
 */
sealed class TopLevelDestination(
    val graph: NavGraph,
    val direction: DirectionDestinationSpec,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val labelResId: Int
) {
    data object HOME : TopLevelDestination(
        graph = NavGraphs.home,
        direction = HomeScreenDestination,
        selectedIcon = VesperaIcons.House,
        unselectedIcon = VesperaIcons.HouseBorder,
        labelResId = R.string.destination_home
    )

    companion object {
        @OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
        fun fromNavigationSuiteType(navigationSuiteType: NavigationSuiteType
        ): List<TopLevelDestination> {
            return when (navigationSuiteType) {
                NavigationSuiteType.NavigationBar -> listOf(HOME)
                else -> listOf(HOME)
            }
        }
    }
}
