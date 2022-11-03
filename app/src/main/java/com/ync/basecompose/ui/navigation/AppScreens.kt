package com.ync.basecompose.ui.navigation

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
sealed class AppScreens(var route: String) {
    object Splash : AppScreens("splash_screen")
    object Home : AppScreens("home_screen")
}