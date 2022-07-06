package app.trian.coordinator.repository.design


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