package com.ync.basecompose.arch.extentions

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.*

/**
 * @author mvn-toan.nguyen2 on 11/7/22
 **/
@Composable
fun LazyListState.OnBottomReached(
    // tells how many items before we reach the bottom of the list
    // to call onLoadMore function
    threadHold: Int = 0,
    loadMore: () -> Unit
) {
    //Detect use scroll to handle event
    if (isScrollInProgress) {
        // ThreadHold must be positive.
        // Or our list will never reach the bottom.
        require(threadHold >= 0) { "threadHold cannot be negative, but was $threadHold" }

        // state object which tells us if we should load more
        val shouldLoadMore = remember {
            derivedStateOf {

                // get last visible item
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                    ?:
                    // list is empty
                    // return false here if loadMore should not be invoked if the list is empty
                    return@derivedStateOf true

                // Check if last visible item is the last item in the list
                lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - threadHold
            }
        }
        // Convert the state into a cold flow and collect
        LaunchedEffect(shouldLoadMore) {
            snapshotFlow { shouldLoadMore.value }
                .collect {
                    // if should load more, then invoke loadMore
                    if (it) loadMore()
                }
        }
    }
}

@Composable
fun LazyGridState.OnBottomReached(
    // tells how many items before we reach the bottom of the list
    // to call onLoadMore function
    threadHold: Int = 0,
    loadMore: () -> Unit
) {
    //Detect use scroll to handle event
    if (isScrollInProgress) {
        // ThreadHold must be positive.
        // Or our list will never reach the bottom.
        require(threadHold >= 0) { "threadHold cannot be negative, but was $threadHold" }

        // state object which tells us if we should load more
        val shouldLoadMore = remember {
            derivedStateOf {

                // get last visible item
                val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                    ?:
                    // list is empty
                    // return false here if loadMore should not be invoked if the list is empty
                    return@derivedStateOf true

                // Check if last visible item is the last item in the list
                lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - threadHold
            }
        }
        // Convert the state into a cold flow and collect
        LaunchedEffect(shouldLoadMore) {
            snapshotFlow { shouldLoadMore.value }
                .collect {
                    // if should load more, then invoke loadMore
                    if (it) loadMore()
                }
        }
    }
}
