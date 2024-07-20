plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.safeargsKotlin)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.example.book"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.book"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }

}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.logging)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.coil)
    implementation(libs.glide)
    implementation(libs.navigationFragment)
    implementation(libs.navigationUi)
    implementation(libs.lifecycleViewmodel)
    implementation(libs.lifecycleLivedata)
    implementation(libs.lifecycleRuntime)
    implementation(libs.kotlinxCoroutines)

    implementation(libs.koinAndroid)
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverterMoshi)
    implementation(libs.retrofitConverterGson)
    implementation(libs.rxjava2Adapter)
    implementation(libs.moshiKotlin)
    ksp(libs.moshiKotlinKsp)
    implementation(libs.pagingRuntimeKtx)
    implementation(libs.pagingCommonKtx)

    implementation(libs.signaturePad)
    implementation(libs.swiperefreshlayout)
    implementation(platform(libs.firebaseBom))
    implementation(platform(libs.firebaseAnalytics))
    implementation(platform(libs.crashlytics))
    implementation(libs.playServicesMaps)

    implementation(libs.eventBus)
}