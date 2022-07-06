package app.trian.coordinator.ui.pages.list_odp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.ScreenListWarga

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeListWarga(
    router: NavHostController
) {
    composable(Routes.ListOdp) {
        ScreenListWarga(
            onBackPressed = {
                            router.popBackStack()
            },
            onDetailOfficer = {
                router.navigate(Routes.DetailOdp.navigate("sas")){
                    launchSingleTop = true
                }
            }
        )
    }
}