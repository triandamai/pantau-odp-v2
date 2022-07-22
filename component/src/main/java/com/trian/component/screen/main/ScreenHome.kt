package com.trian.component.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.component.AppbarHome
import com.trian.component.ItemMenuDrawer
import com.trian.component.ItemStat
import com.trian.component.ItemStatFull
import com.trian.component.theme.ExpensesColor
import com.trian.component.theme.PantauWargaTheme
import com.trian.component.utils.from
import com.trian.data.models.response.MonitoringResponse
import compose.icons.Octicons
import compose.icons.octicons.Quote24
import kotlinx.coroutines.launch

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.21
 * site https://trian.app
 */
data class MonitoringUIState(
    var loading:Boolean =true,
    var error:Boolean=false,
    var errorMessage:String="n/a",

    var odp:String="n/a",
    var odpOnMonitoring:String="n/a",
    var odpFinish:String="n/a",

    var pdp:String="n/a",
    var pdpLabNegative:String="n/a",
    var pdpWaiting:String="n/a",
    var pdpDied:String="n/a",

    var positive:String="n/a",
    var positiveOnTreated:String="n/a",
    var positiveCured:String="n/a",
    var positiveDied:String="n/a"
)

@Composable
fun ScreenHome(
    modifier: Modifier = Modifier,
    router: NavHostController,
    menus:List<ItemMenuDrawer> = listOf(),
    monitoring:MonitoringUIState= MonitoringUIState(),
    userName:String="",
    onFabClicked:()->Unit={},
    onRestartActivity:()->Unit={},
    onDetailMonitoring:()->Unit={}
) {

    val ctx = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    BaseMainScreen(
        drawerState=drawerState,
        router = router,
        onRestartActivity=onRestartActivity,
        onFabClicked = onFabClicked,
        menus = menus,
        userName = userName,
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
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 30.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "ODP",
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "All",
                            style = MaterialTheme.typography.body2.copy(),
                            color = MaterialTheme.colors.primary,
                            modifier=modifier.clickable {
                                onDetailMonitoring()
                            }
                        )

                    }
                }
                item {
                    //                ODP
                    Box(
                        modifier = modifier.padding(
                            horizontal = 30.dp
                        )
                    ) {
                        ItemStatFull(
                            name = "ODP Dalam Pemantauan",
                            value = monitoring.odp
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
                            value = monitoring.odpFinish,
                            iconColor = MaterialTheme.colors.primary
                        ){

                        }
                        ItemStat(
                            name = "ODP Total",
                            value =monitoring.odpFinish ,
                            iconColor = MaterialTheme.colors.primary
                        ){

                        }
                    }
                }
                item {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 30.dp
                            ),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "PDP",
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )


                    }
                }
                item {
                    //                PDP
                    Spacer(modifier = modifier.height(30.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ItemStat(
                            name = "PDP",
                            value = monitoring.pdp,
                            iconColor = MaterialTheme.colors.primary
                        ){

                        }
                        ItemStat(
                            name = "PDP Negative",
                            value = monitoring.pdpLabNegative,
                            iconColor = MaterialTheme.colors.primary
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
                            value = monitoring.pdpWaiting,
                            iconColor = MaterialTheme.colors.secondaryVariant
                        ){

                        }
                        ItemStat(
                            name = "PDP Meninggal",
                            value = monitoring.pdpDied,
                            iconColor = ExpensesColor
                        ){

                        }
                    }
                }

                item {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 30.dp
                            ),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Positif",
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )


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
                            value = monitoring.positive,
                            iconColor = MaterialTheme.colors.primary
                        ){

                        }
                        ItemStat(
                            name = "Dirawat",
                            value = monitoring.positiveOnTreated,
                            iconColor = MaterialTheme.colors.primaryVariant
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
                            value = monitoring.positiveCured,
                            iconColor = MaterialTheme.colors.primary
                        ){

                        }
                        ItemStat(
                            name = "Meninggal",
                            value = monitoring.positiveDied,
                            iconColor = ExpensesColor
                        ){

                        }
                    }
                }
                item {
                    Spacer(modifier = modifier.height(40.dp.from(ctx)))
                }
            }
        }
    )
}


@Preview
@Composable
fun PreviewScreenHome() {
    PantauWargaTheme {
        ScreenHome(router = rememberNavController())
    }
}