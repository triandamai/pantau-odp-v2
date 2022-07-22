package app.trian.pemantau.ui.pages.detail_odp

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
import com.trian.component.screen.pemantauan.DetailOdpUIState
import com.trian.component.screen.pemantauan.ScreenDetailOdp
import com.trian.component.utils.toastError
import com.trian.component.utils.toastSuccess
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
fun NavGraphBuilder.routeDetailOdp(
    router: NavHostController
) {
    composable(
        Routes.DetailOdp.route,
        arguments = Routes.DetailOdp.navArg()
    ) {
        val ctx = LocalContext.current
        val viewModel = hiltViewModel<DetailOdpViewModel>()
        val scope = rememberCoroutineScope()
        val bottomSheetState  = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val odpState by viewModel.odpState.observeAsState(initial = DetailOdpUIState(
            loading = true,
            error = false
        ))

        var loading by remember {
            mutableStateOf(false)
        }

        DialogLoading(show = loading)

      ModalBottomSheetLayout(
          sheetState = bottomSheetState,
          sheetContent = {
              BottomSheetDeleteConfirmation(
                  message = "Apakah Anda yakin mengapus data ${odpState.name}?",
                  onConfirm = {
                      loading = true
                        scope.launch {
                            bottomSheetState.show()
                        }
                      viewModel.deleteOdp(odpState.uid){
                          success,message->
                          loading = false
                          if(success){
                              ctx.toastSuccess(message)
                              router.popBackStack()
                          }else{
                              ctx.toastError(message)
                          }
                      }
                  },
              )

          }
      ) {
          ScreenDetailOdp(
              state = odpState,
              onEdit = {},
              onDelete = {
                         scope.launch {
                             bottomSheetState.show()
                         }
              },
              onAssessment = {

              },
              onBackPressed = {

                  router.popBackStack()
              }
          )
      }
    }
}

