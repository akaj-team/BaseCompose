package com.ync.basecompose.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ync.basecompose.data.network.error.ErrorModel

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/2/22.
 */
@Composable
fun CommonError(throwable: Throwable?, onErrorClicked: (ErrorModel) -> Unit) {
    (throwable as? ErrorModel)?.apply {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text(text = "ERROR")
            },
            text = {
                Text(text = message ?: "")
            },
            confirmButton = {
            },
            dismissButton = {
                Button(onClick = {
                    onErrorClicked.invoke(this)
                }) {
                    Text("Ok")
                }
            }
        )
    }
}