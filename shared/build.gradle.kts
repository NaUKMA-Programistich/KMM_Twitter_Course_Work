plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    kotlin("native.cocoapods")
    id("com.android.library")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../ios/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    val ktorVersion = "2.2.2"

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Key - value
                implementation("com.russhwolf:multiplatform-settings-no-arg:1.0.0-RC")

                // DI
                implementation("io.insert-koin:koin-core:3.3.2")

                // ViewModel
                implementation("com.adeo:kviewmodel:0.12")

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

                // Ktor
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-json:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                // Datetime
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

                // Encode/Decode
                implementation("com.squareup.okio:okio:3.2.0")
                // Crypto
                implementation("com.appmattus.crypto:cryptohash:0.10.1")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
    }
}

android {
    namespace = "ua.ukma.edu.programistich.kmmtwitter"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}
