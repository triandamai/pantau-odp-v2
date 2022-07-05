package app.trian.user.di


import android.content.Context
import com.trian.data.CexupData
import com.trian.data.coroutines.DefaultDispatcherProvider
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.CexupDatabase
import com.trian.data.local.Persistence
import com.trian.data.local.room.*
import com.trian.data.remote.app.design.MainDataSource
import app.trian.user.repository.MainRepositoryImpl
import app.trian.user.repository.design.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import app.trian.user.BuildConfig

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    internal fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()


    //datasource


    @Provides
    internal fun provideDataSource(
        @ApplicationContext appContext: Context
    ): MainDataSource = CexupData.getDataSource(
        BuildConfig.BASE_URL,
        BuildConfig.API_KEY
    )

    //local database
    @Provides
    internal fun localDatabase(
        @ApplicationContext appContext: Context
    ): CexupDatabase = CexupData.initializeDatabase(appContext)

    @Provides
    internal fun providePersistence(
        @ApplicationContext appContext: Context
    ): Persistence = CexupData.getPersistence(appContext)


    //repository
    @Provides
    fun provideMeasurementRepository(
        dispatcherProvider: DispatcherProvider,
        persistence: Persistence,
        mainDataSource: MainDataSource,
        appSettingDao: AppSettingDao
    ): MainRepository {
        return MainRepositoryImpl(
            dispatcherProvider,
            persistence,
            mainDataSource,
            appSettingDao
        )
    }



    @Provides
    internal fun provideAppSetting(
        appDb: CexupDatabase
    ): AppSettingDao =
        appDb.settingDao()


}