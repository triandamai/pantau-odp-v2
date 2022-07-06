package com.trian.data.utils


import com.google.gson.Gson
import com.trian.data.utils.network.DataState
import logcat.LogPriority
import logcat.logcat
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */
suspend fun <T> safeApiCall(call: suspend () -> Response<T>): DataState<T> {

    try {
        val response = call.invoke()
        //mark response as success with code between 200 until 299
        if(response.isSuccessful){
            return DataState.onData((response.body()) as T)
        }else if(response.code() in 400..403){
            val gson = Gson()
            val json = response.errorBody()?.string()
            if(json.isNullOrBlank()){
                return DataState.onFailure("Failed to authenticate")
            }
            val error = gson.fromJson(json,ErrorBody::class.java)
            return DataState.onFailure(error.message)
        }
        return  DataState.onFailure(response.message())
    } catch (e: Exception) {
        e.printStackTrace()
        return when (e) {
            is IOException ->{
                DataState.onFailure(e.message ?: "")
            }
            is ConnectException -> {
                 DataState.onFailure(com.trian.data.utils.network.CONNECT_EXCEPTION)
            }
            is UnknownHostException -> {
                 DataState.onFailure(com.trian.data.utils.network.UNKNOWN_HOST_EXCEPTION)
            }
            is SocketTimeoutException -> {
                 DataState.onFailure(com.trian.data.utils.network.SOCKET_TIME_OUT_EXCEPTION)
            }
            is HttpException -> {
                 DataState.onFailure(com.trian.data.utils.network.UNKNOWN_NETWORK_EXCEPTION)
            }
            else -> {
                 DataState.onFailure(e.message ?:"")
            }
        }
    }
}
data class ErrorBody(
    var message:String
)

