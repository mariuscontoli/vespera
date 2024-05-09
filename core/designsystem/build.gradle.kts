plugins {
    alias(libs.plugins.vespera.android.library)
    alias(libs.plugins.vespera.android.library.compose)
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.absurddevs.vespera.core.designsystem"
}

dependencies {
    lintPublish(projects.lint)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material3.adaptive)
    api(libs.androidx.compose.material3.adaptive.layout)
    api(libs.androidx.compose.material3.adaptive.android)
    api(libs.androidx.compose.material3.adaptive.navigation.suite)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)

    implementation(projects.core.data)

    debugApi(libs.androidx.compose.ui.tooling)

    testImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.compose.ui.test)
}
