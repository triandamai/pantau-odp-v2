package com.trian.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.trian.component.theme.DisableContentColor
import com.trian.component.utils.from
import compose.icons.Octicons
import compose.icons.octicons.*

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 19.01
 * site https://trian.app
 */

@Composable
fun BottomNav(
    router: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by router.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    val ctx = LocalContext.current

    val items = listOf(
        BottomNavigationModel.Home,
        BottomNavigationModel.Center,
        BottomNavigationModel.Profile
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(75.dp.from(ctx))
            .background(MaterialTheme.colors.surface)
            .padding(
                horizontal = 16.dp.from(ctx)
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach {
                 menu ->

                Column(
                    modifier = modifier
                        .width(41.dp.from(ctx))
                        .height(37.dp.from(ctx))
                        .padding(
                            vertical = 4.dp
                        )
                        .clickable {
                            if (menu.route.isNotBlank()) {
                                router.navigate(menu.route) {
                                    launchSingleTop = true
                                }
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = menu.icon,
                        contentDescription = "",
                        tint = if(currentRoute?.route == menu.route) MaterialTheme.colors.primary else DisableContentColor
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Text(
                        text = menu.title,
                        style = MaterialTheme.typography.overline.copy(
                            color = if(currentRoute?.route == menu.route) MaterialTheme.colors.primary else DisableContentColor
                        )
                    )
                    Spacer(modifier = modifier.height(6.dp))
                }
            }

    }


}

sealed class BottomNavigationModel(
    var icon: ImageVector,
    var title:String,
    var route:String
){
    object Home:BottomNavigationModel(
        icon = Octicons.Project16,
        title = "Home",
        route = Routes.Main.Home
    )
    object Center:BottomNavigationModel(
        icon = Octicons.Plus16,
        title = "",
        route = ""
    )
    object Profile:BottomNavigationModel(
        icon = Octicons.Person16,
        title = "Profile",
        route = Routes.Main.Profile
    )
}