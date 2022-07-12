package app.trian.pemantau.ui.pages.form_success_odp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.pemantauan.ScreenSuccessOdp
import com.trian.component.screen.pemantauan.SuccessOdpUIState

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeFormSuccessOdp(
    router: NavHostController
) {
    composable(
        Routes.SuccessFormOdp.route,
        arguments = Routes.SuccessFormOdp.navArg()
    ) {
        val viewModel = hiltViewModel<FormSuccessOdpViewModel>()

        val state by viewModel.odpState.observeAsState(initial = SuccessOdpUIState(
            loading = true,
            error = false
        ))
        ScreenSuccessOdp(
            state = state,
            onDismiss = {
                router.popBackStack()
            }
        )
    }
}
