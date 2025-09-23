plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlinx.serialization.plugin)
}

android {
    namespace = "com.it.userdirectory"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.it.userdirectory"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding=true
        dataBinding=true
        buildConfig=true

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.lifecycle.live.data)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.extension)
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.junit.ktx)
    ksp(libs.dagger.hilt.compiler)
    ksp(libs.androidx.lifecycle.compiler)
    implementation(libs.io.coil.kt)
    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)
    implementation(libs.coroutine.service)
    implementation(libs.coroutine.test)
    implementation(libs.squareup.retrofit)
    implementation(libs.google.gson)
    implementation(libs.retrofit.converter)
    implementation(libs.retrofit.okhttp)
    implementation(libs.squareup.logging)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.lifecycle.common)
    implementation(libs.lifecycle.runtime)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}