package app.trian.pemantau.ui.pages.main.profile

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import app.trian.pemantau.ui.pages.main.home.menu
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.main.ProfileUIState
import com.trian.component.screen.main.ScreenProfile

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeProfile(
    router: NavHostController
) {
    composable(Routes.Main.Profile) {
        val viewModel = hiltViewModel<ProfileViewModel>()
        val profileState by viewModel.profileState.observeAsState(initial = ProfileUIState(
            loading = true,
            error = false
        )
        )

        ScreenProfile(
            router = router,
            menus = menu,
            profile = profileState,
            onRestartActivity = {}
        )
    }
}
