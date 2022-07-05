package com.trian.data.remote.app.services

import com.trian.data.models.response.BaseResponse
import com.trian.data.models.response.TodoResponse
import retrofit2.Response
import retrofit2.http.*

interface MainApiServices {

    /*
    * Body Mass Index
     */
    @GET("api/v1/todos")
    suspend fun getTodos(
        @Query("size") size:Int,
        @Query("page") page:Int
    ): Response<BaseResponse<List<TodoResponse>>>


}