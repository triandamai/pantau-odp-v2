package com.trian.component.picker

import android.annotation.SuppressLint
import android.widget.NumberPicker
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.trian.component.theme.PantauWargaTheme
import com.trian.component.utils.from
import java.time.LocalDate
import kotlin.math.pow
import kotlin.math.sqrt

/**
 *
 * author Trian Damai
 * created_at 12/07/22 - 10.44
 * site https://trian.app
 */

@Composable
fun BottomSheetSpinnerDatePicker(
    modifier: Modifier = Modifier,
    onSubmit: (day: Int, month: Int, year: Int) -> Unit = { _, _, _ -> },
    onClose: () -> Unit = {}
) {
    var dayState by remember {
        mutableStateOf(1)
    }
    var monthState by remember {
        mutableStateOf(1)
    }
    var yearState by remember {
        mutableStateOf(2022)
    }
    val ctx = LocalContext.current


    Column(
        modifier = modifier

            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .clip(
                RoundedCornerShape(
                    topEnd = 16.dp.from(ctx),
                    topStart = 16.dp.from(ctx)
                )
            )
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
            Text(text = "Choose date of birth")
        }
        SpinnerDatePicker(
            day = dayState,
            month = monthState,
            year = yearState,
            onChange = { day, month, year ->

                dayState = day
                monthState = month
                yearState = year
            }
        )
        Button(
            onClick = {
                onSubmit(
                    dayState,
                    monthState,
                    yearState
                )

                onClose()
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
                text = "Save",
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
@SuppressLint("NewApi")
@Composable
fun SpinnerDatePicker(
    modifier: Modifier =Modifier,
    day: Int=1,
    month:Int=1,
    year:Int=1970,
    onChange:(day:Int,month:Int,year:Int)->Unit={
            _,_,_->
    }
){
    val ctx = LocalContext.current

    val listMonth = arrayOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "Augustus",
        "September",
        "October",
        "November",
        "December"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp.from(ctx), horizontal = 16.dp.from(ctx))
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AndroidView(
                factory = {
                    NumberPicker(it).apply {
                        minValue = 1
                        maxValue = 31
                    }
                },
                update = {
                    it.setOnValueChangedListener { _, _, newVal ->
                        onChange(newVal,month,year)
                    }
                }
            )
            AndroidView(
                factory = {
                    NumberPicker(it).apply {
                        minValue=1
                        maxValue = listMonth.size
                        displayedValues = listMonth
                    }
                },
                update = {
                    it.setOnValueChangedListener { _, _, newVal ->
                        onChange(day,newVal,year)
                    }
                }
            )
            AndroidView(
                factory = {
                    NumberPicker(it).apply {
                        minValue = 1970
                        maxValue = LocalDate.now().year
                    }
                },
                update = {
                    it.setOnValueChangedListener { _, _, newVal ->
                        onChange(day,month,newVal)
                    }
                }
            )

        }
    }
}
@Preview
@Composable
fun PreviewSpinnerDatePicker(){
    PantauWargaTheme {
        SpinnerDatePicker()
    }
}