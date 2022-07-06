package app.trian.pemantau

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import app.trian.pemantau.ui.pages.detail_odp.routeDetailOdp
import app.trian.pemantau.ui.pages.form_assesment.routeFormAssessment
import app.trian.pemantau.ui.pages.form_odp.routeFormOdp
import app.trian.pemantau.ui.pages.form_success_assessment.routeFormSuccessAssessment
import app.trian.pemantau.ui.pages.form_success_odp.routeFormSuccessOdp
import app.trian.pemantau.ui.pages.form_success_warga.routeSuccessFormWarga
import app.trian.pemantau.ui.pages.form_warga.routeFormWarga
import app.trian.pemantau.ui.pages.list_warga.routeListWarga
import app.trian.pemantau.ui.pages.login.routeLogin
import app.trian.pemantau.ui.pages.main.home.routeHome
import app.trian.pemantau.ui.pages.main.profile.routeProfile
import app.trian.pemantau.ui.pages.onboard.routeOnboard
import app.trian.pemantau.ui.pages.reset_password.routeResetPassword
import app.trian.pemantau.ui.pages.splash.routeSplash
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.trian.component.Routes
import com.trian.component.theme.PantauWargaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * Main Activity
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 28/08/2021
 **/



@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val router = rememberAnimatedNavController()

            //make status bar custom color
            val systemUiController = rememberSystemUiController()

            fun setStatusBar(color: Color, isDark:Boolean){
                systemUiController.setStatusBarColor(
                    color=color,
                    darkIcons = isDark
                )
            }
            val status = MaterialTheme.colors.background

            LaunchedEffect(key1 = Unit, block = {
                setStatusBar(status,true)
            })
            PantauWargaTheme {
                    AnimatedNavHost(
                        navController = router,
                        startDestination = Routes.Splash
                    ) {
                        routeSplash(router)

                        routeOnboard(router)

                        routeResetPassword(router)

                        routeLogin(router)

                        navigation(
                            route = Routes.Main.MAIN,
                            startDestination = Routes.Main.Home
                        ){
                            routeHome(router)

                            routeProfile(router)

                        }

                        routeFormSuccessOdp(router)

                        routeFormSuccessAssessment(router)

                        routeSuccessFormWarga(router)

                        routeFormOdp(router)

                        routeFormAssessment(router)

                        routeFormWarga(router)

                        routeDetailOdp(router)

                        routeListWarga(router)

                    }
            }


        }
    }


}

