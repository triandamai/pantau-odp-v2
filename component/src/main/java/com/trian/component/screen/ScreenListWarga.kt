package com.trian.component.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.trian.component.AppbarBasic
import com.trian.component.ItemPetugas
import com.trian.component.ItemWarga
import com.trian.component.R
import com.trian.component.theme.PantauWargaTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 13.25
 * site https://trian.app
 */
data class OdpUIState(
    var name:String="",
    var phone:String="",
    var nik:String="",
    var id:String=""
)
data class ListOdpUIState(
    var loading:Boolean = false,
    var error:Boolean = false,
    var errorMessage:String="",
    var data:List<OdpUIState> = listOf()
)
@Composable
fun ScreenListWarga(
    modifier: Modifier = Modifier,
    state:ListOdpUIState= ListOdpUIState(),
    onBackPressed:()->Unit={},
    onDetailOdp:(slug:String)->Unit={}
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    Scaffold(
        topBar = {
            AppbarBasic(
                title = "List Warga",
                navigationIcon = {
                    IconToggleButton(
                        checked = false,
                        onCheckedChange = {
                            onBackPressed()
                        }
                    ) {
                        Icon(
                            imageVector = Octicons.ArrowLeft24,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                },
            )
        }
    ) {
        SwipeRefresh(state =swipeRefreshState, onRefresh = { /*TODO*/ }) {
            if(state.error){
                ScreenEmptyState(
                    image = R.drawable.bg_empty_2,
                    title = "TIdak ada data warga odp",
                    subtitle = state.errorMessage
                )
            }else{
                LazyColumn(content = {
                    item {
                        Spacer(modifier = modifier.height(20.dp))
                    }
                    items(state.data) {
                        ItemWarga(
                            name=it.name,
                            phone = it.phone,
                            nik = it.nik,
                            onClick = {
                                onDetailOdp(it.id)
                            }
                        )
                    }
                })
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenListWarga() {
    PantauWargaTheme {
        ScreenListWarga()
    }
}