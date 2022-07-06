package app.trian.coordinator.ui.pages.list_warga

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.ScreenListWarga
import com.trian.component.theme.PantauWargaTheme

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeListWarga(
    router: NavHostController
) {
    composable(Routes.ListWarga) {
        ScreenListWarga()
    }
}
