package com.ync.basecompose.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import com.ync.basecompose.arch.extentions.OnBottomReached

/**
 * @author mvn-toan.nguyen2 on 11/7/22
 **/

@Composable
fun LazyColumnLoadMore(
    contentPadding: PaddingValues = PaddingValues(),
    onLoadMore: () -> Unit,
    content: LazyListScope.() -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState, contentPadding = contentPadding) {
        content.invoke(this)
    }
    listState.OnBottomReached {
        onLoadMore.invoke()
    }
}

@Composable
fun LazyVerticalGridLoadMore(
    columns: GridCells,
    contentPadding: PaddingValues = PaddingValues(),
    onLoadMore: () -> Unit, content: LazyGridScope.() -> Unit
) {
    val listState = rememberLazyGridState()
    LazyVerticalGrid(
        state = listState, columns = columns,
        contentPadding = contentPadding
    ) {
        content.invoke(this)
    }
    listState.OnBottomReached {
        onLoadMore.invoke()
    }
}
