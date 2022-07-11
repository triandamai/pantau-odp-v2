package com.trian.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.models.dto.Citizen
import com.trian.data.models.dto.Officer
import com.trian.data.models.response.MonitoringResponse
import com.trian.data.remote.app.design.MainDataSource
import com.trian.data.repository.design.OdpRepository
import com.trian.data.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class OdpRepositoryImpl(
 private val dispatcher:DispatcherProvider,
 private val mainDataSource: MainDataSource,
 private val firestore: FirebaseFirestore,
 private val firebaseAuth: FirebaseAuth
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

 override suspend fun getDetailOdp(id:String): Flow<Pair<Citizen,Boolean>> = flow {
  try {
      val detailCitizen = firestore.collection("CITIZEN")
       .document(id)
       .get()
       .await()
       .toObject(Citizen::class.java) ?: throw Exception("Data tidak ditemukan!")

     val assesmentExist = firestore.collection("ASSESSMENT")
      .document(id)
      .get()
      .await()
      .exists()

   emit(Pair(detailCitizen,assesmentExist))
  }catch (e:Exception){
   throw e
  }
 }.flowOn(dispatcher.io())

 override suspend fun getListOdpByVillage(): Flow<List<Citizen>> = flow {
  try {
      val currentUser = firebaseAuth.currentUser ?: throw Exception("Anda belum masuk!")

      val user = firestore
       .collection("OFFICER")
       .document(currentUser.uid)
       .get()
       .await()
       .toObject(Officer::class.java) ?: throw Exception("Anda tidak memiliki akses!")

      val data = firestore
       .collection("CITIZEN")
       .whereEqualTo("villageId",user.villageId)
       .get()
       .await()

     val citizen = data.documents.map {
       it.toObject(Citizen::class.java)!!
     }
    emit(citizen)

  }catch (e:Exception){
   throw e
  }
 }.flowOn(dispatcher.io())

 override suspend fun getListOdpByDistrict(): Flow<List<Citizen>> = flow {
  try {
   val currentUser = firebaseAuth.currentUser ?: throw Exception("Anda belum masuk!")

   val user = firestore
    .collection("OFFICER")
    .document(currentUser.uid)
    .get()
    .await()
    .toObject(Officer::class.java) ?: throw Exception("Anda tidak memiliki akses!")

   val data = firestore
    .collection("CITIZEN")
    .whereEqualTo("districtId",user.districtId)
    .get()
    .await()

   val citizen = data.documents.map {
    it.toObject(Citizen::class.java)!!
   }
   emit(citizen)

  }catch (e:Exception){
   throw e
  }
 }.flowOn(dispatcher.io())
}