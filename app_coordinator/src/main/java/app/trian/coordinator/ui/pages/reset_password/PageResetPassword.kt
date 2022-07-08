package app.trian.coordinator.ui.pages.reset_password

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.dialog.DialogLoading
import com.trian.component.screen.ScreenResetPassword
import com.trian.component.utils.toastError
import com.trian.component.utils.toastSuccess

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeResetPassword(
    router: NavHostController
) {
    composable(Routes.ResetPassword) {
        val viewModel = hiltViewModel<ResetPasswordViewModel>()
        val ctx = LocalContext.current

        var loading by remember {
            mutableStateOf(false)
        }
        DialogLoading(show = loading)

       ScreenResetPassword(
           onLogin = {
               router.popBackStack()
           },
           onSubmit = {
               loading = true
               viewModel.resetPassword(
                   it
               ){
                   success,message->
                   loading = false
                   if(success){
                       ctx.toastSuccess(message)
                       router.popBackStack()

                   }else{
                       ctx.toastError(message)
                   }
               }
           }
       )
    }
}
