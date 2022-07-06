package app.trian.pemantau.ui.pages.form_odp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.pemantauan.ScreenFormOdp

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeFormOdp(
    router: NavHostController
) {
    composable(Routes.FormOdp) {
        ScreenFormOdp()
    }
}

