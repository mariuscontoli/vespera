package com.absurddevs.vespera.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.absurddevs.vespera.R
import com.absurddevs.vespera.core.designsystem.icon.VesperaIcons
import com.absurddevs.vespera.feature.home.R as homeR

/**
 * Type for the top level destination in the app. Each of these destinations can contain one
 * or more screens (based on the window size). Navigation from one screen to the next within
 * a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int
) {
    HOME(
        selectedIcon = VesperaIcons.House,
        unselectedIcon = VesperaIcons.HouseBorder,
        iconTextId = homeR.string.feature_home_title,
        titleTextId = R.string.app_name
    )
}