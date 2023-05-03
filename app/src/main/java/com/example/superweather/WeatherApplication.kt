package com.example.superweather

import android.app.Application
import com.example.superweather.di.ApplicationComponent
import com.example.superweather.di.DaggerApplicationComponent

class WeatherApplication: Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent
            .factory()
            .create(this)
    }

}