package com.trian.component.screen.user


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
import androidx.compose.ui.res.painterResource
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

/**
 *
 * author Trian Damai
 * created_at 09/03/22 - 15.27
 * site https://trian.app
 */
@Composable
fun ScreenSuccessUser(
    modifier: Modifier = Modifier,
    onDismiss:()->Unit={}
) {
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
            Icon(imageVector = Octicons.X24, contentDescription = "Close")
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
                painter = painterResource(id = R.drawable.bg_congratulations),
                contentDescription = ""
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = "Congratulation!",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = modifier.height(20.dp))
            Text(
                text = "User is added successfully to the app",
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSurface
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.height(30.dp))
            Column {
                Column(
                    modifier = modifier

                ) {
                    Column {
                        Text(
                            text = "Name",
                            style = MaterialTheme.typography.caption.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = "Trian Damai",
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Spacer(modifier = modifier.height(30.dp))
                    Column {
                        Text(
                            text = "email",
                            style = MaterialTheme.typography.caption.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = "trian@trian.app",
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Spacer(modifier = modifier.height(30.dp))
                    Column {
                        Text(
                            text = "Penugasan",
                            style = MaterialTheme.typography.caption.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = "Purwokerto selatan",
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
                                text = "Nik",
                                style = MaterialTheme.typography.caption.copy(
                                    color = MaterialTheme.colors.onSurface
                                )
                            )
                            Spacer(modifier = modifier.height(10.dp))
                            Text(
                                text = "43255472743",
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
                                text = "Date of Birth",
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
                                text = "Alamat",
                                style = MaterialTheme.typography.caption.copy(
                                    color = MaterialTheme.colors.onSurface
                                )
                            )
                            Spacer(modifier = modifier.height(10.dp))
                            Text(
                                text = "Purwokerto Selatan",
                                style = MaterialTheme.typography.body1
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
fun PreviewScreenSuccessUser() {
    PantauWargaTheme {
        ScreenSuccessUser()
    }
}