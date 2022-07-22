package app.trian.pemantau.ui.pages.change_password

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.ScreenChangePassword

/**
 * author Trian Damai
 * created_at 22/07/22 - 10.30
 * site https://trian.app
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeChangePassword(
    router: NavHostController
) {
    composable(Routes.ChangePassword) {
        ScreenChangePassword(
            onBackPressed = {
                            router.popBackStack()
            },
            onSubmit = {}
        )
    }
}
