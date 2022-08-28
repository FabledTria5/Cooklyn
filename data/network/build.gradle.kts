plugins {
    id(Plugins.library)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    kotlin(Plugins.serialization) version Versions.kotlin_gradle_version
    id(Plugins.hilt)
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = Config.testRunner
    }

    buildTypes {
        release {
            buildConfigField(
                type = "String",
                name = "API_KEY",
                value = "\"919fb205b3624da79e17d4b60aae39f6\""
            )
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField(
                type = "String",
                name = "API_KEY",
                value = "\"919fb205b3624da79e17d4b60aae39f6\""
            )
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = Config.jvmTargetVersion
    }
}

dependencies {

    // Core
    implementation(dependencyNotation = Dependencies.kotlinCoreKtx)

    // Network
    api(dependencyNotation = Dependencies.retrofit)
    implementation(dependencyNotation = Dependencies.kotlinSerialization)
    implementation(dependencyNotation = Dependencies.kotlinSerializationConverter)
    implementation(dependencyNotation = Dependencies.loggingInterceptor)

    // Dagger Hilt
    implementation(dependencyNotation = Dependencies.hiltAndroid)
    implementation(dependencyNotation = Dependencies.hiltCompose)
    kapt(dependencyNotation = Dependencies.hiltCompiler)

    // Tests
    testApi(dependencyNotation = Dependencies.junit)
    androidTestApi(dependencyNotation = Dependencies.androidJunit)
}