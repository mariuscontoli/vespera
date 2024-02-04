package com.absurddevs.vespera.ui.menu

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.absurddevs.vespera.NavGraphs
import com.absurddevs.vespera.core.ui.DevicePosture
import com.absurddevs.vespera.ui.menu.navigation.MenuNavigationType
import com.absurddevs.vespera.ui.menu.navigation.components.MenuNavigationBar
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.spec.NavHostEngine

@Destination
@RootNavGraph(start = true)
@Composable
fun MenuPage(
    navigator: DestinationsNavigator,
    navController: NavController,
    windowSize: WindowSizeClass,
    devicePosture: DevicePosture
) {
    val navigationType = MenuNavigationType.fromWindowSizeAndDevicePosture(windowSize, devicePosture)

    MenuNavigationWrapperUi(
        navController = navController,
        navigator = navigator,
        navigationType = navigationType,
        windowSize = windowSize,
        devicePosture = devicePosture
    )
}

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
@Composable
private fun MenuNavigationWrapperUi(
    navController: NavController,
    navigator: DestinationsNavigator,
    navigationType: MenuNavigationType,
    windowSize: WindowSizeClass,
    devicePosture: DevicePosture
) {
    val menuNavController = rememberNavController()

    val menuNavHostEngine = rememberAnimatedNavHostEngine(
        navHostContentAlignment = Alignment.TopCenter,
        rootDefaultAnimations = RootNavGraphDefaultAnimations(
            enterTransition = { fadeIn(tween(300)) + slideInVertically { 80 } },
            exitTransition = { fadeOut() }
        ),
        defaultAnimationsForNestedNavGraph = emptyMap()
    )

    val listState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        Box {
            MenuContent(
                navController = navController,
                menuNavController = menuNavController,
                menuNavHostEngine = menuNavHostEngine,
                navigationType = navigationType,
                listState = listState,
                windowSize = windowSize,
                devicePosture = devicePosture
            )
        }
    }
}

@Composable
fun MenuContent(
    navController: NavController,
    menuNavController: NavHostController,
    menuNavHostEngine: NavHostEngine,
    navigationType: MenuNavigationType,
    listState: LazyListState,
    windowSize: WindowSizeClass,
    devicePosture: DevicePosture
) {
    Row(Modifier.fillMaxWidth()) {
        Box(modifier = Modifier
            .weight(1f)
            .background(MaterialTheme.colorScheme.inverseOnSurface),
            contentAlignment = Alignment.BottomCenter
        ) {
            DestinationsNavHost(
                modifier = Modifier
                    .fillMaxSize(),
                navGraph = NavGraphs.menu,
                engine = menuNavHostEngine,
                navController = menuNavController,
                dependenciesContainerBuilder = {
                    dependency(listState)
                    dependency(windowSize)
                    dependency(devicePosture)
                }
            )

            androidx.compose.animation.AnimatedVisibility(
                visible = navigationType == MenuNavigationType.NAVIGATION_BAR,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }
            ) {
                MenuNavigationBar(navController = menuNavController)
            }
        }
    }
}