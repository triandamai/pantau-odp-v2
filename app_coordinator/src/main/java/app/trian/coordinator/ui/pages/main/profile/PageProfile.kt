package app.trian.coordinator.ui.pages.main.profile

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.main.ScreenProfile

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeProfile(
    router: NavHostController
) {
    composable(Routes.Main.Profile) {
        ScreenProfile(
            router = router
        )
    }
}
