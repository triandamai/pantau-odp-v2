package com.trian.data.repository.design

import com.trian.data.models.dto.Officer
import kotlinx.coroutines.flow.Flow


interface OfficerRepository {
    suspend fun saveOfficerPemantau(
        email:String,
        opd:String,
        nip:String,
        level:String,
        name:String,
        villageId:String,
        villageName:String
    ):Flow<Pair<Boolean,String>>

    suspend fun getDetailPemantau(
        uid:String=""
    ):Flow<Officer>

    suspend fun updatePemantau(
        uid:String,
        name:String,
        opd:String,
        nip:String,
        villageId: String,
        villageName: String
    ):Flow<Pair<Boolean,String>>

    suspend fun getListPemantau(
    ):Flow<List<Officer>>

    suspend fun deletePemantau(
        uid: String
    ):Flow<Pair<Boolean,String>>

}