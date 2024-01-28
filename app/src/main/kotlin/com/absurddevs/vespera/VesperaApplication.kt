package com.absurddevs.vespera

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * [Application] class for Vespera.
 */
@HiltAndroidApp
class VesperaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}