package com.trian.data.remote.app

import com.trian.data.remote.app.design.MainDataSource
import com.trian.data.remote.app.services.MainApiServices
import com.trian.data.utils.safeApiCall


class MainDataSourceImpl(
    private val apiServices: MainApiServices
): MainDataSource {
    override suspend fun getDistricts(
    )= safeApiCall {apiServices.getDistricts()}

    override suspend fun getVillages(
    )= safeApiCall { apiServices.getVillage()}

    override suspend fun getMonitoring(
    )= safeApiCall { apiServices.getMonitoring()}

}