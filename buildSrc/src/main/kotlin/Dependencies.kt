object Apps {
    const val compileSdk = 31
    const val minSdk = 21
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "3.5.0"
    const val kotlin = "1.5.21"
    const val appcompat = "1.3.1"
    const val ktx = "1.7.0"
    const val material = "1.4.0"
    const val constraintlayout = "2.1.1"
    const val composeVersion = "1.0.5"
    const val composeActivity = "1.4.0"
    const val composeNavigation = "2.4.0-beta02"
    const val koin = "3.1.3"
    const val coroutinesCore = "1.5.0"

    /* test */
    const val junit = "4.12"

    /* android test */
    const val extJunit = "1.1.3"
    const val espresso = "3.4.0"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.composeVersion}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.composeVersion}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    const val composeRuntimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.composeVersion}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.coroutinesCore}"
}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
}

object AndroidTestLibs {
    const val junit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}