package app.trian.pemantau


import android.app.Application
import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import androidx.hilt.work.HiltWorkerFactory
import androidx.multidex.MultiDex
import androidx.work.Configuration
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import es.dmoral.toasty.Toasty
import logcat.AndroidLogcatLogger
import logcat.LogPriority
import javax.inject.Inject

/**
 * Base Application
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 28/08/2021
 */

@HiltAndroidApp
class MainApplication : Application(),Configuration.Provider{

    @Inject lateinit var workerFactory: HiltWorkerFactory

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        AndroidLogcatLogger.installOnDebuggableApp(this, minPriority = LogPriority.VERBOSE)

        FirebaseApp.initializeApp(this)
        FirebaseCrashlytics.getInstance()
        try {

            //configure toast
            val typeface: Typeface? = ResourcesCompat.getFont(this, com.trian.component.R.font.gt_walsheim_medium)
            Toasty.Config.getInstance()
                .tintIcon(true)
                .setToastTypeface(typeface!!)
                .setTextSize(16)
                .allowQueue(true)
                .setGravity(1,0,1)
                .supportDarkTheme(true)
                .apply()
            AndroidLogcatLogger.installOnDebuggableApp(this, minPriority = LogPriority.VERBOSE)
        }catch (e:Exception){

        }

    }


    override fun getWorkManagerConfiguration(): Configuration=
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}

