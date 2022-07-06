package com.trian.component.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.trian.component.AppbarBasic
import com.trian.component.ItemPetugas
import com.trian.component.theme.PantauWargaTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 13.25
 * site https://trian.app
 */
@Composable
fun ScreenListPetugas(
    modifier: Modifier = Modifier,
    onBackPressed:()->Unit={},
    onDetailOfficer:(slug:String)->Unit={}
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    Scaffold(
        topBar = {
            AppbarBasic(
                title = "List Petugas",
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
            LazyColumn(content = {
                item {
                    ItemPetugas(
                        onClick = {
                            onDetailOfficer("")
                        }
                    )
                }
            })
        }
    }
}

@Preview
@Composable
fun PreviewScreenListPetugas() {
    PantauWargaTheme {
        ScreenListPetugas()
    }
}