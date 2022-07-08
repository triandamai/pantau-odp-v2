/**
 * Copyright Trian Damai 2022 triandamai@gmail.com
 *
 * */
plugins {
    id("com.android.library")
    id("com.google.gms.google-services")
    kotlin("android")
    kotlin("kapt")

}


android{
    compileSdk = 31
    defaultConfig {
        minSdk = 23
        targetSdk = 30

    }
}

dependencies {

    with(Libs.AndroidX.Room){
        api(roomRuntime)
        implementation(roomPaging)
        implementation(roomKtx)
        kapt(roomCompiler)
        testImplementation(roomTesting)

    }

    with(Libs.Com.Squareup.Retrofit2){
        implementation(retrofit)
        implementation(gsonFactory)
    }

    with(Libs.Com.Squareup.Okhttp3){
        implementation(okhttp)
        implementation(loggingInterceptor)
        implementation(mockWebServer)
    }

    implementation(Libs.JodaTime.jodaTime)

    implementation(Libs.Com.Squareup.Logcat.logcat)

    //firebase
    with(Libs.Com.Google.Firebase) {
        implementation(platform(bom))
        implementation(auth)
        implementation(firestore)
        implementation(storage)
        implementation(messaging)
        implementation(crashlytics)
        implementation(analytics)

    }


    //allow use await() in firebase task
    with(Libs.Org.Jetbrains.Kotlinx) {
        implementation(googlePlayKotlinCoroutine)
    }


}
