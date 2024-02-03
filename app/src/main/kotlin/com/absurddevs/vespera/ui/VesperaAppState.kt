package com.absurddevs.vespera.ui

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.calculatePosture
import androidx.compose.material3.adaptive.collectFoldingFeaturesAsState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigation.suite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.absurddevs.vespera.feature.home.navigation.HOME_ROUTE
import com.absurddevs.vespera.feature.home.navigation.navigateToHomeGraph
import com.absurddevs.vespera.navigation.TopLevelDestination
import com.absurddevs.vespera.navigation.TopLevelDestination.HOME
import com.absurddevs.vespera.navigation.VesperaNavigationSuiteDefaults
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
    ExperimentalMaterial3AdaptiveApi::class
)
@Composable
fun rememberNiaAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): VesperaAppState {

    return remember(
        navController,
        coroutineScope,
        windowSizeClass
    ) {
        VesperaAppState(
            navController,
            coroutineScope,
            windowSizeClass
        )
    }
}

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
@Stable
class VesperaAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HOME_ROUTE -> HOME
            else -> null
        }

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar


    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    /**
     * Stores the current layout type.
     *
     * Uses [NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo] android function
     * to calculate the correct layout type among [NavigationSuiteType]s corresponding
     * to the current window size.
     *
     * *Warning:* This doesn't support [NavigationSuiteType.NavigationDrawer] layout.
     */
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    val currentLayoutType: NavigationSuiteType
        @Composable get() {
            return NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(
                currentWindowAdaptiveInfo()
            )
        }

    /**
     * Stores the current [NavigationSuiteType].
     *
     * Uses [VesperaNavigationSuiteDefaults.calculateFromAdaptiveInfo] function to return
     * the correct layout based on the current window.
     */
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    val navigationSuiteType: NavigationSuiteType
        @Composable get() {
            val adaptiveInfo = WindowAdaptiveInfo(
                windowSizeClass = windowSizeClass,
                windowPosture = calculatePosture(collectFoldingFeaturesAsState().value)
            )

            return VesperaNavigationSuiteDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        }

    /**
     * UI logic for navigating to a top level destination in the app.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            launchSingleTop = true

            restoreState = true
        }

        when (topLevelDestination) {
            HOME -> navController.navigateToHomeGraph(topLevelNavOptions)
        }
    }
}