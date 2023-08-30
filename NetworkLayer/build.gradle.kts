plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yasser.networklayer"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        buildConfigField("String","BASE_URL","\"https://dev.btech.com/rest/en/V1/\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    android {
        buildFeatures {
            buildConfig = true
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    setupRetrofit(this)
}

fun  setupRetrofit(configuration:DependencyHandlerScope) {
    val retrofit_version = "2.6.0"
    configuration.implementation("com.squareup.retrofit2:retrofit:$retrofit_version")

    configuration.implementation( "com.squareup.retrofit2:converter-gson:$retrofit_version")
    configuration.implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    configuration.implementation("com.squareup.okhttp3:logging-interceptor:3.9.0")

}