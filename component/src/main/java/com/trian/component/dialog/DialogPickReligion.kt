package com.trian.component.dialog

import android.location.Address
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.trian.component.theme.PantauWargaTheme




@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DialogPickReligion(
    show:Boolean=false,
    modifier: Modifier=Modifier,
    onDismiss:()->Unit={},
    onSelect:(String)->Unit={}
) {
    val listReligion = listOf<String>(
        "Islam",
        "Katolik",
        "Protestant",
        "Hindhu",
        "Budha",
        "Konghucu"
    )
    if(show) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = modifier
                    .padding(
                        horizontal = 30.dp
                    )
            ) {
                Column(
                    modifier = modifier
                        .fillMaxHeight(
                            fraction = 0.5f
                        )
                        .clip(MaterialTheme.shapes.large)
                        .background(color = MaterialTheme.colors.surface)
                        .padding(
                            all = 20.dp,
                        )
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Select date transaction",
                            style = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.onBackground,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Spacer(modifier = modifier.height(16.dp))
                    LazyColumn(
                        content = {
                            items(listReligion){

                                Religion(name = it){
                                    onSelect(it)
                                }
                            }
                        }
                    )

                }
            }
        }
    }
}

@Composable
fun Religion(
    modifier: Modifier=Modifier,
    name:String,
    onClick:()->Unit={}
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 2.dp)
            .clickable { onClick() },
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = name,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
        Divider()
    }
}

@Preview
@Composable
fun PreviewPickReligion() {
    PantauWargaTheme {
        DialogPickReligion(
            show = true
        )
    }
}