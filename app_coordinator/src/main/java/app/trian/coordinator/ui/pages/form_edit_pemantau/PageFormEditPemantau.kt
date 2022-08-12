package app.trian.coordinator.ui.pages.form_edit_pemantau

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.dialog.DialogLoading
import com.trian.component.dialog.DialogPickStrictAndVillage
import com.trian.component.dialog.ItemAddress
import com.trian.component.dialog.PickDistrictOrVillageUIState
import com.trian.component.screen.user.DetailOfficerUIState
import com.trian.component.screen.user.ScreenFormEditPemantau
import com.trian.component.screen.user.ScreenFormPemantau
import com.trian.component.utils.toastError
import com.trian.component.utils.toastSuccess

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeFormEditPemantau(
    router: NavHostController
) {
    composable(Routes.FormEditUser.route) {
        val viewModel = hiltViewModel<FormEditPemantauViewModel>()
        val villageUIState by viewModel.listVillage.observeAsState(initial = PickDistrictOrVillageUIState(
            loading = true,
            error = false
        ))
        val detailPemantau by viewModel.detailOfficerState.observeAsState(
            initial = DetailOfficerUIState(
                loading = true,
                error = false
            )
        )
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

        LaunchedEffect(key1 = detailPemantau, block = {
            selectedAddress = ItemAddress(
                id = detailPemantau.officer.villageId,
                name = detailPemantau.officer.villageName
            )
        })
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

        ScreenFormEditPemantau(
            onBackPressed = {
                router.popBackStack()
            },
            onSelectAddress = {
                showPickAddress = true
            },
            selectedAddress = selectedAddress?.name ?: "",
            currentName = detailPemantau.officer.name,
            currentNip = detailPemantau.officer.nip,
            currentOpd = detailPemantau.officer.opd,
            onSubmit = {
                name,nip,opd->

                loading = true
                viewModel.saveOfficer(
                    name= name,
                    nip =nip,
                    opd=opd,
                    villageName = selectedAddress?.name ?: "",
                    villageId = selectedAddress?.id ?: ""
                ){
                    success,message->
                    loading=false
                    if(success){
                        ctx.toastSuccess("Berhasil membuat akun pemantau")

                        router.popBackStack()
                    }else{
                        ctx.toastError(message)
                    }
                }

            }
        )
    }
}
