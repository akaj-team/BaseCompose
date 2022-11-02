package com.ync.basecompose.ui.navigation

import androidx.compose.animation.*
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@ExperimentalAnimationApi
internal fun AnimatedContentScope<*>.defaultEnterTransition(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
): EnterTransition {
    val initialNavGraph = initial.destination.hostNavGraph
    val targetNavGraph = target.destination.hostNavGraph
    if (initialNavGraph.id != targetNavGraph.id) {
        return fadeIn()
    }
    return fadeIn() + slideIntoContainer(AnimatedContentScope.SlideDirection.Start)
}

@ExperimentalAnimationApi
internal fun AnimatedContentScope<*>.defaultExitTransition(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
): ExitTransition {
    val initialNavGraph = initial.destination.hostNavGraph
    val targetNavGraph = target.destination.hostNavGraph
    if (initialNavGraph.id != targetNavGraph.id) {
        return fadeOut()
    }
    return fadeOut() + slideOutOfContainer(AnimatedContentScope.SlideDirection.Start)
}

internal val NavDestination.hostNavGraph: NavGraph
    get() = hierarchy.first { it is NavGraph } as NavGraph

@ExperimentalAnimationApi
internal fun AnimatedContentScope<*>.defaultPopEnterTransition(): EnterTransition {
    return fadeIn() + slideIntoContainer(AnimatedContentScope.SlideDirection.End)
}

@ExperimentalAnimationApi
internal fun AnimatedContentScope<*>.defaultPopExitTransition(): ExitTransition {
    return fadeOut() + slideOutOfContainer(AnimatedContentScope.SlideDirection.End)
}