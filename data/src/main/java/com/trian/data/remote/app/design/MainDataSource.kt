package com.trian.data.remote.app.design

import com.trian.data.models.response.BaseResponse
import com.trian.data.models.response.TodoResponse
import com.trian.data.utils.network.DataState


interface MainDataSource {
    suspend fun getTodos(
        page:Int,
        size:Int
    ):DataState<BaseResponse<List<TodoResponse>>>
}
