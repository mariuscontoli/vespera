package com.absurddevs.vespera.navigation

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = false)
@NavGraph
annotation class MenuNavGraph(
    val start: Boolean = false
)

@MenuNavGraph(start = true)
@NavGraph
annotation class HomeNavGraph(
    val start: Boolean = false
)

@MenuNavGraph(start = false)
@NavGraph
annotation class SettingsNavGraph(
    val start: Boolean = false
)

