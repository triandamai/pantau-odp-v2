package com.trian.data.repository.design

import kotlinx.coroutines.flow.Flow

interface PetugasRepository {
    suspend fun savePetugas():Flow<Any>

    suspend fun deletePetugas():Flow<Any>

    suspend fun updatePetugas():Flow<Any>
}