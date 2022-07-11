package com.trian.component.screen.pemantauan

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.trian.component.AppbarBasic
import com.trian.component.R
import com.trian.component.form.FormInputWithButton
import com.trian.component.theme.BackgroundDashboard
import com.trian.component.theme.PantauWargaTheme
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
fun ScreenFormAssesment(
    modifier: Modifier = Modifier,
    onBackPressed:()->Unit={},
    onSubmit:()->Unit={}
) {
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        pageCount = 3,
        initialPage = 0,
        infiniteLoop = false
    )



    fun nextPage(){
        scope.launch {
            pagerState.scrollToPage(pagerState.currentPage + 1)
        }

    }
    fun prevPage(){
        scope.launch {
            if(pagerState.currentPage > 0){
                pagerState.scrollToPage(pagerState.currentPage-1)
            }else{
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
        ) {
                page->
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
                    visible = page== pagerState.currentPage,
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
                                            color=MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    LazyColumn(content = {
                                        item {
                                            ItemTripSelection(
                                                name = "A",
                                                selected = false
                                            ){

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "B",
                                                selected = false
                                            ){

                                                nextPage()
                                            }
                                        }
                                    })
                                }
                            )

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
                                        text = "Riwayat Perjalanan",
                                        style = MaterialTheme.typography.body1.copy(
                                            color=MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        textAlign = TextAlign.Start
                                    )
                                    LazyColumn(content = {
                                        item {
                                            ItemTripSelection(
                                                name = "Luar Negeri",
                                                selected = false
                                            ){

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Daerah Terjangkit",
                                                selected = false
                                            ){

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Kontak dengan pasien Positif COVID19",
                                                selected = false
                                            ){

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Tidak melakukan perjalanan",
                                                selected = false
                                            ){

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
                                        text = "Surat pernyataan isolasi mandiri",
                                        style = MaterialTheme.typography.body1.copy(
                                            color=MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        textAlign = TextAlign.Start
                                    )
                                    LazyColumn(content = {
                                        item {
                                            ItemTripSelection(
                                                name = "Surat ada",
                                                selected = false
                                            ){

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Tidak ada surat",
                                                selected = false
                                            ){

                                                nextPage()
                                            }
                                        }
                                    })
                                    Text(
                                        text = "Jaring pengaman",
                                        style = MaterialTheme.typography.body1.copy(
                                            color=MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        textAlign = TextAlign.Start
                                    )
                                    LazyColumn(content = {
                                        item {
                                            ItemTripSelection(
                                                name = "Butuh Bantuan",
                                                selected = false
                                            ){

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Tidak Butuh Bantuan",
                                                selected = false
                                            ){

                                                nextPage()
                                            }
                                        }
                                    })
                                    Text(
                                        text = "Perilaku",
                                        style = MaterialTheme.typography.body1.copy(
                                            color=MaterialTheme.colors.onBackground,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        textAlign = TextAlign.Start
                                    )
                                    LazyColumn(content = {
                                        item {
                                            ItemTripSelection(
                                                name = "Patuh",
                                                selected = false
                                            ){

                                                nextPage()
                                            }
                                        }
                                        item {
                                            ItemTripSelection(
                                                name = "Tidak Patuh",
                                                selected = false
                                            ){

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
nextPage()
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
fun PreviewScreenFormAssesment() {
    PantauWargaTheme {
        ScreenFormAssesment()
    }
}