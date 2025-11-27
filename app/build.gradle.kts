plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.testskills"
    compileSdk = 34 // Downgraded to avoid warnings

    defaultConfig {
        applicationId = "com.example.testskills"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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


    /*packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }*/

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/INDEX.LIST"
            excludes += "META-INF/*.kotlin_module"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/io.netty.versions.properties"
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
}

configurations.all {
    exclude(group = "com.intellij", module = "annotations")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":core:common"))


    implementation(project(":core:di"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature:home"))
    implementation(project(":feature:player"))
    implementation(libs.navigation.compose)



    // Hilt
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("com.google.dagger:hilt-android:2.51.1")

    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.navigation:navigation-compose:2.7.2") // if not in catalog

}

