package com.trian.component.screen.pemantauan

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.theme.DisableContentColor
import com.trian.component.theme.PantauWargaTheme
import com.trian.component.utils.coloredShadow

/**
 * Item Radio Trip
 * author Trian Damai
 * created_at 06/07/22 - 18.01
 * site https://trian.app
 */

@Composable
fun ItemTripSelection(
    modifier: Modifier=Modifier,
    name:String="",
    selected:Boolean=false,
    tag:String="",
    onClick:(Boolean)->Unit={}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .coloredShadow(
                color = DisableContentColor
            )
            .border(
                width = if (selected) 1.dp else 0.dp,
                color = if (selected) MaterialTheme.colors.primary else Color.Transparent,
                shape = MaterialTheme.shapes.medium
            )
            .background(MaterialTheme.colors.surface)
            .padding(
                horizontal = 8.dp,
                vertical = 2.dp
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = selected,
            onCheckedChange = onClick,
            colors = CheckboxDefaults.colors(
                disabledColor = DisableContentColor,
                checkedColor = MaterialTheme.colors.primary,
                uncheckedColor = DisableContentColor
            )
        )
        Text(
            text = name,
            style = MaterialTheme.typography.body2.copy(
                color = MaterialTheme.colors.onBackground,
            )
        )
    }
}

@Preview
@Composable
fun PreviewItemTripSelection() {
    PantauWargaTheme {
        Column {
            ItemTripSelection(
                name = "Perjalanan Luar Negeri",
                tag = "tes"
            )
            Spacer(modifier = Modifier.height(20.dp))
            ItemTripSelection(
                name = "Perjalanan Luar Negeri",
                tag = "tes",
                selected = true
            )
        }
    }
}