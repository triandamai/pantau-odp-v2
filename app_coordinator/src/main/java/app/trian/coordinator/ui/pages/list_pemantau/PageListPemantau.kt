package app.trian.coordinator.ui.pages.list_pemantau

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
import com.trian.component.screen.ListPemantauUIState
import com.trian.component.screen.ScreenListPetugas

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeListPemantau(
    router: NavHostController
) {
    composable(Routes.ListPemantau) {
        val viewModel = hiltViewModel<ListPemantauViewModel>()
        val listPemantau by viewModel.listPemantau.observeAsState(
            initial = ListPemantauUIState(
                loading = true
            )
        )
        ScreenListPetugas(
            state = listPemantau,
            onBackPressed = {
                router.popBackStack()
            },
            onDetailOfficer = {
                router.navigate(Routes.DetailUser.navigate(it)){
                    launchSingleTop= true
                }
            }
        )
    }
}

