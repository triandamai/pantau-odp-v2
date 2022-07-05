package app.trian.pemantau.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

/**
 * Main Activity
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 13/10/2021
 **/

@Module
@InstallIn(SingletonComponent::class, ActivityComponent::class)
object CommonModule {
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }



}