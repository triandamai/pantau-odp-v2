package com.trian.component.screen.main

import androidx.compose.foundation.layout.*
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
            Column (
                modifier = modifier.padding(vertical = 10.dp)
            ){
                Box(
                    modifier = modifier.padding(
                        horizontal = 30.dp
                    )
                ) {
                    //CardChartHome()
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
                        name = "Income",
                        value = "Rp 1.000.00",
                        iconColor = MaterialTheme.colors.secondary
                    ){

                    }
                    ItemStat(
                        name = "Expense",
                        value = "Rp 1.000.00",
                        iconColor = ExpensesColor
                    ){

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