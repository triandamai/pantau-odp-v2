package app.trian.coordinator.ui.pages.form_pemantau

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.dialog.DialogLoading
import com.trian.component.dialog.DialogPickStrictAndVillage
import com.trian.component.dialog.ItemAddress
import com.trian.component.dialog.PickDistrictOrVillageUIState
import com.trian.component.screen.user.ScreenFormPemantau
import com.trian.component.theme.PantauWargaTheme
import com.trian.component.utils.toastError
import com.trian.component.utils.toastSuccess

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeFormPemantau(
    router: NavHostController
) {
    composable(Routes.FormUser) {
        val viewModel = hiltViewModel<FormPemantauViewModel>()
        val villageUIState by viewModel.listVillage.observeAsState(initial = PickDistrictOrVillageUIState(
            loading = true,
            error = false
        ))
        val ctx = LocalContext.current

        var showPickAddress by remember {
            mutableStateOf(false)
        }

        var loading by remember {
            mutableStateOf(false)
        }

        var selectedAddress by remember {
            mutableStateOf<ItemAddress?>(null)
        }

        DialogPickStrictAndVillage(
            show = showPickAddress,
            state = villageUIState,
            onDismiss = {
                showPickAddress = false
            },
            onSelect = {
                selectedAddress = it
                showPickAddress = false
            }
        )

        DialogLoading(show = loading)

        ScreenFormPemantau(
            onBackPressed = {
                router.popBackStack()
            },
            onSelectAddress = {
                showPickAddress = true
            },
            selectedAddress = selectedAddress?.name ?: "",
            onSubmit = {
                name,email,nip,opd->
                loading = true
                viewModel.saveOfficer(
                    name= name,
                    email=email,
                    nip =nip,
                    opd=opd,
                    level="PEMANTAU",
                    villageName = selectedAddress?.name ?: "",
                    villageId = selectedAddress?.id ?: ""
                ){
                    success,message->
                    loading=false
                    if(success){
                        ctx.toastSuccess("Berhasil membuat akun pemantau")
                        router.navigate(Routes.SuccessFormPemantau.navigate(message)){
                            popUpTo(Routes.FormUser){
                                inclusive=true
                            }
                            launchSingleTop = true
                        }
                    }else{
                        ctx.toastError(message)
                    }
                }

            }
        )
    }
}
