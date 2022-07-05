package com.trian.component.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.AppbarAuth
import com.trian.component.R
import com.trian.component.form.FormInputWithButton
import com.trian.component.theme.PantauWargaTheme

@Composable
fun ScreenResetPassword(
    modifier:Modifier=Modifier,
    onLogin:()->Unit={},
    onSubmit:()->Unit={}
) {
    Scaffold(
        topBar = {
            AppbarAuth(navigationText = stringResource(R.string.btn_login)){
                onLogin()
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.bg_onboard_2),
                    contentDescription = stringResource(R.string.content_description_change_password),
                    modifier = modifier.fillMaxWidth(fraction = 0.5f)
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.title_reset_password),
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = stringResource(R.string.subtitle_reset_password),
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                FormInputWithButton(
                    placeholder = stringResource(R.string.placeholder_email),
                    label = stringResource(R.string.label_input_email),
                    singleLine = true,
                    onSubmit = {
                        onSubmit()
                    },
                    keyboardType = KeyboardType.Email,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenResetPassword() {
    PantauWargaTheme {
        ScreenResetPassword()
    }
}