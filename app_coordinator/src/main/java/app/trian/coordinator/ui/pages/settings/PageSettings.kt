package app.trian.coordinator.ui.pages.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.screen.ActionType
import com.trian.component.screen.ScreenSettings
import com.trian.component.screen.SettingModel
import com.trian.component.screen.SettingType
import com.trian.component.theme.PantauWargaTheme
import compose.icons.Octicons
import compose.icons.octicons.Key16

/**
 * author Trian Damai
 * created_at 22/07/22 - 10.26
 * site https://trian.app
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeSettings(
    router: NavHostController
) {
    composable(Routes.Settings) {
        ScreenSettings(
            menus = listOf(
                SettingModel(
                    name = "Ubah password",
                    description = "ubah password anda secara berkala",
                    icon = Octicons.Key16,
                    type = SettingType.navigation,
                    route = Routes.ChangePassword,
                    action = ActionType.none
                )
            ),
            onBackPressed = {
                            router.popBackStack()
            },
            onClick = {
                      //todo::
            },
            onNavigate = {
                router.navigate(it){
                    launchSingleTop = true
                }
            }
        )
    }
}
