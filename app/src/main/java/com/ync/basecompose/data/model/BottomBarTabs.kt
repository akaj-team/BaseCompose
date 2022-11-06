package com.ync.basecompose.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.ync.basecompose.ui.navigation.AppScreens

/**
 * Created by mvn-cuongle-dn
 */
enum class BottomBarTabs(
    val route: String, val title: String, val icon: ImageVector
) {
    HOME(route = AppScreens.Home.route, title = "Home", icon = Icons.Default.Home),
    SEARCH(route = AppScreens.Search.route, title = "Search", icon = Icons.Default.Search),
    FAVORITE(route = AppScreens.Favorite.route, title = "Profile", icon = Icons.Default.Favorite)
}
