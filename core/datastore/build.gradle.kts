plugins {
    alias(libs.plugins.vespera.android.library)
    alias(libs.plugins.vespera.android.hilt)
}

android {
    namespace = "com.absurddevs.vespera.core.datastore"
}

dependencies {
    api(libs.androidx.datastore)
}