package app.trian.pemantau.repository.design

import com.trian.data.models.response.TodoResponse
import com.trian.data.utils.network.DataState
import kotlinx.coroutines.flow.Flow


/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

interface MainRepository {
    suspend fun getTodos(): Flow<DataState<List<TodoResponse>>>

}