package com.ync.basecompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.ync.basecompose.R

/**
 * Created by mvn-cuongle-dn
 * This component to test ConstraintLayout Lib
 */

@Composable
fun TopAppBarMyWalletScreen(onImageAvatarClicked: () -> Unit) {
    TopAppBar(elevation = 10.dp, backgroundColor = Color.White) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (imgAvatar, tvTitleWallet, imgNotification) = createRefs()
            IconButton(modifier = Modifier
                .constrainAs(imgAvatar) {
                    top.linkTo(parent.top, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
                .layoutId(imgAvatar), onClick = { onImageAvatarClicked.invoke() }) {
                AsyncImage(
                    model = "https://avatars.githubusercontent.com/u/59045707?v=4",
                    contentDescription = "",
                    modifier = Modifier
                        .clip(CircleShape)
                        .height(50.dp)
                        .width(50.dp)
                )
            }
            Text(text = "My Wallet", modifier = Modifier.constrainAs(tvTitleWallet) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 22.sp)
            Image(
                Icons.Filled.Notifications,
                contentDescription = "",
                modifier = Modifier.constrainAs(imgNotification) {
                    top.linkTo(tvTitleWallet.top)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}
