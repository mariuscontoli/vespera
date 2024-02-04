package com.absurddevs.vespera.ui.menu

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigation.suite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.absurddevs.vespera.NavGraphs
import com.absurddevs.vespera.appCurrentDestinationAsState
import com.absurddevs.vespera.core.ui.DevicePosture
import com.absurddevs.vespera.navigation.TopLevelDestination
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.dependency

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
    ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class
)
@Destination
@RootNavGraph(start = true)
@Composable
fun MenuScreen(
    navigator: DestinationsNavigator,
    navigationSuiteType: NavigationSuiteType,
    navController: NavController,
    windowSize: WindowSizeClass,
    devicePosture: DevicePosture
) {

    val navigationController = rememberNavController()
    val navigationHostEngine = rememberAnimatedNavHostEngine(
        navHostContentAlignment = Alignment.TopCenter,
        rootDefaultAnimations = RootNavGraphDefaultAnimations(
            enterTransition = { fadeIn(tween(300)) + slideInVertically { 80 } },
            exitTransition = { fadeOut() },
        ),
        defaultAnimationsForNestedNavGraph = emptyMap()
    )

    val appCurrentDestinationState by navigationController.appCurrentDestinationAsState()

    NavigationSuiteScaffold(
        layoutType = navigationSuiteType,
        navigationSuiteItems = {
            val items = TopLevelDestination.fromNavigationSuiteType(navigationSuiteType)
            items.forEach { destination ->
                val selected = appCurrentDestinationState in destination.graph.destinations

                item(
                    selected = selected,
                    onClick = {
                        if (selected) {
                            // TODO handle already selected nav
                        } else {
                            navigationController.navigate(destination.graph.route) {
                                popUpTo(NavGraphs.menu.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }

                    },
                    label = {
                        Text(text = stringResource(id = destination.labelResId),
                            style = MaterialTheme.typography.labelLarge
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                            contentDescription = stringResource(id = destination.labelResId)
                        )
                    },
                    alwaysShowLabel = true
                )
            }
        }
    ) {
        val listState = rememberLazyListState()

        DestinationsNavHost(
            navGraph = NavGraphs.menu,
            engine = navigationHostEngine,
            navController = navigationController,
            dependenciesContainerBuilder = {
                dependency(listState)
                dependency(devicePosture)
                dependency(windowSize)
            }
        )
    }
}
