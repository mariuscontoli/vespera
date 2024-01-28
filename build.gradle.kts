// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()

        // Android Build server
        maven { url = uri("../vespera-prebuilts/m2repository") }
    }
    dependencies {

    }
}

// List all plugins used throughout the project without applying them.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.baselineprofile) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.dependencyGuard) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
}