package com.example.superweather

import android.app.Application
import com.example.superweather.di.ApplicationComponent
import com.example.superweather.di.DaggerApplicationComponent

class WeatherApplication: Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .factory()
            .create(this, this)
    }
}
