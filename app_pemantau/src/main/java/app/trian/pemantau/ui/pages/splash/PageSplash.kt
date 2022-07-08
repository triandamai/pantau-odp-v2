package app.trian.pemantau.ui.pages.splash

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.ScreenSplashScreen
import com.trian.component.theme.PantauWargaTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeSplash(
    router: NavHostController
) {
    composable(Routes.Splash) {
        val viewModel = hiltViewModel<SplashViewModel>()

        LaunchedEffect(key1 = Unit, block = {
            viewModel.isUserAlreadyLoggedIn {
                if(it){
                    router.navigate(Routes.Main.MAIN){
                        popUpTo(Routes.Splash){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }else{
                    router.navigate(Routes.Onboard){
                        popUpTo(Routes.Splash){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        })
        ScreenSplashScreen()
    }
}
