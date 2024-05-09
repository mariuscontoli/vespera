package com.absurddevs.vespera.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationRailDefaults
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.NavGraphDefaultAnimationParams
import com.ramcosta.composedestinations.animations.defaults.NestedNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations

object VesperaNavigationSuiteDefaults {

    fun calculateFromAdaptiveInfo(
        adaptiveInfo: WindowAdaptiveInfo
    ): NavigationSuiteType {
        return with(adaptiveInfo) {
            if (windowPosture.isTabletop || (windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT)) {
                NavigationSuiteType.NavigationBar
            } else if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM) {
                NavigationSuiteType.NavigationRail
            } else if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
                NavigationSuiteType.NavigationDrawer
            } else {
                NavigationSuiteType.NavigationBar
            }
        }
    }

    /**
     * This is the dumbest way to handle multi-layout colors, thank you Google! :)
     *
     * Want to, let's say override the [navigationBarContentColor] ? Then you realise
     * it's just a color and not like the previously [NavigationBarItemColors] class.
     *
     * @see [NavigationSuiteColors]
     */
    @Composable
    fun colors(
        navigationBarContainerColor: Color = NavigationBarDefaults.containerColor,
        navigationBarContentColor: Color = contentColorFor(navigationBarContainerColor),
        navigationRailContainerColor: Color = NavigationRailDefaults.ContainerColor,
        navigationRailContentColor: Color = contentColorFor(navigationRailContainerColor),
        navigationDrawerContainerColor: Color = DrawerDefaults.containerColor,
        navigationDrawerContentColor: Color = contentColorFor(navigationDrawerContainerColor)
    ): NavigationSuiteColors =
        NavigationSuiteDefaults.colors(
            navigationBarContainerColor = navigationBarContainerColor,
            navigationBarContentColor = navigationBarContentColor,
            navigationRailContainerColor = navigationRailContainerColor,
            navigationRailContentColor = navigationRailContentColor,
            navigationDrawerContainerColor = navigationDrawerContainerColor,
            navigationDrawerContentColor = navigationDrawerContentColor
        )
}

/**
 * Object that contains defaults functions or values for a [DestinationsNavHost].
 *
 */
object VesperaNavGraphDefaults {
    /**
     * Object that contains default functions or values for [NavGraphDefaultAnimationParams], used
     * by both [RootNavGraphDefaultAnimations] and [NestedNavGraphDefaultAnimations].
     */
    object Animations {
        fun rootNavGraphDefaultAnimations(
            enterTransition: EnterTransition =  slideInHorizontally { 100 } + fadeIn(),
            exitTransition: ExitTransition = slideOutHorizontally { -100 } + fadeOut(),
            popEnterTransition: EnterTransition = slideInHorizontally { -100 } + fadeIn(),
            popExitTransition: ExitTransition = slideOutHorizontally { 100 } + fadeOut()
        ) : RootNavGraphDefaultAnimations {
            return RootNavGraphDefaultAnimations(
                enterTransition = { enterTransition },
                exitTransition = { exitTransition },
                popEnterTransition = { popEnterTransition },
                popExitTransition = { popExitTransition }
            )
        }

        fun nestedNavGraphDefaultAnimations(
            enterTransition: EnterTransition = slideInHorizontally { it },
            exitTransition: ExitTransition = slideOutHorizontally { -it },
            popEnterTransition: EnterTransition = slideInHorizontally { -it },
            popExitTransition: ExitTransition =  slideOutHorizontally { it },
        ): NestedNavGraphDefaultAnimations {
            return NestedNavGraphDefaultAnimations(
                enterTransition = { enterTransition },
                exitTransition = { exitTransition },
                popEnterTransition = { popEnterTransition },
                popExitTransition = { popExitTransition }
            )
        }
    }
}