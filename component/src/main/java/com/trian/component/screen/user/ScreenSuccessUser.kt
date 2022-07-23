package com.trian.component.screen.user


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
data class SuccessOfficerUIState(
    var loading:Boolean=false,
    var error:Boolean = false,
    var errorMessage:String="",
    var name:String="n/a",
    var email:String="n/a",
    var placeOfAssignment:String="n/a",
    var nip:String="",
    var opd:String="",
    var level:String="n/a"
)

@Composable
fun ScreenSuccessUser(
    modifier: Modifier = Modifier,
    state:SuccessOfficerUIState=SuccessOfficerUIState(),
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
            Icon(imageVector = Octicons.X24, contentDescription = stringResource(R.string.content_description_close))
        }

        if(state.loading){
            Column(
                modifier = modifier.fillMaxWidth().fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }

        if(state.error){
            Column(
                modifier = modifier.fillMaxWidth().fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Oops..!",
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = modifier.height(20.dp))
                Text(
                    text = state.errorMessage,
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        if(!state.loading && !state.error){
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
                    text = stringResource(R.string.txt_message_screen_success_user),
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.txt_message_subtitle_screen_success_user),
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
                                text = stringResource(R.string.label_success_user_name),
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
                                text = stringResource(R.string.label_success_user_email),
                                style = MaterialTheme.typography.caption.copy(
                                    color = MaterialTheme.colors.onSurface
                                )
                            )
                            Spacer(modifier = modifier.height(10.dp))
                            Text(
                                text = state.email,
                                style = MaterialTheme.typography.body1
                            )
                        }
                        Spacer(modifier = modifier.height(30.dp))
                        Column {
                            Text(
                                text = stringResource(R.string.label_success_user_place_of_duty),
                                style = MaterialTheme.typography.caption.copy(
                                    color = MaterialTheme.colors.onSurface
                                )
                            )
                            Spacer(modifier = modifier.height(10.dp))
                            Text(
                                text = state.placeOfAssignment,
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
                                    text = stringResource(R.string.label_success_user_opd),
                                    style = MaterialTheme.typography.caption.copy(
                                        color = MaterialTheme.colors.onSurface
                                    )
                                )
                                Spacer(modifier = modifier.height(10.dp))
                                Text(
                                    text = state.opd,
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
                                    text = stringResource(R.string.label_success_user_level),
                                    style = MaterialTheme.typography.caption.copy(
                                        color = MaterialTheme.colors.onSurface
                                    )
                                )
                                Spacer(modifier = modifier.height(10.dp))
                                Text(
                                    text = state.level,
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
                                    text = stringResource(R.string.label_success_user_nip),
                                    style = MaterialTheme.typography.caption.copy(
                                        color = MaterialTheme.colors.onSurface
                                    )
                                )
                                Spacer(modifier = modifier.height(10.dp))
                                Text(
                                    text = state.nip,
                                    style = MaterialTheme.typography.body1
                                )
                            }
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