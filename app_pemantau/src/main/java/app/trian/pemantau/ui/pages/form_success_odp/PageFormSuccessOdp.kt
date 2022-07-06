package app.trian.pemantau.ui.pages.form_success_odp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.pemantauan.PreviewScreenFormOdp

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeFormSuccessOdp(
    router: NavHostController
) {
    composable(Routes.SuccessFormOdp) {
        PreviewScreenFormOdp()
    }
}
