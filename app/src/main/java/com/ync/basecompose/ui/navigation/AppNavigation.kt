package com.ync.basecompose.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ync.basecompose.ui.components.BottomBar
import com.ync.basecompose.data.model.BottomBarTabs
import com.ync.basecompose.ui.features.detail.DetailCoinScreen
import com.ync.basecompose.ui.features.favorite.FavoriteScreen
import com.ync.basecompose.ui.features.home.HomeScreen
import com.ync.basecompose.ui.features.search.SearchScreen
import com.ync.basecompose.ui.features.splash.SplashScreen

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberAnimatedNavController()
    val isShouldShowBottomBar =
        navController.currentBackStackEntryAsState().value?.destination?.route in (BottomBarTabs.values()
            .map { it.route })
    Scaffold(bottomBar = {
        if (isShouldShowBottomBar) {
            BottomBar(navController = navController)
        }
    }) {
        AnimatedNavHost(
            navController = navController,
            startDestination = AppScreens.Splash.route,
            modifier = modifier
        ) {
            addSplashScreen(navController)
            addHomeScreen(navController)
            addSearchScreen()
            addFavoriteScreen()
            addDetailCoinScreen(navController)
        }
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
private fun NavGraphBuilder.addSearchScreen() {
    composable(route = AppScreens.Search.route,
        exitTransition = { defaultExitTransition(initialState, targetState) },
        enterTransition = { defaultEnterTransition(initialState, targetState) },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }) {
        SearchScreen()
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.addFavoriteScreen() {
    composable(route = AppScreens.Favorite.route,
        exitTransition = { defaultExitTransition(initialState, targetState) },
        enterTransition = { defaultEnterTransition(initialState, targetState) },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }) {
        FavoriteScreen()
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
