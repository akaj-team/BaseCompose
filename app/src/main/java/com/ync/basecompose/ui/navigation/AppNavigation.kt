package com.ync.basecompose.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ync.basecompose.ui.features.detail.DetailCoinScreen
import com.ync.basecompose.ui.features.home.HomeScreen
import com.ync.basecompose.ui.features.splash.SplashScreen

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = AppScreens.Splash.route,
        modifier = modifier
    ) {
        addSplashScreen(navController)
        addHomeScreen(navController)
        addDetailCoinScreen(navController)
    }
}


@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.addSplashScreen(navController: NavController) {
    composable(route = AppScreens.Splash.route) {
        SplashScreen {
            navController.navigate(AppScreens.Home.route) {
                popUpTo(route = AppScreens.Splash.route) {
                    inclusive = true
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.addHomeScreen(
    navController: NavController
) {
    composable(route = AppScreens.Home.route,
        exitTransition = { defaultExitTransition(initialState, targetState) },
        enterTransition = { defaultEnterTransition(initialState, targetState) },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }) {
        HomeScreen(navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.addDetailCoinScreen(navController: NavController) {
    composable(route = AppScreens.DetailCoin.routeArgs(),
        arguments = AppScreens.DetailCoin.namedNavArgs(),
        exitTransition = { defaultExitTransition(initialState, targetState) },
        enterTransition = { defaultEnterTransition(initialState, targetState) },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }) {
        DetailCoinScreen(
            navController,
            it.arguments?.getString(AppScreens.ARGUMENT.COIN_ID.key) ?: "",
            it.arguments?.getString(AppScreens.ARGUMENT.COIN_IMAGE.key) ?: ""
        )
    }
}
