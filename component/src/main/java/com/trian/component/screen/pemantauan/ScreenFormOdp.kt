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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.trian.component.form.FormInputWithButton
import com.trian.component.theme.BackgroundDashboard
import com.trian.component.theme.PantauWargaTheme
import com.trian.component.utils.from
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
    onBackPressed: () -> Unit = {},
    onShowDatePicker: () -> Unit = {},
    onShowPickReligion: () -> Unit = {},
    onSubmit: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        pageCount = 7,
        initialPage = 0,
        infiniteLoop = false
    )


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
                                        initialValue = "",
                                        placeholder = stringResource(R.string.placeholder_warga_nama),
                                        label = stringResource(R.string.label_input_warga_nama),
                                        singleLine = true,
                                        onChange = {

                                        },
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Next
                                    )
                                    FormInput(
                                        initialValue = "",
                                        placeholder = stringResource(R.string.label_input_warga_agama),
                                        label = stringResource(R.string.label_input_warga_agama),
                                        singleLine = true,
                                        onChange = {

                                        },
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Next
                                    )
                                    FormInputWithButton(
                                        initialValue = "",
                                        placeholder = stringResource(R.string.label_input_warga_nik),
                                        label = stringResource(R.string.label_input_warga_nik),
                                        singleLine = true,
                                        keyboardType = KeyboardType.Number,
                                        maxLength = 16,
                                        onChange = {

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
                                        value = "Trian Damai"
                                    )
                                ),
                                inputContent = {
                                    Text(
                                        text = "Tempat, dan tanggal lahir?",
                                        style = MaterialTheme.typography.body1.copy(
                                            color = MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    FormInput(
                                        initialValue = "",
                                        placeholder = stringResource(R.string.label_input_warga_tempat_lahir),
                                        label = stringResource(R.string.label_input_warga_tempat_lahir),
                                        singleLine = true,
                                        onChange = {

                                        },
                                        keyboardType = KeyboardType.Email,
                                        imeAction = ImeAction.Next
                                    )
                                    FormInputWithButton(
                                        initialValue = "",
                                        placeholder = stringResource(R.string.label_input_warga_tanggal_lahir),
                                        label = stringResource(R.string.label_input_warga_tanggal_lahir),
                                        singleLine = true,
                                        keyboardType = KeyboardType.Number,
                                        maxLength = 13,
                                        onChange = {

                                        },
                                        onSubmit = {
                                            nextPage()
                                        },

                                        )

                                }
                            )
                        }
                        2 -> {
                            ScreenNameTransaction(
                                itemModels = listOf(
                                    ItemDetailTransactionModel(
                                        label = "Nama",
                                        value = "Trian Damai"
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
                                        initialValue = "",
                                        placeholder = stringResource(R.string.label_input_warga_alamat),
                                        label = stringResource(R.string.label_input_warga_alamat),
                                        singleLine = true,
                                        onChange = {

                                        },
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Next
                                    )
                                    FormInput(
                                        initialValue = "",
                                        placeholder = stringResource(R.string.label_input_warga_rt),
                                        label = stringResource(R.string.label_input_warga_rt),
                                        singleLine = true,
                                        onChange = {

                                        },
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Next
                                    )
                                    FormInputWithButton(
                                        initialValue = "",
                                        placeholder = stringResource(R.string.label_input_warga_rw),
                                        label = stringResource(R.string.label_input_warga_rw),
                                        singleLine = true,
                                        keyboardType = KeyboardType.Number,
                                        maxLength = 13,
                                        onChange = {

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
                                        value = "Trian Damai"
                                    )
                                ),
                                inputContent = {
                                    Text(
                                        text = "Informasi Pekerjaan",
                                        style = MaterialTheme.typography.body1.copy(
                                            color = MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    FormInput(
                                        initialValue = "",
                                        placeholder = stringResource(R.string.label_input_warga_pekerjaan),
                                        label = stringResource(R.string.label_input_warga_pekerjaan),
                                        singleLine = true,
                                        onChange = {

                                        },
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Next
                                    )
                                    FormInputWithButton(
                                        initialValue = "",
                                        placeholder = stringResource(R.string.label_input_warga_no_telp),
                                        label = stringResource(R.string.label_input_warga_no_telp),
                                        singleLine = true,
                                        keyboardType = KeyboardType.Number,
                                        maxLength = 13,
                                        onChange = {

                                        },
                                        onSubmit = {
nextPage()
                                        },

                                        )

                                }
                            )
                        }
                        4 -> {
                            ScreenNameTransaction(
                                itemModels = listOf(
                                    ItemDetailTransactionModel(
                                        label = "Nama",
                                        value = "Trian Damai"
                                    )
                                ),
                                inputContent = {
                                    Text(
                                        text = "Golongan Darah",
                                        style = MaterialTheme.typography.body1.copy(
                                            color = MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    LazyColumn(content = {
                                        item {
                                            ItemTripSelection(
                                                name = "A",
                                                selected = false
                                            ) {

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "B",
                                                selected = false
                                            ) {

                                                nextPage()
                                            }
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
                                        value = "Trian Damai"
                                    )
                                ),
                                inputContent = {
                                    Text(
                                        text = "Riwayat Perjalanan",
                                        style = MaterialTheme.typography.body1.copy(
                                            color = MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        textAlign = TextAlign.Start
                                    )
                                    LazyColumn(content = {
                                        item {
                                            ItemTripSelection(
                                                name = "Luar Negeri",
                                                selected = false
                                            ) {

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Daerah Terjangkit",
                                                selected = false
                                            ) {

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Kontak dengan pasien Positif COVID19",
                                                selected = false
                                            ) {

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Tidak melakukan perjalanan",
                                                selected = false
                                            ) {

                                                nextPage()
                                            }
                                        }
                                    })
                                    FormInputWithButton(
                                        initialValue = "",
                                        placeholder = stringResource(R.string.label_input_warga_tanggal_lahir),
                                        label = stringResource(R.string.label_input_warga_tanggal_lahir),
                                        singleLine = true,
                                        keyboardType = KeyboardType.Text,
                                        maxLength = 13,
                                        onChange = {

                                        },
                                        onSubmit = {
nextPage()
                                        },

                                        )
                                }
                            )

                        }
                        6 -> {
                            ScreenNameTransaction(
                                itemModels = listOf(
                                    ItemDetailTransactionModel(
                                        label = "Nama",
                                        value = "Trian Damai"
                                    )
                                ),
                                inputContent = {
                                    Text(
                                        text = "Surat pernyataan isolasi mandiri",
                                        style = MaterialTheme.typography.body1.copy(
                                            color = MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        textAlign = TextAlign.Start
                                    )
                                    LazyColumn(content = {
                                        item {
                                            ItemTripSelection(
                                                name = "Surat ada",
                                                selected = false
                                            ) {

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Tidak ada surat",
                                                selected = false
                                            ) {

                                                nextPage()
                                            }
                                        }
                                    })
                                    Text(
                                        text = "Jaring pengaman",
                                        style = MaterialTheme.typography.body1.copy(
                                            color = MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        textAlign = TextAlign.Start
                                    )
                                    LazyColumn(content = {
                                        item {
                                            ItemTripSelection(
                                                name = "Butuh Bantuan",
                                                selected = false
                                            ) {

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Tidak Butuh Bantuan",
                                                selected = false
                                            ) {

                                                nextPage()
                                            }
                                        }
                                    })
                                    Text(
                                        text = "Perilaku",
                                        style = MaterialTheme.typography.body1.copy(
                                            color = MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        textAlign = TextAlign.Start
                                    )
                                    LazyColumn(content = {
                                        item {
                                            ItemTripSelection(
                                                name = "Patuh",
                                                selected = false
                                            ) {

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Tidak Patuh",
                                                selected = false
                                            ) {

                                                nextPage()
                                            }
                                        }
                                    })
                                    FormInputWithButton(
                                        initialValue = "",
                                        placeholder = stringResource(R.string.label_input_warga_kondisi),
                                        label = stringResource(R.string.label_input_warga_kondisi),
                                        singleLine = true,
                                        keyboardType = KeyboardType.Text,
                                        maxLength = 13,
                                        onChange = {

                                        },
                                        onSubmit = {
                                            onSubmit()
                                        },

                                        )
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