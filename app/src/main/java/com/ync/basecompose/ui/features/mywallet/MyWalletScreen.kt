package com.ync.basecompose.ui.features.mywallet

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ync.basecompose.ui.components.BaseScreen
import com.ync.basecompose.ui.components.TopAppBarMyWalletScreen

/**
 * Created by mvn-cuongle-dn
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyWalletScreen() {
    BaseScreen {
        Scaffold(topBar = {
            TopAppBarMyWalletScreen {
                //TODO handle open camera
            }
        }) {
            ConstraintLayout {
                Card(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .padding(15.dp),
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = Color.DarkGray
                ) {
                    ConstraintLayout {
                        val (tvCurrentBalanceTitle, tvTitleUSD, tvCurrentAmount,
                            tvRateAmount, tvBTCAvailable, imgAdd, cardAddress) = createRefs()
                        Text(
                            text = "Current Balance",
                            color = Color.White,
                            modifier = Modifier
                                .padding(20.dp)
                                .constrainAs(tvCurrentBalanceTitle) {}
                        )
                        Text(
                            text = "USD",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            modifier = Modifier
                                .padding(10.dp)
                                .constrainAs(tvTitleUSD) {
                                    end.linkTo(parent.end)
                                    top.linkTo(tvCurrentBalanceTitle.top)
                                    bottom.linkTo(tvCurrentBalanceTitle.bottom)
                                }
                        )
                        Text(
                            text = "$32.026",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .constrainAs(tvCurrentAmount) {
                                    top.linkTo(tvCurrentBalanceTitle.bottom)
                                    start.linkTo(tvCurrentBalanceTitle.start)
                                }
                        )
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Green
                            ),
                            onClick = {},
                            modifier = Modifier
                                .constrainAs(tvRateAmount) {
                                    end.linkTo(parent.end, margin = 10.dp)
                                    top.linkTo(tvCurrentAmount.top)
                                    bottom.linkTo(tvCurrentAmount.bottom)
                                }
                        ) {
                            Text(text = "+3,5%")
                        }

                        Text(
                            text = "4,23112456 BTC",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.constrainAs(tvBTCAvailable) {
                                start.linkTo(tvCurrentBalanceTitle.start, margin = 20.dp)
                                bottom.linkTo(parent.bottom, margin = 20.dp)
                            }
                        )
                        Image(
                            Icons.Default.AddCircle,
                            colorFilter = ColorFilter.tint(Color.Red),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .constrainAs(imgAdd) {
                                    end.linkTo(parent.end, margin = 20.dp)
                                    top.linkTo(tvBTCAvailable.top)
                                }
                        )
                        Card(
                            modifier = Modifier
                                .height(75.dp)
                                .wrapContentWidth()
                                .padding(15.dp)
                                .constrainAs(cardAddress) {
                                    top.linkTo(tvCurrentAmount.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            shape = RoundedCornerShape(16.dp),
                            backgroundColor = Color.Unspecified
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.padding(horizontal = 20.dp)
                            ) {
                                Text(
                                    text = "0x123192.....231n123asdbsd1",
                                    color = Color.White,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
