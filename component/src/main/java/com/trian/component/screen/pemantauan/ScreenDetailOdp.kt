package com.trian.component.screen.pemantauan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.*
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

data class DetailOdpUIState(
    var loading: Boolean = false,
    var error: Boolean = false,
    var errorMessage: String = "",
    var uid: String = "",
    var name: String = "",
    var address: String = "",
    var phone: String = "",
    var placeOfBirth: String = "",
    var dateOfBirth: String = "",
    var alreadyHasAssesment: Boolean = false,
)

@Composable
fun ScreenDetailOdp(
    modifier: Modifier = Modifier,
    state: DetailOdpUIState = DetailOdpUIState(),
    onBackPressed: () -> Unit = {},
    onEdit: () -> Unit = {},
    onDelete: () -> Unit = {},
    onAssessment: () -> Unit = {}

) {
    Scaffold(
        topBar = {
            AppbarBasic(
                title = stringResource(R.string.txt_title_page_detail_citizen),
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
                        text = state.name,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = modifier.height(30.dp))
                Column {
                    Text(
                        text = "Alamat",
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = state.address,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = modifier.height(30.dp))
                Column {
                    Text(
                        text = "No.Telp",
                        style = MaterialTheme.typography.caption.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = state.phone,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = modifier.height(30.dp))
                Row(
                    modifier = modifier
                        .height(IntrinsicSize.Min)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Tempat lahir",
                            style = MaterialTheme.typography.caption.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = state.placeOfBirth,
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
                            text = "Tanggal lahir",
                            style = MaterialTheme.typography.caption.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = state.dateOfBirth,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
                Spacer(
                    modifier = modifier
                        .height(30.dp)
                )
                DottedLine(
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(
                    modifier = modifier
                        .height(30.dp)
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Assesment",
                            style = MaterialTheme.typography.caption.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = if (state.alreadyHasAssesment) "Sudah di survei" else "Belum di survei",
                            style = MaterialTheme.typography.body1
                        )
                    }
                    ButtonSmallSecondary(
                        text = stringResource(R.string.txt_btn_assessment),
                        backgroundColor = MaterialTheme.colors.primary,
                        textColor = MaterialTheme.colors.primary,
                        onClick = {
                            onAssessment()
                        }
                    )
                }

                Spacer(
                    modifier = modifier
                        .height(30.dp)
                )
                ButtonSecondary(
                    text = stringResource(id = R.string.txt_btn_edit),
                    onClick = {
                        onEdit()
                    }
                )
                Spacer(
                    modifier = modifier
                        .height(24.dp)
                )
                ButtonSecondary(
                    text = stringResource(id = R.string.txt_btn_delete),
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
fun PreviewScreenDetailOdp() {
    PantauWargaTheme {
        ScreenDetailOdp()
    }
}