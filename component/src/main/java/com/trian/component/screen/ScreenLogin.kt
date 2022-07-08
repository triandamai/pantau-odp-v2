package com.trian.component.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.AppbarAuth
import com.trian.component.R
import com.trian.component.form.FormInput
import com.trian.component.form.FormInputWithButton
import com.trian.component.theme.PantauWargaTheme
import com.trian.component.theme.fontFamily

@Composable
fun ScreenLogin(
    modifier: Modifier=Modifier,
    onSignIn:(email:String,password:String)->Unit={ _,_-> },
    onResetPassword:()->Unit={}
) {
    val ctx = LocalContext.current
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    //annotation for text forget password
    val annotationStringForgotPassword = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.onBackground,
                fontFamily = fontFamily
            )
        ){
            append(ctx.getString(R.string.forgot_password))
        }
        append(" ")
        pushStringAnnotation(
            tag = "reset_here",
            annotation = "reset_here"
        )
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.primary,
                fontFamily = fontFamily
            )
        ){
            append(ctx.getString(R.string.reset_here))
        }
        pop()
    }

    Scaffold(
        topBar = {
            AppbarAuth(navigationText = stringResource(R.string.btn_sign_up)){

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
                    contentDescription = stringResource(R.string.content_description_imaeg_page_login),
                    modifier = modifier.fillMaxWidth(fraction = 0.5f)
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.login_to_your_account),
                    style = MaterialTheme.typography.body1
                )
                FormInput(
                    initialValue = email,
                    onChange = {
                        email=it
                    },
                    placeholder = stringResource(R.string.placeholder_email),
                    label = stringResource(R.string.label_input_email),
                    singleLine = true,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
                FormInputWithButton(
                    initialValue = password,
                    onChange = {
                        password = it
                    },
                    placeholder = stringResource(R.string.placeholder_password),
                    label = stringResource(R.string.label_input_password),
                    showPasswordObsecure = true,
                    singleLine = true,
                    onSubmit = {
                        onSignIn(
                            email,
                            password
                        )
                    },
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Send,
                    buttonEnabled = email.isNotBlank() && password.isNotBlank()
                )
            }

            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ClickableText(
                    text = annotationStringForgotPassword,
                    onClick = {
                            offset->
                        annotationStringForgotPassword.getStringAnnotations(
                            tag = "reset_here",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let { _ ->
                            onResetPassword()

                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenLogin(){
    PantauWargaTheme {
        ScreenLogin()
    }
}