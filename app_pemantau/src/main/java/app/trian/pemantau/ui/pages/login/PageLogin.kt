package app.trian.pemantau.ui.pages.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.dialog.DialogLoading
import com.trian.component.screen.ScreenLogin
import com.trian.component.theme.PantauWargaTheme
import com.trian.component.utils.toastError
import com.trian.component.utils.toastSuccess

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeLogin(
    router: NavHostController
) {
    composable(Routes.Login) {
        val viewModel = hiltViewModel<LoginViewModel>()
        val ctx = LocalContext.current

        var loading by remember {
            mutableStateOf(false)
        }

        DialogLoading(show = loading)

        ScreenLogin(
            onSignIn = {
                email,password->
                loading = true

                viewModel.signIn(
                    email,password
                ){
                    success,message->
                    if(success){
                        ctx.toastSuccess(message)
                        router.navigate(Routes.Main.MAIN){
                            popUpTo(Routes.Login){
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }else{
                        ctx.toastError(message)
                    }
                }

            },
            onResetPassword = {
                router.navigate(Routes.ResetPassword){
                    launchSingleTop=true
                }
            }
        )
    }
}
