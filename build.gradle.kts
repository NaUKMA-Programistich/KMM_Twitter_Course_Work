plugins {
    id("com.android.application").version("8.0.0-beta01").apply(false)
    id("com.android.library").version("8.0.0-beta01").apply(false)
    kotlin("android").version("1.7.20").apply(false)
    kotlin("multiplatform").version("1.7.20").apply(false)
    kotlin("plugin.serialization").version("1.7.20").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
