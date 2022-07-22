package com.trian.component.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.AppbarBasic
import com.trian.component.theme.PantauWargaTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import com.trian.component.R
import com.trian.component.form.FormInput
import com.trian.component.form.FormInputWithButton
import com.trian.component.utils.toastError

/**
 *
 * author Trian Damai
 * created_at 22/07/22 - 10.07
 * site https://trian.app
 */
@Composable
fun ScreenChangePassword(
    modifier:Modifier=Modifier,
    onBackPressed:()->Unit,
    onSubmit:(String)->Unit
) {

    val ctx = LocalContext.current


    var newPassword by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }

    fun validateFirst(){
        if(newPassword.isBlank()){
            ctx.toastError("Mohon isi password baru!")
            return
        }

        if(confirmPassword.isBlank()){
            ctx.toastError("Password tidak cocok!")
            return
        }

        if(confirmPassword != newPassword){
            ctx.toastError("Password tidak cocok!")
            return
        }

        onSubmit(newPassword)
    }

    Scaffold(
        topBar = {
            AppbarBasic(
                title = stringResource(com.trian.component.R.string.txt_title_change_password),
                navigationIcon = {
                    IconToggleButton(checked = false, onCheckedChange = {
                        onBackPressed()
                    }) {
                        Icon(
                            imageVector = Octicons.ArrowLeft24,
                            contentDescription = stringResource(id = R.string.content_description_icon_back))
                    }
                }
            ){

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
                    contentDescription = stringResource(R.string.conten_description_image_change_password),
                    modifier = modifier.fillMaxWidth(fraction = 0.5f)
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(R.string.title_change_password),
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.subtitle_page_change_password),
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSurface
                    )
                )
                FormInput(
                    initialValue = newPassword,
                    onChange = {
                        newPassword=it
                    },
                    placeholder = stringResource(R.string.placeholed_new_password),
                    label = stringResource(R.string.labe_input_new_password),
                    showPasswordObsecure = true,
                    singleLine = true,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                )
                FormInputWithButton(
                    initialValue = confirmPassword,
                    onChange = {
                        confirmPassword=it
                    },
                    placeholder = stringResource(R.string.placeholder_confirm_password),
                    label = stringResource(R.string.label_input_confirm_password),
                    showPasswordObsecure = true,
                    singleLine = true,
                    keyboardType = KeyboardType.Password,
                ){
                    validateFirst()
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewScreenChangePassword() {
    PantauWargaTheme {
        ScreenChangePassword(
            onBackPressed = {},
            onSubmit = {}
        )
    }
}