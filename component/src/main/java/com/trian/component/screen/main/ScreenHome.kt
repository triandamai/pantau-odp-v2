package com.trian.component.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.component.AppbarHome
import com.trian.component.ItemStat
import com.trian.component.ItemStatFull
import com.trian.component.theme.ExpensesColor
import com.trian.component.theme.PantauWargaTheme
import compose.icons.Octicons
import compose.icons.octicons.Quote24
import kotlinx.coroutines.launch

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.21
 * site https://trian.app
 */

@Composable
fun PageHome(
    modifier: Modifier = Modifier,
    router: NavHostController,
    onRestartActivity:()->Unit={}
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    BaseMainScreen(
        drawerState=drawerState,
        router = router,
        onRestartActivity=onRestartActivity,
        onFabClicked = {

        },
        topAppbar = {
            AppbarHome(
                title = "Home",
                navigationIcon = {
                    IconToggleButton(
                        checked = false,
                        onCheckedChange = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Octicons.Quote24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                },
                content = {

                }
            )
        },
        content = {
            LazyColumn (
                modifier = modifier.padding(vertical = 10.dp)
            ){
                item {
                    //                ODP
                    Box(
                        modifier = modifier.padding(
                            horizontal = 30.dp
                        )
                    ) {
                        ItemStatFull(
                            name = "ODP Dalam Pemantauan",
                            value = "500.000"
                        )
                    }
                    Spacer(modifier = modifier.height(30.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ItemStat(
                            name = "ODP Selesai",
                            value = "300.00",
                            iconColor = MaterialTheme.colors.primary
                        ){

                        }
                        ItemStat(
                            name = "ODP Total",
                            value = "200.000",
                            iconColor = ExpensesColor
                        ){

                        }
                    }
                }
                item {
                    //                PDP
                    Spacer(modifier = modifier.height(30.dp))
                    Box(
                        modifier = modifier.padding(
                            horizontal = 30.dp
                        )
                    ) {
                        ItemStatFull(
                            name = "PDP Total",
                            value = "500.000"
                        )
                    }
                    Spacer(modifier = modifier.height(30.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ItemStat(
                            name = "PDP Dirawat",
                            value = "300.00",
                            iconColor = MaterialTheme.colors.primary
                        ){

                        }
                        ItemStat(
                            name = "PDP Negatif",
                            value = "200.000",
                            iconColor = ExpensesColor
                        ){

                        }
                    }
                    Spacer(modifier = modifier.height(30.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ItemStat(
                            name = "PDP Menunggu",
                            value = "300.00",
                            iconColor = MaterialTheme.colors.primary
                        ){

                        }
                        ItemStat(
                            name = "PDP Meninggal",
                            value = "200.000",
                            iconColor = ExpensesColor
                        ){

                        }
                    }
                }

                item {
                    //POSITIF
                    Spacer(modifier = modifier.height(30.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ItemStat(
                            name = "Total Positif",
                            value = "300.00",
                            iconColor = MaterialTheme.colors.primary
                        ){

                        }
                        ItemStat(
                            name = "Dirawat",
                            value = "200.000",
                            iconColor = ExpensesColor
                        ){

                        }
                    }
                    Spacer(modifier = modifier.height(30.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ItemStat(
                            name = "Sembuh",
                            value = "300.00",
                            iconColor = MaterialTheme.colors.primary
                        ){

                        }
                        ItemStat(
                            name = "Meninggal",
                            value = "200.000",
                            iconColor = ExpensesColor
                        ){

                        }
                    }
                }
            }
        }
    )
}


@Preview
@Composable
fun PreviewPageHome() {
    PantauWargaTheme {
        PageHome(router = rememberNavController())
    }
}