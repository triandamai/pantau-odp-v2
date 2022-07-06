package app.trian.pemantau.ui.pages.main.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.ItemMenuDrawer
import com.trian.component.Routes
import com.trian.component.screen.main.ScreenHome

val menu = listOf(
    ItemMenuDrawer(
        name = "Feedback",
        route = "categories",
        type = "button"
    ),
    ItemMenuDrawer(
        name = "Rating",
        route = "categories",
        type = "button"
    ),
    ItemMenuDrawer(
        name = "Privacy policy",
        route = "categories",
        type = "button"
    )
)

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeHome(
    router: NavHostController
) {
    composable(Routes.Main.Home) {
       ScreenHome(
           router = router,
           menus = menu,
           onFabClicked = {
               router.navigate(Routes.FormOdp){
                   launchSingleTop = true
               }
           },
           onDetailMonitoring = {
               router.navigate(Routes.ListOdp){
                   launchSingleTop = true
               }
           }
       )
    }
}

