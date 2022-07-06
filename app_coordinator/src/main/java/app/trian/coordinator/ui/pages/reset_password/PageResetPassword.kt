package app.trian.coordinator.ui.pages.reset_password

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.ScreenResetPassword

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeResetPassword(
    router: NavHostController
) {
    composable(Routes.ResetPassword) {
       ScreenResetPassword()
    }
}
