package com.absurddevs.vespera.ui.menu.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.absurddevs.vespera.core.ui.DevicePosture

enum class MenuNavigationType {
    NAVIGATION_BAR,
    NAVIGATION_RAIL,
    NAVIGATION_DRAWER;

    companion object {
        fun fromWindowSizeAndDevicePosture(
            windowSizeClass: WindowSizeClass,
            devicePosture: DevicePosture
        ): MenuNavigationType {
            return when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> NAVIGATION_BAR
                WindowWidthSizeClass.Medium -> NAVIGATION_RAIL
                WindowWidthSizeClass.Expanded ->
                    if (devicePosture is DevicePosture.BookPosture) NAVIGATION_RAIL
                    else NAVIGATION_DRAWER
                else -> NAVIGATION_BAR
            }
        }
    }
}