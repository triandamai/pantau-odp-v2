package app.trian.pemantau.ui.pages.form_warga

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.pemantauan.ScreenFormWarga
import com.trian.component.screen.user.ScreenFormPemantau

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeFormWarga(
    router: NavHostController
) {
    composable(
        Routes.FormWarga.route,
        arguments = Routes.FormWarga.navArg()
    ) {
        ScreenFormWarga(
            onBackPressed = {
                router.popBackStack()
            },
            onSubmit = {
                router.navigate(Routes.SuccessFormWarga){
                    launchSingleTop=true
                }
            }
        )
    }
}
