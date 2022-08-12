package app.trian.coordinator.ui.pages.detail_pemantau

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.BottomSheetDeleteConfirmation
import com.trian.component.Routes
import com.trian.component.dialog.DialogLoading
import com.trian.component.screen.user.DetailOfficerUIState
import com.trian.component.screen.user.ScreenDetailOfficer
import com.trian.component.utils.toastError
import com.trian.component.utils.toastSuccess
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
fun NavGraphBuilder.routeDetailPemantau(
    router: NavHostController
) {
    composable(
        Routes.DetailUser.route,
        arguments = Routes.DetailUser.navArg()
    ) {
        val scope = rememberCoroutineScope()
        val ctx = LocalContext.current
        val viewModel = hiltViewModel<DetailPemantauViewModel>()
        val detailPemantau by viewModel.detailOfficerState.observeAsState(
            initial = DetailOfficerUIState(
                loading = true,
                error = false
            )
        )
        val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        var loading by remember {
            mutableStateOf(false)
        }

        DialogLoading(show = loading)

       ModalBottomSheetLayout(
           sheetState = bottomSheetState,
           sheetContent = {
           BottomSheetDeleteConfirmation(
               message = "Apakah Anda yakin menghapus pemantau ${detailPemantau.officer.name}?",
               onConfirm = {
                   loading = true
                           scope.launch {
                               bottomSheetState.hide()
                           }
                   viewModel.deletePemantau(detailPemantau.officer.uid){
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
       }) {
           ScreenDetailOfficer(
               state = detailPemantau,
               onDelete = {
                    scope.launch {
                        bottomSheetState.show()
                    }
               },
               onEdit = {

                   if(detailPemantau.officer.uid.isNotEmpty()) {
                       router.navigate(Routes.FormEditUser.navigate(detailPemantau.officer.uid)){
                           launchSingleTop = true
                       }
                   }
               },
               onBackPressed = {
                   router.popBackStack()
               }
           )
       }
    }
}

