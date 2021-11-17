plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(Apps.compileSdk)
    defaultConfig {
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
    }
}

dependencies {
    implementation(Libs.koinAndroid)
    implementation(Libs.koinCompose)
}