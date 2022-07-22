package com.trian.component.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.component.AppbarProfile
import com.trian.component.ButtonSmallSecondary
import com.trian.component.ItemMenuDrawer
import com.trian.component.theme.HexToJetpackColor
import com.trian.component.theme.PantauWargaTheme
import com.trian.component.theme.listGradient
import com.trian.component.utils.coloredShadow
import com.trian.component.R
import kotlinx.coroutines.launch

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 21.58
 * site https://trian.app
 */

data class ProfileUIState(
    var loading:Boolean=false,
    var error:Boolean = false,
    var errorMessage :String = "",

    var name:String="",
    var email:String="",
    var placeOfAssignment:String="",
    var nip:String="",
    var opd:String="",
)

@Composable
fun ScreenProfile(
    modifier: Modifier = Modifier,
    router: NavHostController,
    menus:List<ItemMenuDrawer> = listOf(),
    profile:ProfileUIState=ProfileUIState(),
    onRestartActivity:()->Unit={}
) {


    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()



    BaseMainScreen(
        drawerState=drawerState,
        router = router,
        onRestartActivity = onRestartActivity,
        menus = menus,
        userName = profile.name,
        onFabClicked = {

        },
        topAppbar = {
            AppbarProfile(
                onNavigation = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                onAction = {

                }
            )
        },
        content = {
            Column {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(
                            RoundedCornerShape(
                                bottomEnd = 20.dp,
                                bottomStart = 20.dp
                            )
                        )
                        .background(MaterialTheme.colors.surface)
                        .padding(horizontal = 30.dp)
                ) {
                    Spacer(modifier = modifier.height(30.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column {
                            Text(
                                text =  profile.name,
                                style = MaterialTheme.typography.h4.copy(
                                    color = MaterialTheme.colors.onBackground,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                    Spacer(modifier = modifier.height(30.dp))
                    Row(modifier = modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.large)
                        .background(
                            brush = Brush.linearGradient(

                                   listOf(
                                    HexToJetpackColor.getColor(
                                        listGradient[0].first
                                    ),
                                    HexToJetpackColor.getColor(
                                        listGradient[0].second
                                    )
                                )

                            )
                        )
                        .coloredShadow(
                            MaterialTheme.colors.primary
                        )
                        .padding(all = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text =  "Penugasan di",
                                style = MaterialTheme.typography.caption.copy(
                                    color = MaterialTheme.colors.onPrimary
                                )
                            )
                            Spacer(modifier = modifier.height(16.dp))
                            Text(
                                text = profile.placeOfAssignment,
                                style = MaterialTheme.typography.body1.copy(
                                    color = MaterialTheme.colors.onPrimary
                                )
                            )
                        }
                    }
                    Spacer(modifier = modifier.height(30.dp))
                }
                Spacer(modifier = modifier.height(30.dp))
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.label_email),
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    Text(
                        text = profile.email,
                        style = MaterialTheme.typography.body1
                    )
                }
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.label_nip),
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    Text(
                        text = profile.nip,
                        style = MaterialTheme.typography.body1
                    )
                }
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.label_opd),
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    Text(
                        text = profile.opd,
                        style = MaterialTheme.typography.body1
                    )
                }

            }
        }
    )
}


@Preview
@Composable
fun PreviewScreenProfile() {
    PantauWargaTheme {
        ScreenProfile(router = rememberNavController())
    }
}