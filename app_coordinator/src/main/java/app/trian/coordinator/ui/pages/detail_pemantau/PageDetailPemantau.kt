package app.trian.coordinator.ui.pages.detail_pemantau

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.user.ScreenDetailPetugas


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeDetailPemantau(
    router: NavHostController
) {
    composable(
        Routes.DetailUser.route,
        arguments = Routes.DetailUser.navArg()
    ) {
       ScreenDetailPetugas()
    }
}

