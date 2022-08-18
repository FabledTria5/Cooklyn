plugins {
    id(Plugins.library)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
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
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = Config.jvmTargetVersion
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.composeCompilerExtensionVersion
    }
}

dependencies {

    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(project(":navigation"))

    // Core
    implementation(dependencyNotation = Dependencies.kotlinCoreKtx)
    implementation(dependencyNotation = Dependencies.firebaseCoroutines)
    api(dependencyNotation = Dependencies.googleAuthentication)

    // Accompanist
    implementation(dependencyNotation = Dependencies.pager)

    // Dagger Hilt
    implementation(dependencyNotation = Dependencies.hiltAndroid)
    kapt(dependencyNotation = Dependencies.hiltCompiler)

    // Tests
    testImplementation(dependencyNotation = Dependencies.junit)
    androidTestImplementation(dependencyNotation = Dependencies.androidJunit)
    androidTestImplementation(dependencyNotation = Dependencies.espressoCore)
    androidTestImplementation(dependencyNotation = Dependencies.junitCompose)
}