
plugins {
    alias(libs.plugins.vespera.android.feature)
    alias(libs.plugins.vespera.android.library.compose)
}

android {
    namespace = "com.absurddevs.vespera.feature.home"
}

dependencies {
    implementation(libs.accompanist.permissions)

    testImplementation(libs.hilt.android.testing)
}