package com.trian.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.models.dto.Officer
import com.trian.data.models.response.VillageResponse
import com.trian.data.remote.app.design.MainDataSource
import com.trian.data.repository.design.UserRepository
import com.trian.data.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import logcat.LogPriority
import logcat.logcat

class UserRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val mainDataSource: MainDataSource
):UserRepository {
    override suspend fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun signIn(
        email: String,
        password: String,
        level:String
    ): Flow<Pair<Boolean,String>> = flow{

            val authenticate = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = authenticate.user
            if(user == null){
                firebaseAuth.signOut()
                throw Exception("Cannot find user")
            }
            if(!user.isEmailVerified){
                firebaseAuth.currentUser?.sendEmailVerification()

                firebaseAuth.signOut()
                throw Exception("Email belum di verifikasi silahkan cek kotak masuk email Anda!")
            }

            val data = firestore
                .collection("OFFICER")
                .document(user.uid)
                .get()
                .await()
                .toObject(Officer::class.java)

            if(data == null){
                firebaseAuth.signOut()
                throw Exception("Akun tidak dapat dikenali!")
            }

            if(data.level != level){
                firebaseAuth.signOut()
                throw Exception("Anda tidak memiliki akses!")
            }
        emit(Pair(true,"Login Berhasil!"))
    }.flowOn(dispatcherProvider.io())

    override suspend fun changePassword(newPassword: String): Flow<Pair<Boolean, String>> = flow<Pair<Boolean, String>> {
        try {
           val user = firebaseAuth.currentUser
               ?: throw Exception("Tidak dapat merubah password!")

            user.updatePassword(newPassword).await()
            firebaseAuth.signOut()
            emit((Pair(true,"Berhasil merubah password, silahkan masuk kembali")))
        }catch (e:Exception){
            throw e
        }
    }.flowOn(dispatcherProvider.io())

    override suspend fun resetPassword(
        email: String
    ): Flow<Pair<Boolean, String>> = flow {
        try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            emit(Pair(true,"Reset password sudah di kirim ke $email Anda."))
        }catch (e:Exception){
            throw e
        }


    }.flowOn(dispatcherProvider.io())

    override suspend fun getProfileOfficer(): Flow<Pair<FirebaseUser,Officer>> = flow {
        try {
            val user = firebaseAuth.currentUser ?: throw Exception("User belum login")
            val officer = firestore.collection("OFFICER")
                .document(user.uid)
                .get().await().toObject(Officer::class.java) ?: throw Exception("Gagal mengambil informasi user. Coba lagi beberapa saat!")
            emit(Pair(user,officer))
        }catch (e:Exception){
            throw e
        }
    }.flowOn(dispatcherProvider.io())

    override suspend fun getListVillage(): Flow<List<VillageResponse>> = flow<List<VillageResponse>> {
        val currentUser = firebaseAuth.currentUser ?: throw Exception("Anda tidak mempunyai akses")

        val user = firestore
            .collection("OFFICER")
            .document(currentUser.uid)
            .get()
            .await()
            .toObject(Officer::class.java) ?: throw Exception("Anda tida mempunyai akses")



        when(val result = mainDataSource.getVillages()){
            is DataState.onData -> {
                emit(result.data.filter {
                    it.id.contains(user.districtId)
                })
            }
            is DataState.onFailure -> throw Exception("Tidak ditemukan data!")

            DataState.onLoading -> throw Exception("Tidak ditemukan data!")
        }
    }.flowOn(dispatcherProvider.io())

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }
}