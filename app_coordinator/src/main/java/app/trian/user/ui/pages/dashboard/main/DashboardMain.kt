package app.trian.user.ui.pages.dashboard.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import app.trian.user.Routes
import app.trian.user.ui.theme.BussTrackerTheme
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeDashboardMain(
    router: NavHostController
) {
    composable(Routes.Dashboard.MAIN) {
        ScreenDashboardMain()
    }
}

@Composable
fun ScreenDashboardMain() {

}

@Preview
@Composable
fun PreviewDashboardMain() {
     BussTrackerTheme(){
        ScreenDashboardMain()
    }
}