package com.ync.basecompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.*
import com.ync.basecompose.arch.base.BaseViewModel

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/2/22.
 */
@Composable
fun BaseScreen(
    viewModel: BaseViewModel,
    content: @Composable () -> Unit,
    onCreate: () -> Unit = {},
    onStart: () -> Unit = {},
    onResume: () -> Unit = {},
    onPause: () -> Unit = {},
    onStop: () -> Unit = {},
    onDestroy: () -> Unit = {}
) {
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val loadingState by viewModel.loadingFlow.collectAsState()
    val commonErrorState by viewModel.viewErrorFlow.collectAsState(Throwable())
    Loading(isShow = loadingState)
    CommonError(throwable = commonErrorState)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {
        content.invoke()
    }

    //handle lifecycle event
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> onCreate.invoke()
                Lifecycle.Event.ON_START -> onStart.invoke()
                Lifecycle.Event.ON_RESUME -> onResume.invoke()
                Lifecycle.Event.ON_PAUSE -> onPause.invoke()
                Lifecycle.Event.ON_STOP -> onStop.invoke()
                Lifecycle.Event.ON_DESTROY -> onDestroy.invoke()
                else -> {}
            }
        }
        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)
        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
