package com.ync.basecompose.ui.features.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ync.basecompose.arch.extentions.onBottomReached
import com.ync.basecompose.data.model.Exchanges
import com.ync.basecompose.ui.components.BaseScreen

/**
 * Created by mvn-cuongle-dn
 */

@Composable
fun FavoriteScreen() {
    val viewModel: FavoriteViewModel = hiltViewModel()
    val viewState by viewModel.favoriteUiViewState.collectAsState()

    BaseScreen(
        viewModel = viewModel,
        background = Color.White,
        onCreate = { viewModel.getExchanges() }) {
        ItemListView(items = viewState.item, viewModel)
    }
}

@Composable
fun ItemListView(items: List<Exchanges>, viewModel: FavoriteViewModel) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = item.imageUrl, contentDescription = "", modifier = Modifier.clip(
                        CircleShape
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))

                Text(text = item.name ?: "")
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
    listState.onBottomReached {
        if (viewModel.isFirstLoad)
            viewModel.getExchanges(true)
    }
}
