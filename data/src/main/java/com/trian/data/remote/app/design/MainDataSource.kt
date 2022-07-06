package com.trian.data.remote.app.design

import com.trian.data.models.response.DistrictResponse
import com.trian.data.models.response.MonitoringResponse
import com.trian.data.models.response.VillageResponse
import com.trian.data.utils.network.DataState
import retrofit2.Response


interface MainDataSource {
    suspend fun getDistricts(
    ):DataState<List<DistrictResponse>>

    suspend fun getVillages(
    ):DataState<List<VillageResponse>>

    suspend fun getMonitoring(
    ):DataState<List<MonitoringResponse>>
}
