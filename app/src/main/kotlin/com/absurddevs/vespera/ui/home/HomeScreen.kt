package com.absurddevs.vespera.ui.home

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.absurddevs.vespera.R
import com.absurddevs.vespera.core.designsystem.icon.VesperaIcons
import com.absurddevs.vespera.navigation.HomeNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@HomeNavGraph(start = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    mainNavController: NavController,
    listState: LazyListState,
    windowSize: WindowSizeClass,
) {
    BoxWithConstraints() {
        val savedMaxWidth by remember {
            mutableStateOf(maxWidth)
        }

        Row(Modifier.fillMaxSize()) {
            val exitUntilCollapsedScrollBehavior =
                TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
            val pinnedScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

            Scaffold(
                modifier = modifier
                    .shadow(8.dp)
                    .zIndex(1f)
                    .nestedScroll(
                        if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact)
                            exitUntilCollapsedScrollBehavior.nestedScrollConnection
                        else pinnedScrollBehavior.nestedScrollConnection
                    )
                ,
                topBar = {
                    if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
                        val colorTransition = exitUntilCollapsedScrollBehavior.state.collapsedFraction
                        val appBarContainerColor by rememberUpdatedState(
                            lerp(
                                MaterialTheme.colorScheme.surface,
                                MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
                                FastOutLinearInEasing.transform(colorTransition)
                            )
                        )
                        LargeTopAppBar(
                            modifier = Modifier
                                .background(appBarContainerColor)
                                .statusBarsPadding(),
                            title = { Text(text = stringResource(id = R.string.destination_home)) },
                            scrollBehavior = exitUntilCollapsedScrollBehavior
                        )
                    } else {
                        TopAppBar(
                            title = { Text(text = stringResource(id = R.string.destination_home)) },
                            navigationIcon = {
                                if (windowSize.widthSizeClass == WindowWidthSizeClass.Expanded) {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(imageVector = VesperaIcons.MenuBorder, contentDescription = "menu")
                                    }
                                }
                            },
                            scrollBehavior = pinnedScrollBehavior
                        )
                    }
                }
            ) { innerPadding ->
                LazyColumn(
                    contentPadding = innerPadding,
                ) {

                }
            }
        }

    }

}