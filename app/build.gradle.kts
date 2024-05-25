@Suppress("DSL_SCOPE_VIOLATION") //
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.googleServices)
}

android {
    namespace = "com.studioprojectx"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.studioprojectx"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
  implementation(libs.firebase.auth)
    implementation(platform(libs.firebase.bom))
  implementation(libs.firebase.analytics)
//  implementation(libs.androidx.material.icons.extended)
//  implementation(libs.androidx.room.runtime)
//  ksp(libs.androidx.room.compiler)
//  implementation(libs.androidx.room.ktx)
  implementation(libs.koin.androidx.compose)
  implementation(libs.koin.android)
    implementation(libs.androidx.navigation.compose)
//  implementation(libs.core.ktx)
//  implementation(libs.lifecycle.runtime.ktx)
//  implementation(libs.activity.compose)
//  implementation(platform(libs.compose.bom))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
//  implementation(libs.ui)
//  implementation(libs.ui.graphics)
//  implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
//  androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("io.insert-koin:koin-android:3.5.3")
    implementation("androidx.navigation:navigation-common-ktx:2.7.7")
}

