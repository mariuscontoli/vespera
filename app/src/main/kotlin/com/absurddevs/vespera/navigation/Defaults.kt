package com.absurddevs.vespera.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigation.suite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass.Companion.Compact
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Medium
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Expanded

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
object VesperaNavigationSuiteDefaults {

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    fun calculateFromAdaptiveInfo(
        adaptiveInfo: WindowAdaptiveInfo
    ): NavigationSuiteType {
        return with(adaptiveInfo) {
            if (windowPosture.isTabletop || (windowSizeClass.heightSizeClass == Compact)) {
                NavigationSuiteType.NavigationBar
            } else if (windowSizeClass.widthSizeClass == Medium) {
                NavigationSuiteType.NavigationRail
            } else if (windowSizeClass.widthSizeClass == Expanded) {
                NavigationSuiteType.NavigationDrawer
            } else {
                NavigationSuiteType.NavigationBar
            }
        }
    }
}