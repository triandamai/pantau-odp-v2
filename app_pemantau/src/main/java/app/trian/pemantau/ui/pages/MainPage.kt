package app.trian.pemantau.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.trian.pemantau.ui.theme.CexupTheme
import com.trian.data.models.response.TodoResponse
import com.trian.data.utils.network.DataState

@Composable
fun MainPage(
    datas: DataState<List<TodoResponse>>
){
    LazyColumn(content = {
        when(datas){
            is DataState.onData -> {
                //show data
                items(datas.data){todo->
                    Text(text = todo.name )
                }
            }
            is DataState.onFailure -> {
                //error when request failed
                item {
                    Text(text = datas.message)
                }
            }
            DataState.onLoading -> {
                //show loading
                item {
                    Column {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    })

}

@Composable
@Preview
fun PreviewMainPage(){
    CexupTheme {
        MainPage(
            datas = DataState.onLoading
        )
    }
}