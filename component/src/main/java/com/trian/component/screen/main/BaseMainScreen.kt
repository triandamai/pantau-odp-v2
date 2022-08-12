package com.trian.component.screen.main

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.trian.component.BottomNav
import com.trian.component.ItemMenuDrawer
import com.trian.component.NavDrawer
import com.trian.component.dialog.DialogLogout
import com.trian.component.theme.BackgroundDashboard
import com.trian.component.utils.emailTo
import com.trian.component.utils.gotoApp
import com.trian.component.utils.intentTo
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
    menus:List<ItemMenuDrawer> = listOf(),
    userName:String="",
    topAppbar: @Composable ()->Unit = {},
    onRestartActivity:()->Unit={},
    content:@Composable ()->Unit={},
    onFabClicked:()->Unit={}
) {

    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()
    var dialogLogout by remember {
        mutableStateOf(false)
    }
    DialogLogout(
        show = dialogLogout,
        onCancel = {
                  dialogLogout = false
        },
        onConfirm = {
                    onRestartActivity()
        },
        onDismiss = {
            dialogLogout = false
        }
    )
    ModalDrawer(
        drawerState = drawerState ,
        drawerContent = {
            NavDrawer(
                menus = menus,
                userName = userName,
                onClick = {

                    scope.launch {
                        drawerState.close()
                        delay(400)
                        when(it.route){
                            "logout"->{
                                //sign out
                               dialogLogout = true
                            }
                            "feedback"->{
                                ctx.emailTo(to = "triandamai@gmail.com", subject = "Feedback ODP")
                            }
                            "rating"->{
                                ctx.gotoApp()
                            }
                            "privacy_policy"->{
                                ctx.intentTo()
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