plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Room
    id("com.google.devtools.ksp")

    // Firebase
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.a221027_sarvesh_mrnelson_project2"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.a221027_sarvesh_mrnelson_project2"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    // Compose
    implementation(platform("androidx.compose:compose-bom:2025.05.01"))

    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.1")
    implementation("androidx.activity:activity-compose:1.10.1")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Navigation
    implementation(
        "androidx.navigation:navigation-compose:2.9.0"
    )

    // ViewModel
    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-compose:2.9.1"
    )

    // ==========================
    // ROOM DATABASE
    // ==========================

    implementation(
        "androidx.room:room-runtime:2.7.1"
    )

    implementation(
        "androidx.room:room-ktx:2.7.1"
    )

    ksp(
        "androidx.room:room-compiler:2.7.1"
    )

    // ==========================
    // RETROFIT
    // ==========================

    implementation(
        "com.squareup.retrofit2:retrofit:2.11.0"
    )

    implementation(
        "com.squareup.retrofit2:converter-gson:2.11.0"
    )

    // ==========================
    // FIREBASE FIRESTORE
    // ==========================

    implementation(
        platform("com.google.firebase:firebase-bom:33.15.0")
    )

    implementation(
        "com.google.firebase:firebase-firestore-ktx"
    )

    // ==========================
    // GPS LOCATION
    // ==========================

    implementation(
        "com.google.android.gms:play-services-location:21.3.0"
    )

    // ==========================
    // TESTING
    // ==========================

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation(
        "androidx.test.ext:junit:1.2.1"
    )

    androidTestImplementation(
        "androidx.test.espresso:espresso-core:3.6.1"
    )

    androidTestImplementation(
        platform("androidx.compose:compose-bom:2025.05.01")
    )

    androidTestImplementation(
        "androidx.compose.ui:ui-test-junit4"
    )

    debugImplementation(
        "androidx.compose.ui:ui-tooling"
    )

    debugImplementation(
        "androidx.compose.ui:ui-test-manifest"
    )
}