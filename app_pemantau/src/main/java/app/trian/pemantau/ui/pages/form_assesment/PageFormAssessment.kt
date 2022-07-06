package app.trian.pemantau.ui.pages.form_assesment

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.pemantauan.ScreenFormAssesment

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeFormAssessment(
    router: NavHostController
) {
    composable(
        Routes.FormAssesment.route,
        arguments = Routes.FormAssesment.navArg()
    ) {
        ScreenFormAssesment()
    }
}

