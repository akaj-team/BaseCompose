package com.ync.basecompose.ui.features

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.ync.basecompose.ui.components.BaseScreen

/**
 * Created by mvn-cuongle-dn
 */

@Composable
fun TestScreen(title: String) {

    BaseScreen {
        Box(contentAlignment = Alignment.Center) {
            Text(text = title)
        }
    }
}
