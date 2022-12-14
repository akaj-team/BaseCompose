package com.ync.basecompose.ui.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ync.basecompose.R
import com.ync.basecompose.data.model.Item
import com.ync.basecompose.ui.components.BaseScreen
import com.ync.basecompose.ui.navigation.AppScreens

/**
 * Copyright © Monstarlab Vietnam Co., Ltd.
 * Created by mvn-ynguyen-dn on 11/1/22.
 */
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val viewState by viewModel.homeUiViewState.collectAsStateWithLifecycle()
    BaseScreen(viewModel = viewModel, background = Color.White, onCreate = {
        viewModel.getTrending()
    }) {
        if (viewState.item.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(R.string.trending_coin_empty_title), fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            ItemListView(items = viewState.item) {
                navController.navigate(
                    AppScreens.DetailCoin.routeWithArgsValue(
                        AppScreens.ARGUMENT.COIN_ID.key to it.id,
                        AppScreens.ARGUMENT.COIN_IMAGE.key to it.large
                    )
                )
            }
        }
    }
}

@Composable
fun ItemListView(items: List<Item>, onItemClicked: (Item) -> Unit) {
    LazyColumn {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        onItemClicked.invoke(item)
                    }, verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = item.large,
                    contentDescription = "",
                    modifier = Modifier.clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = item.name)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
