package com.ync.basecompose.ui.features.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@Composable
fun SplashScreen(onNextScreen: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(3000L)
        onNextScreen.invoke()
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Splash", fontSize = 20.sp)
    }
}

@Preview
@Composable
fun preview() {
    SplashScreen {

    }
}