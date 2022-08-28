import org.gradle.api.JavaVersion

object Config {
    const val compileSdk = 33
    const val minSdk = 24
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"

    const val applicationId = "dev.fabled.cooklyn"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val jvmTargetVersion = "11"
    const val composeCompilerExtensionVersion = "1.3.0"

    val javaVersion = JavaVersion.VERSION_11
}

object Versions {

    // Core
    const val gradle_version = "7.2.2"
    const val kotlin_gradle_version = "1.7.10"
    const val google_services_version = "4.3.13"
    const val ksp_version = "1.7.10-1.0.6"
    const val gradle_dependencies_version = "0.42.0"

    // Kotlin
    const val kotlin_version = "1.8.0"
    const val lifecycle_runtime_version = "2.6.0-alpha01"
    const val kotlin_serialization_version = "1.4.0"
    const val desugar_version = "1.1.5"

    // Firebase
    const val firebase_auth_version = "21.0.7"
    const val fire_store_version = "24.2.2"
    const val coroutines_core_version = "1.6.4"
    const val google_authentication_version = "20.2.0"

    // Design
    const val appCompat_version = "1.6.0-beta01"

    // Compose
    const val compose_version = "1.3.0-beta01"
    const val compose_material_version = "1.0.0-beta01"
    const val activity_compose_version = "1.5.0"
    const val accompanist_version = "0.26.2-beta"
    const val coil_version = "2.2.0"
    const val hilt_compose_version = "1.0.0"
    const val customview_version = "1.2.0-alpha01"
    const val pooling_container_version = "1.0.0"
    const val constraint_layout_version = "1.1.0-alpha03"

    // Dagger Hilt
    const val hilt_android_version = "2.43.2"

    // Testing
    const val jUnit_version = "4.13.2"
    const val android_jUnit_version = "1.1.4-alpha07"
    const val espresso_core_version = "3.5.0-alpha07"

    // Timber
    const val timber_version = "5.0.1"

    // Room
    const val room_version = "2.5.0-alpha03"

    // Network
    const val retrofit_version = "2.9.0"
    const val kotlin_serialization_converter_version = "0.8.0"
    const val logging_interceptor_version = "5.0.0-alpha.10"

    // Preferences
    const val preferences_datastore_version = "1.0.0"

    // Jsoup
    const val jsoup_version = "1.15.2"

    // Pagination
    const val paging_version = "3.2.0-alpha02"
    const val paging_compose_version = "1.0.0-alpha16"
}

object Dependencies {

    // Core
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle_version}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_gradle_version}"
    const val hiltAndroidPlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_android_version}"
    const val googleServices = "com.google.gms:google-services:${Versions.google_services_version}"
    const val gradleDependencies =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.gradle_dependencies_version}"

    // Kotlin
    const val kotlinCoreKtx = "androidx.core:core-ktx:${Versions.kotlin_version}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_runtime_version}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_runtime_version}"
    const val javaInject = "javax.inject:javax.inject:1"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_core_version}"
    const val kotlinSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlin_serialization_version}"
    const val desugar = "com.android.tools:desugar_jdk_libs:${Versions.desugar_version}"

    // Firebase
    const val firebaseAuth =
        "com.google.firebase:firebase-auth-ktx:${Versions.firebase_auth_version}"
    const val fireStore =
        "com.google.firebase:firebase-firestore-ktx:${Versions.fire_store_version}"
    const val firebaseCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines_core_version}"
    const val googleAuthentication = "com.google.android.gms:play-services-auth:${Versions.google_authentication_version}"

    // Design
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat_version}"
    const val coil = "io.coil-kt:coil-compose:${Versions.coil_version}"

    // Compose
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose_version}"
    const val composeMaterial =
        "androidx.compose.material3:material3:${Versions.compose_material_version}"
    const val composeToolingPreview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose_version}"
    const val activityCompose =
        "androidx.activity:activity-compose:${Versions.activity_compose_version}"
    const val composeUiUtil = "androidx.compose.ui:ui-util:${Versions.compose_version}"
    const val junitCompose = "androidx.compose.ui:ui-test-junit4:${Versions.compose_version}"
    const val customview = "androidx.customview:customview:${Versions.customview_version}"
    const val poolingContainer =
        "androidx.customview:customview-poolingcontainer:${Versions.pooling_container_version}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraint_layout_version}"
    const val extendedIcons =
        "androidx.compose.material:material-icons-extended:${Versions.compose_version}"

    // Accompanist
    const val systemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist_version}"
    const val composeNavigation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist_version}"
    const val pager = "com.google.accompanist:accompanist-pager:${Versions.accompanist_version}"
    const val pagerIndicators =
        "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanist_version}"
    const val flowLayout =
        "com.google.accompanist:accompanist-flowlayout:${Versions.accompanist_version}"

    // Dagger Hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt_android_version}"
    const val hiltCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hilt_compose_version}"
    const val hiltCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hilt_android_version}"

    // Paging
    const val pagingRuntime = "androidx.paging:paging-runtime:${Versions.paging_version}"
    const val pagingCommon = "androidx.paging:paging-common:${Versions.paging_version}"
    const val pagingCompose = "androidx.paging:paging-compose:${Versions.paging_compose_version}"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room_version}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room_version}"
    const val roomPaging = "androidx.room:room-paging:${Versions.room_version}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"

    // Preferences
    const val preferencesDatastore =
        "androidx.datastore:datastore-preferences:${Versions.preferences_datastore_version}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val kotlinSerializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlin_serialization_converter_version}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor_version}"
    const val jsoup = "org.jsoup:jsoup:${Versions.jsoup_version}"

    // Testing
    const val junit = "junit:junit:${Versions.jUnit_version}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.android_jUnit_version}"
    const val espressoCore =
        "androidx.test.espresso:espresso-core:${Versions.espresso_core_version}"

    // Logging
    const val timber = "com.jakewharton.timber:timber:${Versions.timber_version}"
}

object Plugins {
    const val application = "com.android.application"
    const val library = "com.android.library"
    const val android = "android"
    const val kotlin = "kotlin"
    const val kapt = "kapt"
    const val ksp = "com.google.devtools.ksp"
    const val hilt = "dagger.hilt.android.plugin"
    const val googleServices = "com.google.gms.google-services"
    const val serialization = "plugin.serialization"
    const val gradleVersions = "com.github.ben-manes.versions"
}