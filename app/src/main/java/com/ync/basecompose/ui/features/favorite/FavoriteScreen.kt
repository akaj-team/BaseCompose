package com.ync.basecompose.ui.features.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.ync.basecompose.ui.components.BaseScreen

/**
 * Created by mvn-cuongle-dn
 */

@Composable
fun FavoriteScreen() {
    BaseScreen {
        Box(contentAlignment = Alignment.Center) {
            Text(text = "Favorite Screen")
        }
    }
}
