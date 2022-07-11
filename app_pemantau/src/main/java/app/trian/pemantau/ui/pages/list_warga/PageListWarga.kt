package app.trian.pemantau.ui.pages.list_warga

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.ListOdpUIState
import com.trian.component.screen.ScreenListWarga

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeListWarga(
    router: NavHostController
) {
    composable(Routes.ListOdp) {
        val viewModel = hiltViewModel<ListWargaViewModel>()
        val odpState by viewModel.listOdp.observeAsState(initial = ListOdpUIState(
            loading = true,
            error = false
        ))
        ScreenListWarga(
            state=odpState,
            onBackPressed = {
                router.popBackStack()
            },
            onDetailOdp = {
                router.navigate(Routes.DetailOdp.navigate(it)){
                    launchSingleTop = true
                }
            }
        )
    }
}
