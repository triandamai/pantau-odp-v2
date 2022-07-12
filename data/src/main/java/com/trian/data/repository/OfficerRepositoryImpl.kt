package com.trian.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.gson.Gson
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.models.dto.Officer
import com.trian.data.models.response.CreateOfficerResponse
import com.trian.data.repository.design.OfficerRepository
import com.trian.data.utils.utils.getRandomPassword
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import org.json.JSONObject

class OfficerRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val function:FirebaseFunctions
):OfficerRepository {
    override suspend fun saveOfficerPemantau(
        email:String,
        opd:String,
        nip:String,
        level:String,
        name:String,
        villageId:String,
        villageName:String
    ): Flow<Pair<Boolean, String>> = flow{
        try {

            val currentUser = firebaseAuth.currentUser ?: throw Exception("Anda belum masuk!")

            val user = firestore.collection("OFFICER")
                .document(currentUser.uid)
                .get().await().toObject(Officer::class.java) ?: throw Exception("Anda belum masuk!.")

            val jsonObj = JSONObject()
            jsonObj.put("name",name)
            jsonObj.put("nip",nip)
            jsonObj.put("opd",opd)
            jsonObj.put("email",email)
            jsonObj.put("villageId",villageId)
            jsonObj.put("villageName",villageName)
            jsonObj.put("districtId",user.districtId)
            jsonObj.put("districtName",user.districtName)

            val createOfficer = function.getHttpsCallable("createPemantau")
                .call(jsonObj)
                .continueWith {
                    val data = it.result.data as HashMap<*, *>
                     data
                }

            val result = createOfficer.await()

            val success = result["success"] as Boolean
            val message = result["message"] as String

            if(success){
                val uid = result["data"] as String
                emit(Pair(true,uid))
            }else{
                emit(Pair(false,message))
            }
        }catch (e:Exception){
            throw e
        }
    }.flowOn(dispatcher.io())

    override suspend fun getDetailPemantau(
        uid: String
    ): Flow<Officer> = flow {
        try {
            val officer = firestore.collection("OFFICER")
                .document(uid)
                .get()
                .await()
                .toObject(Officer::class.java) ?: throw Exception("Tidak dapat menemukan pemantau")

            emit(officer)

        }catch (e:Exception){

        }
    }.flowOn(dispatcher.io())

    override suspend fun getListPemantau(
    ): Flow<List<Officer>> = flow {
        val user = firebaseAuth.currentUser ?: throw Exception("Anda belum masuk!")

        val officer = firestore.collection("OFFICER")
            .document(user.uid)
            .get()
            .await()
            .toObject(Officer::class.java) ?: throw Exception("Anda belum masuk!")

        val listPemantau = firestore.collection("OFFICER")
            .whereEqualTo("districtId",officer.districtId)
            .whereEqualTo("level","PEMANTAU")
            .get()
            .await()

        val toModel = listPemantau.documents.map {
            it.toObject(Officer::class.java)!!
        }.filter {
                (it.uid != officer.uid)
            }

        if(toModel.isEmpty()){
            throw Exception("Tidak ditemukan data, Anda bisa menambahkan pada halaman utama")
        }
        emit(toModel)

    }.flowOn(dispatcher.io())

    override suspend fun deletePemantau(uid: String): Flow<Pair<Boolean, String>> = flow<Pair<Boolean, String>> {
        try {
            firestore.collection("OFFICER")
                .document(uid)
                .delete()
                .await()
            emit(Pair(true,"Berhasil menghapus data!"))
        }catch (e:Exception){
            throw e
        }
    }.flowOn(dispatcher.io())

}