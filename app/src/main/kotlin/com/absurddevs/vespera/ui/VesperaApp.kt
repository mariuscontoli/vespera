package com.absurddevs.vespera.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.navigation.suite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteScope
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.absurddevs.vespera.core.designsystem.component.VesperaBackground
import com.absurddevs.vespera.core.designsystem.component.VesperaNavigationBar
import com.absurddevs.vespera.core.designsystem.component.VesperaNavigationBarItem
import com.absurddevs.vespera.core.designsystem.component.VesperaNavigationRail
import com.absurddevs.vespera.core.designsystem.component.VesperaNavigationRailItem
import com.absurddevs.vespera.core.designsystem.component.VesperaTopAppBar
import com.absurddevs.vespera.navigation.TopLevelDestination
import com.absurddevs.vespera.navigation.VesperaNavHost

@OptIn(ExperimentalComposeUiApi::class,
    ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
)
@Composable
fun VesperaAdaptiveApp(
    windowSizeClass: WindowSizeClass,
    appState: VesperaAppState = rememberNiaAppState(windowSizeClass = windowSizeClass)
) {
    val currentDestination = appState.currentDestination
    val topLevelDestination = appState.currentTopLevelDestination

    VesperaBackground {
        NavigationSuiteScaffold(
            layoutType = appState.currentLayoutType,
            navigationSuiteItems = {
                vesperaNavigationSuiteItems(
                    currentTopLevelDestination = topLevelDestination,
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = currentDestination,
                )
            },
            containerColor = Color.Transparent,
            navigationSuiteColors = NavigationSuiteDefaults.colors()
        ) {
            Scaffold(
                modifier = Modifier.semantics {
                    testTagsAsResourceId = true
                },
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0,0,0,0),
            ) { innerPadding ->
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .consumeWindowInsets(innerPadding)
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal
                            )
                        )
                ) {
                    Column(Modifier.fillMaxSize()) {
                        val destination = appState.currentTopLevelDestination

                        destination?.let {

                        }

                        VesperaNavHost(appState = appState)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun VesperaApp(
    windowSizeClass: WindowSizeClass,
    appState: VesperaAppState = rememberNiaAppState(windowSizeClass = windowSizeClass)
) {
    
    VesperaBackground {
        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0,0,0,0),
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    VesperaBottomBar(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                        modifier = Modifier.testTag("VesperaBottomBar")
                    )
                }
            }
        ) { innerPadding ->
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal
                        )
                    )
            ) {
                if (appState.shouldShowNavRail) {
                    VesperaNavRail(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                        modifier = Modifier
                            .testTag("NiaNavRail")
                            .safeDrawingPadding(),
                    )
                }

                Column(Modifier.fillMaxSize()) {
                    val destination = appState.currentTopLevelDestination

                    destination?.let {
                        VesperaTopAppBar(
                            titleRes = destination.titleTextId,
                            navigationIcon = destination.navigationIcon,
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color.Transparent
                            )
                        )
                    }

                    VesperaNavHost(appState = appState)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
private fun NavigationSuiteScope.vesperaNavigationSuiteItems(
    currentTopLevelDestination: TopLevelDestination?,
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    currentTopLevelDestination?.let {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            item(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = if (selected) destination.selectedIcon
                        else destination.unselectedIcon,
                        contentDescription = null)
                },
                label = { Text(text = stringResource(id = destination.iconTextId)) },
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun VesperaNavRail(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    VesperaNavigationRail(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            VesperaNavigationRailItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = destination.unselectedIcon,
                        contentDescription = null,
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = null,
                    )
                },
                label = { Text(stringResource(destination.iconTextId)) },
                modifier = Modifier,
            )
        }
    }
}

@Composable
private fun VesperaBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    VesperaNavigationBar(
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            VesperaNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                       Icon(
                           imageVector = destination.unselectedIcon,
                           contentDescription = null
                       )
                },
                selectedIcon = {
                    Icon(imageVector = destination.selectedIcon,
                        contentDescription = null)
                },
                label = { Text(text = stringResource(id = destination.iconTextId)) },
                modifier = modifier
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false