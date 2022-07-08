package app.trian.coordinator.ui.pages.onboard

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.ScreenOnboard

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeOnboard(
    router: NavHostController
) {
    composable(Routes.Onboard) {
        ScreenOnboard(
            onGetStarted = {
                router.navigate(Routes.Login){
                    popUpTo(Routes.Onboard){
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
    }
}
