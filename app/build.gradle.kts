plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
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
    }

    buildTypes {
        //todo: rename
        release {
            isMinifyEnabled = false
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
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
    val composeUiVersion = "1.5.0-alpha02"
    val composeActivityVersion = "1.6.1"
    val composeMaterial3Version = "1.0.1"
    val composeNavigationVersion = "2.5.3"
    val hiltVersion = "2.45"
    val hiltNavigationComposeVersion = "1.0.0"
    val splashScreenVersion = "1.0.0"

    val jUnitVersion = "4.13.2"
    val jUnitExtVersion = "1.1.5"
    val espressoVersion = "3.5.1"

    //JUnit
    testImplementation("junit:junit:$jUnitVersion")
    androidTestImplementation("androidx.test.ext:junit:$jUnitExtVersion")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeUiVersion")
    //Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")

    //Core KTX
    implementation("androidx.core:core-ktx:$coreKtxVersion")

    //Lifecycle KTX
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleKtxVersion")

    //Compose
    implementation("androidx.compose.ui:ui:$composeUiVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")
    implementation("androidx.compose.ui:ui-text-google-fonts:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeUiVersion")
    implementation("androidx.activity:activity-compose:$composeActivityVersion")
    implementation("androidx.compose.material3:material3:$composeMaterial3Version")
    implementation("androidx.navigation:navigation-compose:$composeNavigationVersion")

    //Splash Screen
    implementation("androidx.core:core-splashscreen:$splashScreenVersion")

    //Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion")
}

kapt {
    correctErrorTypes = true
}