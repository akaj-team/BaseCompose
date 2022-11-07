package com.ync.basecompose.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import com.ync.basecompose.arch.extentions.OnBottomReached

/**
 * @author mvn-toan.nguyen2 on 11/7/22
 **/

@Composable
fun <T> LazyColumnLoadMore(
    items: List<T>,
    contentPadding: PaddingValues = PaddingValues(),
    threadHold: Int = 0,
    onLoadMore: () -> Unit,
    itemContent: @Composable LazyItemScope.(item: T) -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState, contentPadding = contentPadding) {
        items(items) { item ->
            itemContent.invoke(this, item)
        }
    }
    listState.OnBottomReached(threadHold) {
        onLoadMore.invoke()
    }
}

@Composable
fun <T> LazyVerticalGridLoadMore(
    items: List<T>,
    columns: GridCells,
    threadHold: Int = 0,
    contentPadding: PaddingValues = PaddingValues(),
    onLoadMore: () -> Unit,
    itemContent: @Composable LazyGridItemScope.(item: T) -> Unit
) {
    val listState = rememberLazyGridState()
    LazyVerticalGrid(
        state = listState, columns = columns,
        contentPadding = contentPadding
    ) {
        items(items) { item ->
            itemContent.invoke(this, item)
        }
    }
    listState.OnBottomReached(threadHold) {
        onLoadMore.invoke()
    }
}
