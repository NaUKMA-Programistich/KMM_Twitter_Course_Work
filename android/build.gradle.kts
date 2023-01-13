plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "ua.ukma.edu.programistich.kmmtwitter.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "ua.ukma.edu.programistich.kmmtwitter.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    // KMM
    implementation(project(":shared"))

    // Compose
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.compose.foundation:foundation:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")

    implementation("com.adeo:kviewmodel:0.12")

    // Twitter auth
    implementation("com.twitter.sdk.android:twitter-core:3.0.0") {
        isTransitive = true
    }

    // DI
    implementation("io.insert-koin:koin-android:3.3.2")
}
