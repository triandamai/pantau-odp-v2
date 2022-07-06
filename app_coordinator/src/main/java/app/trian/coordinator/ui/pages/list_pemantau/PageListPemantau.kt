package app.trian.coordinator.ui.pages.list_pemantau

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.ScreenListPetugas

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeListPemantau(
    router: NavHostController
) {
    composable(Routes.ListPemantau) {
        ScreenListPetugas(
            onBackPressed = {
                router.popBackStack()
            },
            onDetailOfficer = {
                router.navigate(Routes.DetailUser.navigate("wkwkwk")){
                    launchSingleTop= true
                }
            }
        )
    }
}

