plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    kotlin("kapt")
}

android {
    namespace = "com.ewingelen.chatter"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ewingelen.chatter"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
            isMinifyEnabled = false
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
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
    val firebaseBomVersion = "31.5.0"
    val preferencesDataStoreVersion = "1.0.0"
    val splashScreenVersion = "1.0.0"
    val timberVersion = "5.0.1"
    val coilComposeVersion = "2.3.0"

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

    //Splash Screen
    implementation("androidx.core:core-splashscreen:$splashScreenVersion")

    //Timber
    implementation("com.jakewharton.timber:timber:$timberVersion")

    //Data store
    implementation("androidx.datastore:datastore-preferences:$preferencesDataStoreVersion")

    //Coil
    implementation("io.coil-kt:coil-compose:$coilComposeVersion")
}

kapt {
    correctErrorTypes = true
}