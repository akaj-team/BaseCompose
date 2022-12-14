plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}
apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

android {
    namespace = "com.ync.basecompose"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ync.basecompose"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    applicationVariants.all {
        when (name) {
            "debug" -> {
                buildConfigField("String", "API_URL", "\"https://api.coingecko.com/api/v3/\"")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.ui:ui:${rootProject.ext.get("composeVersion")}")
    implementation("androidx.compose.ui:ui-tooling-preview:${rootProject.ext.get("composeVersion")}")
    implementation("androidx.compose.material:material:1.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.ext.get("composeVersion")}")
    debugImplementation("androidx.compose.ui:ui-tooling:${rootProject.ext.get("composeVersion")}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${rootProject.ext.get("composeVersion")}")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha03")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")

    //Retrofit
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //Lifecycle
    implementation("androidx.preference:preference-ktx:1.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

    //Compose navigation
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.27.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Coil Image loader
    implementation("io.coil-kt:coil-compose:2.2.2")

    //View Pager
    implementation("com.google.accompanist:accompanist-pager-indicators:0.27.0")

    //Constraint Layout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
}
