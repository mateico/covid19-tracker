package com.rial.covid_19tracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CovidApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // Initializes the Timber library
        Timber.plant(Timber.DebugTree())
    }

}