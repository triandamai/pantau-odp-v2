package app.trian.pemantau.ui.pages.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.ScreenLogin
import com.trian.component.theme.PantauWargaTheme

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeLogin(
    router: NavHostController
) {
    composable(Routes.Login) {
        ScreenLogin(
            onSignIn = {
                _,_->
                router.navigate(Routes.Main.MAIN){
                    launchSingleTop = true
                }
            },
            onResetPassword = {
                router.navigate(Routes.ResetPassword){
                    launchSingleTop=true
                }
            }
        )
    }
}
