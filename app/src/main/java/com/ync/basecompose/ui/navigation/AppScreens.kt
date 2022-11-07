package com.ync.basecompose.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
sealed class AppScreens(var route: String) {
    // Define argument screen first name is screen name
    enum class ARGUMENT(
        var key: String,
        var routeName: String,
        var type: NavType<*>,
        var defaultValue: Any
    ) {
        // Home
        HOME_ID("id", Home.route, NavType.LongType, -1L),

        // Detail Coin
        COIN_ID("coin_id", DetailCoin.route, NavType.StringType, ""),
        COIN_IMAGE("coin_image", DetailCoin.route, NavType.StringType, ""),
    }

    // Define route screen
    object Splash : AppScreens("splash_screen")
    object Home : AppScreens("home_screen")
    object DetailCoin : AppScreens("detail_coin")
    object Search : AppScreens("search_screen")
    object Favorite : AppScreens("favorite_screen")
    object About : AppScreens("about_screen")

    fun routeWithArgsValue(vararg value: Pair<String, Any>): String {
        var routeArg = routeArgs()
        value.forEach {
            routeArg = routeArg.replace("{${it.first}}", it.second.toString())
        }
        return routeArg
    }

    fun namedNavArgs(): List<NamedNavArgument> {
        return ARGUMENT.values().filter { it.routeName == route }.map {
            navArgument(it.key) {
                type = it.type
                defaultValue = it.defaultValue
            }
        }
    }

    fun routeArgs(): String {
        return buildString {
            append(route)
            val args = ARGUMENT.values().filter {
                it.routeName == route
            }
            args.forEachIndexed { index, argument ->
                append(if (index == 0) "?" else "&")
                append("${argument.key}={${argument.key}}")
            }
        }
    }
}
