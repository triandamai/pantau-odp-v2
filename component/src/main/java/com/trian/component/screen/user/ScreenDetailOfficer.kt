package com.trian.component.screen.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.AppbarBasic
import com.trian.component.ButtonSecondary
import com.trian.component.ButtonSmallSecondary
import com.trian.component.DottedLine
import com.trian.component.theme.PantauWargaTheme
import com.trian.data.models.dto.Officer
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

/**
 *
 * author Trian Damai
 * created_at 10/03/22 - 13.25
 * site https://trian.app
 */
data class DetailOfficerUIState(
    var loading:Boolean = false,
    var error:Boolean = false,
    var errorMessage:String = "",

    var officer: Officer = Officer()
)
@Composable
fun ScreenDetailOfficer(
    modifier: Modifier = Modifier,
    state:DetailOfficerUIState=DetailOfficerUIState(),
    onBackPressed:()->Unit={},
    onEdit:()->Unit = {},
    onDelete:()->Unit = {}
) {
    Scaffold(
        topBar = {
            AppbarBasic(
                title = "Detail Petugas",
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
        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    vertical = 30.dp,
                    horizontal = 30.dp
                )
        ) {
            Column(
                modifier = modifier
                    .align(Alignment.TopCenter)

            ) {
                Column {
                    Text(
                        text = "Nama",
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = state.officer.name,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = modifier.height(30.dp))
                Column {
                    Text(
                        text = "NIP",
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = state.officer.nip,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = modifier.height(30.dp))
                Column {
                    Text(
                        text = "OPD",
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = state.officer.opd,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = modifier.height(30.dp))
                Row (
                    modifier= modifier
                        .height(IntrinsicSize.Min)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column {
                        Text(
                            text = "Type",
                            style = MaterialTheme.typography.caption.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = if(state.officer.level == "PEMANTAU") "Pemantau" else "Kordinator",
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Column(
                        modifier = modifier
                            .padding(vertical = 4.dp)
                            .fillMaxHeight()
                            .width(1.dp)
                            .background(
                                color = MaterialTheme.colors.onSurface
                            )
                    ) {

                    }
                    Column {
                        Text(
                            text = "Created at",
                            style = MaterialTheme.typography.caption.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = "04-16-19",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
                Spacer(modifier = modifier
                    .height(30.dp))
                DottedLine(
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = modifier
                    .height(30.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Penugasan",
                            style = MaterialTheme.typography.caption.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = if(state.officer.level == "PEMANTAU")state.officer.villageName else state.officer.districtName,
                            style = MaterialTheme.typography.body1
                        )
                    }
                    ButtonSmallSecondary(
                        text = "Edit",
                        backgroundColor = MaterialTheme.colors.primary,
                        textColor = MaterialTheme.colors.primary,
                        onClick = {
                            onEdit()
                        }
                    )
                }

                Spacer(modifier = modifier
                    .height(24.dp))
                ButtonSecondary(
                    text = "Hapus",
                    color = Color.Red,
                    onClick = {
                        onDelete()
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenDetailPetugas() {
    PantauWargaTheme {
        ScreenDetailOfficer(
            state = DetailOfficerUIState(
                loading = false,
                error = false,
                officer = Officer(
                    name = "Trian Damai",
                    nip = "657890",
                    opd = "657890",
                    level = "PEMANTAU",
                    villageName = "Purwkerto Selatan"
                )
            )
        )
    }
}