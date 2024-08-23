package com.example.superweather

import android.app.Application
import com.example.superweather.di.ApplicationComponent
import com.example.superweather.di.DaggerApplicationComponent
import com.google.firebase.analytics.FirebaseAnalytics
import com.leumas.superweather.BuildConfig
import timber.log.Timber

class WeatherApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseAnalytics.getInstance(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .factory()
            .create(this, this)
    }
}
