package com.trian.data.repository.design

import com.trian.data.models.response.MonitoringResponse
import com.trian.data.utils.network.DataState
import kotlinx.coroutines.flow.Flow

interface OdpRepository {
    suspend fun getMonitoring():Flow<DataState<MonitoringResponse>>
}