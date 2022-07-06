package app.trian.pemantau.ui.pages.form_success_assessment


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.pemantauan.ScreenFormAssesment
import com.trian.component.screen.pemantauan.ScreenSuccessOdp

/**
 * author Trian Damai
 * created_at 06/07/22 - 23.56
 * site https://trian.app
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeFormSuccessAssessment(
    router: NavHostController
) {
    composable(
        Routes.FormAssesment.route,
        arguments = Routes.FormAssesment.navArg()
    ) {
        ScreenFormAssesment(
            onBackPressed = {
                router.popBackStack()
            },
            onSubmit = {
                router.navigate(Routes.SuccessFormAssessment){
                    launchSingleTop = true
                }
            }
        )
    }
}

