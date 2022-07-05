package com.trian.data

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.trian.data.local.CexupDatabase
import com.trian.data.local.Persistence
import com.trian.data.remote.app.MainDataSourceImpl
import com.trian.data.remote.app.design.MainDataSource
import com.trian.data.remote.app.services.MainApiServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CexupData() {
    companion object{
        private val REQUEST_TIMEOUT = 5


        fun getDataSource(
            baseUrl: String,
            apiKey: String
        ):MainDataSource{
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor { chain ->
                    val origin = chain.request()
                    val request = origin.newBuilder()
                        .header(
                            "x-api-key",
                            apiKey
                        )
                        .method(origin.method, origin.body)
                        .build()
                    chain.proceed(request)
                }
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.MINUTES)
                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.MINUTES)
                .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.MINUTES)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://${baseUrl}/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            val service = retrofit.create(MainApiServices::class.java)

            return MainDataSourceImpl(service)
        }

        fun getPersistence(appContext: Context):Persistence{
            return Persistence(provideSharePreferences(appContext))
        }

        fun initializeDatabase(appContext: Context): CexupDatabase =
            Room.databaseBuilder(
                appContext,
                CexupDatabase::class.java,
                CexupDatabase.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()


        private fun provideSharePreferences(
            appContext: Context
        ): SharedPreferences {
            return appContext.getSharedPreferences("fcab4de", Context.MODE_PRIVATE)
        }
    }
}