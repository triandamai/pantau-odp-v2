package app.trian.pemantau.ui.pages.detail_odp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.pemantauan.ScreenDetailOdp


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeDetailOdp(
    router: NavHostController
) {
    composable(
        Routes.DetailOdp.route,
        arguments = Routes.DetailOdp.navArg()
    ) {
       ScreenDetailOdp()
    }
}

