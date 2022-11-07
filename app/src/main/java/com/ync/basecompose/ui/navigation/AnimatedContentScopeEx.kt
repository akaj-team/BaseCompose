package com.ync.basecompose.ui.navigation

import androidx.compose.animation.*
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@ExperimentalAnimationApi
internal fun AnimatedContentScope<*>.defaultEnterTransition(): EnterTransition {
    return fadeIn() + slideInHorizontally(initialOffsetX = { 300 })
}

@ExperimentalAnimationApi
internal fun AnimatedContentScope<*>.defaultExitTransition(): ExitTransition {
    return fadeOut() + slideOutHorizontally(targetOffsetX = { -300 })
}

internal val NavDestination.hostNavGraph: NavGraph
    get() = hierarchy.first { it is NavGraph } as NavGraph

@ExperimentalAnimationApi
internal fun AnimatedContentScope<*>.defaultPopEnterTransition(): EnterTransition {
    return fadeIn() + slideInHorizontally(initialOffsetX = { -300 })
}

@ExperimentalAnimationApi
internal fun AnimatedContentScope<*>.defaultPopExitTransition(): ExitTransition {
    return fadeOut() + slideOutHorizontally(targetOffsetX = { 300 })
}