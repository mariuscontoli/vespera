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
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.absurddevs.vespera.navigation.TopLevelDestination
import com.absurddevs.vespera.navigation.VesperaNavHost

@OptIn(ExperimentalComposeUiApi::class)
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

                }

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