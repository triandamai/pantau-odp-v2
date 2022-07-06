package app.trian.pemantau.ui.pages.splash

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.ScreenSplashScreen
import com.trian.component.theme.PantauWargaTheme

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeSplash(
    router: NavHostController
) {
    composable(Routes.Splash) {
        ScreenSplashScreen()
    }
}
