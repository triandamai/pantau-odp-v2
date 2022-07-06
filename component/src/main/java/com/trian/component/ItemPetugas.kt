package com.trian.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.theme.DisableColor
import com.trian.component.theme.PantauWargaTheme
import compose.icons.Octicons
import compose.icons.octicons.Person24

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 20.03
 * site https://trian.app
 */

@Composable
fun ItemPetugas(
    modifier: Modifier=Modifier,
    onClick:()->Unit={}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardHeight = currentWidth / 5 - 10.dp
    Row(
        modifier = modifier
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 30.dp,
                vertical = 10.dp
            )
            .fillMaxWidth()
            .height(cardHeight),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = modifier
                .size(currentWidth / 6)
                .clip(CircleShape)
                .background(DisableColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Octicons.Person24,
                contentDescription ="",
                modifier = modifier
                    .size(cardHeight - cardHeight / 3)
            )
        }
        Spacer(modifier = modifier.width(10.dp))
        Column(
            modifier= modifier
                .height(cardHeight)
                .width(currentWidth - cardHeight),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "Trian Damai",
                        style = MaterialTheme.typography.body2.copy(

                        )
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = "Desa",
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                }
                Text(
                    text = "01xxxxxx",
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.secondary
                    )
                )
            }
            Divider()
        }

    }
}


@Preview
@Composable
fun PreviewItemPetugas() {
    PantauWargaTheme {
        Column {
            ItemPetugas()

        }

    }
}