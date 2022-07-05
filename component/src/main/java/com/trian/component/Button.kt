package com.trian.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.theme.DisableColor
import com.trian.component.theme.DisableContentColor
import com.trian.component.theme.PantauWargaTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowRight24

/**
 * Button
 * author Trian Damai
 * created_at 08/03/22 - 23.29
 * site https://trian.app
 */

@Composable
fun ButtonPrimary(
    text:String="",
    modifier: Modifier =Modifier,
    enabled:Boolean = true,
    onClick:()->Unit={}
) {
    Button(
        enabled=enabled,
        onClick = onClick,
        modifier= modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            disabledBackgroundColor = DisableColor,
            disabledContentColor = DisableContentColor
        ),
        shape = MaterialTheme.shapes.medium

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
fun ButtonSecondary(
    text:String="",
    modifier: Modifier=Modifier,
    enabled:Boolean = true,
    onClick:()->Unit={}
) {

    OutlinedButton(
        enabled=enabled,
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.primary,
            disabledContentColor = DisableContentColor,
        ),
        border = BorderStroke(
            width=1.dp,
            color = if(enabled) MaterialTheme.colors.primary else DisableColor
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
fun ButtonIcon(
    icon: ImageVector = Octicons.ArrowRight24,
    modifier: Modifier=Modifier,
    enabled:Boolean = true,
    tag:String="ini tag",
    onClick:()->Unit={}
) {
    Box(
        modifier = modifier
            .background(
                color = if (enabled) MaterialTheme.colors.primary else DisableColor,
                shape = MaterialTheme.shapes.medium
            )
            .clickable(
                enabled = enabled
            ) { onClick() }
            .wrapContentSize()
            .defaultMinSize(minWidth = 50.dp, minHeight = 50.dp)
            .padding(all = 6.dp),
    ) {
        Icon(
            modifier = modifier.align(Alignment.Center),
            imageVector = icon,
            contentDescription =tag,
            tint = if(enabled) MaterialTheme.colors.onPrimary else DisableContentColor
        )
    }
}

@Composable
fun ButtonSocial(
    text:String="",
    modifier: Modifier=Modifier,
    tag:String="",
    enabled:Boolean = true,
    onClick:()->Unit={}
) {
    OutlinedButton(
        modifier= modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled=enabled,
        contentPadding= PaddingValues(
            vertical = 6.dp
        ),
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.onBackground,
            disabledContentColor = DisableContentColor
        ),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width=1.dp,
            color = if(enabled) MaterialTheme.colors.onSurface else DisableColor
        ),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google_normal),
                contentDescription = "Continue With Google",
                modifier = modifier.align(Alignment.CenterStart)
            )
            Spacer(modifier = modifier.width(6.dp))
            Text(
                text = text,
                style =  MaterialTheme.typography.button,
                modifier = modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun ButtonSmall(
    text: String="",
    textColor:Color=MaterialTheme.colors.onPrimary,
    backgroundColor:Color=MaterialTheme.colors.primary,
    modifier: Modifier=Modifier,
    enabled:Boolean=true,
    onClick: () -> Unit={}

) {
    Box(modifier = modifier
        .clip(MaterialTheme.shapes.medium)
        .background(backgroundColor)
        .clickable(enabled = enabled, onClick = onClick)
        .padding(all = 10.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button.copy(
                color = if(enabled) textColor else DisableContentColor
            ),
            modifier = modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ButtonSmallSecondary(
    text: String="",
    textColor:Color=MaterialTheme.colors.onPrimary,
    backgroundColor:Color=MaterialTheme.colors.primary,
    modifier: Modifier=Modifier,
    enabled:Boolean=true,
    onClick: () -> Unit={}

) {
    Box(modifier = modifier
        .clip(MaterialTheme.shapes.medium)
        .border(
            width = 1.dp,
            shape = MaterialTheme.shapes.medium,
            color = backgroundColor
        )
        .clickable(enabled = enabled, onClick = onClick)
        .padding(all = 10.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button.copy(
                color = if(enabled) textColor else DisableContentColor
            ),
            modifier = modifier.align(Alignment.Center)
        )
    }
}
@Preview
@Composable
fun PreviewComponentButton(){
    PantauWargaTheme() {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(
                    horizontal = 16.dp
                )
        ) {
            ButtonPrimary(text = "Save")
            ButtonPrimary(text = "Save", enabled = false)

            ButtonSecondary("Edit")
            ButtonSecondary("Edit", enabled = false)

            ButtonIcon()

            ButtonIcon(enabled = false)

            ButtonSocial("Continue With Google")
            ButtonSocial("Continue With Google", enabled = false)

            ButtonSmall("Update"){}
            ButtonSmallSecondary("Update"){}
        }
    }
}