package com.trian.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogLoading(
    modifier:Modifier=Modifier,
    show:Boolean,
    onDismiss:()->Unit
){
    if(show){
        Dialog(onDismissRequest = onDismiss) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier= modifier
                        .size(100.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                ) {
                    CircularProgressIndicator()
                }

        }
    }
}