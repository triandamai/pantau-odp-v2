package com.trian.component.screen.pemantauan

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.trian.component.AppbarBasic
import com.trian.component.R
import com.trian.component.form.FormInput
import com.trian.component.form.FormInputClickable
import com.trian.component.form.FormInputWithButton
import com.trian.component.theme.BackgroundDashboard
import com.trian.component.theme.PantauWargaTheme
import com.trian.component.utils.from
import com.trian.component.utils.toastError
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import kotlinx.coroutines.launch

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 17.14
 * site https://trian.app
 */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScreenFormOdp(
    modifier: Modifier = Modifier,
    dateOfBirth: String = "",
    religion: String = "",
    onBackPressed: () -> Unit = {},
    onShowDatePicker: () -> Unit = {},
    onShowPickReligion: () -> Unit = {},
    onSubmit: (
        name: String,
        religion: String,
        nik: String,
        dateOfBirth: String,
        placeOfBirth: String,
        address: String,
        rt: String,
        rw: String,
        bloodType: String,
        profession: String,
        phoneNUmber: String,
        tripHistory: String,
        placeOfTrip: String,
        isolation: Boolean,
        safetyNet: Boolean,
        behavior: Boolean,
        condition: String,
        gender: String
    ) -> Unit = { _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _ -> }
) {
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        pageCount = 6,
        initialPage = 0,
        infiniteLoop = false
    )

    var name by remember {
        mutableStateOf("")
    }

    var nik by remember {
        mutableStateOf("")
    }
    var placeOfBirth by remember {
        mutableStateOf("")
    }

    var address by remember {
        mutableStateOf("")
    }

    var rt by remember {
        mutableStateOf("")
    }

    var rw by remember {
        mutableStateOf("")
    }

    var proffesion by remember {
        mutableStateOf("")
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }

    var bloodType by remember {
        mutableStateOf("")
    }

    var tripHistory by remember {
        mutableStateOf("")
    }

    var placeOfTrip by remember {
        mutableStateOf("")
    }

    var isolation by remember {
        mutableStateOf<Boolean?>(null)
    }
    var safetyNet by remember {
        mutableStateOf<Boolean?>(null)
    }

    var condition by remember {
        mutableStateOf("")
    }

    var behavior by remember {
        mutableStateOf<Boolean?>(null)
    }
    var gender by remember {
        mutableStateOf("")
    }

    fun validateFirst() {
        if (name.isBlank() ||
            religion.isBlank() ||
            nik.isBlank() ||
            dateOfBirth.isBlank() ||
            placeOfBirth.isBlank() ||
            address.isBlank() ||
            rt.isBlank() ||
            rw.isBlank() ||
            bloodType.isBlank() ||
            proffesion.isBlank() ||
            phoneNumber.isBlank() ||
            tripHistory.isBlank() ||
            placeOfTrip.isBlank() ||
            isolation == null ||
            safetyNet == null ||
            behavior == null ||
            condition.isBlank() ||
            gender.isBlank()
        ) {
            ctx.toastError("Mohon isi semua data!")
        } else {
            onSubmit(
                name,
                religion,
                nik,
                dateOfBirth,
                placeOfBirth,
                address,
                rt,
                rw,
                bloodType,
                proffesion,
                phoneNumber,
                tripHistory,
                placeOfTrip,
                isolation!!,
                safetyNet!!,
                behavior!!,
                condition,
                gender
            )
        }
    }

    fun nextPage() {
        scope.launch {
            pagerState.scrollToPage(pagerState.currentPage + 1)
        }

    }

    fun prevPage() {
        scope.launch {
            if (pagerState.currentPage > 0) {
                pagerState.scrollToPage(pagerState.currentPage - 1)
            } else {
                onBackPressed()
            }
        }

    }


    //handle system back pressed
    BackHandler {
        prevPage()
    }
    Scaffold(
        topBar = {
            AppbarBasic(
                title = stringResource(R.string.appbar_title_form_warga),
                navigationIcon = {
                    IconToggleButton(
                        checked = false,
                        onCheckedChange = {
                            prevPage()
                        }
                    ) {
                        Icon(
                            imageVector = Octicons.ArrowLeft24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            )
        },
        backgroundColor = BackgroundDashboard
    ) {

        HorizontalPager(
            state = pagerState,
            dragEnabled = false
        ) { page ->
            /**
             * page:
             * 0:
             * */

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {

                AnimatedVisibility(
                    visible = page == pagerState.currentPage,
                    enter = slideInHorizontally(
                        initialOffsetX = { it }, // it == fullWidth
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = LinearEasing
                        )
                    ),

                    ) {
                    when (page) {
                        0 -> {
                            Column(
                                modifier = modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 30.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Column(
                                    modifier = modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.bg_onboard_3),
                                        contentDescription = stringResource(R.string.content_description_image_form_odp),
                                        modifier = Modifier
                                            .width(60.dp.from(ctx))
                                            .height(75.dp.from(ctx))
                                    )
                                    Spacer(modifier = modifier.height(10.dp))
                                    Text(
                                        text = stringResource(R.string.title_page_form_monitoring),
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                                Column(
                                    modifier = modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Bottom
                                ) {
                                    FormInput(
                                        initialValue = name,
                                        placeholder = stringResource(R.string.placeholder_warga_nama),
                                        label = stringResource(R.string.label_input_warga_nama),
                                        singleLine = true,
                                        onChange = {
                                            name = it
                                        },
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Next
                                    )
                                    FormInputClickable(
                                        initialValue = religion,
                                        placeholder = stringResource(R.string.label_input_warga_agama),
                                        label = stringResource(R.string.label_input_warga_agama),
                                        onClick = {
                                            onShowPickReligion()
                                        }
                                    )
                                    FormInputWithButton(
                                        initialValue = nik,
                                        placeholder = stringResource(R.string.label_input_warga_nik),
                                        label = stringResource(R.string.label_input_warga_nik),
                                        singleLine = true,
                                        keyboardType = KeyboardType.Number,
                                        maxLength = 16,
                                        onChange = {
                                            nik = it
                                        },
                                        onSubmit = {
                                            nextPage()
                                        },

                                        )
                                }
                            }

                        }
                        1 -> {
                            ScreenNameTransaction(
                                itemModels = listOf(
                                    ItemDetailTransactionModel(
                                        label = "Nama",
                                        value = name
                                    )
                                ),
                                inputContent = {

                                    LazyColumn(content = {
                                        item {
                                            Text(
                                                text = "Jenis kelamin",
                                                style = MaterialTheme.typography.body1.copy(
                                                    color = MaterialTheme.colors.onBackground,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Laki Laki",
                                                selected = "L" == gender
                                            ) {

                                                gender = "L"
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Perempuan",
                                                selected = "P" == gender
                                            ) {

                                                gender = "P"
                                            }
                                        }
                                        item {
                                            Text(
                                                text = "Tempat, dan tanggal lahir",
                                                style = MaterialTheme.typography.body1.copy(
                                                    color = MaterialTheme.colors.onBackground,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )
                                        }
                                        item {
                                            FormInputClickable(
                                                initialValue = dateOfBirth,
                                                placeholder = stringResource(R.string.label_input_warga_tanggal_lahir),
                                                label = stringResource(R.string.label_input_warga_tanggal_lahir),
                                                onClick = {
                                                    onShowDatePicker()
                                                },
                                            )
                                        }
                                        item {
                                            FormInputWithButton(
                                                initialValue = placeOfBirth,
                                                placeholder = stringResource(R.string.label_input_warga_tempat_lahir),
                                                label = stringResource(R.string.label_input_warga_tempat_lahir),
                                                singleLine = true,
                                                keyboardType = KeyboardType.Text,
                                                maxLength = 13,
                                                onChange = {
                                                    placeOfBirth = it
                                                },
                                                onSubmit = {
                                                    nextPage()
                                                },

                                                )
                                        }
                                    })


                                }
                            )
                        }
                        2 -> {
                            ScreenNameTransaction(
                                itemModels = listOf(
                                    ItemDetailTransactionModel(
                                        label = "Nama",
                                        value = name
                                    )
                                ),
                                inputContent = {
                                    Text(
                                        text = "Alamat warga",
                                        style = MaterialTheme.typography.body1.copy(
                                            color = MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    FormInput(
                                        initialValue = address,
                                        placeholder = stringResource(R.string.label_input_warga_alamat),
                                        label = stringResource(R.string.label_input_warga_alamat),
                                        singleLine = true,
                                        onChange = {
                                            address = it
                                        },
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Next
                                    )
                                    FormInput(
                                        initialValue = rt,
                                        placeholder = stringResource(R.string.label_input_warga_rt),
                                        label = stringResource(R.string.label_input_warga_rt),
                                        singleLine = true,
                                        onChange = {
                                            rt = it
                                        },
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Next
                                    )
                                    FormInputWithButton(
                                        initialValue = rw,
                                        placeholder = stringResource(R.string.label_input_warga_rw),
                                        label = stringResource(R.string.label_input_warga_rw),
                                        singleLine = true,
                                        keyboardType = KeyboardType.Number,
                                        maxLength = 13,
                                        onChange = {
                                            rw = it
                                        },
                                        onSubmit = {
                                            nextPage()
                                        },

                                        )

                                }
                            )
                        }
                        3 -> {
                            ScreenNameTransaction(
                                itemModels = listOf(
                                    ItemDetailTransactionModel(
                                        label = "Nama",
                                        value = name
                                    )
                                ),
                                inputContent = {

                                    LazyColumn(content = {
                                        item {
                                            Text(
                                                text = "Golongan Darah",
                                                style = MaterialTheme.typography.body1.copy(
                                                    color = MaterialTheme.colors.onBackground,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "A",
                                                selected = "A" == bloodType
                                            ) {

                                                bloodType = "A"
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "B",
                                                selected = "B" == bloodType
                                            ) {

                                                bloodType = "B"
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "AB",
                                                selected = "AB" == bloodType
                                            ) {

                                                bloodType = "AB"
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "O",
                                                selected = "O" == bloodType
                                            ) {

                                                bloodType = "O"
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Tidak Ada",
                                                selected = "NONE" == bloodType
                                            ) {

                                                bloodType = "NONE"
                                            }
                                        }
                                        item {
                                            Text(
                                                text = "Informasi Pekerjaan",
                                                style = MaterialTheme.typography.body1.copy(
                                                    color = MaterialTheme.colors.onBackground,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )
                                        }
                                        item {
                                            FormInput(
                                                initialValue = proffesion,
                                                placeholder = stringResource(R.string.label_input_warga_pekerjaan),
                                                label = stringResource(R.string.label_input_warga_pekerjaan),
                                                singleLine = true,
                                                onChange = {
                                                    proffesion = it
                                                },
                                                keyboardType = KeyboardType.Text,
                                                imeAction = ImeAction.Next
                                            )
                                        }
                                        item {
                                            FormInputWithButton(
                                                initialValue = phoneNumber,
                                                placeholder = stringResource(R.string.label_input_warga_no_telp),
                                                label = stringResource(R.string.label_input_warga_no_telp),
                                                singleLine = true,
                                                keyboardType = KeyboardType.Number,
                                                maxLength = 13,
                                                onChange = {
                                                    phoneNumber = it
                                                },
                                                onSubmit = {
                                                    nextPage()
                                                },

                                                )

                                        }
                                    })


                                }
                            )
                        }
                        4 -> {
                            ScreenNameTransaction(
                                itemModels = listOf(
                                    ItemDetailTransactionModel(
                                        label = "Nama",
                                        value = name
                                    )
                                ),
                                inputContent = {

                                    LazyColumn(content = {
                                        item {
                                            Text(
                                                text = "Riwayat Perjalanan",
                                                style = MaterialTheme.typography.body1.copy(
                                                    color = MaterialTheme.colors.onBackground,
                                                    fontWeight = FontWeight.Bold
                                                ),
                                                textAlign = TextAlign.Start
                                            )
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Luar Negeri",
                                                selected = "LN" == tripHistory
                                            ) {

                                                tripHistory = "LN"
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Daerah Terjangkit",
                                                selected = "DT" == tripHistory
                                            ) {

                                                tripHistory = "DT"
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Kontak dengan pasien Positif COVID19",
                                                selected = "CONTACT" == tripHistory
                                            ) {

                                                tripHistory = "CONTACT"
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Tidak melakukan perjalanan",
                                                selected = "NONE" == tripHistory
                                            ) {

                                                tripHistory = "NONE"
                                            }
                                        }
                                        item {
                                            FormInputWithButton(
                                                initialValue = placeOfTrip,
                                                placeholder = stringResource(R.string.placeholder_input_warga_nama_wilayah_perjalanan),
                                                label = stringResource(R.string.label_input_warga_nama_wilayah_perjalanan),
                                                keyboardType = KeyboardType.Text,
                                                imeAction = ImeAction.Next,
                                                onChange = {
                                                    placeOfTrip = it
                                                },
                                                onSubmit = {
                                                    nextPage()
                                                }
                                            )
                                        }
                                    })

                                }
                            )

                        }
                        5 -> {
                            ScreenNameTransaction(
                                itemModels = listOf(
                                    ItemDetailTransactionModel(
                                        label = "Nama",
                                        value = name
                                    )
                                ),
                                inputContent = {

                                    LazyColumn(content = {
                                        item {
                                            Text(
                                                text = "Surat pernyataan isolasi mandiri",
                                                style = MaterialTheme.typography.body1.copy(
                                                    color = MaterialTheme.colors.onBackground,
                                                    fontWeight = FontWeight.Bold
                                                ),
                                                textAlign = TextAlign.Start
                                            )
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Surat ada",
                                                selected = isolation ?: false
                                            ) {

                                                isolation = true
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Tidak ada surat",
                                                selected = !(isolation ?: true)
                                            ) {

                                                isolation = false
                                            }
                                        }
                                        item {
                                            Text(
                                                text = "Jaring pengaman",
                                                style = MaterialTheme.typography.body1.copy(
                                                    color = MaterialTheme.colors.onBackground,
                                                    fontWeight = FontWeight.Bold
                                                ),
                                                textAlign = TextAlign.Start
                                            )
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Butuh Bantuan",
                                                selected = safetyNet ?: false
                                            ) {

                                                safetyNet = true
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Tidak Butuh Bantuan",
                                                selected = !(safetyNet ?: true)
                                            ) {

                                                safetyNet = false
                                            }
                                        }
                                        item {
                                            Text(
                                                text = "Perilaku",
                                                style = MaterialTheme.typography.body1.copy(
                                                    color = MaterialTheme.colors.onBackground,
                                                    fontWeight = FontWeight.Bold
                                                ),
                                                textAlign = TextAlign.Start
                                            )
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Patuh",
                                                selected = behavior ?: false
                                            ) {

                                                behavior = true
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Tidak Patuh",
                                                selected = !(behavior ?: false)
                                            ) {

                                                behavior = false
                                            }
                                        }
                                        item {
                                            FormInputWithButton(
                                                initialValue = condition,
                                                placeholder = stringResource(R.string.label_input_warga_kondisi),
                                                label = stringResource(R.string.label_input_warga_kondisi),
                                                singleLine = true,
                                                keyboardType = KeyboardType.Text,
                                                maxLength = 13,
                                                onChange = {
                                                    condition = it
                                                },
                                                onSubmit = {
                                                    validateFirst()
                                                },

                                                )
                                        }
                                    })


                                }
                            )

                        }
                        else -> {

                        }
                    }
                }


            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenFormOdp() {
    PantauWargaTheme {
        ScreenFormOdp()
    }
}