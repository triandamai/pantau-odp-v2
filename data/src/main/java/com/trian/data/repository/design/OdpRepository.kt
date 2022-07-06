package com.trian.data.repository.design

import kotlinx.coroutines.flow.Flow

interface OdpRepository {
    suspend fun saveOdp():Flow<Any>

    suspend fun saveAssessment():Flow<Any>

    suspend fun saveWarga():Flow<Any>
}