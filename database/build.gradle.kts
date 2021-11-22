plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Apps.compileSdk)
    defaultConfig {
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
    }
}

dependencies {
    implementation(project(":network"))
    kapt(Libs.roomCompiler)
    implementation(Libs.koinAndroid)
    implementation(Libs.roomRuntime)
    implementation(Libs.roomKtx)
    implementation(Libs.retrofit)
    implementation(Libs.timber)
    implementation(Libs.retrofitGson)
}