package com.trian.component.screen.main

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.trian.component.BottomNav
import com.trian.component.NavDrawer
import com.trian.component.theme.BackgroundDashboard
import compose.icons.Octicons
import compose.icons.octicons.Plus24
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 17.07
 * site https://trian.app
 */
@Composable
fun BaseMainScreen(
    modifier: Modifier = Modifier,
    router: NavHostController,
    drawerState: DrawerState,
    topAppbar: @Composable ()->Unit = {},
    onRestartActivity:()->Unit={},
    content:@Composable ()->Unit={},
    onFabClicked:()->Unit={}
) {

    val scope = rememberCoroutineScope()
    ModalDrawer(
        drawerState = drawerState ,
        drawerContent = {
            NavDrawer(
                onClick = {

                    scope.launch {
                        drawerState.close()
                        delay(400)
                        when(it.route){
                            "logout"->{
                                //sign out
                                onRestartActivity()
                            }
                        }

                    }
                },
                onNavigate = {
                    scope.launch {
                        drawerState.close()
                        delay(400)
                        router.navigate(it)
                    }
                }
            )
        },
        drawerShape = RectangleShape,
        drawerElevation = 0.dp,
        scrimColor = MaterialTheme.colors.primary.copy(
            alpha = 0.3f
        )
    ) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    cutoutShape = CircleShape,
                    elevation=1.dp,
                    backgroundColor = BackgroundDashboard,
                ) {
                    BottomNav(router = router)
                }
            },
            topBar = {
                topAppbar.invoke()
            },
            isFloatingActionButtonDocked = true,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = onFabClicked,
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        imageVector = Octicons.Plus24,
                        contentDescription = "",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            },
            backgroundColor= BackgroundDashboard,
            floatingActionButtonPosition = FabPosition.Center
        ) {

            content.invoke()

        }
    }
}