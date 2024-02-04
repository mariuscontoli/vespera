package com.absurddevs.vespera.ui.menu.navigation.components

import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.absurddevs.vespera.appCurrentDestinationAsState
import com.absurddevs.vespera.navigation.TopLevelDestination

@Composable
fun MenuNavigationBar(
    items: List<TopLevelDestination>,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        items.forEach { destination ->
            val selected =
                navController.appCurrentDestinationAsState().value in destination.graph.destinations

            NavigationBarItem(
                selected = selected,
                onClick = { /*TODO*/ },
                icon = {
                    BadgedBox(badge = {}) {
                        Icon(
                            imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                            contentDescription = stringResource(id = destination.labelResId))
                    }
                },
                label = {
                    Text(
                        text = stringResource(id = destination.labelResId),
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}