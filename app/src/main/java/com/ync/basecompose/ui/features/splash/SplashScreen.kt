package com.ync.basecompose.ui.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ync.basecompose.R
import com.ync.basecompose.ui.components.BaseScreen
import kotlinx.coroutines.delay

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@Composable
fun SplashScreen(onNextScreen: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(2000L)
        onNextScreen.invoke()
    }
    BaseScreen(contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.img_splash),
            contentDescription = null,
            Modifier.padding(30.dp)
        )
    }
}

@Preview
@Composable
fun preview() {
    SplashScreen {

    }
}