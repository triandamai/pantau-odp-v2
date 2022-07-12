package com.trian.data.repository.design

import com.trian.data.models.dto.Citizen
import com.trian.data.models.response.MonitoringResponse
import com.trian.data.utils.network.DataState
import kotlinx.coroutines.flow.Flow

interface OdpRepository {
    suspend fun getMonitoring():Flow<DataState<MonitoringResponse>>

    suspend fun getDetailOdp(id:String):Flow<Pair<Citizen,Boolean>>

    suspend fun getListOdpByVillage():Flow<List<Citizen>>

    suspend fun getListOdpByDistrict():Flow<List<Citizen>>

    suspend fun saveOdp( name:String,
                         religion:String,
                         nik:String,
                         dateOfBirth:String,
                         placeOfBirth:String,
                         address:String,
                         rt:String,
                         rw:String,
                         bloodType:String,
                         profession:String,
                         phoneNUmber:String,
                         tripHistory:String,
                         placeOfTrip:String,
                         isolation:Boolean,
                         safetyNet:Boolean,
                         behavior:Boolean,
                         condition:String,
                         gender:String
    ):Flow<Pair<Boolean,String>>
}