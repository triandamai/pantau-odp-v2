package com.trian.component.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.AppbarBasic
import com.trian.component.R
import com.trian.component.theme.DisableColor
import com.trian.component.theme.DisableContentColor
import com.trian.component.theme.PantauWargaTheme
import com.trian.component.utils.coloredShadow
import com.trian.component.utils.from
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.Person16
import compose.icons.octicons.Person24

/**
 * author Trian Damai
 * created_at 22/07/22 - 09.57
 * site https://trian.app
 */
@Composable
fun ScreenSettings(
    modifier: Modifier = Modifier,
    menus: List<SettingModel> = listOf(),
    onClick:()->Unit,
    onNavigate:(String)->Unit,
    onBackPressed:()->Unit
) {

    val ctx = LocalContext.current

    Scaffold(
        topBar = {
            AppbarBasic(
                title = stringResource(R.string.txt_title_screen_setting),
                navigationIcon = {
                    IconToggleButton(checked = false, onCheckedChange = {
                        onBackPressed()
                    }) {
                        Icon(
                            imageVector = Octicons.ArrowLeft24,
                            contentDescription = stringResource(R.string.content_description_icon_back),
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(content = {
            item {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 30.dp
                        )
                ) {

                    Column(
                        modifier = modifier.padding(top = 20.dp)
                    ) {
                        Row(modifier = modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.large)
                            .background(MaterialTheme.colors.primary)
                            .coloredShadow(
                                MaterialTheme.colors.primary
                            )
                            .padding(all = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Do more with premium",
                                    style = MaterialTheme.typography.caption.copy(
                                        color = MaterialTheme.colors.onPrimary
                                    )
                                )
                                Spacer(modifier = modifier.height(6.dp))
                                Text(
                                    text = "Become premium",
                                    style = MaterialTheme.typography.body1.copy(
                                        color = MaterialTheme.colors.onPrimary
                                    )
                                )
                            }
                        }
                    }
                    Image(
                        painter = painterResource(id =R.drawable.ic_premium),
                        contentDescription = "",
                        modifier = modifier
                            .align(Alignment.TopEnd)
                            .size(50.dp)
                    )
                }
            }
            item {
                Spacer(modifier = modifier.height(30.dp))
            }
            items(menus){
                ItemSetting(
                    name = it.name,
                    description = it.description,
                    icon = it.icon,
                    actions = {},
                    onClick = {}
                )
            }

        })

    }

}

@Composable
fun ItemSetting(
    modifier: Modifier=Modifier,
    name:String="",
    description: String="",
    icon: ImageVector=Octicons.Person16,
    actions:@Composable ()->Unit={},
    onClick: () -> Unit={},
) {
    val ctx = LocalContext.current

    Row(
        modifier = modifier
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 30.dp,
                vertical = 6.dp
            )
            .fillMaxWidth()
            .height(59.dp.from(ctx)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = modifier
                .size(48.dp.from(ctx))
                .clip(CircleShape)
                .background(DisableColor)
                .padding(
                    all = 10.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription ="",
                modifier = modifier
                    .size(46.dp.from(ctx)),
                tint = DisableContentColor
            )
        }
        Spacer(modifier = modifier.width(10.dp))
        Column(
            modifier= modifier
                .height(50.dp.from(ctx)),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.body2.copy(

                        )
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = description,
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                }
                actions.invoke()
            }
            Spacer(modifier = modifier.height(6.dp.from(ctx)))
            Divider()
        }

    }
}

data class SettingModel(
    var name:String,
    var description:String,
    var icon:ImageVector,
    var action:String,
    var type:SettingType,
    var route:String
)

enum class SettingType{
    navigation,
    button,
}

@Preview
@Composable
fun PreviewScreenSetting() {
    PantauWargaTheme {
        ScreenSettings(
            menus = listOf(
                SettingModel(
                    name = "Item pertama",
                    description = "deskripsi",
                    icon = Octicons.Person24,
                    action = "",
                    type = SettingType.button,
                    route = ""
                )
            ),
            onClick = {},
            onBackPressed = {},
            onNavigate = {}
        )
    }
}