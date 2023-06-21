plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    kotlin("kapt")
    id("com.google.devtools.ksp") version "1.8.20-1.0.11"
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.ewingelen.chatter"
    compileSdk = 33

    splits {
        abi {
            isEnable = true
            reset()
            include("armeabi-v7a", "arm64-v8a", "mips", "x86", "x86_64")
            isUniversalApk = false
        }
    }

    defaultConfig {
        applicationId = "com.ewingelen.chatter"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        defaultConfig {
            resourceConfigurations.addAll(listOf("en", "ru", "ua"))
        }

        manifestPlaceholders["crashlyticsCollectionEnabled"] = false
        manifestPlaceholders["analyticsCollectionEnabled"] = false
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            manifestPlaceholders["crashlyticsCollectionEnabled"] = true
            manifestPlaceholders["analyticsCollectionEnabled"] = true
        }

        debug {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val coreKtxVersion = "1.10.0"
    val lifecycleKtxVersion = "2.6.1"
    val lifecycleRuntimeComposeVersion = "2.6.1"
    val composeBom = "androidx.compose:compose-bom:2023.05.01"
    val composeActivityVersion = "1.6.1"
    val composeNavigationVersion = "2.5.3"
    val hiltVersion = "2.45"
    val hiltNavigationComposeVersion = "1.0.0"
    val firebaseBomVersion = "32.1.1"
    val kspVersion = "1.8.20-1.0.11"
    val roomVersion = "2.5.1"
    val preferencesDataStoreVersion = "1.0.0"
    val splashScreenVersion = "1.0.0"
    val timberVersion = "5.0.1"
    val coilComposeVersion = "2.3.0"
    val glideVersion = "4.15.1"
    val glideComposeVersion = "1.0.0-alpha.3"
    val oneSignalVersion = "[4.0.0, 4.99.99]"

    val jUnitVersion = "4.13.2"
    val jUnitExtVersion = "1.1.5"
    val espressoVersion = "3.5.1"

    //JUnit
    testImplementation("junit:junit:$jUnitVersion")
    androidTestImplementation("androidx.test.ext:junit:$jUnitExtVersion")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    //Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")

    //Core KTX
    implementation("androidx.core:core-ktx:$coreKtxVersion")

    //Lifecycle KTX
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleKtxVersion")

    //Compose
    implementation(platform(composeBom))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:$composeActivityVersion")
    implementation("androidx.navigation:navigation-compose:$composeNavigationVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleRuntimeComposeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    testImplementation(platform(composeBom))

    //Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:$firebaseBomVersion"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")

    //KSP
    implementation("com.google.devtools.ksp:symbol-processing-api:$kspVersion")

    //Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    //Paging 3 Integration
    //implementation "androidx.room:room-paging:$room_version"
    //Test helpers
    //testImplementation "androidx.room:room-testing:$room_version"

    //Splash Screen
    implementation("androidx.core:core-splashscreen:$splashScreenVersion")

    //Timber
    implementation("com.jakewharton.timber:timber:$timberVersion")

    //Data store
    implementation("androidx.datastore:datastore-preferences:$preferencesDataStoreVersion")

    //Coil
    implementation("io.coil-kt:coil-compose:$coilComposeVersion")

    //Glide
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    implementation("com.github.bumptech.glide:compose:$glideComposeVersion")
    kapt("com.github.bumptech.glide:ksp:$glideVersion")

    //OneSignal
    implementation("com.onesignal:OneSignal:$oneSignalVersion")

    //Agora
//    implementation("io.agora.rtc:full-sdk:4.0.1")
    implementation("io.agora.rtc:full-rtc-basic:3.6.2")
}

kapt {
    correctErrorTypes = true
}