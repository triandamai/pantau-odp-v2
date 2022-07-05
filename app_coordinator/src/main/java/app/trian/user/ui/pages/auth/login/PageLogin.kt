package app.trian.user.ui.pages.auth.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import app.trian.user.Routes
import app.trian.user.ui.theme.BussTrackerTheme
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeLogin(
    router: NavHostController
) {
    composable(Routes.LOGIN) {
        ScreenLogin()
    }
}

@Composable
fun ScreenLogin() {

}

@Preview
@Composable
fun PreviewLogin() {
     BussTrackerTheme{
        ScreenLogin()
    }
}