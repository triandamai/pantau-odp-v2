/**
 * Copyright Trian Damai 2022 triandamai@gmail.com
 *
 * */
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        mavenCentral()

    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        jcenter()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.google.com")
    }
}

rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "Pantau-ODP"
include(":app_coordinator")
include(":app_pemantau")
include(":data")
include(":component")


