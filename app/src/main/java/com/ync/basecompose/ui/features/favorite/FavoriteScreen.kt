package com.ync.basecompose.ui.features.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ync.basecompose.ui.components.BaseScreen

/**
 * Created by mvn-cuongle-dn
 */

@Composable
fun FavoriteScreen() {
    BaseScreen {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Favorite Screen")
        }
    }
}
