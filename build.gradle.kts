plugins {
    id("com.android.application").version("8.0.0-alpha11").apply(false)
    id("com.android.library").version("8.0.0-alpha11").apply(false)
    id("com.google.devtools.ksp").version("1.7.20-1.0.8").apply(false)
    kotlin("android").version("1.7.20").apply(false)
    kotlin("multiplatform").version("1.7.20").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
