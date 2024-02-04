package com.absurddevs.vespera.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.absurddevs.vespera.navigation.SettingsNavGraph
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@SettingsNavGraph(start = true)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "This is the settings screen",
            modifier = Modifier.align(Alignment.Center
            )
        )
    }
}