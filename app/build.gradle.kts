plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.vespera.android.application)
}

android {
    defaultConfig {
        applicationId = "com.absurddevs.vespera"
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {

        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.absurddevs.vespera"
}

dependencies {
    implementation(libs.kotlinx.coroutines.guava)
}

//dependencyGuard {
//    configuration("prodReleaseRuntimeClasspath")
//}