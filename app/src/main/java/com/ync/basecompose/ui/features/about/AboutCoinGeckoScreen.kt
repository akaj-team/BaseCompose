package com.ync.basecompose.ui.features.about

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import com.ync.basecompose.R
import com.ync.basecompose.data.model.HorizontalPagerContent
import com.ync.basecompose.ui.components.BaseScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by mvn-cuongle-dn
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun AboutCoinGeckoScreen(navController: NavController) {
    BaseScreen {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.more_about_coin_gecko_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp
            )
        }) {
            Column(modifier = Modifier.fillMaxSize()) {
                val items = createItems()
                val pagerState = rememberPagerState()
                val coroutineScope = rememberCoroutineScope()
                HorizontalTabs(createItems(), pagerState = pagerState, scope = coroutineScope)
                HorizontalPager(
                    count = items.size, state = pagerState, modifier = Modifier.weight(1f)
                ) { currentPage ->
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = items[currentPage].title, style = MaterialTheme.typography.h2
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = items[currentPage].subTitle, style = MaterialTheme.typography.h4
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = items[currentPage].description,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalTabs(
    items: List<HorizontalPagerContent>, pagerState: PagerState, scope: CoroutineScope
) {
    TabRow(
        selectedTabIndex = pagerState.currentPage, indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }, modifier = Modifier.height(56.dp)
    ) {
        items.forEachIndexed { index, item ->
            Tab(selected = pagerState.currentPage == index, onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(page = index)
                }
            }) {
                Text(text = item.title)
            }
        }
    }
}


private fun createItems() = listOf(
    HorizontalPagerContent(title = "NFTs", subTitle = "Subtitle1", description = "Description1"),
    HorizontalPagerContent(
        title = "Exchange Rate", subTitle = "Subtitle2", description = "Description2"
    ),
    HorizontalPagerContent(
        title = "Server Status", subTitle = "Subtitle3", description = "Description3"
    )
)
