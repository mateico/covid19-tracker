package com.rial.covid_19tracker

import android.app.Application
import timber.log.Timber

class CovidApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // Initializes the Timber library
        Timber.plant(Timber.DebugTree())
    }

}