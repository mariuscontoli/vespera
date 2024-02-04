package com.absurddevs.vespera.navigation

import com.ramcosta.composedestinations.annotation.NavGraph

@NavGraph
annotation class MenuNavGraph(
    val start: Boolean = false
)

@MenuNavGraph(start = true)
@NavGraph
annotation class HomeNavGraph(
    val start: Boolean = false
)

