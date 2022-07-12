package com.trian.component

import androidx.navigation.NavType
import androidx.navigation.navArgument

object Routes {
    const val Splash = "SplashScreen"
    const val Onboard ="OnboardScreen"
    const val ResetPassword = "ResetPasswordScreen"
    const val Login = "Login"

    object SuccessFormPemantau{
        const val argKey = "slug"
        const val route = "SuccessFormPemantau/{$argKey}"
        fun navArg()=listOf(navArgument(argKey) { type = NavType.StringType })
        fun navigate(arg:String) = "Petugas/$arg"
    }
    const val SuccessFormWarga = "SuccessFormWarga"
    const val SuccessFormAssessment = "SuccessFormAssessment"
    object SuccessFormOdp {
        const val argKey = "slug"
        const val route = "SuccessODP/{$argKey}"
        fun navArg()=listOf(navArgument(argKey) { type = NavType.StringType })
        fun navigate(arg:String) = "SuccessODP/$arg"
    }

    object Main{
        const val MAIN = "MAIN"
        const val Home = "Home"
        const val Profile = "Profile"
    }

    const val FormUser = "FormUserScreen"
    const val FormOdp = "FormOdpScreen"

    const val ListPemantau = "ListPemantau"
    const val ListOdp = "ListOdp"


    object DetailUser{
        const val argKey = "slug"
        const val route = "Petugas/{$argKey}"
        fun navArg()=listOf(navArgument(argKey) { type = NavType.StringType })
        fun navigate(arg:String) = "Petugas/$arg"
    }

    object DetailOdp{
        const val argKey = "slug"
        const val route = "Odp/{$argKey}"
        fun navArg()=listOf(navArgument(argKey) { type = NavType.StringType })
        fun navigate(arg:String) = "Odp/$arg"
    }

    object FormAssesment{
        const val argKey = "slug"
        const val route = "Assessment/{$argKey}"
        fun navArg()=listOf(navArgument(argKey) { type = NavType.StringType })
        fun navigate(arg:String) = "Assessment/$arg"
    }

    object FormWarga{
        const val argKey = "slug"
        const val route = "FormWarga/{$argKey}"
        fun navArg()=listOf(navArgument(argKey) { type = NavType.StringType })
        fun navigate(arg:String) = "FormWarga/$arg"
    }

}