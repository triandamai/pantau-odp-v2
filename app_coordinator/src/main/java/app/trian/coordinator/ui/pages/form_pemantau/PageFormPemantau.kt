package app.trian.coordinator.ui.pages.form_pemantau

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.user.ScreenFormPemantau
import com.trian.component.theme.PantauWargaTheme

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeFormPemantau(
    router: NavHostController
) {
    composable(Routes.FormUser) {
        ScreenFormPemantau()
    }
}
