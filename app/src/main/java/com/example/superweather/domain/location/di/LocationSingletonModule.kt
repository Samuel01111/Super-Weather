package com.example.superweather.domain.location.di

import com.example.superweather.data.location.DefaultLocationTracker
import com.example.superweather.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class LocationSingletonModule {

    @Singleton
    @Binds
    abstract fun provideLocationTracker(defaultLocationTracker: DefaultLocationTracker): LocationTracker
}
