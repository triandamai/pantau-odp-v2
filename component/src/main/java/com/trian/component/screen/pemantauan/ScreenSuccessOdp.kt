package com.trian.component.screen.pemantauan


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.component.ButtonSmallSecondary
import com.trian.component.DottedLine
import com.trian.component.theme.PantauWargaTheme
import compose.icons.Octicons
import compose.icons.octicons.X24
import com.trian.component.R
import com.trian.component.theme.HexToJetpackColor
import com.trian.component.theme.listGradient

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 15.27
 * site https://trian.app
 */
data class SuccessOdpUIState(
    var loading:Boolean=false,
    var error:Boolean=false,
    var errorMessage:String="",
    var citizenName:String="",
    var address:String="",
    var dateOfBirth:String=""
)
@Composable
fun ScreenSuccessOdp(
    modifier: Modifier = Modifier,
    state:SuccessOdpUIState=SuccessOdpUIState(),
    onDismiss:()->Unit={}
) {
    BackHandler {
        onDismiss()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        IconToggleButton(
            checked = false,
            onCheckedChange = {
                onDismiss()
            },
            modifier = modifier.align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Octicons.X24,
                contentDescription = "Close"
            )
        }

        Column(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.bg_congratulations
                ),
                contentDescription = ""
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = "Success!",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = "Berhasil menambahkan data warga ODP",
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSurface
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.height(30.dp))
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
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
                    .padding(all = 20.dp)
            ) {
                Column {
                    Column {
                        Text(
                            text = "ODP Name",
                            style = MaterialTheme.typography.caption.copy(
                                color = Color.White
                            )
                        )
                        Spacer(modifier = modifier.height(6.dp))
                        Text(
                            text = state.citizenName,
                            style = MaterialTheme.typography.body2.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Spacer(modifier = modifier.height(30.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Alamat",
                                style = MaterialTheme.typography.caption.copy(
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = modifier.height(6.dp))
                            Text(
                                text = state.address,
                                style = MaterialTheme.typography.body2.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Column(
                            modifier = modifier
                                .height(40.dp)
                                .width(1.dp)
                                .background(
                                    MaterialTheme.colors.surface
                                ),
                        ) {

                        }
                        Column {
                            Text(
                                text = "Date",
                                style = MaterialTheme.typography.caption.copy(
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = modifier.height(6.dp))
                            Text(
                                text = state.dateOfBirth,
                                style = MaterialTheme.typography.body2.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun PreviewScreenSuccessOdp() {
    PantauWargaTheme {
        ScreenSuccessOdp()
    }
}