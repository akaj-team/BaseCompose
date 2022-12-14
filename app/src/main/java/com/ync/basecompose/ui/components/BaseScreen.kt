package com.ync.basecompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.*
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ync.basecompose.arch.base.BaseViewModel
import com.ync.basecompose.data.network.error.ErrorModel

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/2/22.
 */
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun BaseScreen(
    viewModel: BaseViewModel? = null,
    background: Color = Color.White,
    contentAlignment: Alignment = Alignment.TopStart,
    onErrorClicked: (ErrorModel) -> Unit = {},
    onCreate: () -> Unit = {},
    onStart: () -> Unit = {},
    onResume: () -> Unit = {},
    onPause: () -> Unit = {},
    onStop: () -> Unit = {},
    onDestroy: () -> Unit = {},
    content: @Composable() BoxScope.() -> Unit,
) {
    var isFirstLaunched by rememberSaveable {
        mutableStateOf(true)
    }
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(background),
        contentAlignment = contentAlignment
    ) {
        content.invoke(this)
    }

    viewModel?.run {
        val loadingState by loadingFlow.collectAsStateWithLifecycle()
        val commonErrorState by viewErrorFlow.collectAsStateWithLifecycle(Throwable())
        Loading(isShow = loadingState)
        CommonError(throwable = commonErrorState) {
            dismissError()
            if (it.isCommonError()) {
                // TODO: Handle late
            } else {
                onErrorClicked.invoke(it)
            }
        }
    }

    //handle lifecycle event
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE ->
                    if (isFirstLaunched) {
                        onCreate.invoke()
                    }
                Lifecycle.Event.ON_START -> {
                    if (isFirstLaunched) {
                        onStart.invoke()
                    }
                }
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
            isFirstLaunched = false
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
