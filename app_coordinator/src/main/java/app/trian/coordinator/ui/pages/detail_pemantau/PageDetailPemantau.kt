package app.trian.coordinator.ui.pages.detail_pemantau

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.user.DetailOfficerUIState
import com.trian.component.screen.user.ScreenDetailOfficer


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeDetailPemantau(
    router: NavHostController
) {
    composable(
        Routes.DetailUser.route,
        arguments = Routes.DetailUser.navArg()
    ) {
        val viewModel = hiltViewModel<DetailPemantauViewModel>()
        val detailPemantau by viewModel.detailOfficerState.observeAsState(
            initial = DetailOfficerUIState(
                loading = true,
                error = false
            )
        )
       ScreenDetailOfficer(
           state = detailPemantau,
           onBackPressed = {
               router.popBackStack()
           }
       )
    }
}

