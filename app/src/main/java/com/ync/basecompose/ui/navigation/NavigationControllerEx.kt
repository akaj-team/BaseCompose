package com.ync.basecompose.ui.navigation

import androidx.navigation.NavController

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/6/22.
 */
fun NavController.navigationParcelables(route: String, vararg arguments: Pair<String, Any>) {
    arguments.forEach {
        currentBackStackEntry?.savedStateHandle?.set(it.first, it.second)
    }
    navigate(route)
}

fun <T> NavController.getParcelable(key: String): T? {
    return previousBackStackEntry?.savedStateHandle?.get<T>(key)
}