package com.trian.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.models.dto.Officer
import com.trian.data.repository.design.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import logcat.LogPriority
import logcat.logcat

class UserRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
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
                throw Exception("Anda tidak memiliki akses!")
            }
        emit(Pair(true,"Login Berhasil!"))
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
}