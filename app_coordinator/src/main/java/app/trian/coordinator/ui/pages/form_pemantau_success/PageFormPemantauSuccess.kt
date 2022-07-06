package app.trian.coordinator.ui.pages.form_pemantau_success

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.user.ScreenSuccessUser

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeFormPemantauSuccess(
    router: NavHostController
) {
    composable(Routes.SuccessFormPemantau) {
        ScreenSuccessUser()
    }
}
