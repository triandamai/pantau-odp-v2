package com.trian.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.models.dto.Officer
import com.trian.data.repository.design.OfficerRepository
import com.trian.data.utils.utils.getRandomPassword
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class OfficerRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
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

            val randomPassword = getRandomPassword()
            //TODO: should use firebase function http
            val authenticate = firebaseAuth.createUserWithEmailAndPassword(
                email,
                randomPassword
            ).await()


            val updateName = userProfileChangeRequest {
                displayName = name
            }

            firebaseAuth.currentUser?.updateProfile(updateName)
            firebaseAuth.currentUser?.sendEmailVerification()

            val officer = Officer(
                uid = "${authenticate.user?.uid}",
                email = email,
                name = name,
                villageId = villageId,
                villageName = villageName,
                districtId = user.districtId,
                districtName = user.districtName,
                nip = nip,
                opd = opd,
                level = level
            )

            firestore.collection("OFFICER")
                .document(authenticate.user!!.uid)
                .set(
                    officer,
                    SetOptions.merge()
                )

            emit(Pair(true,"Berhasil menambah petugas"))
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
            .get()
            .await()
        val toModel = listPemantau.documents.map {
            it.toObject(Officer::class.java)!!
        }

        emit(toModel)

    }.flowOn(dispatcher.io())

}