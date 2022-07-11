package com.trian.component.screen.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.trian.component.R
import com.trian.component.form.FormInput
import com.trian.component.form.FormInputClickable
import com.trian.component.form.FormInputWithButton
import com.trian.component.theme.PantauWargaTheme
import com.trian.component.utils.from
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

@Composable
fun ScreenFormPemantau(
    modifier: Modifier=Modifier,
    selectedAddress:String ="",
    onSelectAddress:()->Unit={},
    onBackPressed:()->Unit={},
    onSubmit:(
        name:String,
        email:String,
        nip:String,
        opd:String
    )->Unit={ _,_,_,_-> }
){
    val ctx = LocalContext.current
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var nip by remember {
        mutableStateOf("")
    }
    var opd by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            AppbarBasic(
                title = stringResource(R.string.appbar_title_form_user),
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
                }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_onboard_3),
                    contentDescription = stringResource(R.string.content_description_image_form_user),
                    modifier = Modifier
                        .width(60.dp.from(ctx))
                        .height(75.dp.from(ctx))
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.title_page_form_user),
                    style = MaterialTheme.typography.body1
                )
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {


                FormInput(
                    initialValue = name,
                    placeholder = stringResource(R.string.placeholder_petugas_name),
                    label = stringResource(R.string.label_input_petugas_name),
                    singleLine = true,
                    onChange = {
                        name = it
                    },
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
                FormInputClickable(
                    initialValue = selectedAddress,
                    placeholder = stringResource(R.string.placeholder_petugas_desa),
                    label = stringResource(R.string.label_input_petugas_desa),
                    onClick={
                            onSelectAddress()
                    }
                )
                FormInput(
                    initialValue = nip,
                    placeholder = stringResource(R.string.placeholder_petugas_nip),
                    label = stringResource(R.string.label_input_petugas_nip),
                    singleLine = true,
                    onChange = {
                        nip = it
                    },
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
                FormInput(
                    initialValue = opd,
                    placeholder = stringResource(R.string.placeholder_petugas_opd),
                    label = stringResource(R.string.label_input_petugas_opd),
                    singleLine = true,
                    onChange = {
                               opd = it
                    },
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
                FormInputWithButton(
                    initialValue = email,
                    singleLine = true,
                    placeholder = stringResource(R.string.placeholder_petugas_email),
                    label = stringResource(R.string.label_input_petugas_email),
                    keyboardType = KeyboardType.Email,
                    buttonEnabled = nip.isNotBlank() &&
                            email.isNotBlank() &&
                            name.isNotBlank() &&
                            opd.isNotBlank() &&
                            selectedAddress.isNotBlank(),
                    onChange = {
                               email = it
                    },
                    onSubmit = {
                        onSubmit(
                            name,
                            email,
                            nip,
                            opd
                        )
                    },

                    )
            }

        }
    }
}

@Preview
@Composable
fun PreviewScreenFormPemantau() {
    PantauWargaTheme {
        ScreenFormPemantau()
    }
}