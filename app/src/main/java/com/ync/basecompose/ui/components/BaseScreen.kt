package com.ync.basecompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.dismissError
import androidx.lifecycle.loadingFlow
import androidx.lifecycle.viewErrorFlow
import com.ync.basecompose.arch.base.BaseViewModel
import com.ync.basecompose.data.network.error.ErrorModel

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/2/22.
 */
@Composable
fun BaseScreen(
    viewModel: BaseViewModel,
    background: Color = Color.White,
    onErrorClicked: (ErrorModel) -> Unit = {},
    content: @Composable() BoxScope.() -> Unit,
) {
    val loadingState by viewModel.loadingFlow.collectAsState()
    val commonErrorState by viewModel.viewErrorFlow.collectAsState(Throwable())
    Loading(isShow = loadingState)
    CommonError(throwable = commonErrorState) {
        viewModel.dismissError()
        if (it.isCommonError()) {
            // TODO: Handle late
        } else {
            onErrorClicked.invoke(it)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(background)
    ) {
        content.invoke(this)
    }
}