package com.example.superweather.domain.location.di

import android.app.Application
import androidx.annotation.Keep
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides

@Keep
@Module
class LocationProviderModule {

    @Keep
    @Provides
    fun providesFusedLocationProviderClient(
        app: Application
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }
}
