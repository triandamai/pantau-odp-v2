package app.trian.pemantau.ui.pages.main.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.main.ScreenHome

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeHome(
    router: NavHostController
) {
    composable(Routes.Main.Home) {
       ScreenHome(router = router)
    }
}

