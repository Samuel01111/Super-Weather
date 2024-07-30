package com.example.superweather.domain.location.di

import androidx.annotation.Keep
import com.example.superweather.data.location.DefaultLocationTracker
import com.example.superweather.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Keep
@Module
abstract class LocationSingletonModule {

    @Keep
    @Singleton
    @Binds
    abstract fun provideLocationTracker(defaultLocationTracker: DefaultLocationTracker): LocationTracker
}
