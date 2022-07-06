package com.trian.data.repository.design

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signIn(
        email:String,
        password:String
    ):Flow<Any>
}