package com.absurddevs.vespera.ui.menu.navigation.components

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.absurddevs.vespera.NavGraph
import com.absurddevs.vespera.NavGraphs
import com.absurddevs.vespera.R
import com.absurddevs.vespera.core.designsystem.icon.VesperaIcons


/**
 * Type for the top level destination in the app. Each of these destinations can contain one
 * or more screens (based on the window size). Navigation from one screen to the next within
 * a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val graph: NavGraph,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val labelResId: Int
) {
    HOME(
        graph = NavGraphs.home,
        selectedIcon = VesperaIcons.House,
        unselectedIcon = VesperaIcons.HouseBorder,
        labelResId = R.string.destination_home
    )
}