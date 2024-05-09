package com.absurddevs.vespera.navigation

import androidx.annotation.StringRes
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.ui.graphics.vector.ImageVector
import com.absurddevs.vespera.NavGraph
import com.absurddevs.vespera.NavGraphs
import com.absurddevs.vespera.R
import com.absurddevs.vespera.core.designsystem.icon.VesperaIcons
import com.absurddevs.vespera.destinations.HomeScreenDestination
import com.absurddevs.vespera.destinations.SettingsScreenDestination
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

    data object SETTINGS: TopLevelDestination(
        graph = NavGraphs.settings,
        direction = SettingsScreenDestination,
        selectedIcon = VesperaIcons.Settings,
        unselectedIcon = VesperaIcons.SettingsBorder,
        labelResId = R.string.destination_settings
    )

    companion object {
        fun fromNavigationSuiteType(navigationSuiteType: NavigationSuiteType
        ): List<TopLevelDestination> {
            return when (navigationSuiteType) {
                NavigationSuiteType.NavigationBar -> listOf(HOME, SETTINGS)
                else -> listOf(HOME, SETTINGS)
            }
        }
    }
}
