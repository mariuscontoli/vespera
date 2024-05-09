package com.absurddevs.vespera.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.calculatePosture
import androidx.compose.material3.adaptive.collectFoldingFeaturesAsState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.absurddevs.vespera.NavGraph
import com.absurddevs.vespera.navigation.VesperaNavGraphDefaults
import com.absurddevs.vespera.navigation.VesperaNavigationSuiteDefaults
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.spec.NavHostEngine
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterialNavigationApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun rememberVesperaAppState(
    windowSizeClass: WindowSizeClass,
    defaultNavGraph: NavGraph,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    navHostEngine: NavHostEngine = rememberAnimatedNavHostEngine(
        navHostContentAlignment = Alignment.TopCenter,
        rootDefaultAnimations = VesperaNavGraphDefaults.Animations.rootNavGraphDefaultAnimations(),
        defaultAnimationsForNestedNavGraph = mapOf(
            defaultNavGraph to VesperaNavGraphDefaults.Animations.nestedNavGraphDefaultAnimations()
        )
    )
): VesperaAppState {

    return remember(
        navController,
        coroutineScope,
        windowSizeClass,
        navHostEngine
    ) {
        VesperaAppState(
            navController,
            coroutineScope,
            windowSizeClass,
            navHostEngine
        )
    }
}

@Stable
class VesperaAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    val navHostEngine: NavHostEngine
) {
    /**
     * Stores the current layout type.
     *
     * Uses [NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo] android function
     * to calculate the correct layout type among [NavigationSuiteType]s corresponding
     * to the current window size.
     *
     * *Warning:* This doesn't support [NavigationSuiteType.NavigationDrawer] layout.
     */
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
            val windowSize = with(LocalDensity.current) {
                currentWindowSize().toSize().toDpSize()
            }
            val adaptiveInfo = WindowAdaptiveInfo(
                windowSizeClass = androidx.window.core.layout.WindowSizeClass.compute(
                    windowSize.width.value, windowSize.height.value
                ),
                windowPosture = calculatePosture(collectFoldingFeaturesAsState().value)
            )

            return VesperaNavigationSuiteDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        }
}