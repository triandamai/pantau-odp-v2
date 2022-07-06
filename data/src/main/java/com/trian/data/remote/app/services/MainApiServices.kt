package com.trian.data.remote.app.services

import com.trian.data.models.response.DistrictResponse
import com.trian.data.models.response.MonitoringResponse
import com.trian.data.models.response.VillageResponse
import retrofit2.Response
import retrofit2.http.*

interface MainApiServices {

    /*
    * Body Mass Index
     */
    @GET("rest/kelurahan")
    suspend fun getVillage(
    ): Response<List<VillageResponse>>

    @GET("rest/kecamatan")
    suspend fun getDistricts(
    ): Response<List<DistrictResponse>>

    @POST("rest/pantauan")
    @FormUrlEncoded
    suspend fun getMonitoring(
        @Field("p") p:String=""
    ): Response<List<MonitoringResponse>>


}