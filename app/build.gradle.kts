import com.absurddevs.vespera.VesperaBuildType

plugins {
    alias(libs.plugins.vespera.android.application)
    alias(libs.plugins.vespera.android.application.compose)
    alias(libs.plugins.vespera.android.application.flavors)
    alias(libs.plugins.vespera.android.hilt)
    alias(libs.plugins.baselineprofile)
}

android {
    defaultConfig {
        applicationId = "com.absurddevs.vespera"
        versionCode = 8
        versionName = "0.0.1"

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = VesperaBuildType.DEBUG.applicationIdSuffix
        }

        val release = getByName("release") {
            isMinifyEnabled = true
            applicationIdSuffix = VesperaBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            baselineProfile.automaticGenerationDuringBuild = true
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    namespace = "com.absurddevs.vespera"
}

// Will propagate the navigation graphs as a whole through the app as com.absurddevs.vespera dependency
ksp {
    arg("compose-destinations.codeGenPackageName", "com.absurddevs.vespera")
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.core.data)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.animation.core)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.window)
    implementation(libs.kotlinx.coroutines.guava)

    implementation(libs.compose.destinations.animations.core)
    ksp(libs.compose.destinations.ksp)

    kspTest(libs.hilt.compiler)

    testImplementation(libs.hilt.android.testing)

    androidTestImplementation(libs.hilt.android.testing)
}

baselineProfile {
    automaticGenerationDuringBuild = false
}