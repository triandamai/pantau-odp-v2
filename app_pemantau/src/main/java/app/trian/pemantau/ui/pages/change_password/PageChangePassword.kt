package app.trian.pemantau.ui.pages.change_password

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
import com.trian.component.screen.ScreenChangePassword
import com.trian.component.utils.toastError
import com.trian.component.utils.toastSuccess

/**
 * author Trian Damai
 * created_at 22/07/22 - 10.30
 * site https://trian.app
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeChangePassword(
    router: NavHostController,
    restartActivity:()->Unit
) {
    composable(Routes.ChangePassword) {
        val viewModel = hiltViewModel<ChangePasswordViewModel>()
        val ctx = LocalContext.current

        var loading by remember {
            mutableStateOf(false)
        }
        DialogLoading(show = loading)

        ScreenChangePassword(
            onBackPressed = {
                            router.popBackStack()
            },
            onSubmit = {
                loading = true
                viewModel.changePassword(it){
                    success,message->
                    loading=false
                    if(success){
                        ctx.toastSuccess(message)
                        restartActivity()
                    }else{
                        ctx.toastError(message)
                    }
                }
            }
        )
    }
}
