package com.trian.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.theme.DisableContentColor
import com.trian.component.theme.PantauWargaTheme
import com.trian.component.utils.coloredShadow
import compose.icons.Octicons
import compose.icons.octicons.Gear24
import compose.icons.octicons.Quote24

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 13.55
 * site https://trian.app
 */

@Composable
fun AppbarAuth(
    modifier: Modifier =Modifier,
    navigationText:String="",
    onNavigate:()->Unit={}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                )
            )
            .coloredShadow(
                color = DisableContentColor
            )
            .height(70.dp)
            .background(
                MaterialTheme.colors.surface
            )
            .padding(
                horizontal = 30.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h6.copy(
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Light
            )
        )
        Text(
            text = navigationText,
            style = MaterialTheme.typography.overline.copy(
                color = MaterialTheme.colors.primary
            ),
            modifier= modifier
                .padding(all = 4.dp)
                .clickable {
                    onNavigate()
                }
        )
    }
}

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 20.23
 * site https://trian.app
 */

@Composable
fun AppbarHome(
    modifier: Modifier = Modifier,
    title:String="",
    navigationIcon:@Composable ()->Unit={},
    actions:@Composable RowScope.()->Unit={},
    content:@Composable ()->Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
                )
            )
            .background(MaterialTheme.colors.surface)
    ) {
        TopAppBar(
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.surface,
            navigationIcon = {
                navigationIcon.invoke()
            },
            actions = actions,
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h4.copy(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        )
        Box(modifier = modifier.padding(horizontal = 16.dp)){
            content.invoke()
        }
        Spacer(modifier = modifier.height(16.dp))
    }
}

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 22.35
 * site https://trian.app
 */

@Composable
fun AppbarProfile(
    onNavigation:()->Unit={},
    onAction:()->Unit={}
) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface,
        navigationIcon = {
            IconToggleButton(
                checked = false,
                onCheckedChange = {
                    onNavigation()
                }
            ) {
                Icon(
                    imageVector = Octicons.Quote24,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onBackground
                )
            }

        },
        title = {
            Row {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.h4.copy(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        },
        actions = {
            IconToggleButton(
                checked = false,
                onCheckedChange = {
                    onAction()
                }
            ) {
                Icon(
                    imageVector = Octicons.Gear24,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }

    )
}

@Preview
@Composable
fun PreviewAppBarProfile() {
    PantauWargaTheme {
        AppbarProfile()
    }
}
@Preview
@Composable
fun PreviewAppbarDashboard() {
    PantauWargaTheme {
        AppbarHome(
            title = "Stat"
        )
    }
}
@Preview
@Composable
fun PreviewAppbarAuth() {
    PantauWargaTheme {
        AppbarAuth()
    }
}