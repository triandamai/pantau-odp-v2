package com.trian.data.repository.design

import com.google.firebase.auth.FirebaseUser
import com.trian.data.models.dto.Officer
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun isUserLoggedIn():Boolean

    suspend fun signIn(
        email:String,
        password:String,
        level:String
    ):Flow<Pair<Boolean,String>>

    suspend fun resetPassword(email:String):Flow<Pair<Boolean,String>>

    suspend fun getProfileOfficer():Flow<Pair<FirebaseUser,Officer>>
}