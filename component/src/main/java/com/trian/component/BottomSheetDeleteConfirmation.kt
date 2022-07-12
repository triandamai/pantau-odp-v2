package com.trian.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.picker.SpinnerDatePicker
import com.trian.component.theme.PantauWargaTheme
import com.trian.component.utils.from

@Composable
fun BottomSheetDeleteConfirmation(
    modifier: Modifier = Modifier,
    message:String="",
    onConfirm:()->Unit ={}
) {
  val ctx = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topEnd = 16.dp.from(ctx),
                    topStart = 16.dp.from(ctx)
                )
            )
            .background(MaterialTheme.colors.background)
            .padding(
                vertical = 16.dp.from(ctx),
                horizontal = 16.dp.from(ctx)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .width(36.dp.from(ctx))
                .height(5.dp.from(ctx))
                .clip(RoundedCornerShape(2.5.dp.from(ctx)))
                .background(Color.LightGray)
        ) {

        }
        Spacer(modifier = modifier.height(20.dp.from(ctx)))
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = message)
        }

        Spacer(modifier = modifier.height(30.dp.from(ctx)))
        Button(
            onClick = {

                onConfirm()
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp.from(ctx))
                .height(48.dp.from(ctx))
                .clip(
                    RoundedCornerShape(8.dp.from(ctx))
                )
        ) {
            Text(
                text = "Hapus",
                style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp.from(ctx),
                    lineHeight = 20.sp.from(ctx),
                    color = Color.White
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomSheetDeleteConfirmation() {
    PantauWargaTheme {
        BottomSheetDeleteConfirmation(
            message = "Apakah kamu yakin menghapus data 2?"
        )
    }
}