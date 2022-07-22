package app.trian.coordinator.ui.pages.main.profile

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import app.trian.coordinator.ui.pages.main.home.menu
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.main.ProfileUIState
import com.trian.component.screen.main.ScreenProfile

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeProfile(
    router: NavHostController,
    restartActivity:()->Unit
) {
    composable(Routes.Main.Profile) {
        val viewModel = hiltViewModel<ProfileViewModel>()
        val profileState by viewModel.profileState.observeAsState(initial = ProfileUIState(
            loading = true,
            error = false
        ))
        ScreenProfile(
            router = router,
            menus = menu,
            profile = profileState,
            onSetting = {
                        router.navigate(Routes.Settings){
                            launchSingleTop=true
                        }
            },
            onFabClick = {
                router.navigate(Routes.FormUser){
                    launchSingleTop=true
                }
            },
            onRestartActivity = {
                viewModel.signOut()
                restartActivity()
            }
        )
    }
}
