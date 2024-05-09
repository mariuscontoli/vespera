package com.absurddevs.vespera.core.ui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BetterInsets(
    val statusBarHeight: Dp = 0.dp,
    val navigationBarHeight: Dp = 0.dp
)

val LocalBetterInsets = compositionLocalOf<BetterInsets> { error("No better insets provided") }