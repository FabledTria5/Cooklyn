plugins {
    id(Plugins.library)
    kotlin(Plugins.android)
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

    implementation(project(":domain"))

    // Core
    implementation(dependencyNotation = Dependencies.kotlinCoreKtx)
    api(dependencyNotation = Dependencies.lifecycleRuntime)
    api(dependencyNotation = Dependencies.appcompat)
    api(dependencyNotation = Dependencies.timber)

    // Compose
    api(dependencyNotation = Dependencies.activityCompose)
    api(dependencyNotation = Dependencies.composeUi)
    api(dependencyNotation = Dependencies.composeMaterial)
    api(dependencyNotation = Dependencies.extendedIcons)

    // Compose Preview
    api(dependencyNotation = Dependencies.composeToolingPreview)
    debugApi(dependencyNotation = Dependencies.composeTooling)
    debugApi(dependencyNotation = Dependencies.customview)
    debugApi(dependencyNotation = Dependencies.poolingContainer)

    // Accompanist
    implementation(dependencyNotation = Dependencies.pager)
}