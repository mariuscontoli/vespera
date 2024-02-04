package com.absurddevs.vespera.ui

//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.WindowInsets
//import androidx.compose.foundation.layout.WindowInsetsSides
//import androidx.compose.foundation.layout.consumeWindowInsets
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.only
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.safeDrawing
//import androidx.compose.foundation.layout.windowInsetsPadding
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.adaptive.navigation.suite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
//import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteDefaults
//import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteScaffold
//import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteScope
//import androidx.compose.material3.windowsizeclass.WindowSizeClass
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.ExperimentalComposeUiApi
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.semantics.semantics
//import androidx.compose.ui.semantics.testTagsAsResourceId
//import androidx.navigation.NavDestination
//import androidx.navigation.NavDestination.Companion.hierarchy
//import com.absurddevs.vespera.core.designsystem.component.VesperaBackground
//import com.absurddevs.vespera.core.designsystem.component.VesperaNavigationBar
//import com.absurddevs.vespera.core.designsystem.component.VesperaNavigationBarItem
//import com.absurddevs.vespera.core.designsystem.component.VesperaNavigationRail
//import com.absurddevs.vespera.core.designsystem.component.VesperaNavigationRailItem
//import com.absurddevs.vespera.ui.menu.navigation.components.TopLevelDestination
//import com.absurddevs.vespera.navigation.VesperaNavHost

//@OptIn(ExperimentalComposeUiApi::class,
//    ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
//)
//@Composable
//fun VesperaAdaptiveApp(
//    windowSizeClass: WindowSizeClass,
//    appState: VesperaAppState = rememberVesperaAppState(windowSizeClass = windowSizeClass)
//) {
//
//
//    VesperaBackground {
//        NavigationSuiteScaffold(
//            layoutType = appState.navigationSuiteType,
//            navigationSuiteItems = {
//
//            },
//            containerColor = Color.Transparent,
//            navigationSuiteColors = NavigationSuiteDefaults.colors()
//        ) {
//            Scaffold(
//                modifier = Modifier.semantics {
//                    testTagsAsResourceId = true
//                },
//                containerColor = Color.Transparent,
//                contentColor = MaterialTheme.colorScheme.onBackground,
//                contentWindowInsets = WindowInsets(0,0,0,0),
//            ) { innerPadding ->
//                Row(
//                    Modifier
//                        .fillMaxSize()
//                        .padding(innerPadding)
//                        .consumeWindowInsets(innerPadding)
//                        .windowInsetsPadding(
//                            WindowInsets.safeDrawing.only(
//                                WindowInsetsSides.Horizontal
//                            )
//                        )
//                ) {
//                    Column(Modifier.fillMaxSize()) {
//
//                        VesperaNavHost(appState = appState)
//                    }
//                }
//            }
//        }
//    }
//}

//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//fun VesperaApp(
//    windowSizeClass: WindowSizeClass,
//    appState: VesperaAppState = rememberVesperaAppState(windowSizeClass = windowSizeClass)
//) {
//
//    VesperaBackground {
//        Scaffold(
//            modifier = Modifier.semantics {
//                testTagsAsResourceId = true
//            },
//            containerColor = Color.Transparent,
//            contentColor = MaterialTheme.colorScheme.onBackground,
//            contentWindowInsets = WindowInsets(0, 0, 0, 0),
//            bottomBar = {
//
//            }
//        ) { innerPadding ->
//            Row(
//                Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding)
//                    .consumeWindowInsets(innerPadding)
//                    .windowInsetsPadding(
//                        WindowInsets.safeDrawing.only(
//                            WindowInsetsSides.Horizontal
//                        )
//                    )
//            ) {
//
//
//                Column(Modifier.fillMaxSize()) {
//                    VesperaNavHost(appState = appState)
//                }
//            }
//        }
//    }
//}