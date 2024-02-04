package com.absurddevs.vespera.ui.home

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.absurddevs.vespera.core.ui.DevicePosture

enum class HomeLayoutType {
    NORMAL,
    SPLIT;

    companion object {
        fun fromWindowSizeAndDevicePosture(
            windowSize: WindowSizeClass,
            devicePosture: DevicePosture
        ): HomeLayoutType {
            return when (windowSize.widthSizeClass) {
                WindowWidthSizeClass.Compact -> NORMAL
                WindowWidthSizeClass.Medium -> NORMAL
                WindowWidthSizeClass.Expanded ->
                    if (devicePosture is DevicePosture.BookPosture) NORMAL
                    else SPLIT
                else -> NORMAL
            }
        }
    }
}