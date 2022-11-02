package com.ync.basecompose.ui.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.ync.basecompose.data.model.Item
import com.ync.basecompose.ui.components.BaseScreen

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val viewState by viewModel.homeUiViewState.collectAsState()
    BaseScreen(viewModel = viewModel, background = Color.White) {
        ItemListView(items = viewState.item)
    }
}

@Composable
fun ItemListView(items: List<Item>) {
    LazyColumn {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = item.large, contentDescription = "", modifier = Modifier.clip(
                        CircleShape
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))

                Text(text = item.name)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
