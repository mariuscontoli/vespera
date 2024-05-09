plugins {
    alias(libs.plugins.vespera.android.library)
    alias(libs.plugins.vespera.android.hilt)
}

android {
    namespace = "com.absurddevs.vespera.core.data"
}

dependencies {
    api(projects.core.datastore)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.serialization.json)
}