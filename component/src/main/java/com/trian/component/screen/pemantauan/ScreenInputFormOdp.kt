package com.trian.component.screen.pemantauan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.theme.PantauWargaTheme

/**
 *
 * author Trian Damai
 * created_at 03/04/22 - 14.19
 * site https://trian.app
 */
@Composable
fun ScreenNameTransaction(
    modifier: Modifier =Modifier,
    itemModels:List<ItemDetailTransactionModel> = listOf(),
    inputContent:@Composable ()->Unit={},
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(
                vertical = 30.dp,
                horizontal = 30.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Column {
            itemModels.forEach {
                if(it.isWithAmount){
                    ItemAddTransaction(
                        transactionType = it.value,
                        amountName = it.amountLabel,
                        amount = it.amountValue
                    )
                }else{
                    ItemAddTransaction(
                        name = it.label,
                        value = it.value
                    )
                }
                Spacer(modifier = modifier.height(16.dp))
            }

        }
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            inputContent.invoke()
        }
    }
}

@Preview
@Composable
fun PreviewScreenNameTransaction() {
    PantauWargaTheme {
        Column(modifier=Modifier.background(MaterialTheme.colors.background)) {
            ScreenNameTransaction()
        }
    }
}