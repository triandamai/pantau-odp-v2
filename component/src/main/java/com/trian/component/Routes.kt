package com.trian.component

import androidx.navigation.NavType
import androidx.navigation.navArgument

object Routes {
    const val Splash = "SplashScreen"
    const val Onboard ="OnboardScreen"
    const val ResetPassword = "ResetPasswordScreen"
    const val Login = "Login"
    const val SuccessFormPemantau = "SuccessFormPemantau"
    const val SuccessFormWarga = "SuccessFormWarga"
    const val SuccessFormAssessment = "SuccessFormAssessment"
    const val SuccessFormOdp = "SuccessOdp"

    object Main{
        const val MAIN = "MAIN"
        const val Home = "Home"
        const val Profile = "Profile"
    }

    const val FormUser = "FormUserScreen"
    const val FormOdp = "FormOdpScreen"

    const val ListPemantau = "ListPemantau"
    const val ListWarga = "ListWarga"

    object DetailUser{
        const val argKey = "slug"
        const val route = "Petugas/{$argKey}"
        fun navArg()=listOf(navArgument(argKey) { type = NavType.StringType })
        fun navigate(arg:String) = "$route/$arg"
    }

    object DetailWarga{
        const val argKey = "slug"
        const val route = "Warga/{$argKey}"
        fun navArg()=listOf(navArgument(argKey) { type = NavType.StringType })
        fun navigate(arg:String) = "$route/$arg"
    }

    object FormAssesment{
        const val argKey = "slug"
        const val route = "Assesment/{$argKey}"
        fun navArg()=listOf(navArgument(argKey) { type = NavType.StringType })
        fun navigate(arg:String) = "${route}/$arg"
    }

    object FormWarga{
        const val argKey = "slug"
        const val route = "FormWarga/{$argKey}"
        fun navArg()=listOf(navArgument(argKey) { type = NavType.StringType })
        fun navigate(arg:String) = "${route}/$arg"
    }

}