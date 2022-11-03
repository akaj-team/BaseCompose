package com.ync.basecompose.ui.features.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ync.basecompose.R
import com.ync.basecompose.arch.base.BaseViewModel
import com.ync.basecompose.data.model.Platform
import com.ync.basecompose.ui.components.BaseScreen

/**
 * Created by mvn-cuongle-dn
 */

@Composable
fun DetailCoinScreen(id: String) {

    val viewModel: DetailCoinViewModel = hiltViewModel()
    val viewState by viewModel.detailCoinUiViewState.collectAsState()

    BaseScreen(viewModel = BaseViewModel(), onCreate = {
        viewModel.getDetailCoin(id)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Title")
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Text(
                text = "Available Platform",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            AvailablePlatformList(
                mutableListOf(
                    Platform("acacac", "123123"),
                    Platform("acacac", "123123"),
                    Platform("acacac", "123123"),
                    Platform("acacac", "123123")
                )
            )
        }
    }
}

@Composable
fun AvailablePlatformList(list: MutableList<Platform>) {
    LazyColumn {
        items(list) { item ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = item.decimalPlace, modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = item.contractAddress,
                )
            }
        }
    }
}
