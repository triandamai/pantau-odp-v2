package app.trian.coordinator.ui.pages.form_pemantau_success

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.user.ScreenSuccessUser
import com.trian.component.screen.user.SuccessOfficerUIState

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeFormPemantauSuccess(
    router: NavHostController
) {
    composable(
        Routes.SuccessFormPemantau.route,
        arguments=Routes.SuccessFormPemantau.navArg()
    ) {
        val viewModel = hiltViewModel<FormPemantauSuccessViewModel>()

        val officerState by viewModel.officerState.observeAsState(initial = SuccessOfficerUIState(
            loading = true,
            error=false,
        ))
        ScreenSuccessUser(
            state = officerState,
            onDismiss = {
                router.navigate(Routes.Main.MAIN){
                    popUpTo(Routes.Main.Home)
                    launchSingleTop=true
                }
            }
        )
    }
}
