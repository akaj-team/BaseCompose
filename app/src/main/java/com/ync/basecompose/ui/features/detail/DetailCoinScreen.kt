package com.ync.basecompose.ui.features.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.ync.basecompose.data.model.Platform
import com.ync.basecompose.ui.components.BaseScreen

/**
 * Created by mvn-cuongle-dn
 */

@OptIn(ExperimentalLifecycleComposeApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailCoinScreen(navController: NavController, id: String, imageCoin: String) {
    val viewModel: DetailCoinViewModel = hiltViewModel()
    val viewState by viewModel.detailCoinUiViewState.collectAsStateWithLifecycle()

    BaseScreen(
        viewModel = viewModel,
        onCreate = {
            viewModel.getDetailCoin(id)
        }, onErrorClicked = {
            navController.navigateUp()
        }
    ) {
        Scaffold(topBar = { DetailScreenAppBar(navController = navController) }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Row(modifier = Modifier.padding(top = 20.dp)) {
                    AsyncImage(
                        model = imageCoin, contentDescription = "", modifier = Modifier.clip(
                            CircleShape
                        )
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = viewState.detailCoin.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = stringResource(id = R.string.available_platform_title),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                viewState.detailCoin.detailListPlatform?.let {
                    AvailablePlatformList(it)
                }
            }
        }
    }
}

@Composable
fun AvailablePlatformList(list: Map<String, Platform>) {
    LazyColumn {
        this.items(list.values.toMutableList()) { item ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = stringResource(
                        id = R.string.platform,
                        list.keys.toMutableList()[list.values.indexOf(item)]
                    ), fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = stringResource(
                        id = R.string.decimal_place, item.decimalPlace
                    ), modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = stringResource(
                        id = R.string.contract_address, item.contractAddress
                    ), fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun DetailScreenAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.detail_screen_title)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 10.dp
    )
}
