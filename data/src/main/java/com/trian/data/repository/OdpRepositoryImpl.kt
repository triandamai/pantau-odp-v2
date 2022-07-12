package com.trian.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.models.dto.Assessment
import com.trian.data.models.dto.Citizen
import com.trian.data.models.dto.Officer
import com.trian.data.models.response.MonitoringResponse
import com.trian.data.remote.app.design.MainDataSource
import com.trian.data.repository.design.OdpRepository
import com.trian.data.utils.network.DataState
import com.trian.data.utils.utils.getTodayTimeStamp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.OffsetDateTime

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
      .filter {
       (it.uid != user.uid)
      }
   if(citizen.isEmpty()){
    throw Exception("Anda bisa menambahkan data odp di halaman utama")
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
   }.filter {
    (it.uid != user.uid)
   }
   if(citizen.isEmpty()){
    throw Exception("Anda bisa menambahkan data odp di halaman utama")
   }

   emit(citizen)

  }catch (e:Exception){
   throw e
  }
 }.flowOn(dispatcher.io())

 override suspend fun saveOdp(
  name: String,
  religion: String,
  nik: String,
  dateOfBirth: String,
  placeOfBirth: String,
  address: String,
  rt: String,
  rw: String,
  bloodType: String,
  profession: String,
  phoneNUmber: String,
  tripHistory: String,
  placeOfTrip: String,
  isolation: Boolean,
  safetyNet: Boolean,
  behavior: Boolean,
  condition: String,
  gender:String
 ): Flow<Pair<Boolean, String>> = flow {
    try {
        val user = firebaseAuth.currentUser ?: throw Exception("Anda belum masuk!")


        val officer = firestore
            .collection("OFFICER")
            .document(user.uid)
            .get()
            .await()
            .toObject(Officer::class.java) ?: throw Exception("Anda belum masuk!")




        val uid = firestore.collection("CITIZEN").document().id

        val citizen = Citizen(
            uid = uid,
            name= name,
            placeOfBirth = placeOfBirth,
            dateOfBirth = dateOfBirth,
            address = address,
            religion = religion,
            districtId = officer.districtId,
            districtName = officer.districtName,
            villageId = officer.villageId,
            villageName = officer.villageName,
            proffesion =  profession,
            gender = gender,
            identityNumber = nik,
            phoneNumber = phoneNUmber,
            officerNip = officer.nip,
            officerName = officer.name,
            bloodType = bloodType,
            createdAt = getTodayTimeStamp(),
            updatedAt = getTodayTimeStamp()
        )
        val assessment = Assessment(
            citizenUid = uid,
            tripHistory=tripHistory,
            placeOfTrip = placeOfTrip,
            isolation=isolation,
            safetyNet=safetyNet,
            behaviour = behavior,
            condition = condition,
            createdAt = getTodayTimeStamp(),
            updateAt = getTodayTimeStamp()

        )
        firestore.collection("CITIZEN")
            .document(uid)
            .set(citizen, SetOptions.merge())
            .await()
        firestore.collection("ASSESSMENT")
            .document(uid)
            .set(assessment, SetOptions.merge())
            .await()
        emit(Pair(true,uid))
    }catch (e:Exception){
        throw e
    }
 }.flowOn(dispatcher.io())
}