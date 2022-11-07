package com.ync.basecompose.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ync.basecompose.R
import com.ync.basecompose.ui.navigation.AppScreens

/**
 * Created by mvn-cuongle-dn
 */

@SuppressLint("UnrememberedMutableState")
@Composable
fun TopAppBarNav(navController: NavController) {
    val showMenu = remember {
        mutableStateOf(false)
    }
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_tool_bar_title))
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 10.dp,
        actions = {
            Row(horizontalArrangement = Arrangement.End) {
                IconButton(onClick = {
                    //DropdownMenu(showMenu = showMenu)
                    showMenu.value = !showMenu.value
                }) {
                    Icon(Icons.Filled.Menu, "menuIcon")
                }
            }
        }
    )
    if (showMenu.value) {
        ShowSettingDropDownMenu(navController) {
            showMenu.value = false
        }
    }
}

@Composable
fun ShowSettingDropDownMenu(navController: NavController, onDismissRequest: () -> Unit) {
    var expanded by remember { mutableStateOf(true) }
    val items = listOf(
        stringResource(R.string.drop_menu_item_title_more),
        stringResource(R.string.drop_menu_item_title_help)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                onDismissRequest.invoke()
            },
            modifier = Modifier.background(Color.White)
        ) {
            items.forEachIndexed { index, title ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onDismissRequest.invoke()
                    when (index) {
                        0 -> {
                            navController.navigate(AppScreens.About.route)
                        }
                    }
                }) {
                    Text(text = title, maxLines = 1)
                    if (index < 3) {
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}
