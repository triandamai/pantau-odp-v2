package app.trian.coordinator.di


import android.content.Context
import com.trian.data.PantauOdpData
import com.trian.data.coroutines.DefaultDispatcherProvider
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.CexupDatabase
import com.trian.data.local.Persistence
import com.trian.data.local.room.*
import com.trian.data.remote.app.design.MainDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import app.trian.coordinator.BuildConfig
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.functions.FirebaseFunctions
import com.trian.data.repository.OdpRepositoryImpl
import com.trian.data.repository.OfficerRepositoryImpl
import com.trian.data.repository.UserRepositoryImpl
import com.trian.data.repository.design.OdpRepository
import com.trian.data.repository.design.OfficerRepository
import com.trian.data.repository.design.UserRepository

/**
 * Persistence Class
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
    ): MainDataSource = PantauOdpData.getDataSource(
        BuildConfig.BASE_URL,
        BuildConfig.API_KEY
    )

    //repository
    @Provides
    internal fun provideOdpRepository(
        dispatcherProvider: DispatcherProvider,
        mainDataSource: MainDataSource,
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ):OdpRepository = OdpRepositoryImpl(
        dispatcher = dispatcherProvider,
        mainDataSource = mainDataSource,
        firebaseAuth = firebaseAuth,
        firestore = firestore
    )

    @Provides
    internal fun provideUserRepository(
        dispatcherProvider: DispatcherProvider,
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore,
        mainDataSource: MainDataSource
    ):UserRepository=UserRepositoryImpl(
        dispatcherProvider = dispatcherProvider,
        firebaseAuth = firebaseAuth,
        firestore = firestore,
        mainDataSource = mainDataSource
    )

    @Provides
    internal fun provideOfficerRepository(
        dispatcherProvider: DispatcherProvider,
        firestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth,
        firebaseFunctions: FirebaseFunctions
    ):OfficerRepository = OfficerRepositoryImpl(
        dispatcher=dispatcherProvider,
        firebaseAuth = firebaseAuth,
        firestore = firestore,
        function = firebaseFunctions
    )



    //firebase
    @Provides
    internal fun provideFirebaseFunction()= FirebaseFunctions.getInstance()

    @Provides
    fun provideFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirestore():FirebaseFirestore=FirebaseFirestore.getInstance()

    //local database
    @Provides
    internal fun localDatabase(
        @ApplicationContext appContext: Context
    ): CexupDatabase = PantauOdpData.initializeDatabase(appContext)

    @Provides
    internal fun providePersistence(
        @ApplicationContext appContext: Context
    ): Persistence = PantauOdpData.getPersistence(appContext)



    @Provides
    internal fun provideAppSetting(
        appDb: CexupDatabase
    ): AppSettingDao =
        appDb.settingDao()


}