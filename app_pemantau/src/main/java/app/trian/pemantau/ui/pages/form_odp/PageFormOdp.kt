package app.trian.pemantau.ui.pages.form_odp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.trian.component.Routes
import com.trian.component.dialog.DialogLoading
import com.trian.component.dialog.DialogPickReligion
import com.trian.component.picker.BottomSheetSpinnerDatePicker
import com.trian.component.picker.SpinnerDatePicker
import com.trian.component.screen.pemantauan.ScreenFormOdp
import com.trian.component.utils.toastError
import com.trian.component.utils.toastSuccess
import com.trian.data.utils.utils.formatDate
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
fun NavGraphBuilder.routeFormOdp(
    router: NavHostController
) {
    composable(Routes.FormOdp) {
        val ctx = LocalContext.current
        val scope = rememberCoroutineScope()
        val viewModel = hiltViewModel<FormOdpViewModel>()
        val bottomSheetState = rememberModalBottomSheetState(initialValue =ModalBottomSheetValue.Hidden)

        var dateOfBirth by remember {
            mutableStateOf("")
        }
        var showPickReligion by remember {
            mutableStateOf(false)
        }
        var religion by remember {
            mutableStateOf("")
        }
        var loading by remember {
            mutableStateOf(false)
        }

        DialogPickReligion(
            show = showPickReligion,
            onSelect = {
                religion = it
                showPickReligion = false
            },
            onDismiss = {
                showPickReligion = false
            }
        )

        DialogLoading(show = loading)

        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetContent = {
                BottomSheetSpinnerDatePicker(
                    onSubmit = {
                        day, month, year ->

                        dateOfBirth = "$day-$month-$year"

                        scope.launch {
                            bottomSheetState.hide()
                        }
                    },
                    onClose = {
                        scope.launch {
                            bottomSheetState.hide()
                        }
                    }
                )
            }
        ) {
            ScreenFormOdp(
                onBackPressed = {
                    router.popBackStack()
                },
                religion = religion,
                dateOfBirth = dateOfBirth,
                onSubmit = {
                        name:String,
                        religion:String,
                        nik:String,
                        dateOfBirth:String,
                        placeOfBirth:String,
                        address:String,
                        rt:String,
                        rw:String,
                        bloodType:String,
                        profession:String,
                        phoneNUmber:String,
                        tripHistory:String,
                        placeOfTrip:String,
                        isolation:Boolean,
                        safetyNet:Boolean,
                        behavior:Boolean,
                        condition:String,
                        gender:String->
                    loading = true
                    viewModel.submitOdp(
                        name = name,
                        religion=religion,
                        nik = nik,
                        dateOfBirth = dateOfBirth,
                        placeOfBirth = placeOfBirth,
                        address = address,
                        rt=rt,
                        rw=rw,
                        bloodType = bloodType,
                        profession = profession,
                        phoneNUmber = phoneNUmber,
                        tripHistory = tripHistory,
                        placeOfTrip = placeOfTrip,
                        isolation = isolation,
                        safetyNet = safetyNet,
                        behavior = behavior,
                        condition =  condition,
                        gender=gender
                    ){
                        success,message->
                        loading = false
                        if(success){
                            ctx.toastSuccess("Berhasil menyimpan")
                            router.navigate(Routes.SuccessFormOdp.navigate(message)){
                                launchSingleTop = true
                                popUpTo(Routes.FormOdp){
                                    inclusive = true
                                }
                            }
                        }else{
                            ctx.toastError(message)
                        }
                    }
                },
                onShowDatePicker = {
                    scope.launch {
                        bottomSheetState.show()
                    }
                },
                onShowPickReligion = {
                      showPickReligion = true
                },
            )
        }
    }
}

