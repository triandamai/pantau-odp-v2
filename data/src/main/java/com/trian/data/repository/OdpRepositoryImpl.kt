package com.trian.data.repository

import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.models.response.MonitoringResponse
import com.trian.data.remote.app.design.MainDataSource
import com.trian.data.repository.design.OdpRepository
import com.trian.data.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class OdpRepositoryImpl(
 private val dispatcher:DispatcherProvider,
 private val mainDataSource: MainDataSource
) :OdpRepository{
 override suspend fun getMonitoring(
 ): Flow<DataState<MonitoringResponse>> = flow {
   emit(DataState.onLoading)
   emit(when(val result = mainDataSource.getMonitoring()){
    is DataState.onData -> {
     if(result.data.isEmpty()) {
      DataState.onFailure("No data")
     }else{
      DataState.onData(result.data.first())
     }

    }
    is DataState.onFailure -> result
    DataState.onLoading ->DataState.onLoading
   })
 }.flowOn(dispatcher.io())
}